import javafx.scene.Scene;
import javafx.scene.layout.HBox;

import javax.swing.*;
import java.awt.*;

/**
 * Created by AliPC on 18-Dec-16.
 */
public class Board extends JFrame {
    private char gameMode;
    private NormalMode gp;

    public Board(char gameMode) {
        setResizable(false);
        this.gameMode = gameMode;
        InitBoard();
    }

    public void InitBoard() {
        //Create instance of game
        if (gameMode == 'n') {
            gp = new NormalMode();
            //create holding panel for game
            JPanel gpHoldingPanel = new JPanel(new BorderLayout());
            gpHoldingPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
            gpHoldingPanel.add(gp, BorderLayout.CENTER);
            add(gpHoldingPanel, BorderLayout.WEST);
            gp.setPreferredSize(new Dimension(200, 400));
        }
        //add(gp);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
