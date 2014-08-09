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
    public static boolean collidesAny(Entity entity, List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity2 = entities.get(i);

            if (entity2 == entity) {
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
}
