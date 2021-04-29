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
        MENU,
        PLAYING,
        GAME_OVER
    }

    // Initialization
    //--------------------------------------------------------------------------------------
    static final int screenWidth = 800;
    static final int screenHeight = 600;

    public static void main(String args[]) {
        GameStates gameState = GameStates.MENU;

        InitWindow(screenWidth, screenHeight, "flappy bird clone");
        InitAudioDevice();

        SetTargetFPS(60);               // Set our game to run at 60 frames-per-second
        //--------------------------------------------------------------------------------------

        Sound fxButton = LoadSound("Resources/buttonClick.wav");
        Texture2D startButtonTexture = LoadTexture("Resources/startButton.png");
        Texture2D backgroundTexture = LoadTexture("Resources/background.png");
        Texture2D pipeTopTexture = LoadTexture("Resources/pipeTop.png");
        Texture2D pipeBottomTexture = LoadTexture("Resources/pipeBottom.png");
        Texture2D floorTexture = LoadTexture("Resources/floor.png");

        List<Pipe> pipeBottomList = new ArrayList<>();
        List<Pipe> pipeTopList = new ArrayList<>();
        List<Background> backgroundList = new ArrayList<>();
        List<Floor> floorList = new ArrayList<>();

        List<Texture2D> playerSprites = new ArrayList<>();

        playerSprites.add(LoadTexture("Resources/bird1.png"));
        playerSprites.add(LoadTexture("Resources/bird2.png"));

        Player player = new Player(playerSprites, new Vector2(100, screenHeight / 2 - 200), 0f, 5, 2, 0.1f, WHITE);

        Rectangle sourceRect = new Rectangle(0, 0, pipeBottomTexture.width(), pipeBottomTexture.height() / 2);
        for (int i = 0; i < 5; i++) {
            pipeBottomList.add(new Pipe(pipeBottomTexture, new Vector2((screenWidth / 5) * i, screenHeight - (pipeBottomTexture.height() / 2) - floorTexture.height()), 0f, 3.0f, 1.0f, WHITE, sourceRect));
        }
        for (int i = 0; i < 5; i++) {
            pipeTopList.add(new Pipe(pipeTopTexture, new Vector2((screenWidth / 5) * i, -(pipeTopTexture.height() / 2)), 0f, 3.0f, 1.0f, WHITE));
        }

        for (int i = 0; i < 4; i++) {
            backgroundList.add(new Background(backgroundTexture, new Vector2(i * backgroundTexture.width(), 0), 0f, 3.0f, 1.2f, WHITE));
        }

        for (int i = 0; i < 4; i++) {
            floorList.add(new Floor(LoadTexture("Resources/floor.png"), new Vector2(floorTexture.width() * i - 2, screenHeight - floorTexture.height()), 0f, 3.0f, 1.0f, WHITE));
        }

        Button playButton = new Button(startButtonTexture, new Vector2(screenWidth / 2 - startButtonTexture.width() / 2, screenHeight / 2 - startButtonTexture.height() / 2), 0f, 1.0f, WHITE, fxButton);

        // Main game loop
        while (!WindowShouldClose()) // Detect window close button or ESC key
        {
            // Update
            switch (gameState) {
                case MENU:
                    playButton.Update();
                    if (playButton.action == true) {
                        gameState = GameStates.PLAYING;
                        playButton.action = false;
                    }
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
                    for (Pipe p : pipeBottomList) {
                        if (player.destRect.x() + player.destRect.width() / 2 - 10 > p.destRect.x()
                                && player.destRect.x() + player.destRect.width() / 2 - 10 < p.destRect.x() + p.destRect.width()
                                && player.destRect.y() + player.destRect.height() / 2 - 10 > p.destRect.y()) {
                            gameState = GameStates.GAME_OVER;
                        }
                    }
                    for (Pipe p : pipeTopList) {
                        if (player.destRect.x() + player.destRect.width() / 2 - 10 > p.destRect.x()
                                && player.destRect.x() < p.destRect.x() + p.destRect.width()
                                && player.destRect.y() - player.destRect.height() / 2 + 5 < p.destRect.y() + p.destRect.height()) {
                            gameState = GameStates.GAME_OVER;
                        }
                    }
                    break;
                case GAME_OVER:
                    playButton.Update();
                    if (playButton.action == true) {
                        player.destRect.x(100);
                        player.destRect.y(screenHeight / 2 - 100);

                        gameState = GameStates.MENU;
                        playButton.action = false;
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
                    player.Render();
                    break;
                case GAME_OVER:
                    backgroundList.forEach(b -> {
                        b.Render();
                    });
                    floorList.forEach(f -> {
                        f.Render();
                    });
                    playButton.Render();
                    pipeBottomList.forEach(p -> {
                        p.Render();
                    });
                    pipeTopList.forEach(p -> {
                        p.Render();
                    });
                    player.Render();
                    break;
                default:
                    break;
            }
            EndDrawing();
            //----------------------------------------------------------------------------------
        }

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

        player.Unload();

        playButton.Unload();
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

    public Pipe(Texture2D texture, Vector2 position, float rotation, float speed, float scale, Color tint, Rectangle sourceRect) {
        this.texture = texture;
        this.rotation = rotation;
        this.speed = speed;
        this.scale = scale;
        this.tint = tint;

        this.sourceRect = sourceRect;
        destRect = new Rectangle(position.x(), position.y(), sourceRect.width() * scale, sourceRect.height() * scale);
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

class Floor extends GameObject {

    float speed;
    Vector2 originalPosition;

    public Floor(Texture2D texture, Vector2 position, float rotation, float speed, float scale, Color tint) {
        this.texture = texture;
        this.rotation = rotation;
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

class Button extends GameObject {

    int state = 0; // 0 - normal, 1 - mouse over, 2 - pressed
    boolean action = false;
    Sound fx;

    public Button(Texture2D texture, Vector2 position, float rotation, float scale, Color tint, Sound fx) {
        this.texture = texture;
        this.rotation = rotation;
        this.scale = scale;
        this.tint = tint;
        this.fx = fx;

        float width = texture.width();
        float height = texture.height();

        sourceRect = new Rectangle(0, 0, width, height);
        destRect = new Rectangle(position.x(), position.y(), width * scale, height * scale);
    }

    public Button(Texture2D texture, Vector2 position, float rotation, float scale, Color tint, Sound fx, Rectangle sourceRect) {
        this.texture = texture;
        this.rotation = rotation;
        this.scale = scale;
        this.tint = tint;
        this.fx = fx;

        this.sourceRect = sourceRect;
        destRect = new Rectangle(position.x(), position.y(), sourceRect.width() * scale, sourceRect.height() * scale);
    }

    @Override
    public void Update() {
        action = false; // Check button state
        if (CheckCollisionPointRec(GetMousePosition(), destRect)) {
            System.out.println("collided");
            if (IsMouseButtonDown(MOUSE_LEFT_BUTTON)) {
                state = 2;
                tint = BLACK;
            } else {
                state = 1;
                tint = GRAY;
            }

            if (IsMouseButtonReleased(MOUSE_LEFT_BUTTON)) {
                action = true;
            }
        } else {
            state = 0;
            tint = WHITE;
        }

        if (action) {
            PlaySound(fx);
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
