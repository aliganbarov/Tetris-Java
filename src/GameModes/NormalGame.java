package GameModes;

import Listeners.GameOverListener;

/**
 * Created by AliPC on 18-Dec-16.
 */
public class NormalGame extends GameMode {

    public void checkForGameOver() {
        //if new created block overlaps, then game over, clear board
        if (checkOverlap()) {
            gameOver = true;
            clearBoard();
            t.stop();           //stop timer

            if (gameOverListener != null) {
                gameOverListener.saveMaxScore('n');
            }
        }
    }

    /*
        since game over occurs only in this mode
        register game over listener here
     */
    public void setGameOverListener(GameOverListener gameOverListener) {
        this.gameOverListener = gameOverListener;
    }
}
