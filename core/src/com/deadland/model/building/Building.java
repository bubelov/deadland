package com.deadland.model.building;

import com.badlogic.gdx.math.Vector2;
import com.deadland.EntityManager;
import com.deadland.model.Entity;
import com.deadland.model.Zombie;
import com.deadland.support.Health;

/**
 * Created by inver on 09.08.2014.
 */
public abstract class Building extends Entity {
    protected Health health;
    protected Vector2 pos;

    public Building(float x, float y, float health) {
        initSprite(x, y);
        pos = new Vector2(x, y);
        this.health = new Health(this, health, 40, 30);
    }

    protected abstract void initSprite(float x, float y);

    @Override
    public void onCollision(Entity entity) {
        if (entity instanceof Zombie) {
            health.wound(Zombie.POWER);
            if (health.isDead()) {
                EntityManager.instance.destroy(this);
            }
        }
    }
}
