package com.deadland.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.deadland.EntityManager;

/**
 * Created by inver on 09.08.2014.
 */
public class Bullet extends Entity {
    public static Texture texture = new Texture("bullet.png");

    private float speed = 500;

    private float power = 2;

    private Vector2 pos;
    private float age = 0;
    private float liveTime = 10;

    private ParticleEffect effect;

    public Bullet(float x, float y, float dstX, float dstY) {
        this(x, y, dstX, dstY, false);
    }

    public Bullet(float x, float y, float dstX, float dstY, boolean fire) {
        pos = new Vector2(dstX - x, dstY - y);

        sprite = new Sprite(texture);
        sprite.setSize(8, 2);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(x, y);
        sprite.setRotation(pos.angle());

        boundingCircle = new Circle(x() + sprite.getWidth() / 2, y() + sprite.getHeight() / 2, 5);

        if (fire) {
            effect = new ParticleEffect();
            effect.load(Gdx.files.internal("particles/fire.p"), Gdx.files.internal("particles"));
            effect.setPosition(200, 200);
            effect.setDuration(10000);

            effect.getEmitters().get(0).setAdditive(false);

            effect.start();
        }
    }

    @Override
    public void update() {
        super.update();

        float deltaSecs = Gdx.graphics.getDeltaTime();
        age = age + deltaSecs;
        if (age > liveTime) {
            EntityManager.instance.destroy(this);
        } else {
            pos.add(pos.nor().x * deltaSecs * speed, pos.nor().y * deltaSecs * speed);
            sprite.translate(pos.x, pos.y);
            boundingCircle.setPosition(x() + sprite.getWidth() / 2, y() + sprite.getHeight() / 2);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);

        if (effect != null) {
            effect.setPosition(x(), y());
            effect.draw(batch, Gdx.graphics.getDeltaTime());
        }
    }

    @Override
    public void onCollision(Entity entity) {
        if (entity instanceof Zombie) {
            ((Zombie) entity).wound(power);
            if (((Zombie) entity).isDead()) {
                EntityManager.instance.destroy(entity);
                EntityManager.instance.add(new BloodMess(entity.x(), entity.y()));
            }
            EntityManager.instance.destroy(this);
        }
    }
}
