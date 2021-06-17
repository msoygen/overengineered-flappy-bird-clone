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
public class Pipe implements GameObject {

    private float scale;
    private float rotation;
    private Color tint;
    private Raylib.Texture2D texture;
    private Rectangle sourceRect;
    private Rectangle destRect;
    private float speed;

    public Pipe(Raylib.Texture2D texture, Vector2 position, float rotation, float speed, float scale, Color tint) {
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

    public Pipe(Raylib.Texture2D texture, Vector2 position, float rotation, float speed, float scale, Color tint, Rectangle sourceRect) {
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

    @Override
    public Rectangle getDestRect() {
        return destRect;
    }
}

