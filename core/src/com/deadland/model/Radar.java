package com.deadland.model;

import com.badlogic.gdx.math.Vector2;
import com.deadland.EntityManager;
import com.deadland.base.model.Entity;
import com.deadland.model.zombie.Zombie;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by inver on 09.08.2014.
 */
public class Radar {
    private Entity e;

    public Radar(Entity e) {
        this.e = e;
    }

    public Collection<Entity> getEntities(float dst) {
        Collection<Entity> res = new ArrayList<>();
        for (Entity entity : EntityManager.instance.entities) {
            if (Vector2.dst(e.x(), e.y(), entity.x(), entity.y()) <= dst)
                res.add(entity);
        }
        return res;
    }

    public Entity getEnemy(float dst) {
        Collection<Entity> entities = getEntities(dst);
        if (CollectionUtils.isNotEmpty(entities)) {
            for (Entity entity : entities) {
                if (entity instanceof Zombie)
                    return entity;
            }
        }
        return null;
    }
}
