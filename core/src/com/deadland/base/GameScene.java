package com.deadland.base;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.deadland.base.model.Entity;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alexey Nevinsky
 * @version 20.08.14 22:27
 */
public abstract class GameScene {

    protected List<Entity> entities;

    protected Map<String, Entity> fastAccessMap;

    protected InputProcessor inputProcessor;
    protected GestureDetector gestureDetector;

    protected GameScene() {
        entities = new ArrayList<>();
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
        for (Entity entity : entities) {
            entity.update(delta);
        }
    }

    //todo change public access to protected
//    protected void add(Entity entity) {
    public void add(Entity entity) {
        entities.add(entity);
    }

    //todo change public access to protected
    //    protected void remove(Entity entity) {
    public void remove(Entity entity) {
        entities.remove(entity);
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
}
