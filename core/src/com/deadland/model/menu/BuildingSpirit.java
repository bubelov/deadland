package com.deadland.model.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
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

    private boolean collidesAll = false;

    public BuildingSpirit(float x, float y, MenuButton e) {
        button = e;
        sprite = new Sprite(getTexture());
        sprite.setSize(32, 32);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(x, y);
        boundingRectangle = new Rectangle(x, y, 32, 32);
    }

    protected abstract Texture getTexture();

    @Override
    public void update() {
        Camera c = Chelper.camera();
        collidesAll = EntityUtils.collidesAll(this);
        if (collidesAll) {
            setCollidesTexture();
        } else {
            setNormalTexture();
        }
        int xPos = (int) (c.position.x - c.viewportWidth / 2 + Gdx.input.getX() - 16);
        int yPos = (int) (c.position.y + c.viewportHeight / 2 - Gdx.input.getY() - 16);
        sprite.setPosition(xPos - (xPos % 16) + 16, yPos + 16 - (yPos % 16));
        boundingRectangle.setPosition(sprite.getX(), sprite.getY());
    }

    @Override
    public void onTap(float x, float y, int count, int button) {
        if (!enabled) {
            return;
        }
        Building building = createBuilding(x - (x % 16) + 16, y - (y % 16) + 16);
        if (!collidesAll && ResourcesManager.instance.spendTrash(building.getPrice())) {
            EHelper.add(building);
        }
    }

    protected abstract void setCollidesTexture();

    protected abstract void setNormalTexture();

    protected abstract Building createBuilding(float x, float y);

    public void disable() {
        enabled = false;
    }
}
