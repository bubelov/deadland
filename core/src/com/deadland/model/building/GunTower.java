package com.deadland.model.building;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.deadland.Assets;
import com.deadland.EntityManager;
import com.deadland.ResourcesManager;
import com.deadland.model.Bullet;
import com.deadland.model.Entity;
import com.deadland.model.Radar;

/**
 * Created by inver on 09.08.2014.
 */
public class GunTower extends Building {
    public static int price = 100;

    private Sprite towerSprite;


    public Radar radar;
    private float shootTimeout = 0.5f;
    private float lastDeltaTs = 0;

    public GunTower(float x, float y) {
        super(x, y, 300);
        radar = new Radar(this);

        boundingRectangle = new Rectangle(x, y, 32, 32);
    }

    @Override
    protected void initSprite(float x, float y) {
        towerSprite = new Sprite(Assets.Textures.buildingTower);
        towerSprite.setSize(32, 32);
        towerSprite.setCenter(16, 16);
        towerSprite.setOrigin(16, 16);
        towerSprite.setPosition(x, y);

        sprite = new Sprite(Assets.Textures.buildingGun);
        sprite.setSize(32, 32);
        sprite.setCenter(16, 16);
        sprite.setOrigin(16, 16);
        sprite.setPosition(x, y);

        //boundingRectangle = new Rectangle(x(), y(), sprite.getWidth() / 2, sprite.getHeight() / 2);
    }

    @Override
    public int getPrice() {
        return 100;
    }

    @Override
    public void update() {
        super.update();

        Entity enemy = radar.getEnemy(300);
        if (enemy != null) {
            rotateTo(enemy.x(), enemy.y());
            lastDeltaTs += Gdx.graphics.getDeltaTime() * (level + 1);
            if (lastDeltaTs >= shootTimeout) {
                shoot(enemy.x(), enemy.y());
                lastDeltaTs = 0;
            }
        }
    }

    private void shoot(float x, float y) {
        if (ResourcesManager.instance.spendWeapon(1)) {
            if (level == 0) {
                EntityManager.instance.add(new Bullet(getXCoord(0), getYCoord(0), sprite.getRotation(), false, 2));
            } else if (level == 1) {
                EntityManager.instance.add(new Bullet(getXCoord(8), getYCoord(8), sprite.getRotation(), false, 10));
                EntityManager.instance.add(new Bullet(getXCoord(-8), getYCoord(-8), sprite.getRotation(), false, 10));
            } else {
                EntityManager.instance.add(new Bullet(getXCoord(8), getYCoord(8), sprite.getRotation(), true, 30));
                EntityManager.instance.add(new Bullet(getXCoord(-8), getYCoord(-8), sprite.getRotation(), true, 30));
            }
        }
    }

    private float getXCoord(float shift) {
        return (float) (
                centerX()
                        + 16.0f * Math.cos(MathUtils.degreesToRadians * sprite.getRotation())
                        - shift * Math.sin(MathUtils.degreesToRadians * sprite.getRotation())
        );
    }

    private float getYCoord(float shift) {
        return (float) (
                centerY() + 16.0f * Math.sin(MathUtils.degreesToRadians * sprite.getRotation())
                        + shift * Math.cos(MathUtils.degreesToRadians * sprite.getRotation())
        );
    }

    private void rotateTo(float x, float y) {
        Vector2 movementVector = new Vector2(x, y);
        movementVector.sub(x(), y());

        sprite.setRotation(movementVector.angle());
    }

    @Override
    public void render(SpriteBatch batch) {
        towerSprite.draw(batch);
        super.render(batch);
    }

    @Override
    public void onUpgrade() {
        super.onUpgrade();
        if (level == 1)
            sprite.setTexture(Assets.Textures.buildingGun2);
    }
}
