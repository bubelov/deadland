package com.deadland.support;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deadland.model.Entity;

/**
 * Created by inver on 09.08.2014.
 */
public class Health {
    private static Texture blackPixel = new Texture("blackPixel.png");
    private static Texture greenPixel = new Texture("greenPixel.png");

    private Sprite blackSprite;
    private Sprite greenSprite;

    private Entity entity;
    private float health;
    private float panelWidth;
    private float startHealth;
    private float panelMargin;

    public Health(Entity entity, float health, float panelWidth, float panelMargin) {
        this.health = this.startHealth = health;
        this.entity = entity;
        this.panelMargin = panelMargin;
        this.panelWidth = panelWidth;

        blackSprite = new Sprite(blackPixel);
        blackSprite.setSize(panelWidth, 5);
        blackSprite.setCenter(panelWidth / 2, 0);
        blackSprite.setOrigin(panelWidth / 2, 0);
        blackSprite.setPosition(entity.x(), entity.y() + panelMargin);

        greenSprite = new Sprite(greenPixel);
        greenSprite.setSize(panelWidth - 2, 3);
        greenSprite.setCenter((panelWidth - 2) / 2, 0);
        greenSprite.setOrigin((panelWidth - 2) / 2, 0);
        greenSprite.setPosition(entity.x() + 1, entity.y() + panelMargin + 1);
    }

    public boolean isDead() {
        return health <= 0;
    }

    public void wound(float woundValue) {
        health -= woundValue;
        greenSprite.setSize(health / startHealth * panelWidth, 3);
    }

    public void update(float x, float y) {
        blackSprite.setPosition(x, y + panelMargin);
        greenSprite.setPosition(x + 1, y + 1 + panelMargin);
    }

    public void render(SpriteBatch batch) {
        blackSprite.draw(batch);
        greenSprite.draw(batch);
    }


}
