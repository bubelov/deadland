package com.deadland;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by inver on 09.08.2014.
 */
public class ResourcesManager {
    public static ResourcesManager instance = new ResourcesManager();

    private int trash = 10000000;
    private int weapon = 10000000;
    BitmapFont font;

    public ResourcesManager() {
        font = new BitmapFont();
        font.setScale(1);
        font.setColor(Color.BLACK);
    }

    public boolean spendTrash(int trash) {
        if (this.trash - trash >= 0) {
            this.trash -= trash;
            return true;
        }
        return false;
    }

    public boolean spendWeapon(int weapon) {
        if (this.weapon - weapon >= 0) {
            this.weapon -= weapon;
            return true;
        }
        return false;
    }

    public void render(SpriteBatch batch, Camera camera) {
        String trashStr = "Thash: " + trash;
        BitmapFont.TextBounds bounds = font.getBounds(trashStr);
        font.draw(batch, trashStr,
                camera.position.x + camera.viewportWidth / 2 - bounds.width - 5,
                camera.position.y + camera.viewportHeight / 2 - 5
        );

        String weaponStr = "Weapon: " + weapon;
        bounds = font.getBounds(weaponStr);
        font.draw(batch, weaponStr,
                camera.position.x + camera.viewportWidth / 2 - bounds.width - 5,
                camera.position.y + camera.viewportHeight / 2 - bounds.height / 2 - 20);
    }
}

