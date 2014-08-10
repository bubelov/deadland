package com.deadland.model.building;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.deadland.EntityManager;
import com.deadland.EntityUtils;
import com.deadland.model.Bullet;
import com.deadland.model.Entity;
import com.deadland.model.Zombie;

/**
 * Created by inver on 10.08.2014.
 */
public class Cave extends Building {
    public static Texture texture = new Texture("building_cave.png");

    public float spawnTimeout = 10;

    public Cave(float x, float y) {
        super(x, y, 500);
    }

    @Override
    protected void initSprite(float x, float y) {
        sprite = new Sprite(texture);
        sprite.setSize(96, 96);
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
    public void update() {
        spawnTimeout -= Gdx.graphics.getDeltaTime();

        if (spawnTimeout < 0) {
            spawnTimeout = 10;

            int zombiesToSpawn = 3;

            while (zombiesToSpawn > 0) {
                Zombie zombie = new Zombie(centerX() - 100 + MathUtils.random(200), centerY() - 100 + MathUtils.random(200));

                while (EntityUtils.collidesAny(zombie, EntityManager.instance.entities, false)) {
                    zombie = new Zombie(centerX() - 100 + MathUtils.random(200), centerY() - 100 + MathUtils.random(200));
                }

                EntityManager.instance.add(zombie);
                zombiesToSpawn--;
            }
        }
    }
}
