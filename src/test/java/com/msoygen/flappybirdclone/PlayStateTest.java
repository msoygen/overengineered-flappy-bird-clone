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
public class PlayStateTest {
    
    public PlayStateTest() {
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
     * Test of Init method, of class PlayState.
     */
    @Test
    public void testInit() {
        System.out.println("Init");

        InitWindow(screenWidth, screenHeight, "flappy bird clone");
        InitAudioDevice();

        SetTargetFPS(60);

        PlayState instance = new PlayState(new Game());
        
        String expResult = "play initialized";
        String result = instance.Init();

        assertEquals(expResult, result);
    }

    /**
     * Test of Update method, of class PlayState.
     */
    @Test
    public void testUpdate() {
        InitWindow(screenWidth, screenHeight, "flappy bird clone");
        InitAudioDevice();

        SetTargetFPS(60);

        System.out.println("Update");
        PlayState instance = new PlayState(new Game());
        
        instance.Init();
        
        String expResult = "play updated";
        String result = instance.Update();

        assertEquals(expResult, result);
    }

    /**
     * Test of Render method, of class PlayState.
     */
    @Test
    public void testRender() {
        System.out.println("Render");
        String expResult = "play rendered";
        String result;

        InitWindow(screenWidth, screenHeight, "flappy bird clone");
        InitAudioDevice();

        SetTargetFPS(60);

        PlayState instance = new PlayState(new Game());

        instance.Init();
        
        BeginDrawing();

        ClearBackground(RAYWHITE);

        result = instance.Render();
        assertEquals(expResult, result);

        EndDrawing();
    }
    
}
