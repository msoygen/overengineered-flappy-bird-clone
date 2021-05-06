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
public class GameOverStateUIFactory implements GUIFactory{

    @Override
    public Button createButton(Texture2D texture, Vector2 position, float rotation, float scale, Color tint, Sound fx) {
        return new UIButton(texture, position, rotation, scale, tint, fx);
    }

    @Override
    public Button createButton(Texture2D texture, Vector2 position, float rotation, float scale, Color tint, Sound fx, Rectangle sourceRect) {
        return new UIButton(texture, position, rotation, scale, tint, fx, sourceRect);
    }

    @Override
    public UIImage createUIImage(Texture2D texture, Vector2 position, float rotation, float scale, Color tint) {
        return new MenuUIImage(texture, position, rotation, scale, tint);
    }
    
    public UIImage createScoreCanvas(Vector2 position, float rotation, float scale, Color tint, int score){
        return new ScoreCanvas(position, rotation, scale, tint, score);
    }
}
