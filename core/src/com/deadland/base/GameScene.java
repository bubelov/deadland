package com.deadland.base;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.deadland.base.model.Entity;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * @author Alexey Nevinsky
 * @version 20.08.14 22:27
 */
public abstract class GameScene {

    protected List<Entity> entities;
    protected Map<String, Entity> fastAccessMap;
    protected InputProcessor inputProcessor;
    protected GestureDetector gestureDetector;
    private Collection<Entity> entitiesToCreate;
    private Collection<Entity> entitiesToDestroy;

    protected GameScene() {
        entities = new ArrayList<>();
        entitiesToCreate = new ArrayList<>();
        entitiesToDestroy = new ArrayList<>();
        fastAccessMap = new HashMap<>();
    }

    public void render(SpriteBatch batch) {
        for (Entity entity : entities) {
            entity.render(batch);
        }
    }

    public void smartRender(SpriteBatch batch, float left, float top, float right, float bottom) {
        drawGround(batch, left, top, right, bottom);
        if (CollectionUtils.isNotEmpty(entities)) {
            for (Entity entity : entities) {
                entity.smartRender(batch, left, top, right, bottom);
            }
        }
    }

    public void update(float delta) {
        //todo change list entities to set
        Collections.sort(entities, new Comparator<Entity>() {
            @Override
            public int compare(Entity o1, Entity o2) {
                return o1.z - o2.z;
            }
        });

        for (Entity entity : entities) {
            entity.update(delta);
        }

        for (Entity entity : entitiesToDestroy) {
            entity.onDestroy();
        }
        entities.removeAll(entitiesToDestroy);
        entitiesToDestroy.clear();

        entities.addAll(entitiesToCreate);
        entitiesToCreate.clear();
    }

    //todo change public access to protected
//    protected void add(Entity entity) {
    public void add(Entity entity) {
        entities.add(entity);
    }

    public void create(Entity entity) {
        entitiesToCreate.add(entity);
    }

    //todo change public access to protected
    //    protected void remove(Entity entity) {
    public void remove(Entity entity) {
        entitiesToDestroy.add(entity);
    }

    protected void addToFastAccess(String key, Entity e) {
        fastAccessMap.put(key, e);
    }

    @SuppressWarnings("unchecked")
    public <T extends Entity> T fastAccess(String key) {
        return (T) fastAccessMap.get(key);
    }

    protected void drawGround(SpriteBatch batch, float left, float top, float right, float bottom) {
        //nothing to do
    }

    public void keyDown(int button) {
        for (Entity entity : entities) {
            entity.keyDown(button);
        }
    }

    public void keyUp(int button) {
        for (Entity entity : entities) {
            entity.keyUp(button);
        }
    }

    public void onTap(float x, float y, int count, int button) {
        for (Entity entity : entities) {
            entity.onTap(x, y, count, button);
        }
    }
}
