package com.msoygen.flappybirdclone;

/**
 *
 * @author msoyg
 */
public abstract class State {
    Game game;
    
    State(Game game){
        this.game = game;
    }
    public abstract String Init();
    public abstract String Update();
    public abstract String Render();
    public abstract String OnExit(State state);
}
