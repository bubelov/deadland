package com.deadland.model.building;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.deadland.Assets;
import com.deadland.EntityManager;
import com.deadland.base.model.Entity;
import com.deadland.model.Bullet;

/**
 * Created by inver on 10.08.2014.
 */
public class Cave extends Building {
    private static final int WIDTH = 96 * 2;
    private static final int HEIGHT = 96 * 2;

    public float spawnTimeout = 10;

    public Cave(float x, float y) {
        super(x, y, 500);
    }

    @Override
    protected void initSprite(float x, float y) {
        sprite = new Sprite(Assets.Textures.buildingCave);
        sprite.setSize(WIDTH, HEIGHT);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setPosition(x, y);

        boundingRectangle = new Rectangle(x(), y(), sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public void onCollision(Entity entity) {
        if (entity instanceof Bullet) {
            health.wound(1);

            if (health.isDead()) {
                EntityManager.instance.destroy(this);
            }
        }
    }

    @Override
    public void update(float delta) {
        spawnTimeout -= Gdx.graphics.getDeltaTime();

        if (spawnTimeout < 0) {
            spawnTimeout = 10;

            int zombiesToSpawn = 3;

            while (zombiesToSpawn > 0) {
//                Zombie zombie = new Zombie(centerX() - 100 + MathUtils.random(200), centerY() - 100 + MathUtils.random(200));
//
//                while (EntityUtils.collidesAny(zombie, EntityManager.instance.entities, false)) {
//                    zombie = new Zombie(centerX() - 200 + MathUtils.random(400), centerY() - 200 + MathUtils.random(400));
//                }
//
//                EntityManager.instance.add(zombie);
                zombiesToSpawn--;
            }
        }
    }
}
