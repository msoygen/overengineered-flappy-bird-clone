/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import static com.msoygen.flappybirdclone.App.screenWidth;
import com.raylib.Raylib;
import static extensions.JaylibX.*;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author msoyg
 */
public class Player implements GameObject {

    private float scale;
    private float rotation;
    private Color tint;
    private Texture2D texture;
    private Rectangle sourceRect;
    private Rectangle destRect;
    private float speed;
    private int frameCounter;
    private int frameSpeed;
    private int currentSprite;
    private List<Texture2D> spriteList;
    private boolean controllable;

    public Player(Vector2 position, float rotation, float speed, float scale, Color tint) {
        

        spriteList = new ArrayList<>();

        spriteList.add(LoadTexture("Resources/bird1.png"));
        spriteList.add(LoadTexture("Resources/bird2.png"));
        
        this.spriteList = spriteList;
        this.rotation = rotation;
        this.speed = speed;
        this.scale = scale;
        this.tint = tint;

        frameCounter = 0;
        this.frameSpeed = 2;
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

    @Override
    public Rectangle getDestRect() {
        return destRect;
    }
}