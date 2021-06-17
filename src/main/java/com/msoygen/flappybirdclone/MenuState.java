/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msoygen.flappybirdclone;

import static com.msoygen.flappybirdclone.Game.screenWidth;
import static com.msoygen.flappybirdclone.Game.screenHeight;
import static extensions.JaylibX.*;
import com.raylib.Raylib;
import gameobject.ConcreteGameObjectFactory;
import gameobject.GameObject;
import gameobject.ui.Button;

import gameobject.ui.MenuStateUIFactory;
import gameobject.ui.UIImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author msoyg
 */
public class MenuState extends State {

    private MenuStateUIFactory menuStateUIFactory;
    private ConcreteGameObjectFactory gameObjectFactory;

    private Sound fxButton;

    private Texture2D playButtonTexture;
    private Texture2D leaderboardButtonTexture;
    private Texture2D titleImageTexture;
    //private Texture2D tapImageTexture;

    private Button playButton;
    private Button leaderboardButton;

    private UIImage titleImage;
    //private UIImage tapImage;
    //private UIImage upArrowIcon;

    private GameObject player;

    public MenuState(Game game) {
        super(game);

        menuStateUIFactory = new MenuStateUIFactory();
        gameObjectFactory = new ConcreteGameObjectFactory();
    }

    @Override
    public String Init() {
        fxButton = LoadSound("Resources/buttonClick.wav");

        playButtonTexture = LoadTexture("Resources/playButton.png");
        leaderboardButtonTexture = LoadTexture("Resources/leaderboardButton.png");
        titleImageTexture = LoadTexture("Resources/title.png");
        //tapImageTexture = LoadTexture("Resources/tap.png");

        playButton = menuStateUIFactory.createButton(playButtonTexture, new Vector2(90, 450), 0f, 1.3f, WHITE, fxButton);
        leaderboardButton = menuStateUIFactory.createButton(leaderboardButtonTexture, new Vector2(530, 450), 0f, 1.3f, WHITE, fxButton);
        titleImage = menuStateUIFactory.createUIImage(titleImageTexture, new Vector2(122.45f, 80), 0f, 1.3f, WHITE);
        //tapImage = menuStateUIFactory.createUIImage(tapImageTexture, new Vector2(screenWidth / 2 - (tapImageTexture.width() * 2) / 2, playButton.getDestRect().y() - (tapImageTexture.height() * 2)), 0f, 2.0f, WHITE);
        //upArrowIcon = menuStateUIFactory.createUpArrowIconAnimated(new Vector2(screenWidth / 2, tapImage.getDestRect().y() - 3), 0f, 1.0f, WHITE);

        player = gameObjectFactory.createPlayer(new Vector2(100, screenHeight / 2 - 150), 0f, 5, 0.1f, WHITE);

        return "menu initialized";
    }

    @Override
    public String Update() {
        playButton.Update();
        leaderboardButton.Update();
        if (playButton.getAction() == true) {
            playButton.setAction(false);
            game.changeState(new PlayState(game));
        }
        leaderboardButton.Update();
        titleImage.Update();
        //tapImage.Update();
        //upArrowIcon.Update();

        return "menu updated";
    }

    @Override
    public String Render() {
        playButton.Render();
        leaderboardButton.Render();
        titleImage.Render();
        //tapImage.Render();
        player.Render();
        //upArrowIcon.Render();

        return "menu rendered";
    }

    @Override
    public String OnExit(State state) {

        player.Unload();
        playButton.Unload();
        leaderboardButton.Unload();
        titleImage.Unload();
        //tapImage.Unload();
        //upArrowIcon.Unload();

        state.Init();
        return "exited from menu";
    }
}
