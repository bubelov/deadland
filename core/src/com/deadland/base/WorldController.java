package com.deadland.base;

/**
 * @author Alexey Nevinsky
 * @version 20.08.14 22:32
 */
public class WorldController {

    private DeadlandWorld world;

    public WorldController(DeadlandWorld world) {
        this.world = world;
    }

    public void update(float delta) {
        world.getScene().update(delta);
    }
}
