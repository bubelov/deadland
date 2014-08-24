package com.deadland.model.menu;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.deadland.ControlManager;
import com.deadland.base.model.Entity;

/**
 * Created by inver on 10.08.2014.
 */
public abstract class MenuButton extends Entity {
    BitmapFont font;
    String menuName;
    private Vector2 pos;

    public MenuButton(float x, float y, String menuName) {
        pos = new Vector2(x, y);
        sprite = new Sprite(getTexture());
        sprite.setSize(64, 64);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setPosition(x, y);
        addSprite(sprite);

        z = -1000;

        font = new BitmapFont();
        font.setScale(1);
        font.setColor(Color.BLACK);
        this.menuName = menuName;
    }

    protected abstract TextureRegion getTexture();

    @Override
    public void onTap(float x, float y, int count, int button) {
        if (isButtonDown(x, y)) {
            BuildingSpirit bs = createSpirit(x - 16, y - 16);
            if (ControlManager.instance.spirit != null) {
                ControlManager.instance.spirit.disable();
                // todo
//                EntityManager.instance.destroy(ControlManager.instance.spirit);
//                if (ControlManager.instance.spirit.getClass() == bs.getClass()) {
//                    ControlManager.instance.isUnderConstruction = null;
//                    ControlManager.instance.justLeftConstruction = true;
//                    ControlManager.instance.spirit = null;
//                } else {
//                    ControlManager.instance.spirit = bs;
//                    ControlManager.instance.demoScene.add(bs);
//                    ControlManager.instance.isUnderConstruction = this;
//                }
            } else {
                ControlManager.instance.spirit = bs;
                ControlManager.instance.demoScene.add(bs);
                ControlManager.instance.isUnderConstruction = this;
            }
        }
    }

    public boolean isButtonDown(float x, float y) {
        Camera c = ControlManager.instance.camera;
        float pX = c.position.x - c.viewportWidth / 2 + pos.x;
        float pY = c.position.y - c.viewportHeight / 2 + pos.y;
        if (x >= pX && x <= pX + 64 && y >= pY && y <= pY + 64)
            return true;
        return false;
    }

    protected abstract BuildingSpirit createSpirit(float x, float y);

    @Override
    public void smartRender(SpriteBatch batch, float left, float top, float right, float bottom) {
        BitmapFont.TextBounds bounds = font.getBounds(menuName);
        font.draw(batch, menuName, right + pos.x + 60 - bounds.width, bottom + pos.y + sprite.getHeight() + 15);
        //well, this is dirty hack...
        sprite.setPosition(right + pos.x, bottom + pos.y);

        super.render(batch);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}
