package com.deadland.base;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.deadland.CMHelper;
import com.deadland.ControlManager;
import com.deadland.control.*;
import com.deadland.model.vehicle.MovableObject;

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
        MovableObject obj = (MovableObject) ControlManager.instance.demoScene.fastAccess(DemoScene.HERO_KEY);
        switch (keycode) {
            case Input.Keys.UP:
            case Input.Keys.DOWN:
                CMHelper.addCommand(new StopCommand(obj));
                break;
            case Input.Keys.LEFT:
            case Input.Keys.RIGHT:
                CMHelper.addCommand(new SteerNoneCommand(obj));
                break;
        }

//        ControlManager.instance.demoScene.keyUp(keycode);
//        if (keycode == Input.Keys.H) {
//            Hero hero = scene.fastAccess(DemoScene.HERO_KEY);
//            if (hero == null)
//                return false;
//
//            hero.health.health = hero.health.startHealth;
//            hero.health.wound(0);
//        }
//
//        if (keycode == Input.Keys.A) {
//            Hero hero = scene.fastAccess(DemoScene.HERO_KEY);
//            if (hero == null)
//                return false;
//
//            for (Entity entity : EntityManager.instance.entities) {
//                //todo
////                if (entity instanceof Zombie) {
////                    Zombie zombie = (Zombie) entity;
////                    zombie.destination = new Vector2(hero.centerX(), hero.centerY());
////                }
//            }
//        }
////todo
////        if (keycode == Input.Keys.ENTER && gameOver) {
////            gameOver = false;
////            EntityManager.instance.entities.clear();
////            ResourcesManager.instance = new ResourcesManager();
////            ZombieSpawner.instance.clearEmitters();
////            create();
////        }
//
//        if (keycode == Input.Keys.S) {
//            //todo
////            for (int i = 0; i < 500; i++) {
////                Zombie zombie = new Zombie(MathUtils.random(8000), MathUtils.random(6000));
////                EntityManager.instance.add(zombie);
////            }
//        }

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        MovableObject obj = (MovableObject) ControlManager.instance.demoScene.fastAccess(DemoScene.HERO_KEY);

        switch (keycode) {
            case Input.Keys.UP:
                CMHelper.addCommand(new GoForwardCommand(obj));
                break;
            case Input.Keys.DOWN:
                CMHelper.addCommand(new GoBackwardCommand(obj));
                break;
            case Input.Keys.LEFT:
                CMHelper.addCommand(new SteerLeftCommand(obj));
                break;
            case Input.Keys.RIGHT:
                CMHelper.addCommand(new SteerRightCommand(obj));
                break;
        }

//        ControlManager.instance.demoScene.keyDown(keycode);

//        ControlManager.instance.demoScene.onTap(0, 0, 0, keycode);
        return false;
    }
}
