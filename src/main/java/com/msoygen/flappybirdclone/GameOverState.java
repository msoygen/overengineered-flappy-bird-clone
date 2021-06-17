/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msoygen.flappybirdclone;

import static com.msoygen.flappybirdclone.Game.score;
import static com.msoygen.flappybirdclone.Game.screenHeight;
import static com.msoygen.flappybirdclone.Game.screenWidth;
import gameobject.ConcreteGameObjectFactory;
import gameobject.ui.GameOverStateUIFactory;

import static extensions.JaylibX.*;
import gameobject.ui.Button;
import gameobject.ui.UIImage;

/**
 *
 * @author msoyg
 */
public class GameOverState extends State {

    private GameOverStateUIFactory gameOverStateUIFactory;
    private ConcreteGameObjectFactory gameObjectFactory;

    private Sound fxButton;

    private Texture2D scoreCanvasTexture;
    private Texture2D gameOverTexture;
    private Texture2D okButtonTexture;
    private Texture2D menuButtonTexture;

    private UIImage gameOverImage;
    private UIImage scoreCanvas;
    private Button menuButton;
    private Button okButton;

    public GameOverState(Game game) {
        super(game);

        gameOverStateUIFactory = new GameOverStateUIFactory();

        gameObjectFactory = new ConcreteGameObjectFactory();
    }

    @Override
    public String Init() {
        fxButton = LoadSound("Resources/buttonClick.wav");

        scoreCanvasTexture = LoadTexture("Resources/scoreCanvas.png");
        gameOverTexture = LoadTexture("Resources/gameOver.png");
        okButtonTexture = LoadTexture("Resources/okButton.png");
        menuButtonTexture = LoadTexture("Resources/menuButton.png");

        gameOverImage = gameOverStateUIFactory.createUIImage(gameOverTexture, new Vector2(290, 100), 0f, 1.0f, WHITE);
        scoreCanvas = gameOverStateUIFactory.createScoreCanvas(new Vector2(screenWidth / 2 - (scoreCanvasTexture.width() * 2.0f) / 2, screenHeight / 2 - (scoreCanvasTexture.height() * 2.0f) / 2), 0f, 2.0f, WHITE, score);
        menuButton = gameOverStateUIFactory.createButton(menuButtonTexture, new Vector2(screenWidth / 2 - menuButtonTexture.width() - 100, scoreCanvas.getDestRect().y() + scoreCanvas.getDestRect().height()), 0f, 2.0f, WHITE, fxButton);
        okButton = gameOverStateUIFactory.createButton(okButtonTexture, new Vector2(screenWidth / 2 - okButtonTexture.width() + 100, scoreCanvas.getDestRect().y() + scoreCanvas.getDestRect().height()), 0f, 2.0f, WHITE, fxButton);

        return "game over initialized";
    }
    
    @Override
    public String Update() {
        gameOverImage.Update();
        scoreCanvas.Update();
        okButton.Update();
        menuButton.Update();

        if (menuButton.getAction() == true) {
            menuButton.setAction(false);

            score = 0;

            game.changeState(new MenuState(game));
        }

        if (okButton.getAction() == true) {
            menuButton.setAction(false);

            score = 0;

            game.changeState(new MenuState(game));
        }

        return "game over updated";
    }

    @Override
    public String Render() {
        gameOverImage.Render();
        scoreCanvas.Render();
        okButton.Render();
        menuButton.Render();
        
        return "game over rendered";
    }

    @Override
    public String OnExit(State state) {
        gameOverImage.Unload();
        scoreCanvas.Unload();
        okButton.Unload();
        menuButton.Unload();
        
        state.Init();
        
        return "exited from game over";
    }
}
