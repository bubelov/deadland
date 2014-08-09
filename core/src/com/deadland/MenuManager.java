package com.deadland;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by inver on 09.08.2014.
 */
public class MenuManager {

    public static MenuManager instance = new MenuManager();

    private Stage stage;

    public void init() {
        stage = new Stage();
        Skin skin = new Skin();
        skin.add("gunTower", new Texture("menu_gunTower.png"));
        Button.ButtonStyle style = new Button.ButtonStyle();
//        textButtonStyle.font = font;
//        textButtonStyle.up = skin.getDrawable("up-button");
//        textButtonStyle.down = skin.getDrawable("down-button");
//        textButtonStyle.checked = skin.getDrawable("checked-button");
        Button button = new Button(style);
        stage.addActor(button);

        stage.draw();
    }

}
