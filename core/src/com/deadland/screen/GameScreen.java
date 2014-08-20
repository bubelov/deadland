package com.deadland.screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.deadland.Assets;
import com.deadland.base.DeadlandWorld;
import com.deadland.base.WorldController;
import com.deadland.base.WorldRenderer;

/**
 * @author Alexey Nevinsky
 * @version 15.08.14 1:05
 */
public class GameScreen extends ApplicationAdapter implements InputProcessor {

    private DeadlandWorld world;
    private WorldRenderer renderer;
    private WorldController controller;

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }


    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void create() {

        Assets.initialize();

        world = new DeadlandWorld();
        renderer = new WorldRenderer(world);
        controller = new WorldController(world);
//        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

//        renderer.render();
        renderer.debugRender();
        world.step();
        controller.update(delta);
    }

    @Override
    public void resize(int width, int height) {

    }
}
