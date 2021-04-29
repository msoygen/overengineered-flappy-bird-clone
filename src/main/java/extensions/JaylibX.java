/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extensions;

import com.raylib.Raylib;

public class JaylibX extends Raylib {
    
    public static Color LIGHTGRAY = new Color(200, 200, 200, 255);
    public static Color GRAY = new Color(130, 130, 130, 255);
    public static Color DARKGRAY = new Color(80, 80, 80, 255);
    public static Color YELLOW = new Color(253, 249, 0, 255);
    public static Color GOLD = new Color(255, 203, 0, 255);
    public static Color ORANGE = new Color(255, 161, 0, 255);
    public static Color PINK = new Color(255, 109, 194, 255);
    public static Color RED = new Color(230, 41, 55, 255);
    public static Color MAROON = new Color(190, 33, 55, 255);
    public static Color GREEN = new Color(0, 228, 48, 255);
    public static Color LIME = new Color(0, 158, 47, 255);
    public static Color DARKGREEN = new Color(0, 117, 44, 255);
    public static Color SKYBLUE = new Color(102, 191, 255, 255);
    public static Color BLUE = new Color(0, 121, 241, 255);
    public static Color DARKBLUE = new Color(0, 82, 172, 255);
    public static Color PURPLE = new Color(200, 122, 255, 255);
    public static Color VIOLET = new Color(135, 60, 190, 255);
    public static Color DARKPURPLE = new Color(112, 31, 126, 255);
    public static Color BEIGE = new Color(211, 176, 131, 255);
    public static Color BROWN = new Color(127, 106, 79, 255);
    public static Color DARKBROWN = new Color(76, 63, 47, 255);
    public static Color WHITE = new Color(255, 255, 255, 255);
    public static Color BLACK = new Color(0, 0, 0, 255);
    public static Color BLANK = new Color(0, 0, 0, 0);
    public static Color MAGENTA = new Color(255, 0, 255, 255);
    public static Color RAYWHITE = new Color(245, 245, 245, 255);

    public static class Color extends Raylib.Color {

        public Color(int r, int g, int b, int a){
            super();
            r((byte) r);
            g((byte) g);
            b((byte) b);
            a((byte) a);
        }
    }

    public static class Camera extends Raylib.Camera3D {

        public Camera(Raylib.Vector3 position, Raylib.Vector3 target, Raylib.Vector3 up, float fovy, int type) {
            super();
            _position(position);
            target(target);
            up(up);
            fovy(fovy);
            type(type);
        }
    }

    public static class Camera2D extends Raylib.Camera2D {

        public Camera2D(Raylib.Vector2 target, Raylib.Vector2 offset, float rotation, float zoom) {
            super();
            target(target);
            offset(offset);
            rotation(rotation);
            zoom(zoom);
        }
    }

    public static class Rectangle extends Raylib.Rectangle {

        public Rectangle() {
            super();
        }

        public Rectangle(float x, float y, float width, float height) {
            super();
            x(x);
            y(y);
            width(width);
            height(height);
        }

        public Rectangle(Raylib.Rectangle r) {
            this(r.x(), r.y(), r.width(), r.height());
        }
    }

    public static class BoundingBox extends Raylib.BoundingBox {

        public BoundingBox() {
            super();
        }

        public BoundingBox(Raylib.Vector3 min, Raylib.Vector3 max) {
            super();
            min(min);
            max(max);
        }

        public BoundingBox(Raylib.BoundingBox b) {
            this(b.min(), b.max());
        }
    }

    public static class Ray extends Raylib.Ray {

        public Ray() {
            super();
        }

        public Ray(Raylib.Vector3 position, Raylib.Vector3 direction) {
            super();
            _position(position);
            direction(direction);
        }

        public Ray(Raylib.Ray r) {
            this(r._position(), r.direction());
        }
    }

    public static class RayHitInfo extends Raylib.RayHitInfo {

        public RayHitInfo() {
            super();
        }

        public RayHitInfo(boolean hit, float distance, Raylib.Vector3 position, Raylib.Vector3 normal) {
            super();
            hit(hit);
            distance(distance);
            _position(position);
            normal(normal);
        }

        public RayHitInfo(Raylib.RayHitInfo rhi) {
            this(rhi.hit(), rhi.distance(), rhi._position(), rhi.normal());
        }
    }

    public static class Vector3 extends Raylib.Vector3 {

        public Vector3() {
            super();
        }

        public Vector3(float x, float y, float z) {
            super();
            x(x);
            y(y);
            z(z);
        }

        public Vector3(Raylib.Vector3 v) {
            this(v.x(), v.y(), v.z());
        }
    }

    public static class Vector2 extends Raylib.Vector2 {

        public Vector2() {
            super();
        }

        public Vector2(float x, float y) {
            super();
            x(x);
            y(y);
        }

        public Vector2(Raylib.Vector3 v) {
            this(v.x(), v.y());
        }
    }
    
}
