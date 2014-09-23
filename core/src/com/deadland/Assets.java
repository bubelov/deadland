package com.deadland;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public final class Assets {
    public static void initialize() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("atlas/atlas.atlas"));

        Textures.blackPixel = atlas.findRegion("blackPixel");
        Textures.bloodmess = atlas.findRegion("bloodmess");
        Textures.buildingCave = atlas.findRegion("building_cave");
        Textures.buildingGun = atlas.findRegion("building_gun");
        Textures.buildingGun2 = atlas.findRegion("building_gun2");
        Textures.buildingMainTower = atlas.findRegion("building_mainTower");
        Textures.buildingMainTowerTransparent = atlas.findRegion("building_mainTower_transparent");
        Textures.buildingRemove = atlas.findRegion("building_remove");
        Textures.buildingTower = atlas.findRegion("building_tower");
        Textures.buildingTowerGreenTransparent = atlas.findRegion("building_tower_green_transparent");
        Textures.buildingTowerRedTransparent = atlas.findRegion("building_tower_red_transparent");
        Textures.buildingUpgrade = atlas.findRegion("building_upgrade");
        Textures.buildingWall = atlas.findRegion("building_wall");
        Textures.buildingWallGreenTransparent = atlas.findRegion("building_wall_green_transparent");
        Textures.buildingWallRedTransparent = atlas.findRegion("building_wall_red_transparent");
        Textures.bullet = atlas.findRegion("bullet");
        Textures.gates = atlas.findRegion("gates");
        Textures.greenPixel = atlas.findRegion("greenPixel");
        Textures.heroGun = atlas.findRegion("hero_gun");
        Textures.menuGunTower = atlas.findRegion("menu_gunTower");
        Textures.menuMainTower = atlas.findRegion("menu_mainTower");
        Textures.menuWall = atlas.findRegion("menu_wall");
        Textures.pain = atlas.findRegion("pain");
        Textures.sand = atlas.findRegion("sand");
        Textures.stone = atlas.findRegion("stone");
        Textures.stone2 = atlas.findRegion("stone2");
        Textures.trash = atlas.findRegion("trash");
        Textures.truck = atlas.findRegion("truck");
        Textures.weapons = atlas.findRegion("weapons");
        Textures.zombie = atlas.findRegion("zombie");
        Textures.zombieHand = atlas.findRegion("zombie_hand");
        Textures.zombieHead = atlas.findRegion("zombie_head");
        Textures.zombieFlying = atlas.findRegion("zombie_flying");

        Textures.mapBackground = atlas.findRegion("map_background");
        Textures.mapHeroMarker = atlas.findRegion("map_hero_marker");
        Textures.mapDestination = atlas.findRegion("map_destination");
    }

    public static final class Textures {
        public static TextureRegion blackPixel;
        public static TextureRegion bloodmess;
        public static TextureRegion buildingCave;
        public static TextureRegion buildingGun;
        public static TextureRegion buildingGun2;
        public static TextureRegion buildingMainTower;
        public static TextureRegion buildingMainTowerTransparent;
        public static TextureRegion buildingRemove;
        public static TextureRegion buildingTower;
        public static TextureRegion buildingTowerGreenTransparent;
        public static TextureRegion buildingTowerRedTransparent;
        public static TextureRegion buildingUpgrade;
        public static TextureRegion buildingWall;
        public static TextureRegion buildingWallGreenTransparent;
        public static TextureRegion buildingWallRedTransparent;
        public static TextureRegion bullet;
        public static TextureRegion gates;
        public static TextureRegion greenPixel;
        public static TextureRegion heroGun;
        public static TextureRegion menuGunTower;
        public static TextureRegion menuMainTower;
        public static TextureRegion menuWall;
        public static TextureRegion pain;
        public static TextureRegion sand;
        public static TextureRegion stone;
        public static TextureRegion stone2;
        public static TextureRegion trash;
        public static TextureRegion truck;
        public static TextureRegion weapons;
        public static TextureRegion zombie;
        public static TextureRegion zombieHand;
        public static TextureRegion zombieHead;
        public static TextureRegion zombieFlying;

        public static TextureRegion mapBackground;
        public static TextureRegion mapHeroMarker;
        public static TextureRegion mapDestination;
    }

    public static final class Sounds {
        // TODO
    }

    public static final class Animations {
        // TODO
    }

    public static final class Fonts {
        // TODO
    }
}