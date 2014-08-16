package com.deadland.desktop.tools;

import com.badlogic.gdx.graphics.Texture;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class TexturePacker {
    public static void main(String[] args) {
        com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings settings = new com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings();
        //settings.filterMin = Texture.TextureFilter.Linear;
        //settings.filterMag = Texture.TextureFilter.Linear;
        settings.combineSubdirectories = true;
        com.badlogic.gdx.tools.texturepacker.TexturePacker.process(settings, "textures", "atlas", "atlas");
    }
}
