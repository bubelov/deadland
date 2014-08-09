package com.deadland.model.building;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.deadland.ControlManager;
import com.deadland.EntityManager;
import com.deadland.ResourcesManager;
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

    protected int level = 0;

    public Building(float x, float y, float health) {
        z = -1000;
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
        if (isButtonDown(x, y)) {
            if (ControlManager.instance.isUnderConstruction == this) {
                ControlManager.instance.isUnderConstruction = null;
                control = null;
            } else if (ControlManager.instance.isUnderConstruction == null) {
                ControlManager.instance.isUnderConstruction = this;
                ControlManager.instance.justLeftConstruction = true;
                control = new EditBuildingControl(this, 30);
            }
        } else {
            if (ControlManager.instance.isUnderConstruction == this) {
                control.onClick(x, y);
            }
        }
    }

    public boolean isButtonDown(float x, float y) {
        if (x > x() && x < x() + sprite.getWidth() && y > y() && y < y() + sprite.getHeight()) {
            return true;
        }
        return false;
    }

    public void onRemove() {
        ResourcesManager.instance.spendTrash(-50);
        EntityManager.instance.destroy(this);
        ControlManager.instance.isUnderConstruction = null;
        System.out.println("onRemove");
    }

    public void onUpgrade() {
        if (ResourcesManager.instance.spendTrash(100)) {
            level++;
        }
        System.out.println("onUpgrade");
    }

    @Override
    public void render(SpriteBatch batch) {
        if (control != null) {
            control.render(batch);
        }
        super.render(batch);
    }

    @Override
    public void destroy() {
        if (ControlManager.instance.isUnderConstruction == this) {
            ControlManager.instance.isUnderConstruction = null;
        }
    }
}
