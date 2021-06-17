/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import com.raylib.Raylib;
import static extensions.JaylibX.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author msoyg
 */
public class Floor implements GameObject {

    private float scale;
    private float rotation;
    private Color tint;
    private Texture2D texture;
    private Rectangle sourceRect;
    private Rectangle destRect;
    private float speed;
    private Vector2 originalPosition;

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

    @Override
    public Rectangle getDestRect() {
        return destRect;
    }
}
