package com.deadland;

import com.deadland.base.model.Entity;

/**
 * Created by inver on 09.08.2014.
 */
public class EHelper {
    public static void add(Entity entity) {
        EntityManager.instance.add(entity);
    }
}
