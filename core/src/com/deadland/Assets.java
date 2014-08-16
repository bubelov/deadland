package com.deadland;

import com.badlogic.gdx.graphics.Texture;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public final class Assets {
    public static final class Textures {
        public static Texture blackPixel;
        public static Texture bloodmess;
        public static Texture buildingCave;
        public static Texture buildingGun;
        public static Texture buildingGun2;
        public static Texture buildingMainTower;
        public static Texture buildingMainTowerTransparent;
        public static Texture buildingRemove;
        public static Texture buildingTower;
        public static Texture buildingTowerGreenTransparent;
        public static Texture buildingTowerRedTransparent;
        public static Texture buildingUpgrade;
        public static Texture buildingWall;
        public static Texture buildingWallGreenTransparent;
        public static Texture buildingWallRedTransparent;
        public static Texture bullet;
        public static Texture gates;
        public static Texture greenPixel;
        public static Texture heroGun;
        public static Texture menuGunTower;
        public static Texture menuMainTower;
        public static Texture menuWall;
        public static Texture pain;
        public static Texture sand;
        public static Texture stone;
        public static Texture stone2;
        public static Texture trash;
        public static Texture truck;
        public static Texture weapons;
        public static Texture zombie;
        public static Texture zombieHand;
        public static Texture zombieHead;
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

    public static void initialize() {
        Textures.blackPixel = new Texture("blackPixel.png");
        Textures.bloodmess = new Texture("bloodmess.png");
        Textures.buildingCave = new Texture("building_cave.png");
        Textures.buildingGun = new Texture("building_gun.png");
        Textures.buildingGun2 = new Texture("building_gun2.png");
        Textures.buildingMainTower = new Texture("building_mainTower.png");
        Textures.buildingMainTowerTransparent = new Texture("building_mainTower_transparent.png");
        Textures.buildingRemove = new Texture("building_remove.png");
        Textures.buildingTower = new Texture("building_tower.png");
        Textures.buildingTowerGreenTransparent = new Texture("building_tower_green_transparent.png");
        Textures.buildingTowerRedTransparent = new Texture("building_tower_red_transparent.png");
        Textures.buildingUpgrade = new Texture("building_upgrade.png");
        Textures.buildingWall = new Texture("building_wall.png");
        Textures.buildingWallGreenTransparent = new Texture("building_wall_green_transparent.png");
        Textures.buildingWallRedTransparent = new Texture("building_wall_red_transparent.png");
        Textures.bullet = new Texture("bullet.png");
        Textures.gates = new Texture("gates.png");
        Textures.greenPixel = new Texture("greenPixel.png");
        Textures.heroGun = new Texture("hero_gun.png");
        Textures.menuGunTower = new Texture("menu_gunTower.png");
        Textures.menuMainTower = new Texture("menu_mainTower.png");
        Textures.menuWall = new Texture("menu_wall.png");
        Textures.pain = new Texture("pain.png");
        Textures.sand = new Texture("sand.png");
        Textures.stone = new Texture("stone.png");
        Textures.stone2 = new Texture("stone2.png");
        Textures.trash = new Texture("trash.png");
        Textures.truck = new Texture("truck.png");
        Textures.weapons = new Texture("weapons.png");
        Textures.zombie = new Texture("zombie.png");
        Textures.zombieHand = new Texture("zombie_hand.png");
        Textures.zombieHead = new Texture("zombie_head.png");
    }
}