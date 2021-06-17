/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import static com.msoygen.flappybirdclone.Game.screenWidth;
import com.raylib.Raylib;
import static extensions.JaylibX.*;

/**
 *
 * @author msoyg
 */
public class Coin implements GameObject {

    private float scale;
    private float rotation;
    private Color tint;
    private Raylib.Texture2D texture;
    private Rectangle sourceRect;
    private Rectangle destRect;

    private float speed;
    private float animationSpeed;
    private int frameCounter;
    private int frameSpeed;

    public Coin(Vector2 position, float rotation, float speed, float scale, Color tint) {
        this.texture = LoadTexture("Resources/coin.png");;
        this.rotation = rotation;
        this.speed = speed;
        this.scale = scale;
        this.tint = tint;
        
        frameCounter = 0;
        frameSpeed = 1;
        animationSpeed = -speed / 2;

        float width = texture.width();
        float height = texture.height();

        sourceRect = new Rectangle(0, 0, width, height);
        destRect = new Rectangle(position.x(), position.y(), width * scale, height * scale);
    }
    
    @Override
    public void Update() {
        frameCounter++;
        if (frameCounter >= (60 / frameSpeed)) {
            frameCounter = 0;
            animationSpeed *= -1;
        }
        destRect.y(destRect.y() - animationSpeed);
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

    @Override
    public Rectangle getDestRect() {
        return destRect;
    }
}
