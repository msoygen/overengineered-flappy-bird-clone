/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extensions;

import com.raylib.Raylib;

/**
 *
 * @author msoyg
 */
public class JayMath extends JaylibX{
    public static Vector2 Vector2Subtract(Raylib.Vector2 v1, Raylib.Vector2 v2){
        return new Vector2(v1.x() - v2.x(), v1.y() - v2.y());
    }
    
    public static float Vector2Length(Raylib.Vector2 v){
        return (float) Math.sqrt(Math.pow(v.x(), 2) + Math.pow(v.y(), 2));
    }
    
    public static Vector2 Vector2Add(Raylib.Vector2 v1, Raylib.Vector2 v2){
        return new Vector2(v1.x() + v2.x(), v1.y() + v2.y());
    }
    
    public static Vector2 Vector2Scale(Raylib.Vector2 v, float scale){
        return new Vector2(v.x() * scale, v.y() * scale);
    }
}
