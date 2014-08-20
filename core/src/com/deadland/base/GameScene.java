package com.deadland.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deadland.base.model.BaseEntity;

import java.util.List;

/**
 * @author Alexey Nevinsky
 * @version 20.08.14 22:27
 */
public abstract class GameScene {

    protected List<BaseEntity> entities;

    public abstract void render(SpriteBatch batch);

    public abstract void update(float delta);
}
