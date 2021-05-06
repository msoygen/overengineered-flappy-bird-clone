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
public class ConcreteGameObjectFactory implements GameObjectFactory {

    @Override
    public GameObject createBackground(Texture2D texture, Vector2 position, float rotation, float speed, float scale, Color tint) {
        return new Background(texture, position, rotation, speed, scale, tint);
    }

    @Override
    public GameObject createCoin(Vector2 position, float rotation, float speed, float scale, Color tint) {
        return new Coin(position, rotation, speed, scale, tint);
    }

    @Override
    public GameObject createFloor(Raylib.Texture2D texture, Vector2 position, float rotation, float speed, float scale, Color tint) {
        return new Floor(texture, position, rotation, speed, scale, tint);
    }

    @Override
    public GameObject createPipe(Raylib.Texture2D texture, Vector2 position, float rotation, float speed, float scale, Color tint) {
        return new Pipe(texture, position, rotation, speed, scale, tint);
    }

    @Override
    public GameObject createPipe(Raylib.Texture2D texture, Vector2 position, float rotation, float speed, float scale, Color tint, Rectangle sourceRect) {
        return new Pipe(texture, position, rotation, speed, scale, tint, sourceRect);
    }

    @Override
    public GameObject createPlayer(Vector2 position, float rotation, float speed, float scale, Color tint) {
        return new Player(position, rotation, speed, scale, tint);
    }
    
}
