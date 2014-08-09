package com.deadland;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deadland.model.Entity;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class EntityManager {
    public static EntityManager instance = new EntityManager();

    public Collection<Entity> entities = new ArrayList<Entity>();
    private Collection<Entity> entitiesToCreate = new ArrayList<Entity>();
    private Collection<Entity> entitiesToDestroy = new ArrayList<Entity>();

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
        for (Entity entity : entities) {
            entity.render(batch);
        }
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
