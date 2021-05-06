/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.ui;

import static com.msoygen.flappybirdclone.App.screenWidth;
import com.raylib.Raylib;
import static extensions.JaylibX.*;
import gameobject.GameObject;
/**
 *
 * @author msoyg
 */
public interface GUIFactory {
    Button createButton(Texture2D texture, Vector2 position, float rotation, float scale, Color tint, Sound fx);
    Button createButton(Texture2D texture, Vector2 position, float rotation, float scale, Color tint, Sound fx, Rectangle sourceRect);
    
    UIImage createUIImage(Texture2D texture, Vector2 position, float rotation, float scale, Color tint);
}
