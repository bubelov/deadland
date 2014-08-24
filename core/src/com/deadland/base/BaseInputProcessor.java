package com.deadland.base;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.deadland.EntityManager;
import com.deadland.base.model.Entity;
import com.deadland.model.Hero;

/**
 * @author Alexey Nevinsky
 * @version 24.08.14 0:28
 */
public class BaseInputProcessor extends InputAdapter {

    private GameScene scene;

    public BaseInputProcessor(GameScene scene) {
        this.scene = scene;
    }

    public void setScene(GameScene scene) {
        this.scene = scene;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.H) {
            Hero hero = scene.fastAccess(DemoScene.HERO_KEY);
            if (hero == null)
                return false;

            hero.health.health = hero.health.startHealth;
            hero.health.wound(0);
        }

        if (keycode == Input.Keys.A) {
            Hero hero = scene.fastAccess(DemoScene.HERO_KEY);
            if (hero == null)
                return false;

            for (Entity entity : EntityManager.instance.entities) {
                //todo
//                if (entity instanceof Zombie) {
//                    Zombie zombie = (Zombie) entity;
//                    zombie.destination = new Vector2(hero.centerX(), hero.centerY());
//                }
            }
        }
//todo
//        if (keycode == Input.Keys.ENTER && gameOver) {
//            gameOver = false;
//            EntityManager.instance.entities.clear();
//            ResourcesManager.instance = new ResourcesManager();
//            ZombieSpawner.instance.clearEmitters();
//            create();
//        }

        if (keycode == Input.Keys.S) {
            //todo
//            for (int i = 0; i < 500; i++) {
//                Zombie zombie = new Zombie(MathUtils.random(8000), MathUtils.random(6000));
//                EntityManager.instance.add(zombie);
//            }
        }

        return false;
    }
}
