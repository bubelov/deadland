package com.deadland;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.deadland.model.*;
import com.deadland.model.menu.MenuButton;
import com.deadland.model.menu.gun_tower.GunTowerButton;
import com.deadland.model.menu.main_tower.MainTowerButton;
import com.deadland.model.menu.wall.WallButton;

public class DeadlandGame extends ApplicationAdapter {
    public static DeadlandGame instance;

    private OrthographicCamera camera;

    SpriteBatch batch;
    Texture sand;

    public boolean gameOver;
    BitmapFont font;

    @Override
    public void create() {
        instance = this;

        System.out.println(Gdx.graphics.getWidth());

        ControlManager.instance.camera = new OrthographicCamera(800, 600);
        camera = ControlManager.instance.camera;
        camera.setToOrtho(false, 800, 600);

        batch = new SpriteBatch();
        sand = new Texture("sand1.jpg");

        EntityManager.instance.add(new Hero(0, 0));

        for (int i = 0; i < 400; i++) {
            Zombie zombie = new Zombie(MathUtils.random(8000), MathUtils.random(6000));
            zombie.destination = new Vector2(400, 300);
            EntityManager.instance.add(zombie);
        }

        for (int i = 0; i < 500; i++) {
            EntityManager.instance.add(new Stone(MathUtils.random(8000), MathUtils.random(6000)));
        }

        TownHelper.createTown(600, 500, 18);

        MenuButton mb = new GunTowerButton(camera.viewportWidth - 32, 0);
        EHelper.add(mb);

        mb = new WallButton(camera.viewportWidth - 66, 0);
        EHelper.add(mb);

        mb = new MainTowerButton(camera.viewportWidth - 100, 0);
        EHelper.add(mb);

        Gdx.input.setInputProcessor(new InputMultiplexer(new GestureDetector(new GestureDetector.GestureAdapter() {
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
        }), new InputAdapter() {
            @Override
            public boolean keyUp(int keycode) {
                if (keycode == Input.Keys.H) {
                    for (Entity entity : EntityManager.instance.entities) {
                        if (entity instanceof Hero) {
                            Hero hero = (Hero)entity;
                            hero.health.health = hero.health.startHealth;
                            hero.health.wound(0);
                        }
                    }
                }

                if (keycode == Input.Keys.A) {
                    Hero hero = null;

                    for (Entity entity : EntityManager.instance.entities) {
                        if (entity instanceof Hero) {
                            hero = (Hero)entity;
                        }
                    }

                    if (hero != null) {
                        for (Entity entity : EntityManager.instance.entities) {
                            if (entity instanceof Zombie) {
                                Zombie zombie = (Zombie)entity;
                                zombie.destination = new Vector2(hero.centerX(), hero.centerY());
                            }
                        }
                    }
                }

                return false;
            }
        }));

        Gdx.input.setCursorImage(new Pixmap(Gdx.files.internal("cursor.png")), 0, 0);

        font = new BitmapFont();
        font.setScale(5);

        EntityManager.instance.add(new Weapons(400, 1400));
        EntityManager.instance.add(new Trash(600, 1400));

        EntityManager.instance.add(new Zombie(100, 100));
        EntityManager.instance.add(new Zombie(110, 100));
        EntityManager.instance.add(new Zombie(120, 100));
        EntityManager.instance.add(new Zombie(130, 100));

        EntityManager.instance.add(new Zombie(-23, -50));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        if (gameOver) {
            BitmapFont.TextBounds bounds = font.getBounds("GAME OVER");
            font.draw(batch, "GAME OVER",
                    camera.position.x - bounds.width / 2,
                    camera.position.y + bounds.height / 2 + 50
            );

            bounds = font.getBounds("R.I.P.");
            font.draw(batch, "R.I.P.",
                    camera.position.x - bounds.width / 2,
                    camera.position.y + bounds.height / 2 - 20
            );

            batch.end();
            return;
        }

        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                if (camera.position.dst(i * 32, j * 32, 0) < 550) {
                    batch.draw(sand, i * 32, j * 32, 32, 32);
                }
            }
        }

        EntityManager.instance.update();
        EntityManager.instance.render(batch);
        ResourcesManager.instance.render(batch, camera);

        batch.end();

        if (ControlManager.instance.isUnderConstruction != null)
            EntityManager.instance.renderArea(camera);

        //System.out.println(Gdx.graphics.getFramesPerSecond());

        //EntityManager.instance.renderCollisions(camera);
    }
}
