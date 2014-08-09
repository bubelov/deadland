package com.deadland.model.building;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.deadland.EntityManager;
import com.deadland.model.Bullet;
import com.deadland.model.Entity;
import com.deadland.model.Radar;

/**
 * Created by inver on 09.08.2014.
 */
public class GunTower extends Building {
    public static Texture towerTexture = new Texture("building_tower.png");
    public static Texture gunTexture = new Texture("building_gun.png");

    private Sprite towerSprite;

    public Radar radar;
    private float shootTimeout = 0.1f;
    private float lastDeltaTs = 0;

    public GunTower(float x, float y) {
        super(x, y, 300);
        radar = new Radar(this);

        boundingRectangle = new Rectangle(x, y, 32, 32);
    }

    @Override
    protected void initSprite(float x, float y) {
        towerSprite = new Sprite(towerTexture);
        towerSprite.setSize(32, 32);
        towerSprite.setCenter(16, 16);
        towerSprite.setOrigin(16, 16);
        towerSprite.setPosition(x, y);

        sprite = new Sprite(gunTexture);
        sprite.setSize(32, 32);
        sprite.setCenter(16, 16);
        sprite.setOrigin(16, 16);
        sprite.setPosition(x, y);
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

    @Override
    public void render(SpriteBatch batch) {
        towerSprite.draw(batch);
        super.render(batch);
    }
}