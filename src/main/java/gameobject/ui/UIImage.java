/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.ui;


import com.raylib.Raylib;
import static extensions.JaylibX.*;
import gameobject.GameObject;

/**
 *
 * @author msoyg
 */

public interface UIImage extends GameObject{
    public Rectangle getDestRect();
}

