package com.msoygen.flappybirdclone;

import static com.msoygen.flappybirdclone.App.screenWidth;
import com.raylib.Raylib;
import static extensions.JaylibX.*;
import gameobject.ConcreteGameObjectFactory;
import gameobject.GameObject;
import gameobject.Pipe;
import gameobject.Player;
import gameobject.ui.Button;
import gameobject.ui.GameOverStateUIFactory;
import gameobject.ui.MenuStateUIFactory;
import gameobject.ui.PlayingStateUIFactory;
import gameobject.ui.UIImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author msoyg
 */
public class App {

    enum GameStates {
        MENU,
        PLAYING,
        GAME_OVER
    }

    // Initialization
    //--------------------------------------------------------------------------------------
    public static final int screenWidth = 800;
    public static final int screenHeight = 600;

    public static int score = 0;

    public static void main(String args[]) {
        MenuStateUIFactory menuStateUIFactory = new MenuStateUIFactory();
        PlayingStateUIFactory playingStateUIFactory = new PlayingStateUIFactory();
        GameOverStateUIFactory gameOverStateUIFactory = new GameOverStateUIFactory();
        
        ConcreteGameObjectFactory gameObjectFactory = new ConcreteGameObjectFactory();

        GameStates gameState = GameStates.MENU;
        boolean pipeCheck = false;
        int pipeCount = 1;

        InitWindow(screenWidth, screenHeight, "flappy bird clone");
        InitAudioDevice();

        SetTargetFPS(60);               // Set our game to run at 60 frames-per-second
        //--------------------------------------------------------------------------------------

        Sound fxButton = LoadSound("Resources/buttonClick.wav");
        Sound fxScore = LoadSound("Resources/score.wav");
        Sound fxCoin = LoadSound("Resources/coin.wav");
        Sound fxGameOver = LoadSound("Resources/gameOver.wav");

        Texture2D playButtonTexture = LoadTexture("Resources/playButton.png");
        Texture2D leaderboardButtonTexture = LoadTexture("Resources/leaderboardButton.png");
        Texture2D titleImageTexture = LoadTexture("Resources/title.png");
        Texture2D tapImageTexture = LoadTexture("Resources/tap.png");

        Texture2D scoreCanvasTexture = LoadTexture("Resources/scoreCanvas.png");
        Texture2D gameOverTexture = LoadTexture("Resources/gameOver.png");
        Texture2D okButtonTexture = LoadTexture("Resources/okButton.png");
        Texture2D menuButtonTexture = LoadTexture("Resources/menuButton.png");
        
        Texture2D backgroundTexture = LoadTexture("Resources/background.png");
        Texture2D pipeTopTexture = LoadTexture("Resources/pipeTop.png");
        Texture2D pipeBottomTexture = LoadTexture("Resources/pipeBottom.png");
        Texture2D floorTexture = LoadTexture("Resources/floor.png");

        List<GameObject> pipeBottomList = new ArrayList<>();
        List<GameObject> pipeTopList = new ArrayList<>();
        List<GameObject> backgroundList = new ArrayList<>();
        List<GameObject> floorList = new ArrayList<>();

        GameObject player = gameObjectFactory.createPlayer(new Vector2(100, screenHeight / 2 - 150), 0f, 5, 0.1f, WHITE);

        Rectangle sourceRect = new Rectangle(0, 0, pipeBottomTexture.width(), pipeBottomTexture.height() / 2);
        for (int i = 0; i < 5; i++) {
            pipeBottomList.add(gameObjectFactory.createPipe(pipeBottomTexture, new Vector2((screenWidth / 5) * i, screenHeight - (pipeBottomTexture.height() / 2) - floorTexture.height()), 0f, 3.0f, 1.0f, WHITE, sourceRect));
        }
        for (int i = 0; i < 5; i++) {
            pipeTopList.add(gameObjectFactory.createPipe(pipeTopTexture, new Vector2((screenWidth / 5) * i, -(pipeTopTexture.height() / 2)), 0f, 3.0f, 1.0f, WHITE));
        }

        GameObject coin = gameObjectFactory.createCoin(new Vector2(80, 10), 0f, 3.0f, 1.0f, WHITE);

        for (int i = 0; i < 4; i++) {
            backgroundList.add(gameObjectFactory.createBackground(backgroundTexture, new Vector2(i * backgroundTexture.width(), 0), 0f, 3.0f, 1.2f, WHITE));
        }

        for (int i = 0; i < 4; i++) {
            floorList.add(gameObjectFactory.createFloor(LoadTexture("Resources/floor.png"), new Vector2(floorTexture.width() * i, screenHeight - floorTexture.height()), 0f, 3.0f, 1.32f, WHITE));
        }

        Button playButton = menuStateUIFactory.createButton(playButtonTexture, new Vector2(90, 450), 0f, 1.3f, WHITE, fxButton);
        Button leaderboardButton = menuStateUIFactory.createButton(leaderboardButtonTexture, new Vector2(530, 450), 0f, 1.3f, WHITE, fxButton);
        UIImage titleImage = menuStateUIFactory.createUIImage(titleImageTexture, new Vector2(122.45f, 80), 0f, 1.3f, WHITE);
        UIImage tapImage = menuStateUIFactory.createUIImage(tapImageTexture, new Vector2(screenWidth / 2 - (tapImageTexture.width() * 2) / 2, playButton.getDestRect().y() - (tapImageTexture.height() * 2)), 0f, 2.0f, WHITE);
        UIImage upArrowIcon = menuStateUIFactory.createUpArrowIconAnimated(new Vector2(screenWidth / 2 - 3, tapImage.getDestRect().y() - 3), 0f, 1.0f, WHITE);

        UIImage inGameUIScore = playingStateUIFactory.createInGameUIScore(new Vector2(5, 5), 0f, 1.5f, WHITE);

        UIImage gameOverImage = gameOverStateUIFactory.createUIImage(gameOverTexture, new Vector2(290, 100), 0f, 1.0f, WHITE);
        UIImage scoreCanvas = gameOverStateUIFactory.createScoreCanvas(new Vector2(screenWidth / 2 - (scoreCanvasTexture.width() * 2.0f) / 2, screenHeight / 2 - (scoreCanvasTexture.height() * 2.0f) / 2), 0f, 2.0f, WHITE, score);
        Button menuButton = gameOverStateUIFactory.createButton(menuButtonTexture, new Vector2(screenWidth / 2 - menuButtonTexture.width() - 100, scoreCanvas.getDestRect().y() + scoreCanvas.getDestRect().height()), 0f, 2.0f, WHITE, fxButton);
        Button okButton = gameOverStateUIFactory.createButton(okButtonTexture, new Vector2(screenWidth / 2 - okButtonTexture.width() + 100, scoreCanvas.getDestRect().y() + scoreCanvas.getDestRect().height()), 0f, 2.0f, WHITE, fxButton);

        // Main game loop
        while (!WindowShouldClose()) // Detect window close button or ESC key
        {
            // Update
            switch (gameState) {
                case MENU:
                    playButton.Update();
                    leaderboardButton.Update();
                    if (playButton.getAction() == true) {
                        gameState = GameStates.PLAYING;
                        playButton.setAction(false);
                    }
                    leaderboardButton.Update();
                    titleImage.Update();
                    tapImage.Update();
                    upArrowIcon.Update();
                    break;
                case PLAYING:
                    backgroundList.forEach(b -> {
                        b.Update();
                    });
                    floorList.forEach(f -> {
                        f.Update();
                    });
                    pipeBottomList.forEach(p -> {
                        p.Update();
                    });
                    pipeTopList.forEach(p -> {
                        p.Update();
                    });
                    player.Update();
                    for (GameObject p : pipeBottomList) {
                        if (player.getDestRect().x() + player.getDestRect().width() / 2 - 10 > p.getDestRect().x()
                                && player.getDestRect().x() + player.getDestRect().width() / 2 - 10 < p.getDestRect().x() + p.getDestRect().width()
                                && player.getDestRect().y() + player.getDestRect().height() / 2 - 10 > p.getDestRect().y()) {
                            gameState = GameStates.GAME_OVER;
                            PlaySound(fxGameOver);
                        }
                    }
                    for (GameObject p : pipeTopList) {
                        if (player.getDestRect().x() + player.getDestRect().width() / 2 - 10 > p.getDestRect().x()
                                && player.getDestRect().x() < p.getDestRect().x() + p.getDestRect().width()
                                && player.getDestRect().y() - player.getDestRect().height() / 2 + 5 < p.getDestRect().y() + p.getDestRect().height()) {
                            gameState = GameStates.GAME_OVER;
                            PlaySound(fxGameOver);
                        }
                    }
                    if (player.getDestRect().x() + player.getDestRect().width() / 2 > pipeBottomList.get(pipeCount).getDestRect().x() + pipeBottomList.get(pipeCount).getDestRect().width()) {
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

                    if (player.getDestRect().x() + player.getDestRect().width() / 2 - 10 > coin.getDestRect().x()
                            && player.getDestRect().x() < coin.getDestRect().x() + coin.getDestRect().width()
                            && player.getDestRect().y() - player.getDestRect().height() / 2 + 5 < coin.getDestRect().y() + coin.getDestRect().height()) {
                        PlaySound(fxCoin);
                    }

                    coin.Update();
                    inGameUIScore.Update();
                    break;
                case GAME_OVER:
                    gameOverImage.Update();
                    scoreCanvas.Update();
                    okButton.Update();
                    menuButton.Update();
                    if (menuButton.getAction() == true) {
                        player.getDestRect().x(100);
                        player.getDestRect().y(screenHeight / 2 - 100);

                        gameState = GameStates.MENU;
                        menuButton.setAction(false);

                        player = new Player(new Vector2(100, screenHeight / 2 - 100), 0f, 5, 0.1f, WHITE);
                        score = 0;

                        sourceRect = new Rectangle(0, 0, pipeBottomTexture.width(), pipeBottomTexture.height() / 2);
                        for (int i = 0; i < 5; i++) {
                            pipeBottomList.set(i, new Pipe(pipeBottomTexture, new Vector2((screenWidth / 5) * i, screenHeight - (pipeBottomTexture.height() / 2) - floorTexture.height()), 0f, 3.0f, 1.0f, WHITE, sourceRect));
                        }
                        for (int i = 0; i < 5; i++) {
                            pipeTopList.set(i, new Pipe(pipeTopTexture, new Vector2((screenWidth / 5) * i, -(pipeTopTexture.height() / 2)), 0f, 3.0f, 1.0f, WHITE));
                        }
                    }
                    if (okButton.getAction() == true) {
                        player.getDestRect().x(100);
                        player.getDestRect().y(screenHeight / 2 - 100);

                        gameState = GameStates.PLAYING;
                        okButton.setAction(false);

                        player = new Player(new Vector2(100, screenHeight / 2 - 100), 0f, 5, 0.1f, WHITE);
                        score = 0;
                        
                        sourceRect = new Rectangle(0, 0, pipeBottomTexture.width(), pipeBottomTexture.height() / 2);
                        for (int i = 0; i < 5; i++) {
                            pipeBottomList.set(i, new Pipe(pipeBottomTexture, new Vector2((screenWidth / 5) * i, screenHeight - (pipeBottomTexture.height() / 2) - floorTexture.height()), 0f, 3.0f, 1.0f, WHITE, sourceRect));
                        }
                        for (int i = 0; i < 5; i++) {
                            pipeTopList.set(i, new Pipe(pipeTopTexture, new Vector2((screenWidth / 5) * i, -(pipeTopTexture.height() / 2)), 0f, 3.0f, 1.0f, WHITE));
                        }
                    }
                    break;
                default:
                    break;
            }
            // Draw
            //----------------------------------------------------------------------------------
            BeginDrawing();

            ClearBackground(RAYWHITE);

            switch (gameState) {
                case MENU:
                    backgroundList.forEach(b -> {
                        b.Render();
                    });
                    floorList.forEach(f -> {
                        f.Render();
                    });
                    playButton.Render();
                    leaderboardButton.Render();
                    titleImage.Render();
                    tapImage.Render();
                    player.Render();
                    upArrowIcon.Render();
                    break;
                case PLAYING:
                    backgroundList.forEach(b -> {
                        b.Render();
                    });
                    floorList.forEach(f -> {
                        f.Render();
                    });
                    pipeBottomList.forEach(p -> {
                        p.Render();
                    });
                    pipeTopList.forEach(p -> {
                        p.Render();
                    });
                    coin.Render();
                    player.Render();
                    inGameUIScore.Render();
                    break;
                case GAME_OVER:
                    backgroundList.forEach(b -> {
                        b.Render();
                    });
                    floorList.forEach(f -> {
                        f.Render();
                    });
                    pipeBottomList.forEach(p -> {
                        p.Render();
                    });
                    pipeTopList.forEach(p -> {
                        p.Render();
                    });
                    player.Render();
                    gameOverImage.Render();
                    scoreCanvas.Render();
                    okButton.Render();
                    menuButton.Render();
                    break;
                default:
                    break;
            }
            EndDrawing();
            //----------------------------------------------------------------------------------
        }

        // De-Initialization
        backgroundList.forEach(b -> {
            b.Unload();
        });

        floorList.forEach(f -> {
            f.Unload();
        });

        pipeBottomList.forEach(p -> {
            p.Unload();
        });
        pipeTopList.forEach(p -> {
            p.Unload();
        });

        coin.Unload();
        player.Unload();
        playButton.Unload();
        leaderboardButton.Unload();
        titleImage.Unload();
        tapImage.Unload();
        upArrowIcon.Unload();
        inGameUIScore.Unload();
        gameOverImage.Unload();
        scoreCanvas.Unload();
        okButton.Unload();
        menuButton.Unload();
        //--------------------------------------------------------------------------------------
        CloseWindow();        // Close window and OpenGL context
        //--------------------------------------------------------------------------------------

    }
}
