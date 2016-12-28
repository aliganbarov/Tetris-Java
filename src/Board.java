import javafx.scene.Scene;
import javafx.scene.layout.HBox;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Set;

/**
 * Created by AliPC on 18-Dec-16.
 */
public class Board extends JFrame {
    private char gameMode;
    private NormalMode gp;
    private GameStatistics gs;

    public Board(char gameMode) {
        setResizable(false);
        this.gameMode = gameMode;
        InitBoard();
    }

    public void InitBoard() {
        gs = new GameStatistics();

        //Create instance of game
        if (gameMode == 'n') {
            gp = new NormalMode();
            //create holding panel for game
            JPanel gpHoldingPanel = new JPanel(new BorderLayout());
            gpHoldingPanel.add(gp, BorderLayout.CENTER);
            add(gpHoldingPanel, BorderLayout.WEST);
            gp.setPreferredSize(new Dimension(200, 400));
            gp.setFullRowListener(new FullRowListener() {
                @Override
                public void currentScore(int score) {
                    gs.setCurrentScoreLabel(score);
                }
            });
            gp.setNextBlockListener(new NextBlockListener() {
                @Override
                public void nextBlock(int nextBlock) {
                    gs.drawNextBlock(nextBlock);
                }
            });
        }

        gs = new GameStatistics();

        JPanel gsHoldingPanel = new JPanel(new BorderLayout());
        gsHoldingPanel.add(gs, BorderLayout.CENTER);

        //add(new JLabel("Some label"), BorderLayout.EAST);
        add(gsHoldingPanel, BorderLayout.EAST);
        gs.setPreferredSize(new Dimension(200, 400));

        setSize(Settings.MENU_WIDTH + 10, Settings.MENU_HEIGHT + 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
