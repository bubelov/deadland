package com.deadland.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.deadland.EntityManager;

/**
 * Created by inver on 09.08.2014.
 */
public class GunTower extends Tower {
    public static Texture texture = new Texture("gun.png");

    public Radar radar;
    private float shootTimeout = 0.1f;
    private float lastDeltaTs = 0;

    public GunTower(float x, float y) {
        sprite = new Sprite(texture);
        sprite.setSize(32, 16);
        sprite.setCenter(8, 8);
        sprite.setOrigin(8, 8);
        sprite.setPosition(x, y);

        radar = new Radar(this);
    }

    @Override
    public void update() {
        super.update();

        Entity enemy = radar.getEnemy(300);
        if (enemy != null) {
            rotateTo(enemy.x(), enemy.y());
            lastDeltaTs += Gdx.graphics.getDeltaTime();
            if (lastDeltaTs >= shootTimeout) {
                shoot(enemy.x(), enemy.y());
                lastDeltaTs = 0;
            }
        }
    }

    private void shoot(float x, float y) {
        Bullet b = new Bullet(x(), y(), x, y);
        EntityManager.instance.add(b);
    }

    private void rotateTo(float x, float y) {
        Vector2 movementVector = new Vector2(x, y);
        movementVector.sub(x(), y());

        sprite.setRotation(movementVector.angle());
//        sprite.translate(movementVector.nor().x * 3, movementVector.nor().y * 3);
    }
}
