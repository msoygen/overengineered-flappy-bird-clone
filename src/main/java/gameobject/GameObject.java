/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import com.raylib.Raylib;
import static extensions.JaylibX.*;
/**
 *
 * @author msoyg
 */
public interface GameObject {

    public void Update();

    public void Render();

    public void Unload();
    
    public Rectangle getDestRect();
}
