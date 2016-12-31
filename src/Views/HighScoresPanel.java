package Views;


import General.GameSettings;
import Listeners.HighScoresListener;
import Listeners.MenuBtnListener;
import Models.HighScores;

import javax.swing.*;
import java.awt.*;

/**
 * Created by AliPC on 31-Dec-16.
 */
public class HighScoresPanel extends JPanel {
    private JLabel title;
    private JLabel normalScoreLabel;
    private JLabel casualScoreLabel;
    private JLabel madnessScoreLabel;
    private JButton menuBtn;

    private JPanel holder;
    private JPanel scoresHolder;

    private HighScores highScores;

    private MenuBtnListener menuBtnListener;

    public HighScoresPanel() {
        highScores = new HighScores();

        title = new JLabel("Highest Scores");
        normalScoreLabel = new JLabel("Normal mode: " + highScores.getNormalScore());
        casualScoreLabel = new JLabel("Casual mode: " + highScores.getCasualScore());
        madnessScoreLabel = new JLabel("Madness mode: " + highScores.getMadnessScore());
        menuBtn = new JButton("Menu");
        holder = new JPanel(new GridBagLayout());
        scoresHolder = new JPanel(new GridBagLayout());

        title.setFont(new Font("Helvetica", Font.BOLD, 40));
        normalScoreLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        casualScoreLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        madnessScoreLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        menuBtn.setPreferredSize(new Dimension(GameSettings.BTN_WIDTH, GameSettings.BTN_HEIGHT));

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE;

        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;

        gc.gridy = 0;
        holder.add(title, gc);

        gc.anchor = GridBagConstraints.CENTER;

        gc.insets = new Insets(5, 5, 5, 5);

        gc.gridy = 1;
        scoresHolder.add(normalScoreLabel, gc);

        gc.gridy = 2;
        scoresHolder.add(casualScoreLabel, gc);

        gc.gridy = 3;
        scoresHolder.add(madnessScoreLabel, gc);

        gc.gridy = 4;
        scoresHolder.add(menuBtn, gc);

        gc.gridx = 0;
        gc.gridy = 0;
        add(holder, gc);

        gc.gridy = 1;
        add(scoresHolder, gc);

        menuBtn.addActionListener(e -> {
            if (menuBtnListener != null) {
                menuBtnListener.backToMenu();
            }
        });
    }

    public void setMenuBtnListener(MenuBtnListener menuBtnListener) {
        this.menuBtnListener = menuBtnListener;
    }

}
