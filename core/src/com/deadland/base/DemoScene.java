package com.deadland.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.deadland.Assets;
import com.deadland.Constants;
import com.deadland.model.menu.MenuButton;
import com.deadland.model.menu.gun_tower.GunTowerButton;
import com.deadland.model.menu.main_tower.MainTowerButton;
import com.deadland.model.menu.wall.WallButton;
import com.deadland.model.vehicle.Car;
import com.deadland.model.zombie.Zombie;

/**
 * @author Alexey Nevinsky
 * @version 20.08.14 22:38
 */
public class DemoScene extends GameScene {

    public static final String HERO_KEY = "hero";

    private World world;

    public DemoScene() {
        super();
        world = new World(new Vector2(0, 0), true);
        world.setContactListener(new GameContactListener());

        initScene();
        initButtons();
    }

    private void initScene() {
//        Hero hero = new Hero(world, 100, 100);
        Car hero = new Car(world, 0, 0);
        add(hero);
        addToFastAccess(HERO_KEY, hero);

        for (int i = 0; i < 2000; i++) {
            Zombie z = new Zombie(world, this, MathUtils.random(-200, 10000), MathUtils.random(-200, 10000));
            add(z);
//            Stone stone = new Stone(world, MathUtils.random(-200, 10000), MathUtils.random(-200, 10000));
//            add(stone);
        }
    }

    @Override
    public void update(float delta) {
        world.step(Constants.timeStep, Constants.velocityIterations, Constants.positionIterations);
        super.update(delta);
    }

    public World getWorld() {
        return world;
    }

    /**
     * Infinity ground of sand
     *
     * @param batch
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void drawGround(SpriteBatch batch, float left, float top, float right, float bottom) {
        int minI = Math.round(left / 32) - 1;
        int maxI = Math.round(right / 32) + 1;
        int minJ = Math.round(bottom / 32) - 1;
        int maxJ = Math.round(top / 32) + 1;
        for (int i = minI; i < maxI; i++) {
            for (int j = minJ; j < maxJ; j++) {
                batch.draw(Assets.Textures.sand, i * 32, j * 32, 32, 32);
            }
        }
    }

    private void initButtons() {
        MenuButton mb = new GunTowerButton(-72, 4, "Fire tower");
        add(mb);

        mb = new WallButton(-72, 90, "Wall");
        add(mb);

        mb = new MainTowerButton(-72, 174, "Main tower");
        add(mb);
    }
}
