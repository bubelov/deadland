package com.deadland;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.deadland.model.*;
import com.deadland.model.building.Cave;
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

        ControlManager.instance.camera = new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera = ControlManager.instance.camera;
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        batch = new SpriteBatch();
        sand = new Texture("sand1.jpg");

        EntityManager.instance.add(new Hero(0, 0));

        for (int i = 0; i < 600; i++) {
            Zombie zombie = new Zombie(MathUtils.random(8000), MathUtils.random(6000));
            zombie.destination = new Vector2(400, 300);
            EntityManager.instance.add(zombie);
        }

        for (int i = 0; i < 500; i++) {
            EntityManager.instance.add(new Stone(MathUtils.random(8000), MathUtils.random(6000)));
        }

        TownHelper.createTown(600, 500, 18);

        MenuButton mb = new GunTowerButton(camera.viewportWidth - 72, 4, "Fire tower");
        EHelper.add(mb);

        mb = new WallButton(camera.viewportWidth - 72, 90, "Wall");
        EHelper.add(mb);

        mb = new MainTowerButton(camera.viewportWidth - 72, 174, "Main tower");
        EHelper.add(mb);

        Gdx.input.setInputProcessor(new InputMultiplexer(new GestureDetector(new GestureDetector.GestureAdapter() {
            @Override
            public boolean tap(float x, float y, int count, int button) {
                Vector3 position = new Vector3(x, y, 0);
                camera.unproject(position);
                EntityManager.instance.onTap(position.x, position.y, count, button);
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
                            Hero hero = (Hero) entity;
                            hero.health.health = hero.health.startHealth;
                            hero.health.wound(0);
                        }
                    }
                }

                if (keycode == Input.Keys.A) {
                    Hero hero = null;

                    for (Entity entity : EntityManager.instance.entities) {
                        if (entity instanceof Hero) {
                            hero = (Hero) entity;
                        }
                    }

                    if (hero != null) {
                        for (Entity entity : EntityManager.instance.entities) {
                            if (entity instanceof Zombie) {
                                Zombie zombie = (Zombie) entity;
                                zombie.destination = new Vector2(hero.centerX(), hero.centerY());
                            }
                        }
                    }
                }

                if (keycode == Input.Keys.ENTER && gameOver) {
                    gameOver = false;
                    EntityManager.instance.entities.clear();
                    ResourcesManager.instance = new ResourcesManager();
                    ZombieSpawner.instance.clearEmitters();
                    create();
                }

                if (keycode == Input.Keys.S) {
                    for (int i = 0; i < 500; i++) {
                        Zombie zombie = new Zombie(MathUtils.random(8000), MathUtils.random(6000));
                        EntityManager.instance.add(zombie);
                    }
                }

                return false;
            }
        }));

        Gdx.input.setCursorImage(new Pixmap(Gdx.files.internal("cursor.png")), 0, 0);

        font = new BitmapFont();
        font.setScale(5);

        EntityManager.instance.add(new Weapons(400, 1200));
        EntityManager.instance.add(new Trash(600, 1000));

        EntityManager.instance.add(new Cave(800, 1400));

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

        Camera c = ControlManager.instance.camera;
        float minX = c.position.x - c.viewportWidth / 2 - 32;
        float maxX = c.position.x + c.viewportWidth / 2 + 32;
        float minY = c.position.y - c.viewportHeight / 2 - 32;
        float maxY = c.position.y + c.viewportHeight / 2 + 32;
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                if (i * 32 > minX && i * 32 < maxX && j * 32 > minY && j * 32 < maxY) {
                    batch.draw(sand, i * 32, j * 32, 32, 32);
                }
            }
        }

        ZombieSpawner.instance.update();

        EntityManager.instance.update();
        EntityManager.instance.render(batch);
        ResourcesManager.instance.render(batch, camera);

        batch.end();

        if (ControlManager.instance.isUnderConstruction != null)
            EntityManager.instance.renderArea(camera);

//        System.out.println(Gdx.graphics.getFramesPerSecond());

//        EntityManager.instance.renderCollisions(camera);
    }
}
