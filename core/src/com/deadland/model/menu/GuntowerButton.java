package com.deadland.model.menu;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.deadland.model.Entity;

/**
 * Created by inver on 09.08.2014.
 */
public class GuntowerButton extends Entity {
    public static Texture texture = new Texture("menu_gunTower.png");

    private Vector2 pos;
    private Camera camera;

    public GuntowerButton(float x, float y, Camera camera) {
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
                camera.position.x + pos.x + camera.viewportWidth / 2 - 32,
                camera.position.y + pos.y - camera.viewportHeight / 2
        );
        super.update();
    }

    @Override
    public void onTap(float x, float y, int count, int button) {
        super.onTap(x, y, count, button);
    }
}
