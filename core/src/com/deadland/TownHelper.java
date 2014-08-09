package com.deadland;

import com.deadland.model.building.GunTower;
import com.deadland.model.building.MainTower;
import com.deadland.model.building.Wall;

/**
 * Created by inver on 09.08.2014.
 */
public class TownHelper {
    public static void createTown(float x, float y) {
        GunTower gt = new GunTower(x - 32, y - 8);
        EntityManager.instance.add(gt);
        for (int i = 0; i < 4; i++) {
            Wall w = new Wall(x + i * 16, y, 300);
            EntityManager.instance.add(w);
        }

        gt = new GunTower(x + 4 * 16, y - 8);
        EntityManager.instance.add(gt);
        for (int i = 0; i < 4; i++) {
            Wall w = new Wall(x + 4 * 16 + 8, y + i * 16 + 24, 300);
            EntityManager.instance.add(w);
        }

        gt = new GunTower(x + 4 * 16, y + 5 * 16 + 8);
        EntityManager.instance.add(gt);
        for (int i = 0; i < 4; i++) {
            Wall w = new Wall(x + i * 16, y + 4 * 16 + 32, 300);
            EntityManager.instance.add(w);
        }

        gt = new GunTower(x - 32, y + 5 * 16 + 8);
        EntityManager.instance.add(gt);
        for (int i = 0; i < 4; i++) {
            Wall w = new Wall(x - 24, y + i * 16 + 24, 300);
            EntityManager.instance.add(w);
        }

        MainTower t = new MainTower(x + 16, y + 40);
        EntityManager.instance.add(t);
    }
}
