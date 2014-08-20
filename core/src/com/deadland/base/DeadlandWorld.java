package com.deadland.base;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.deadland.model.Car;
import com.deadland.model.building.Stone;

/**
 * @author Alexey Nevinsky
 * @version 18.08.14 22:51
 */
public class DeadlandWorld {

    private World world;

    private DefaultScene scene;

    public DeadlandWorld() {
        world = new World(new Vector2(0, 0), true);
        world.setContactListener(new BaseContactListener());
        createDefaultWorld();
    }

    protected void createDefaultWorld() {
        scene = new DefaultScene();

        Car car = new Car(world, 147, 72);
        scene.add(car);

//        Stone stone = new Stone(world, 64, 64);
//        stone.setPosition(400, 400);
//        scene.add(stone);

        for (int i = 0; i < 1000; i++) {
            Stone stone = new Stone(world, 64, 64);
            stone.setPosition(MathUtils.random(16000), MathUtils.random(1600));
            scene.add(stone);
        }
    }


    public GameScene getScene() {
        return scene;
    }

    public void step() {
        world.step(1 / 300f, 6, 2);
    }

    public World getWorld() {
        return world;
    }
}
