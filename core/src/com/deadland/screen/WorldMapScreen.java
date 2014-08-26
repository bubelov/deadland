package com.deadland.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.deadland.Assets;
import com.deadland.Constants;

/**
 * Author: Igor Bubelov
 * Date: 6/16/13 8:47 PM
 */

public class WorldMapScreen extends AbstractScreen {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private TextureRegion mapTexture;

    private Sprite hero;
    private Sprite destination;

    public WorldMapScreen(Game game) {
        super(game);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        batch = new SpriteBatch();

        mapTexture = Assets.Textures.mapBackground;

        hero = new Sprite(Assets.Textures.mapHeroMarker);
        hero.setSize(48, 48);
        hero.setCenter(512, 512);
        hero.setOriginCenter();

        createDestination(768, 512);

        Gdx.input.setInputProcessor(new GestureDetector(new GestureDetector.GestureAdapter() {
            @Override
            public boolean tap(float x, float y, int count, int button) {
                Vector3 position = new Vector3(x, y, 0);
                camera.unproject(position);
                createDestination(position.x, position.y);
                return true;
            }
        }));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (destination != null) {
            Vector2 heroLocation = new Vector2(hero.getX(), hero.getY());
            Vector2 destinationLocation = new Vector2(destination.getX(), destination.getY());

            if (heroLocation.dst(destinationLocation) < 5) {
                destination = null;
            } else {
                Vector2 movementVector = new Vector2(hero.getX(), hero.getY()).sub(destination.getX(), destination.getY()).nor().scl(75);
                hero.translate(-movementVector.x * Gdx.graphics.getDeltaTime(), -movementVector.y * Gdx.graphics.getDeltaTime());
            }
        }

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(mapTexture, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        batch.end();

        if (destination != null) {
            ShapeRenderer shapeRenderer = new ShapeRenderer();
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

            shapeRenderer.line(
                    hero.getX() + hero.getWidth() / 2,
                    hero.getY() + hero.getHeight() / 2,
                    destination.getX() + destination.getWidth() / 2,
                    destination.getY() + destination.getHeight() / 2
            );

            shapeRenderer.end();
        }

        batch.begin();

        if (destination != null) {
            destination.draw(batch);
        }

        hero.draw(batch);
        batch.end();
    }

    private void createDestination(float x, float y) {
        destination = new Sprite(Assets.Textures.mapDestination);
        destination.setSize(48, 48);
        destination.setCenter(x, y);
    }
}
