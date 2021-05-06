/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import com.raylib.Raylib;
import static extensions.JaylibX.*;
import java.util.List;
/**
 *
 * @author msoyg
 */
public interface GameObjectFactory {
    public GameObject createBackground(Texture2D texture, Vector2 position, float rotation, float speed, float scale, Color tint);
    public GameObject createCoin(Vector2 position, float rotation, float speed, float scale, Color tint);
    public GameObject createFloor(Texture2D texture, Vector2 position, float rotation, float speed, float scale, Color tint);
    public GameObject createPipe(Texture2D texture, Vector2 position, float rotation, float speed, float scale, Color tint);
    public GameObject createPipe(Texture2D texture, Vector2 position, float rotation, float speed, float scale, Color tint, Rectangle sourceRect);
    public GameObject createPlayer(Vector2 position, float rotation, float speed, float scale, Color tint);
}
