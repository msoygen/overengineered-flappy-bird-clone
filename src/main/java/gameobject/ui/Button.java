/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.ui;

import gameobject.GameObject;

import com.raylib.Raylib;
import static extensions.JaylibX.*;


/**
 *
 * @author msoyg
 */
public interface Button extends GameObject {
    public void ActionListener();
    
    public boolean getAction();
    public void setAction(boolean action);
    /**
     *
     * @return 0 - normal, 1 - mouse over, 2 - pressed
     */
    public int getState();
    public Rectangle getDestRect();
}
