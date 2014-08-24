package com.deadland;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.deadland.base.model.Entity;
import com.deadland.model.Zombie;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class ZombieSpawner {
    public static ZombieSpawner instance = new ZombieSpawner();
    public float spawnTimeout = 10;
    private Collection<Entity> emitters = new ArrayList<>();

    public void registerEmitter(Entity entity) {
        emitters.add(entity);
    }

    public void clearEmitters() {
        emitters.clear();
    }

    public void update() {
        spawnTimeout -= Gdx.graphics.getDeltaTime();

        if (spawnTimeout < 0) {
            spawnTimeout = 10;

            for (Entity emitter : emitters) {
                int zombiesToSpawn = 3;

                while (zombiesToSpawn > 0) {
                    Zombie zombie = new Zombie(emitter.centerX() - 100 + MathUtils.random(200), emitter.centerY() - 100 + MathUtils.random(200));

                    while (EntityUtils.collidesAny(zombie, EntityManager.instance.entities, false)) {
                        zombie = new Zombie(emitter.centerX() - 100 + MathUtils.random(200), emitter.centerY() - 100 + MathUtils.random(200));
                    }

                    EntityManager.instance.add(zombie);
                    zombiesToSpawn--;
                }
            }
        }
    }
}
