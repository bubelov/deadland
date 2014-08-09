package com.deadland.model.menu;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.deadland.ControlManager;
import com.deadland.EHelper;
import com.deadland.EntityManager;
import com.deadland.model.Entity;

/**
 * Created by inver on 09.08.2014.
 */
public class GunTButton extends Entity {
    public static Texture texture = new Texture("menu_gunTower.png");

    private Vector2 pos;
    private Camera camera;
    private Entity spirit = null;

    public GunTButton(float x, float y, Camera camera) {
        pos = new Vector2(x, y);
        this.camera = camera;

        sprite = new Sprite(texture);
        sprite.setSize(32, 32);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(x, y);
    }

    @Override
    public void update() {
        sprite.setPosition(
                camera.position.x - camera.viewportWidth / 2 + pos.x,
                camera.position.y - camera.viewportHeight / 2 + pos.y
        );
        super.update();
    }

    @Override
    public void onTap(float x, float y, int count, int button) {
        if (isButtonDown(x, y)) {
            if (spirit == null) {
                createSpirit(x, y);
                ControlManager.instance.isUnderConstruction = true;
            } else {
                ControlManager.instance.isUnderConstruction = false;
                EntityManager.instance.destroy(spirit);
                spirit = null;
            }
        }
    }

    public boolean isButtonDown(float x, float y) {
        Camera c = ControlManager.instance.camera;
        float pX = c.position.x - c.viewportWidth / 2 + pos.x;
        float pY = c.position.y - c.viewportHeight / 2 + pos.y;
        if (x >= pX && x <= pX + 32 && y >= pY && y <= pY + 32)
            return true;
        return false;
    }

    private void createSpirit(float x, float y) {
        spirit = new GunTowerSpirit(x, y, this);
        EHelper.add(spirit);
    }
}
