package com.deadland;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.deadland.model.GunTower;
import com.deadland.model.Hero;
import com.deadland.model.Stone;
import com.deadland.model.Zombie;

public class DeadlandGame extends ApplicationAdapter {
    private OrthographicCamera camera;

    SpriteBatch batch;
    Texture sand;

    @Override
    public void create() {
        camera = new OrthographicCamera(800, 600);
        camera.setToOrtho(false);

        batch = new SpriteBatch();
        sand = new Texture("sand1.jpg");

        EntityManager.instance.add(new Hero(0, 0));

        for (int i = 0; i < 100; i++) {
            Zombie zombie = new Zombie(MathUtils.random(800), MathUtils.random(600));
            zombie.destination = new Vector2(400, 300);
            EntityManager.instance.add(zombie);
        }

        for (int i = 0; i < 500; i++) {
            EntityManager.instance.add(new Stone(MathUtils.random(8000), MathUtils.random(6000)));
        }

        GunTower gunTower = new GunTower(350, 250);
        EntityManager.instance.add(gunTower);

        Gdx.input.setInputProcessor(new GestureDetector(new GestureDetector.GestureAdapter() {
            @Override
            public boolean tap(float x, float y, int count, int button) {
                x = camera.position.x + x - camera.viewportWidth / 2;
                y = 600 - y + camera.position.y - 300;
                EntityManager.instance.onTap(x, y, count, button);
                return false;
            }

            @Override
            public boolean pan(float x, float y, float deltaX, float deltaY) {
                camera.translate(-deltaX, deltaY);
                camera.update();
                return false;
            }
        }));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                batch.draw(sand, i * 32, j * 32, 32, 32);
            }
        }

        EntityManager.instance.update();
        EntityManager.instance.render(batch);


        batch.end();

        //EntityManager.instance.renderCollisions(camera);
    }
}
