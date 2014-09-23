package com.deadland.model.zombie;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.deadland.Assets;
import com.deadland.base.model.Entity;

/**
 * Created by linv3r on 16.09.14.
 */
public class ZombiePart extends Entity {

    public ZombiePart(float x, float y, boolean handOrHead) {
        Sprite sprite = new Sprite(handOrHead ? Assets.Textures.zombieHand : Assets.Textures.zombieHead);
        sprite.setSize(40, 14);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        sprite.setPosition(x, y);

        z = -50;
        sprite.setRotation(MathUtils.random(360));
        addSprite(sprite);
    }
}
