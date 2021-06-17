/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extensions;

import com.raylib.Raylib;
import static extensions.JaylibX.*;
import gameobject.Coin;
import gameobject.GameObject;
import gameobject.Pipe;
import gameobject.Player;
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
public class PlayerCollisionTest {

    public PlayerCollisionTest() {
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
     * Test of checkBottomPipeCollision method, of class PlayerCollision.
     */
    @Test
    public void testCheckBottomPipeCollision() {
        InitWindow(800, 600, "flappy bird clone unit test");

        SetTargetFPS(60);
        
        System.out.println("checkBottomPipeCollision");
        // collides
        GameObject player = new Player(new Vector2(10, 10), 0f, 1.0f, 1.0f, WHITE);
        GameObject pipe = new Pipe(LoadTexture("Resources/pipeBottom.png"), new Vector2(8, 8), 0f, 1.0f, 1.0f, WHITE);
        PlayerCollision instance = new PlayerCollision();
        boolean result = instance.checkBottomPipeCollision(player, pipe);

        // ahead
        pipe = new Pipe(LoadTexture("Resources/pipeBottom.png"), new Vector2(8, 8), 0f, 1.0f, 1.0f, WHITE);
        player = new Player(new Vector2(pipe.getDestRect().x() + pipe.getDestRect().width() + 50, 10), 0f, 1.0f, 1.0f, WHITE);
        result = instance.checkBottomPipeCollision(player, pipe);
        assertEquals(false, result);

        // behind
        player = new Player(new Vector2(200, 200), 0f, 1.0f, 1.0f, WHITE);
        pipe = new Pipe(LoadTexture("Resources/pipeBottom.png"), new Vector2(10, 10), 0f, 1.0f, 1.0f, WHITE);
        result = instance.checkBottomPipeCollision(player, pipe);
        assertEquals(false, result);

        player.Unload();
        pipe.Unload();
        CloseWindow();
    }

    /**
     * Test of checkTopPipeCollision method, of class PlayerCollision.
     */
    @Test
    public void testCheckTopPipeCollision() {
        InitWindow(800, 600, "flappy bird clone unit test");

        SetTargetFPS(60);
        
        System.out.println("checkTopPipeCollision");
        
        // collides
        GameObject player = new Player(new Vector2(10, 10), 0f, 1.0f, 1.0f, WHITE);
        GameObject pipe = new Pipe(LoadTexture("Resources/pipeTop.png"), new Vector2(8, 8), 0f, 1.0f, 1.0f, WHITE);
        PlayerCollision instance = new PlayerCollision();
        boolean result = instance.checkTopPipeCollision(player, pipe);
        assertEquals(true, result);
        
        // ahead
        pipe = new Pipe(LoadTexture("Resources/pipeTop.png"), new Vector2(8, 8), 0f, 1.0f, 1.0f, WHITE);
        player = new Player(new Vector2(pipe.getDestRect().x() + pipe.getDestRect().width() + 50, 10), 0f, 1.0f, 1.0f, WHITE);
        result = instance.checkTopPipeCollision(player, pipe);
        assertEquals(false, result);

        // behind
        player = new Player(new Vector2(200, 200), 0f, 1.0f, 1.0f, WHITE);
        pipe = new Pipe(LoadTexture("Resources/pipeTop.png"), new Vector2(10, 10), 0f, 1.0f, 1.0f, WHITE);
        result = instance.checkTopPipeCollision(player, pipe);
        assertEquals(false, result);

        player.Unload();
        pipe.Unload();
        CloseWindow();
    }

    /**
     * Test of checkPlayerPassedCurrentPipe method, of class PlayerCollision.
     */
    @Test
    public void testCheckPlayerPassedCurrentPipe() {
        InitWindow(800, 600, "flappy bird clone unit test");

        SetTargetFPS(60);
        
        System.out.println("checkPlayerPassedCurrentPipe");
        
        // passed
        GameObject pipe = new Pipe(LoadTexture("Resources/pipeTop.png"), new Vector2(8, 8), 0f, 1.0f, 1.0f, WHITE);
        GameObject player = new Player(new Vector2(pipe.getDestRect().x() + pipe.getDestRect().width() + 2, 10), 0f, 1.0f, 1.0f, WHITE);
        PlayerCollision instance = new PlayerCollision();
        boolean result = instance.checkPlayerPassedCurrentPipe(player, pipe);
        assertEquals(true, result);
        
        // far ahead
        pipe = new Pipe(LoadTexture("Resources/pipeTop.png"), new Vector2(8, 8), 0f, 1.0f, 1.0f, WHITE);
        player = new Player(new Vector2(pipe.getDestRect().x() + pipe.getDestRect().width() + player.getDestRect().width(), 200), 0f, 1.0f, 1.0f, WHITE);
        result = instance.checkPlayerPassedCurrentPipe(player, pipe);
        assertEquals(false, result);
        
        // far behind
        player = new Player(new Vector2(10, 10), 0f, 1.0f, 1.0f, WHITE);
        pipe = new Pipe(LoadTexture("Resources/pipeTop.png"), new Vector2(player.getDestRect().x() + player.getDestRect().width() + pipe.getDestRect().width(), 200), 0f, 1.0f, 1.0f, WHITE);
        result = instance.checkPlayerPassedCurrentPipe(player, pipe);
        assertEquals(false, result);

        player.Unload();
        pipe.Unload();
        CloseWindow();
    }

    /**
     * Test of checkPlayerCollidesCoin method, of class PlayerCollision.
     */
    @Test
    public void testCheckPlayerCollidesCoin() {
        InitWindow(800, 600, "flappy bird clone unit test");

        SetTargetFPS(60);
        
        System.out.println("checkPlayerCollidesCoin");
        
        // collides
        GameObject player = new Player(new Vector2(6, 6), 0f, 1.0f, 1.0f, WHITE);
        GameObject coin = new Coin(new Vector2(7, 7), 0f, 1.0f, 1.0f, WHITE);
        PlayerCollision instance = new PlayerCollision();
        boolean result = instance.checkPlayerCollidesCoin(player, coin);
        assertEquals(true, result);
        
        // ahead
        coin = new Coin(new Vector2(5, 5), 0f, 1.0f, 1.0f, WHITE);
        player = new Player(new Vector2(coin.getDestRect().x() + coin.getDestRect().width() + player.getDestRect().width() + 15, 100), 0f, 1.0f, 1.0f, WHITE);
        result = instance.checkPlayerCollidesCoin(player, coin);
        assertEquals(false, result);
        
        // behind
        player = new Player(new Vector2(5, 5), 0f, 1.0f, 1.0f, WHITE);
        coin = new Coin(new Vector2(player.getDestRect().x() + player.getDestRect().width() + coin.getDestRect().width() + 15, 100), 0f, 1.0f, 1.0f, WHITE);
        result = instance.checkPlayerCollidesCoin(player, coin);
        assertEquals(false, result);

        player.Unload();
        coin.Unload();
        CloseWindow();
    }

}
