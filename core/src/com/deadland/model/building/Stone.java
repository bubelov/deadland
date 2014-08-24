package com.deadland.model.building;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.deadland.Assets;
import com.deadland.base.model.PhysicsEntity;

/**
 * @author Alexey Nevinsky
 * @version 18.08.14 23:06
 */
public class Stone extends PhysicsEntity {

    public Stone(World world, float x, float y) {
        super(world, x, y, 0);
    }

    protected void initSprite() {
        sprite = new Sprite(Assets.Textures.stone);
        sprite.setSize(32, 32);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
        addSprite(sprite);
    }
}
