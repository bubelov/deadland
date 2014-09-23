package com.deadland;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.deadland.base.*;

public class DeadlandGame extends Game {
    public static DeadlandGame instance;
    //    public boolean gameOver;
//    BitmapFont font;
    private WorldRenderer renderer;
    private GameScene demoScene;

    @Override
    public void create() {
        initializeResources();

//        setScreen(new WorldMapScreen(this));

        renderer = new WorldRenderer();
        demoScene = new DemoScene();
        renderer.setScene(demoScene);

        InputProcessor ip = new BaseInputProcessor(demoScene);
        GestureDetector.GestureListener gd = new BaseGestureDetector(renderer.getCamera());

        Gdx.input.setInputProcessor(new InputMultiplexer(new GestureDetector(gd), ip));

        /** Following ugly code will e removed after demo will be released **/
        ControlManager.instance.camera = renderer.getCamera();
        ControlManager.instance.demoScene = demoScene;
//        EntityManager.instance.add(new Hero(world.getWorld(), 0, 0));
//
//        for (int i = 0; i < 100; i++) {
//            Zombie zombie = new Zombie(MathUtils.random(16000), MathUtils.random(12000));
//            zombie.destination = new Vector2(800, 600);
//            EntityManager.instance.add(zombie);
//        }
//
////        for (int i = 0; i < 50; i++) {
////            EntityManager.instance.add(new Stone(MathUtils.random(8000), MathUtils.random(6000)));
////        }
//
//        TownHelper.createTown(1200, 1000, 18);
//
//        Gdx.input.setCursorImage(new Pixmap(Gdx.files.internal("cursor.png")), 0, 0);
//
//        font = new BitmapFont();
//        font.setScale(5);
//
//        EntityManager.instance.add(new Weapons(800, 2400));
//        EntityManager.instance.add(new Trash(1200, 2000));
//
//        EntityManager.instance.add(new Cave(1600, 2800));
//
//        EntityManager.instance.add(new Zombie(200, 200));
//        EntityManager.instance.add(new Zombie(220, 200));
//        EntityManager.instance.add(new Zombie(240, 200));
//        EntityManager.instance.add(new Zombie(260, 200));
//
//        EntityManager.instance.add(new Zombie(-46, -100));
        instance = this;
    }

    private void initializeResources() {
        Assets.initialize();
    }

    @Override
    public void render() {
        renderer.render(true);

        float delta = Gdx.graphics.getDeltaTime();
        //do update physics and positions of objects
        demoScene.update(delta);


//        Gdx.gl.glClearColor(0, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        batch.setProjectionMatrix(camera.combined);
//        batch.begin();
//
//        if (gameOver) {
//            BitmapFont.TextBounds bounds = font.getBounds("GAME OVER");
//            font.draw(batch, "GAME OVER",
//                    camera.position.x - bounds.width / 2,
//                    camera.position.y + bounds.height / 2 + 50
//            );
//
//            bounds = font.getBounds("R.I.P.");
//            font.draw(batch, "R.I.P.",
//                    camera.position.x - bounds.width / 2,
//                    camera.position.y + bounds.height / 2 - 20
//            );
//
//            batch.end();
//            return;
//        }
//
//        ZombieSpawner.instance.update();
//
//        long timeBeforeUpdate = System.nanoTime();
//        EntityManager.instance.update();
//        long updateTime = System.nanoTime() - timeBeforeUpdate;
//        System.out.println("Update: " + TimeUnit.NANOSECONDS.toMillis(updateTime) + " ms");
//
//        long timeBeforeRender = System.nanoTime();
//        EntityManager.instance.render(batch);
//        long renderTime = System.nanoTime() - timeBeforeRender;
//        System.out.println("Render: " + TimeUnit.NANOSECONDS.toMillis(renderTime) + " ms");
//
//        ResourcesManager.instance.render(batch, camera);
//
//        batch.end();
//
//        if (ControlManager.instance.isUnderConstruction != null)
//            EntityManager.instance.renderArea(camera);
//
//        System.out.println("FPS: " + Gdx.graphics.getFramesPerSecond());

        //EntityManager.instance.renderCollisions(camera);
    }
}
