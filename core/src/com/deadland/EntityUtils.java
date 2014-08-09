package com.deadland;

import com.badlogic.gdx.math.Intersector;
import com.deadland.model.Entity;

import java.util.Collection;
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
}
