package GameModes;

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
        }
    }
}
