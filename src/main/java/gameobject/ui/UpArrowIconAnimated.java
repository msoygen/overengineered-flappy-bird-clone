/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.ui;

import static com.msoygen.flappybirdclone.App.screenWidth;
import com.raylib.Raylib;
import static extensions.JaylibX.*;

/**
 *
 * @author msoyg
 */
public class UpArrowIconAnimated implements UIImage {

    public float scale;
    public float rotation;
    public Color tint;
    public Texture2D texture;
    public Rectangle sourceRect;
    public Rectangle destRect;
    float speed;
    int frameCounter;
    int frameSpeed;

    public UpArrowIconAnimated(Vector2 position, float rotation, float scale, Color tint) {
        this.texture = LoadTexture("Resources/upArrow.png");;
        this.rotation = rotation;
        this.scale = scale;
        this.tint = tint;;

        float width = texture.width();
        float height = texture.height();

        sourceRect = new Rectangle(0, 0, width, height);
        destRect = new Rectangle(position.x(), position.y(), width * scale, height * scale);

        speed = -1.0f;
        frameSpeed = 2;
    }

    @Override
    public void Update() {
        frameCounter++;
        if (frameCounter >= (60 / frameSpeed)) {
            frameCounter = 0;

            speed *= -1;
        }

        destRect.y(destRect.y() - speed);
    }

    @Override
    public void Render() {
        DrawTexturePro(texture, sourceRect, destRect, new Vector2(destRect.x() / 2, destRect.y() / 2), rotation, tint);
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
