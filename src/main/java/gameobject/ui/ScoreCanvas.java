/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.ui;

import com.msoygen.flappybirdclone.App;
import com.raylib.Raylib;
import static extensions.JaylibX.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author msoyg
 */
public class ScoreCanvas implements UIImage {

    private float scale;
    private float rotation;
    private Color tint;
    private Rectangle sourceRect;
    private Rectangle destRect;
    private Vector2 originalPosition;
    private int score;
    private int bestScore;

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

    Texture2D texture = LoadTexture("Resources/scoreCanvas.png");
    Texture2D bronzeMedalTexture = LoadTexture("Resources/bronzeMedal.png");
    Texture2D goldMedalTexture = LoadTexture("Resources/goldMedal.png");

    private Rectangle medalSourceRect;
    private Rectangle medalDestRect;

    private Rectangle scoreSourceRect;
    private Rectangle scoreDestRect;
    private Vector2 originalScorePosition;

    private Rectangle bestScoreSourceRect;
    private Rectangle bestScoreDestRect;
    private Vector2 originalBestScorePosition;

    public ScoreCanvas(Vector2 position, float rotation, float scale, Color tint, int score) {

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
        this.score = score;

        float width = texture.width();
        float height = texture.height();

        originalPosition = position;

        sourceRect = new Rectangle(0, 0, width, height);
        destRect = new Rectangle(position.x(), position.y(), width * scale, height * scale);

        medalSourceRect = new Rectangle(0, 0, bronzeMedalTexture.width(), bronzeMedalTexture.height());
        medalDestRect = new Rectangle(destRect.x() + 100, destRect.y() + 100, medalSourceRect.width() * scale, medalSourceRect.height() * scale);

        scoreSourceRect = new Rectangle(0, 0, scoreTextures.get(0).width(), scoreTextures.get(0).height());
        scoreDestRect = new Rectangle(destRect.x() + destRect.width() - 140, destRect.y() + 80, scoreSourceRect.width() * scale, scoreSourceRect.height() * scale);
        originalScorePosition = new Vector2(scoreDestRect.x(), scoreDestRect.y());

        bestScoreSourceRect = new Rectangle(0, 0, scoreTextures.get(0).width(), scoreTextures.get(0).height());
        bestScoreDestRect = new Rectangle(destRect.x() + destRect.width() - 130, destRect.y() + 160, scoreSourceRect.width() * scale, scoreSourceRect.height() * scale);
        originalBestScorePosition = new Vector2(bestScoreDestRect.x(), bestScoreDestRect.y());

        bestScore = 11; // TO-DO fetch from database
    }

    @Override
    public void Update() {
    }

    @Override
    public void Render() {
        int[] scoreIntTab = String.valueOf(score).chars().map(Character::getNumericValue).toArray();
        int[] bestScoreIntTab = String.valueOf(bestScore).chars().map(Character::getNumericValue).toArray();
        DrawTexturePro(texture, sourceRect, destRect, new Vector2(0, 0), rotation, tint);
        if (score >= bestScore) {
            DrawTexturePro(goldMedalTexture, medalSourceRect, medalDestRect, new Vector2(0, 0), rotation, tint);
        } else {
            DrawTexturePro(bronzeMedalTexture, medalSourceRect, medalDestRect, new Vector2(0, 0), rotation, tint);
        }

        for (int i = 0; i < scoreIntTab.length; i++) {
            DrawTexturePro(scoreTextures.get(scoreIntTab[i]), scoreSourceRect, scoreDestRect, new Vector2(0, 0), rotation, tint);
            scoreDestRect.x(scoreDestRect.x() + scoreTextures.get(scoreIntTab[i]).width() * scale);
        }
        scoreDestRect.x(originalScorePosition.x());
        scoreDestRect.y(originalScorePosition.y());

        for (int i = 0; i < bestScoreIntTab.length; i++) {
            DrawTexturePro(scoreTextures.get(bestScoreIntTab[i]), bestScoreSourceRect, bestScoreDestRect, new Vector2(0, 0), rotation, tint);
            bestScoreDestRect.x(bestScoreDestRect.x() + scoreTextures.get(bestScoreIntTab[i]).width() * scale);
        }
        bestScoreDestRect.x(originalBestScorePosition.x());
        bestScoreDestRect.y(originalBestScorePosition.y());
    }

    @Override
    public void Unload() {
        UnloadTexture(texture);
        UnloadTexture(bronzeMedalTexture);
        UnloadTexture(goldMedalTexture);
    }

    @Override
    public Rectangle getDestRect() {
        return destRect;
    }
}
