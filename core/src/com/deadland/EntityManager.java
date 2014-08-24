package com.deadland;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.deadland.base.model.Entity;
import com.deadland.model.building.MainTower;

import java.util.*;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class EntityManager {
    public static EntityManager instance = new EntityManager();
    private static ShapeRenderer renderer = new ShapeRenderer();
    public List<Entity> entities = new ArrayList<Entity>();
    private Collection<Entity> entitiesToCreate = new ArrayList<Entity>();
    private Collection<Entity> entitiesToDestroy = new ArrayList<Entity>();

    public void update() {
        Collections.sort(entities, new Comparator<Entity>() {
            @Override
            public int compare(Entity o1, Entity o2) {
                return o1.z - o2.z;
            }
        });

        for (Entity entity : entities) {
            entity.update(0);
        }

        for (Entity entity : entitiesToDestroy) {
            entity.onDestroy();
        }
        entities.removeAll(entitiesToDestroy);
        entitiesToDestroy.clear();

        entities.addAll(entitiesToCreate);
        entitiesToCreate.clear();
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < entities.size(); i++) {
            for (int j = 0; j < entities.size(); j++) {
                if (i == j) {
                    continue;
                }

                Entity entity1 = entities.get(i);
                Entity entity2 = entities.get(j);

                if (entity1.boundingCircle != null && entity2.boundingCircle != null) {
                    if (Intersector.overlaps(entity1.boundingCircle, entity2.boundingCircle)) {
                        entity1.onCollision(entity2);
                    }
                }

                if (entity1.boundingCircle != null && entity2.boundingRectangle != null) {
                    if (Intersector.overlaps(entity1.boundingCircle, entity2.boundingRectangle)) {
                        entity1.onCollision(entity2);
                    }
                }

                if (entity1.boundingRectangle != null && entity2.boundingCircle != null) {
                    if (Intersector.overlaps(entity2.boundingCircle, entity1.boundingRectangle)) {
                        entity1.onCollision(entity2);
                    }
                }
            }
        }

        Camera c = ControlManager.instance.camera;
        float minX = c.position.x - c.viewportWidth / 2 - 32;
        float maxX = c.position.x + c.viewportWidth / 2 + 32;
        float minY = c.position.y - c.viewportHeight / 2 - 32;
        float maxY = c.position.y + c.viewportHeight / 2 + 32;
        int i = 0;
        for (Entity e : entities) {
            if (e.x() > minX && e.x() < maxX && e.y() > minY && e.y() < maxY) {
                i++;
                e.render(batch);
            }
        }
//        System.out.println("count of entities: " + i);
    }

    public void renderCollisions(OrthographicCamera camera) {
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        for (Entity entity : entities) {
            if (entity.boundingCircle != null) {
                renderer.circle(entity.boundingCircle.x, entity.boundingCircle.y, entity.boundingCircle.radius);
            }

            if (entity.boundingRectangle != null) {
                renderer.rect(entity.boundingRectangle.x, entity.boundingRectangle.y, entity.boundingRectangle.width, entity.boundingRectangle.height);
            }
        }

        renderer.end();
    }

    public void renderArea(OrthographicCamera camera) {
        renderer.setProjectionMatrix(camera.combined);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        renderer.setColor(new Color(0, 0, 1, 0.1f));

        for (Entity entity : entities) {
            if (entity instanceof MainTower) {
                renderer.circle(entity.x() + entity.sprite.getWidth() / 2,
                        entity.y() + entity.sprite.getHeight() / 2,
                        ((MainTower) entity).buildingRadius);
            }
        }

        renderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    public void add(Entity entity) {
        entitiesToCreate.add(entity);
    }

    public void destroy(Entity entity) {
        entitiesToDestroy.add(entity);
    }

    public void onTap(float x, float y, int count, int button) {
        for (Entity entity : entities) {
            entity.onTap(x, y, count, button);
        }
    }


}
