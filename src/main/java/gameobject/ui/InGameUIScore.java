/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.ui;

import static com.msoygen.flappybirdclone.Game.score;
import com.raylib.Raylib;
import static extensions.JaylibX.*;
import java.util.ArrayList;
import java.util.List;
import gameobject.GameObject;

/**
 *
 * @author msoyg
 */
public class InGameUIScore implements UIImage {

    private float scale;
    private float rotation;
    private Color tint;
    private Rectangle sourceRect;
    private Rectangle destRect;
    private Vector2 originalPosition;

    Texture2D scoreZeroTexture = LoadTexture("Resources/0.png");
    Texture2D scoreOneTexture = LoadTexture("Resources/1.png");
    Texture2D scoreTwoTexture = LoadTexture("Resources/2.png");
    Texture2D scoreThreeTexture = LoadTexture("Resources/3.png");
    Texture2D scoreFourTexture = LoadTexture("Resources/4.png");
    Texture2D scoreFiveTexture = LoadTexture("Resources/5.png");
    Texture2D scoreSixTexture = LoadTexture("Resources/6.png");
    Texture2D scoreSevenTexture = LoadTexture("Resources/7.png");
    Texture2D scoreEightTexture = LoadTexture("Resources/8.png");
    Texture2D scoreNineTexture = LoadTexture("Resources/9.png");

    List<Texture2D> scoreTextures = new ArrayList<>();

    public InGameUIScore(Vector2 position, float rotation, float scale, Color tint) {

        scoreTextures.add(scoreZeroTexture);

        scoreTextures.add(scoreOneTexture);

        scoreTextures.add(scoreTwoTexture);

        scoreTextures.add(scoreThreeTexture);

        scoreTextures.add(scoreFourTexture);

        scoreTextures.add(scoreFiveTexture);

        scoreTextures.add(scoreSixTexture);

        scoreTextures.add(scoreSevenTexture);

        scoreTextures.add(scoreEightTexture);

        scoreTextures.add(scoreNineTexture);
        
        this.rotation = rotation;
        this.scale = scale;
        this.tint = tint;

        float width = scoreTextures.get(0).width();
        float height = scoreTextures.get(0).height();

        originalPosition = position;

        sourceRect = new Rectangle(0, 0, width, height);
        destRect = new Rectangle(position.x(), position.y(), width * scale, height * scale);
    }

    @Override
    public void Update() {
    }

    @Override
    public void Render() {
        if (score < 10) {
            DrawTexturePro(scoreTextures.get(0), sourceRect, destRect, new Vector2(0, 0), rotation, tint);
            destRect.x(destRect.x() + scoreTextures.get(0).width() * scale);
            DrawTexturePro(scoreTextures.get(score), sourceRect, destRect, new Vector2(0, 0), rotation, tint);
            destRect = new Rectangle(originalPosition.x(), originalPosition.y(), scoreTextures.get(score).width() * scale, scoreTextures.get(score).height() * scale);
        } else {
            int[] intTab = String.valueOf(score).chars().map(Character::getNumericValue).toArray();
            for (int i = 0; i < intTab.length; i++) {
                DrawTexturePro(scoreTextures.get(intTab[i]), sourceRect, destRect, new Vector2(0, 0), rotation, tint);
                destRect.x(destRect.x() + scoreTextures.get(intTab[i]).width() * scale);
            }
            destRect = new Rectangle(originalPosition.x(), originalPosition.y(), scoreTextures.get(0).width() * scale, scoreTextures.get(0).height() * scale);
        }
    }

    @Override
    public void Unload() {
        scoreTextures.forEach(s -> {
            UnloadTexture(s);
        });
    }

    @Override
    public Rectangle getDestRect() {
        return destRect;
    }
}
