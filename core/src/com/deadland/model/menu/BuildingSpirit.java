package com.deadland.model.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.deadland.Chelper;
import com.deadland.EHelper;
import com.deadland.EntityUtils;
import com.deadland.ResourcesManager;
import com.deadland.model.Entity;
import com.deadland.model.building.Building;

/**
 * Created by inver on 10.08.2014.
 */
public abstract class BuildingSpirit extends Entity {
    private MenuButton button;

    private boolean enabled = true;

    public BuildingSpirit(float x, float y, MenuButton e) {
        button = e;
        sprite = new Sprite(getTexture());
        sprite.setSize(32, 32);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(x, y);
    }

    protected abstract Texture getTexture();

    @Override
    public void update() {
        Camera c = Chelper.camera();
        sprite.setPosition(
                c.position.x - c.viewportWidth / 2 + Gdx.input.getX() - 16,
                c.position.y + c.viewportHeight / 2 - Gdx.input.getY() - 16
        );
    }

    @Override
    public void onTap(float x, float y, int count, int button) {
        if (!enabled) {
            return;
        }
        Building building = createBuilding(x - 16, y - 16);
        if (!EntityUtils.collidesAll(this) && ResourcesManager.instance.spendTrash(building.getPrice())) {
            EHelper.add(building);
        }
    }

    protected abstract Building createBuilding(float x, float y);

    public void disable() {
        enabled = false;
    }
}
