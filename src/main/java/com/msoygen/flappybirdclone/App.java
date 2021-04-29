/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msoygen.flappybirdclone;

import static com.msoygen.flappybirdclone.App.screenWidth;
import com.raylib.Raylib;
import static extensions.JaylibX.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author msoyg
 */
public class App {

    enum GameStates {
        PLAYING,
        GAME_OVER
    }

    // Initialization
    //--------------------------------------------------------------------------------------
    static final int screenWidth = 800;
    static final int screenHeight = 600;

    public static void main(String args[]) {
        GameStates gameState = GameStates.PLAYING;

        InitWindow(screenWidth, screenHeight, "flappy bird clone");

        SetTargetFPS(60);               // Set our game to run at 60 frames-per-second
        //--------------------------------------------------------------------------------------

        Texture2D background = LoadTexture("Resources/background.png");
        Texture2D pipeTop = LoadTexture("Resources/pipeTop.png");
        Texture2D pipeBottom = LoadTexture("Resources/pipeBottom.png");
        float scrolling = 0.0f;
        float pipeScrolling = 0.0f;

        List<Pipe> pipeBottomList = new ArrayList<>();
        List<Pipe> pipeTopList = new ArrayList<>();
        List<Background> backgroundList = new ArrayList<>();

        List<Texture2D> playerSprites = new ArrayList<>();

        playerSprites.add(LoadTexture("Resources/bird1.png"));
        playerSprites.add(LoadTexture("Resources/bird2.png"));

        Player player = new Player(playerSprites, new Vector2(100, screenHeight / 2 - 200), 1.0f, 5, 2, 0.1f, WHITE);

        for (int i = 0; i < 5; i++) {
            pipeBottomList.add(new Pipe(pipeBottom, new Vector2((screenWidth / 5) * i, screenHeight - (pipeBottom.height() / 2)), 1.0f, 3.0f, 1.0f, WHITE));
        }
        for (int i = 0; i < 5; i++) {
            pipeTopList.add(new Pipe(pipeTop, new Vector2((screenWidth / 5) * i, -(pipeTop.height() / 2)), 1.0f, 3.0f, 1.0f, WHITE));
        }

        for (int i = 0; i < 4; i++) {
            backgroundList.add(new Background(background, new Vector2(i * background.width(), 0), 1.0f, 3.0f, 1.2f, WHITE));
        }

        // Main game loop
        while (!WindowShouldClose()) // Detect window close button or ESC key
        {
            // Update
            if (gameState.equals(GameStates.PLAYING)) {
                backgroundList.forEach(b -> {
                    b.Update();
                });

                pipeBottomList.forEach(p -> {
                    p.Update();
                });
                pipeTopList.forEach(p -> {
                    p.Update();
                });

                player.Update();

                for (Pipe p : pipeBottomList) {
                    if (player.destRect.x() + player.destRect.width() / 2 - 10 > p.destRect.x() 
                            && player.destRect.x() + player.destRect.width() / 2 - 10 < p.destRect.x() + p.destRect.width() 
                            && player.destRect.y() + player.destRect.height() / 2 - 10 > p.destRect.y()) {
                        gameState = GameStates.GAME_OVER;
                    }
                }

                for (Pipe p : pipeTopList) {
                    if (player.destRect.x() > p.destRect.x() 
                            && player.destRect.x() < p.destRect.x() + p.destRect.width() 
                            && player.destRect.y() < p.destRect.y() + p.destRect.height()) {
                        gameState = GameStates.GAME_OVER;
                    }
                }
            }
            // Draw
            //----------------------------------------------------------------------------------
            BeginDrawing();

            ClearBackground(RAYWHITE);

            backgroundList.forEach(b -> {
                b.Render();
            });

            pipeBottomList.forEach(p -> {
                p.Render();
            });
            pipeTopList.forEach(p -> {
                p.Render();
            });

            player.Render();

            EndDrawing();
            //----------------------------------------------------------------------------------
        }

        backgroundList.forEach(b -> {
            b.Unload();
        });

        pipeBottomList.forEach(p -> {
            p.Unload();
        });
        pipeTopList.forEach(p -> {
            p.Unload();
        });

        player.Unload();
        // De-Initialization
        //--------------------------------------------------------------------------------------
        CloseWindow();        // Close window and OpenGL context
        //--------------------------------------------------------------------------------------

    }
}

