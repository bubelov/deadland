package com.deadland.support;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deadland.model.Entity;

/**
 * Created by inver on 09.08.2014.
 */
public class EditBuildingControl {
    private static Texture upgradeTexture = new Texture("building_remove.png");
    private static Texture removeTexture = new Texture("building_upgrade.png");

    private Sprite upgradeSprite;
    private Sprite removeSprite;

    private Entity e;

    public EditBuildingControl(Entity e, float panelMargin) {
        this.e = e;

        upgradeSprite = new Sprite(upgradeTexture);
        upgradeSprite.setSize(16, 16);
        upgradeSprite.setCenter(8, 8);
        upgradeSprite.setOrigin(8, 8);
        upgradeSprite.setPosition(e.x() + 1, e.y() + panelMargin + 1);

        removeSprite = new Sprite(removeTexture);
        removeSprite.setSize(16, 16);
        removeSprite.setCenter(8, 8);
        removeSprite.setOrigin(8, 8);
        removeSprite.setPosition(e.x() + 16, e.y() + panelMargin + 1);
    }

    public void render(SpriteBatch batch) {
        removeSprite.draw(batch);
        upgradeSprite.draw(batch);
    }

    public void onClick(float x, float y) {

    }
}
