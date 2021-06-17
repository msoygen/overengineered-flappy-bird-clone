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
public class UIButton implements Button {

    public float scale;
    public float rotation;
    public Color tint;
    public Texture2D texture;
    public Rectangle sourceRect;
    public Rectangle destRect;
    private int state = 0; // 0 - normal, 1 - mouse over, 2 - pressed
    private boolean action = false;
    private Sound fx;

    public UIButton(Texture2D texture, Vector2 position, float rotation, float scale, Color tint, Sound fx) {
        this.texture = texture;
        this.rotation = rotation;
        this.scale = scale;
        this.tint = tint;
        this.fx = fx;

        float width = texture.width();
        float height = texture.height();

        sourceRect = new Rectangle(0, 0, width, height);
        destRect = new Rectangle(position.x(), position.y(), width * scale, height * scale);
    }

    public UIButton(Texture2D texture, Vector2 position, float rotation, float scale, Color tint, Sound fx, Rectangle sourceRect) {
        this.texture = texture;
        this.rotation = rotation;
        this.scale = scale;
        this.tint = tint;
        this.fx = fx;

        this.sourceRect = sourceRect;
        destRect = new Rectangle(position.x(), position.y(), sourceRect.width() * scale, sourceRect.height() * scale);
    }

    @Override
    public void Update() {
        action = false; // Check button state
        ActionListener();
        if (action) {
            PlaySound(fx);
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
    public void ActionListener() {
        if (CheckCollisionPointRec(GetMousePosition(), destRect)) {
            if (IsMouseButtonDown(MOUSE_LEFT_BUTTON)) {
                state = 2;
                tint = BLACK;
            } else {
                state = 1;
                tint = GRAY;
            }

            if (IsMouseButtonReleased(MOUSE_LEFT_BUTTON)) {
                action = true;
            }
        } else {
            state = 0;
            tint = WHITE;
        }
    }

    @Override
    public boolean getAction() {
        return action;
    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public void setAction(boolean action) {
        this.action = action;
    }

    @Override
    public Rectangle getDestRect() {
        return destRect;
    }
}
