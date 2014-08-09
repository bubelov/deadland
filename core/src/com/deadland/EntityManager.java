package com.deadland;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.deadland.model.Entity;
import javafx.scene.Camera;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class EntityManager {
    public static EntityManager instance = new EntityManager();

    public List<Entity> entities = new ArrayList<Entity>();
    private Collection<Entity> entitiesToCreate = new ArrayList<Entity>();
    private Collection<Entity> entitiesToDestroy = new ArrayList<Entity>();

    private static ShapeRenderer renderer = new ShapeRenderer();

    public void update() {
        for (Entity entity : entities) {
            entity.update();
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
            }
        }

        for (Entity entity : entities) {
            entity.render(batch);
        }
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
