package com.deadland;

import com.deadland.model.Gates;
import com.deadland.model.building.GunTower;
import com.deadland.model.building.MainTower;
import com.deadland.model.building.Wall;

/**
 * Created by inver on 09.08.2014.
 */
public class TownHelper {
    public static void createTown(float x, float y, int size) {
        GunTower gt = new GunTower(x - 64, y - 16);

        EntityManager.instance.add(gt);

        for (int i = 0; i < size; i++) {
            Wall w = new Wall(x + i * 32, y, 300);
            EntityManager.instance.add(w);
        }

        gt = new GunTower(x + size * 32, y - 16);
        EntityManager.instance.add(gt);

        for (int i = 0; i < size; i++) {
            Wall w = new Wall(x + size * 32 + 16, y + i * 32 + 48, 300);
            EntityManager.instance.add(w);
        }

        gt = new GunTower(x + size * 32, y + (size + 1) * 32 + 16);
        EntityManager.instance.add(gt);

        for (int i = 0; i < size; i++) {
            Wall w = new Wall(x + i * 32, y + size * 32 + 64, 300);
            EntityManager.instance.add(w);
        }

        gt = new GunTower(x - 64, y + (size + 1) * 32 + 16);
        EntityManager.instance.add(gt);

//        for (int i = 0; i < size; i++) {
//            Wall w = new Wall(x - 24, y + i * 16 + 24, 300);
//            EntityManager.instance.add(w);
//        }

        EntityManager.instance.add(new Wall(x - 48, y + 48, 300));
        EntityManager.instance.add(new Gates(x - 48, y + 80));
        EntityManager.instance.add(new Wall(x - 48, y + 168 * 2, 300));
        EntityManager.instance.add(new Wall(x - 48, y + 168 * 2 - 32, 300));
        EntityManager.instance.add(new Wall(x - 48, y + 168 * 2 - 32 - 32, 300));

        for (int i = 1; i < size - 9; i++) {
            EntityManager.instance.add(new Wall(x - 48, y + 168 * 2 + 32 * i, 300));
        }

//        EntityManager.instance.add(new Wall(x - 24, y + 168 + 16, 300));
//        EntityManager.instance.add(new Wall(x - 24, y + 168 + 16 + 16, 300));
//        EntityManager.instance.add(new Wall(x - 24, y + 168 + 16 + 16 + 16, 300));
//        EntityManager.instance.add(new Wall(x - 24, y + 168 + 16 + 16 + 16 + 16, 300));
//        EntityManager.instance.add(new Wall(x - 24, y + 168 + 16 + 16 + 16 + 16 + 16, 300));

        MainTower t = new MainTower(x + 280, y + 300);
        EntityManager.instance.add(t);
    }
}
