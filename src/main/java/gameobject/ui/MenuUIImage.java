/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.ui;

import com.raylib.Raylib;
import static extensions.JaylibX.*;

/**
 *
 * @author msoyg
 */
public class MenuUIImage implements UIImage {

    public float scale;
    public float rotation;
    public Color tint;
    public Texture2D texture;
    public Rectangle sourceRect;
    public Rectangle destRect;

    public MenuUIImage(Texture2D texture, Vector2 position, float rotation, float scale, Color tint) {
        this.texture = texture;
        this.rotation = rotation;
        this.scale = scale;
        this.tint = tint;;

        float width = texture.width();
        float height = texture.height();

        sourceRect = new Rectangle(0, 0, width, height);
        destRect = new Rectangle(position.x(), position.y(), width * scale, height * scale);
    }
    
    @Override
    public void Update() {}
    
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

