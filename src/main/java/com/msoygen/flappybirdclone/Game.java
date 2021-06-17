/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msoygen.flappybirdclone;

import static extensions.JaylibX.*;
import com.raylib.Raylib;
import gameobject.ConcreteGameObjectFactory;
import gameobject.GameObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author msoyg
 */
public class Game {

    public static final int screenWidth = 800;
    public static final int screenHeight = 600;

    public static int score = 0;

    private State state;

    private Texture2D backgroundTexture;
    private Texture2D floorTexture;

    private List<GameObject> backgroundList;
    private List<GameObject> floorList;

    private ConcreteGameObjectFactory gameObjectFactory;

    public Game() {
        gameObjectFactory = new ConcreteGameObjectFactory();

        backgroundList = new ArrayList<>();
        floorList = new ArrayList<>();

        this.state = new MenuState(this);
    }

    public void changeState(State state) {
        this.state.OnExit(state);
        this.state = state;
    }

    public void Start() {
        InitWindow(screenWidth, screenHeight, "flappy bird clone");
        InitAudioDevice();

        SetTargetFPS(60);

        backgroundTexture = LoadTexture("Resources/background.png");
        floorTexture = LoadTexture("Resources/floor.png");

        for (int i = 0; i < 4; i++) {
            backgroundList.add(gameObjectFactory.createBackground(backgroundTexture, new Vector2(i * backgroundTexture.width(), 0), 0f, 3.0f, 1.2f, WHITE));
        }

        for (int i = 0; i < 4; i++) {
            floorList.add(gameObjectFactory.createFloor(LoadTexture("Resources/floor.png"), new Vector2(floorTexture.width() * i, screenHeight - floorTexture.height()), 0f, 3.0f, 1.32f, WHITE));
        }

        this.state.Init();
        this.Update();
    }

    private void Update() {
        // Main game loop
        while (!WindowShouldClose()) // Detect window close button or ESC key
        {
            backgroundList.forEach(b -> {
                b.Update();
            });
            floorList.forEach(f -> {
                f.Update();
            });
            
            this.state.Update();

            BeginDrawing();

            ClearBackground(RAYWHITE);

            backgroundList.forEach(b -> {
                b.Render();
            });
            floorList.forEach(f -> {
                f.Render();
            });

            this.state.Render();

            EndDrawing();
        }

        this.Exit();
    }

    private void Exit() {
        backgroundList.forEach(b -> {
            b.Unload();
        });

        floorList.forEach(f -> {
            f.Unload();
        });

        CloseWindow();
    }
}
