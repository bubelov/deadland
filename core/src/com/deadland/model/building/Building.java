package com.deadland.model.building;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.deadland.ControlManager;
import com.deadland.EntityManager;
import com.deadland.model.Entity;
import com.deadland.model.Zombie;
import com.deadland.support.EditBuildingControl;
import com.deadland.support.Health;

/**
 * Created by inver on 09.08.2014.
 */
public abstract class Building extends Entity {
    protected Health health;
    protected Vector2 pos;

    protected EditBuildingControl control;

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

    @Override
    public void onTap(float x, float y, int count, int button) {
        if (isButtonDown(x, y) && !ControlManager.instance.isUnderConstruction) {
            if (control == null) {
                control = new EditBuildingControl(this, 30);
                ControlManager.instance.isUnderConstruction = true;
            } else {
                ControlManager.instance.isUnderConstruction = false;
                control = null;
            }
        } else if (control != null) {
            control.onClick(x, y);
        }
    }

    public boolean isButtonDown(float x, float y) {
        if (x > x() && x < x() + sprite.getWidth() && y > y() && y < y() + sprite.getHeight()) {
            return true;
        }

        return false;
    }

    @Override
    public void render(SpriteBatch batch) {
        if (control != null) {
            control.render(batch);
        }
        super.render(batch);
    }
}
