/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msoygen.flappybirdclone;

import static com.msoygen.flappybirdclone.Game.score;
import static com.msoygen.flappybirdclone.Game.screenWidth;
import static com.msoygen.flappybirdclone.Game.screenHeight;
import static extensions.JaylibX.*;
import extensions.PlayerCollision;
import gameobject.ConcreteGameObjectFactory;
import gameobject.GameObject;
import gameobject.ui.PlayingStateUIFactory;
import gameobject.ui.UIImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author msoyg
 */
public class PlayState extends State {

    private PlayingStateUIFactory playingStateUIFactory;
    private ConcreteGameObjectFactory gameObjectFactory;

    private boolean pipeCheck;
    private int pipeCount;

    private PlayerCollision playerCollision;

    private Sound fxScore;
    private Sound fxCoin;
    private Sound fxGameOver;

    private Texture2D pipeTopTexture;
    private Texture2D pipeBottomTexture;
    
    private Texture2D floorTexture;

    private List<GameObject> pipeBottomList;
    private List<GameObject> pipeTopList;

    private GameObject player;

    private Rectangle sourceRect;
    private GameObject coin;

    private UIImage inGameUIScore;

    public PlayState(Game game) {
        super(game);

        playingStateUIFactory = new PlayingStateUIFactory();
        gameObjectFactory = new ConcreteGameObjectFactory();

        pipeCheck = false;
        pipeCount = 1;

        playerCollision = new PlayerCollision();
    }

    @Override
    public String Init() {
        fxScore = LoadSound("Resources/score.wav");
        fxCoin = LoadSound("Resources/coin.wav");
        fxGameOver = LoadSound("Resources/gameOver.wav");

        pipeTopTexture = LoadTexture("Resources/pipeTop.png");
        pipeBottomTexture = LoadTexture("Resources/pipeBottom.png");
       
        floorTexture = LoadTexture("Resources/floor.png");

        pipeBottomList = new ArrayList<>();
        pipeTopList = new ArrayList<>();

        player = gameObjectFactory.createPlayer(new Vector2(100, screenHeight / 2 - 150), 0f, 5, 0.1f, WHITE);
        
        sourceRect = new Rectangle(0, 0, pipeBottomTexture.width(), pipeBottomTexture.height() / 2);
        for (int i = 0; i < 5; i++) {
            pipeBottomList.add(gameObjectFactory.createPipe(pipeBottomTexture, new Vector2((screenWidth / 5) * i, screenHeight - (pipeBottomTexture.height() / 2) - floorTexture.height()), 0f, 3.0f, 1.0f, WHITE, sourceRect));
        }
        for (int i = 0; i < 5; i++) {
            pipeTopList.add(gameObjectFactory.createPipe(pipeTopTexture, new Vector2((screenWidth / 5) * i, -(pipeTopTexture.height() / 2)), 0f, 3.0f, 1.0f, WHITE));
        }

        coin = gameObjectFactory.createCoin(new Vector2(80, 10), 0f, 3.0f, 1.0f, WHITE);

        inGameUIScore = playingStateUIFactory.createInGameUIScore(new Vector2(5, 5), 0f, 1.5f, WHITE);

        return "play initialized";
    }

    @Override
    public String Update() {
        pipeBottomList.forEach(p -> {
            p.Update();
        });
        pipeTopList.forEach(p -> {
            p.Update();
        });
        player.Update();
        for (GameObject p : pipeBottomList) {
            if (playerCollision.checkBottomPipeCollision(player, p)) {
                PlaySound(fxGameOver);
                game.changeState(new GameOverState(game));
            }
        }
        for (GameObject p : pipeTopList) {
            if (playerCollision.checkTopPipeCollision(player, p)) {
                PlaySound(fxGameOver);
                game.changeState(new GameOverState(game));
            }
        }
        if (playerCollision.checkPlayerPassedCurrentPipe(player, pipeBottomList.get(pipeCount))) {
            pipeCheck = true;
            pipeCount++;
            if (pipeCount >= pipeBottomList.size()) {
                pipeCount = 0;
            }
            PlaySound(fxScore);
        } else {
            if (pipeCheck) {
                score += 1;
            }
            pipeCheck = false;
        }

        if (playerCollision.checkPlayerCollidesCoin(player, coin)) {
            score += 10;
            PlaySound(fxCoin);
        }

        coin.Update();
        inGameUIScore.Update();

        return "play updated";
    }

    @Override
    public String Render() {
        pipeBottomList.forEach(p -> {
            p.Render();
        });
        pipeTopList.forEach(p -> {
            p.Render();
        });
        coin.Render();
        player.Render();
        inGameUIScore.Render();

        return "play rendered";
    }

    @Override
    public String OnExit(State state) {
        pipeBottomList.forEach(p -> {
            p.Unload();
        });
        pipeTopList.forEach(p -> {
            p.Unload();
        });

        coin.Unload();
        player.Unload();
        inGameUIScore.Unload();
        
        state.Init();
        return "exited from play";
    }

}
