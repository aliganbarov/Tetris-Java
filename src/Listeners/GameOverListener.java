package Listeners;

/**
 * Created by AliPC on 31-Dec-16.
 */

/*
    whenever there is a game over or game window is closed
    this listener activates to store current max score
 */
public interface GameOverListener {
    void saveMaxScore(char gameMode);
}