abstract class GameObject {

    float scale;
    float rotation;
    Color tint;
    Texture2D texture;
    Rectangle sourceRect;
    Rectangle destRect;

    public abstract void Update();

    public abstract void Render();

    public abstract void Unload();
}

class Pipe extends GameObject {

    float speed;

    public Pipe(Texture2D texture, Vector2 position, float rotation, float speed, float scale, Color tint) {
        this.texture = texture;
        this.rotation = rotation;
        this.speed = speed;
        this.scale = scale;
        this.tint = tint;

        float width = texture.width();
        float height = texture.height();

        sourceRect = new Rectangle(0, 0, width, height);
        destRect = new Rectangle(position.x(), position.y(), width * scale, height * scale);
    }

    @Override
    public void Update() {
        destRect.x(destRect.x() - speed);
        if (destRect.x() + texture.width() <= 0) {
            destRect.x(screenWidth);
        }
    }

    @Override
    public void Render() {
        DrawTexturePro(texture, sourceRect, destRect, new Vector2(0, 0), rotation, tint);
    }

    @Override
    public void Unload() {
        UnloadTexture(texture);
    }
}

class Background extends GameObject {

    float speed;
    Vector2 originalPosition;

    public Background(Texture2D texture, Vector2 position, float rotation, float speed, float scale, Color tint) {
        this.rotation = rotation;
        this.texture = texture;
        this.speed = speed;
        this.scale = scale;
        this.tint = tint;

        this.originalPosition = new Vector2(position.x(), position.y());

        float width = texture.width();
        float height = texture.height();

        sourceRect = new Rectangle(0, 0, width, height);
        destRect = new Rectangle(position.x(), position.y(), width * scale, height * scale);
    }

    @Override
    public void Update() {
        destRect.x(destRect.x() - speed);
        if (destRect.x() - originalPosition.x() <= -texture.width()) {
            destRect.x(originalPosition.x());
            destRect.y(originalPosition.y());
        }
    }

    @Override
    public void Render() {
        DrawTexturePro(texture, sourceRect, destRect, new Vector2(0, 0), rotation, tint);
    }

    @Override
    public void Unload() {
        UnloadTexture(texture);
    }
}

class Player extends GameObject {

    float speed;
    int frameCounter;
    int frameSpeed;
    int currentSprite;
    List<Texture2D> spriteList;
    Rectangle sourceRect;
    Rectangle destRect;
    boolean controllable;

    public Player(List<Texture2D> spriteList, Vector2 position, float rotation, float speed, int frameSpeed, float scale, Color tint) {
        this.spriteList = spriteList;
        this.rotation = rotation;
        this.speed = speed;
        this.scale = scale;
        this.tint = tint;

        frameCounter = 0;
        this.frameSpeed = frameSpeed;
        currentSprite = 0;

        float width = spriteList.get(currentSprite).width();
        float height = spriteList.get(currentSprite).height();

        sourceRect = new Rectangle(0, 0, width, height);
        destRect = new Rectangle(position.x(), position.y(), width * scale, height * scale);

        controllable = true;
    }

    @Override
    public void Update() {
        if (controllable) {
            frameCounter++;
            if (frameCounter >= (60 / frameSpeed)) {
                frameCounter = 0;
                currentSprite++;

                if (currentSprite == spriteList.size()) {
                    currentSprite = 0;
                }
            }
            if (IsKeyDown(KEY_SPACE)) {
                destRect.y(destRect.y() - speed);

                rotation = 330.0f;
            } else {
                destRect.y(destRect.y() + speed);

                rotation = 30.0f;
            }
        }
    }

    @Override
    public void Render() {
        DrawTexturePro(spriteList.get(currentSprite), sourceRect, destRect, new Vector2(destRect.width() / 2, destRect.height() / 2), rotation, tint);
    }

    @Override
    public void Unload() {
        spriteList.forEach(t -> {
            UnloadTexture(t);
        });
    }
}
