package GameModes;

/**
 * Created by AliPC on 30-Dec-16.
 */
public class CasualGame extends GameMode {

    public void checkForGameOver() {
        if (checkOverlap()) {
            //remove new block
            currentScore -= 13;
            removeRow(0);
            removeRow(1);

            //remove 11 lines from bottom
            for (int i = 0; i <= 10; i++) {
                removeRow(20 - i);
            }

        }
    }
}
