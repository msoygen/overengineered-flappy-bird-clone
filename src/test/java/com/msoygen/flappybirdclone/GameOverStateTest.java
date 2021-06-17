/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msoygen.flappybirdclone;

import static extensions.JaylibX.*;
import static com.msoygen.flappybirdclone.Game.screenHeight;
import static com.msoygen.flappybirdclone.Game.screenWidth;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author msoyg
 */
public class GameOverStateTest {
    
    public GameOverStateTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of Init method, of class GameOverState.
     */
    @Test
    public void testInit() {
        System.out.println("Init");

        InitWindow(screenWidth, screenHeight, "flappy bird clone");
        InitAudioDevice();

        SetTargetFPS(60);

        GameOverState instance = new GameOverState(new Game());
        
        String expResult = "game over initialized";
        String result = instance.Init();

        assertEquals(expResult, result);
    }

    /**
     * Test of Update method, of class GameOverState.
     */
    @Test
    public void testUpdate() {
        InitWindow(screenWidth, screenHeight, "flappy bird clone");
        InitAudioDevice();

        SetTargetFPS(60);

        System.out.println("Update");
        GameOverState instance = new GameOverState(new Game());
        
        instance.Init();
        
        String expResult = "game over updated";
        String result = instance.Update();

        assertEquals(expResult, result);
    }

    /**
     * Test of Render method, of class GameOverState.
     */
    @Test
    public void testRender() {
        System.out.println("Render");
        String expResult = "game over rendered";
        String result;

        InitWindow(screenWidth, screenHeight, "flappy bird clone");
        InitAudioDevice();

        SetTargetFPS(60);

        GameOverState instance = new GameOverState(new Game());

        instance.Init();
        
        BeginDrawing();

        ClearBackground(RAYWHITE);

        result = instance.Render();
        assertEquals(expResult, result);

        EndDrawing();
    }

}
