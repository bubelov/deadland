package com.deadland.model.menu.gun_tower;

import com.badlogic.gdx.graphics.Texture;
import com.deadland.model.building.Building;
import com.deadland.model.building.GunTower;
import com.deadland.model.menu.BuildingSpirit;
import com.deadland.model.menu.MenuButton;

/**
 * Created by inver on 09.08.2014.
 */
public class GunTowerSpirit extends BuildingSpirit {
    public static Texture texture = new Texture("building_tower_transparent.png");

//    private GunTButton button;

    public GunTowerSpirit(float x, float y, MenuButton e) {
        super(x, y, e);
    }

//    public GunTowerSpirit(float x, float y, GunTButton e) {
//        button = e;
//        sprite = new Sprite(texture);
//        sprite.setSize(32, 32);
//        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
//        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
//
//        sprite.setPosition(x, y);
//    }

    @Override
    protected Texture getTexture() {
        return texture;
    }

//    @Override
//    public void update() {
//        Camera c = ControlManager.instance.camera;
//        sprite.setPosition(
//                c.position.x - c.viewportWidth / 2 + Gdx.input.getX() - 16,
//                c.position.y + c.viewportHeight / 2 - Gdx.input.getY() - 16
//        );
//    }
//
//    @Override
//    public void onTap(float x, float y, int count, int button) {
//        if (this.button.isButtonDown(x, y)) {
//            return;
//        }
//
//        if (!EntityUtils.collidesAll(this) && ResourcesManager.instance.spendTrash(GunTower.price)) {
//            Camera c = ControlManager.instance.camera;
////            GunTower gt = new GunTower(
////                    c.position.x - c.viewportWidth / 2 + Gdx.input.getX() - 16,
////                    c.position.y + c.viewportHeight / 2 - Gdx.input.getY() - 16
////            );
//            GunTower gt = new GunTower(x - 16, y - 16);
//            EHelper.add(gt);
//        }
//    }

    @Override
    protected Building createBuilding(float x, float y) {
        return new GunTower(x, y);
    }

//    private boolean checkMainTowerRadius(float x, float y) {
//        for (Entity entity : EntityManager.instance.entities) {
//            if (entity instanceof MainTower) {
//                //todo
//            }
//        }
//        return true;
//    }
}
