package Views;

import Listeners.MenuBtnListener;

import javax.swing.*;
import java.awt.*;

/**
 * Created by AliPC on 25-Dec-16.
 */
public class GameStatisticsPanel extends JPanel {
    private int maxScore = 0;
    private int currentScore = 0;

    private JPanel scorePanel;
    private JLabel nextBlockLabel;
    private JLabel maxScoreLabel;
    private JLabel currentScoreLabel;

    private JPanel controlsPanel;
    private JLabel title;
    private JLabel qLabel;
    private JLabel eLabel;
    private JLabel aLabel;
    private JLabel sLabel;
    private JLabel dLabel;

    private JPanel btnPanel;
    private JButton backBtn;

    private MenuBtnListener menuBtnListener;

    public GameStatisticsPanel() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE;
        gc.weighty = 1;
        gc.weightx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 20, 0, 0);

        //set score panel
        maxScoreLabel = new JLabel("Max Score: " + maxScore);
        currentScoreLabel = new JLabel("Current Score: " + currentScore);
        nextBlockLabel = new JLabel("Next block type");

        scorePanel = new JPanel(new GridBagLayout());

        gc.gridx = 0;
        gc.gridy = 0;
        scorePanel.add(nextBlockLabel, gc);

        gc.gridy = 1;
        scorePanel.add(currentScoreLabel, gc);

        gc.gridy = 2;
        scorePanel.add(maxScoreLabel, gc);

        //set controls panel
        controlsPanel = new JPanel(new GridBagLayout());
        title = new JLabel("Controls:");
        qLabel = new JLabel("Q - rotate left");
        eLabel = new JLabel("E - rotate right");
        aLabel = new JLabel("A - move left");
        dLabel =new JLabel("D - move right");
        sLabel = new JLabel("S - accelerate down");

        gc.weighty = 1;
        gc.weightx = 1;


        gc.gridx = 0;
        gc.gridy = 0;
        controlsPanel.add(title, gc);

        gc.gridy = 1;
        gc.gridx = 0;
        controlsPanel.add(qLabel, gc);

        gc.gridy = 2;
        gc.gridx = 0;
        controlsPanel.add(eLabel, gc);

        gc.gridy = 3;
        gc.gridx = 0;
        controlsPanel.add(aLabel, gc);

        gc.gridy = 4;
        gc.gridx = 0;
        controlsPanel.add(dLabel, gc);

        gc.gridy = 5;
        gc.gridx = 0;
        controlsPanel.add(sLabel, gc);

        //set btn panel
        backBtn = new JButton("Menu");
        backBtn.addActionListener(e -> {
            if (menuBtnListener != null) {
                menuBtnListener.backToMenu();
            }
        });

        btnPanel = new JPanel(new GridBagLayout());
        gc.gridy = 0;
        btnPanel.add(backBtn, gc);


        //set whole panel
        setLayout(new GridBagLayout());

        gc.gridy = 0;
        add(scorePanel, gc);

        gc.gridy = 1;
        add(controlsPanel, gc);

        gc.gridy = 2;
        add(btnPanel, gc);
    }

    public void setCurrentScoreLabel(int score) {
        this.currentScore = score;
        if (maxScore < currentScore) {
            maxScore = currentScore;
            setMaxScoreLabel(maxScore);
        }
        currentScoreLabel.setText("Current Score: " + currentScore);
    }

    public void setMaxScoreLabel(int score) {
        this.maxScore = score;
        maxScoreLabel.setText("Max Score: " + maxScore);
    }

    public void drawNextBlock(int nextBlock) {
        switch(nextBlock) {
            case 0:
                nextBlockLabel.setText("Next block is: I Type");
                break;
            case 1:
                nextBlockLabel.setText("Next block is: L Type");
                break;
            case 2:
                nextBlockLabel.setText("Next block is: Z Type");
                break;
            case 3:
                nextBlockLabel.setText("Next block is: S Type");
                break;
            case 4:
                nextBlockLabel.setText("Next block is: O Type");
                break;
            case 5:
                nextBlockLabel.setText("Next block is: T Type");
                break;
            case 6:
                nextBlockLabel.setText("Next block is: J Type");
                break;
        }
    }

    public void setMenuBtnListener(MenuBtnListener menuBtnListener) {
        this.menuBtnListener = menuBtnListener;
    }
}
