import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by AliPC on 25-Dec-16.
 */
public class GameStatistics extends JPanel {
    private int maxScore = 0;
    private int currentScore = 0;
    private JLabel maxScoreText;
    private JLabel currentScoreText;
    private JPanel scorePanel;
    private JLabel nextBlockText;
    private GridLayout grid;

    public GameStatistics() {
        maxScoreText = new JLabel("Max Score: " + maxScore);
        currentScoreText = new JLabel("Current Score: " + currentScore);
        nextBlockText = new JLabel("Next block is: I type");

        grid = new GridLayout(4, 0, 10, 20);

        scorePanel = new JPanel();
        scorePanel.setLayout(grid);

        scorePanel.add(nextBlockText);
        scorePanel.add(maxScoreText);
        scorePanel.add(currentScoreText);

        add(scorePanel, BorderLayout.CENTER);
    }

    public void setCurrentScoreLabel(int score) {
        this.currentScore = score;
        if (maxScore < currentScore) {
            maxScore = currentScore;
            setMaxScoreLabel(maxScore);
        }
        currentScoreText.setText("Current Score: " + currentScore);
    }

    public void setMaxScoreLabel(int score) {
        this.maxScore = score;
        maxScoreText.setText("Max Score: " + maxScore);
    }

    public void drawNextBlock(int nextBlock) {
        switch(nextBlock) {
            case 0:
                nextBlockText.setText("Next block is: I Type");
                break;
            case 1:
                nextBlockText.setText("Next block is: L Type");
                break;
            case 2:
                nextBlockText.setText("Next block is: Z Type");
                break;
            case 3:
                nextBlockText.setText("Next block is: S Type");
                break;
            case 4:
                nextBlockText.setText("Next block is: O Type");
                break;
            case 5:
                nextBlockText.setText("Next block is: T Type");
                break;
            case 6:
                nextBlockText.setText("Next block is: J Type");
                break;
        }
    }
}
