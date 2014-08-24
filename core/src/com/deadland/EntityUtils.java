package com.deadland;

import com.badlogic.gdx.math.Intersector;
import com.deadland.base.model.Entity;
import com.deadland.model.Trash;
import com.deadland.model.Weapons;
import com.deadland.model.building.Building;

import java.util.List;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class EntityUtils {
    public static boolean collidesAny(Entity entity, List<Entity> entities, boolean collide) {
        boolean collides = false;

        for (int i = 0; i < entities.size(); i++) {
            Entity entity2 = entities.get(i);

            if (entity2 instanceof Weapons || entity2 instanceof Trash) {
                continue;
            }

            if (entity2 == entity) {
                continue;
            }

            if (entity.boundingCircle != null && entity2.boundingCircle != null) {
                if (Intersector.overlaps(entity.boundingCircle, entity2.boundingCircle)) {
                    if (collide) {
                        entity.onCollision(entity2);
                        entity2.onCollision(entity);
                    }

                    collides = true;
                }
            }

            if (entity.boundingCircle != null && entity2.boundingRectangle != null) {
                if (Intersector.overlaps(entity.boundingCircle, entity2.boundingRectangle)) {
                    if (collide) {
                        entity.onCollision(entity2);
                        entity2.onCollision(entity);
                    }

                    collides = true;
                }
            }
        }

        return collides;
    }

    public static boolean collidesWithBuilding(Entity entity, List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity2 = entities.get(i);

            if (!(entity2 instanceof Building)) {
                continue;
            }

            if (entity.boundingCircle != null && entity2.boundingCircle != null) {
                if (Intersector.overlaps(entity.boundingCircle, entity2.boundingCircle)) {
                    return true;
                }
            }

            if (entity.boundingCircle != null && entity2.boundingRectangle != null) {
                if (Intersector.overlaps(entity.boundingCircle, entity2.boundingRectangle)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean collidesAll(Entity e) {
        for (Entity entity : EntityManager.instance.entities) {
            if (e == entity) {
                continue;
            }

            if (e.boundingCircle != null && entity.boundingCircle != null) {
                if (Intersector.overlaps(e.boundingCircle, entity.boundingCircle)) {
                    return true;
                }
            }

            if (e.boundingCircle != null && entity.boundingRectangle != null) {
                if (Intersector.overlaps(e.boundingCircle, entity.boundingRectangle)) {
                    return true;
                }
            }

            if (e.boundingRectangle != null && entity.boundingRectangle != null) {
                if (Intersector.overlaps(e.boundingRectangle, entity.boundingRectangle)) {
                    return true;
                }
            }

            if (e.boundingRectangle != null && entity.boundingCircle != null) {
                if (Intersector.overlaps(entity.boundingCircle, e.boundingRectangle)) {
                    return true;
                }
            }
        }
        return false;
    }
}
