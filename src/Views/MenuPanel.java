package Views;

import General.GameSettings;
import Listeners.HighScoresListener;
import Listeners.NewGameListener;

import javax.swing.*;
import java.awt.*;

/**
 * Created by AliPC on 30-Dec-16.
 */
public class MenuPanel extends JPanel{
    private JLabel title;
    private JButton newGameBtn;
    private JButton stasBtn;
    private JButton exitBtn;
    private JPanel btnHolder;

    private NewGameListener newGameListener;

    private HighScoresListener highScoresListener;

    public MenuPanel() {
        Dimension dim = getPreferredSize();
        dim.width = 200;
        dim.height = 300;
        setPreferredSize(dim);

        title = new JLabel("TETRIS");

        newGameBtn = new JButton("New Game");
        stasBtn = new JButton("Statistics");
        exitBtn = new JButton("Exit");
        btnHolder = new JPanel(new GridBagLayout());

        title.setFont(new Font("Helvetica", Font.BOLD, 50));
        newGameBtn.setPreferredSize(new Dimension(GameSettings.BTN_WIDTH, GameSettings.BTN_HEIGHT));
        stasBtn.setPreferredSize(new Dimension(GameSettings.BTN_WIDTH, GameSettings.BTN_HEIGHT));
        exitBtn.setPreferredSize(new Dimension(GameSettings.BTN_WIDTH, GameSettings.BTN_HEIGHT));

        newGameBtn.addActionListener(e -> {
            if (newGameListener != null) {
                newGameListener.newGameWindow();
            }
        });

        stasBtn.addActionListener( e -> {
            if (highScoresListener != null) {
                highScoresListener.highScoresWindow();
            }
        });

        exitBtn.addActionListener(e -> System.exit(0));

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        //Fill btnHolder
        gc.gridy = 0;
        gc.gridx = 0;
        gc.insets = new Insets(5, 5, 5, 5);
        btnHolder.add(newGameBtn, gc);

        gc.gridy = 1;
        btnHolder.add(stasBtn, gc);

        gc.gridy = 2;
        btnHolder.add(exitBtn, gc);

        //Fill MenuPanel
        gc.gridx = 0;
        gc.gridy = 0;
        add(title, gc);

        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridy = 1;
        add(btnHolder, gc);
    }

    public void setNewGameListener(NewGameListener listener) {
        this.newGameListener = listener;
    }

    public void setHighScoresListener(HighScoresListener highScoresListener) {
        this.highScoresListener = highScoresListener;
    }
}



















