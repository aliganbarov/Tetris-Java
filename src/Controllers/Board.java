package Controllers;

import GameModes.*;
import Listeners.FullRowListener;
import Listeners.GameOverListener;
import Listeners.MenuBtnListener;
import Listeners.NextBlockListener;
import Models.FileManager;
import Views.GameStatisticsPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by AliPC on 18-Dec-16.
 */
public class Board extends JPanel{
    private char gameMode = 'e';
    private GameMode gp;
    private GameStatisticsPanel gs;

    private FileManager fileManager;

    private MenuBtnListener bMenuBtnListener;

    public Board(char gameMode) {
        //setResizable(false);
        this.gameMode = gameMode;
        InitBoard();
    }

    public void InitBoard() {
        fileManager = new FileManager();

        switch (gameMode) {
            case 'n':
                gs = new GameStatisticsPanel(gameMode, fileManager.getNormalScore());
                break;
            case 'c':
                gs = new GameStatisticsPanel(gameMode, fileManager.getCasualScore());
                break;
            case 'm':
                gs = new GameStatisticsPanel(gameMode, fileManager.getMadnessScore());
                break;
        }

        //Create instance of game
        if (gameMode != 'e') {
            if (gameMode == 'n') {
                gp = new NormalGame();
            }
            else if (gameMode == 'c') {
                gp = new CasualGame();
            }

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
            gp.setGameOverListener(new GameOverListener() {
                @Override
                public void saveMaxScore(char gameMode) {
                    int maxScore = gs.getMaxScore();
                    fileManager.newRecord(maxScore, gameMode);
                }
            });

        }

        //gs = new GameStatisticsPanel();
        gs.setMenuBtnListener(new MenuBtnListener() {
            @Override
            public void backToMenu() {
                if (bMenuBtnListener != null) {
                    bMenuBtnListener.backToMenu();
                }
            }
        });
        gs.setGameOverListener(new GameOverListener() {
            @Override
            public void saveMaxScore(char gameMode) {
                int maxScore = gs.getMaxScore();
                fileManager.newRecord(maxScore, gameMode);
            }
        });

        JPanel gsHoldingPanel = new JPanel(new BorderLayout());
        gsHoldingPanel.add(gs, BorderLayout.CENTER);

        //add(new JLabel("Some label"), BorderLayout.EAST);
        add(gsHoldingPanel, BorderLayout.EAST);
        gs.setPreferredSize(new Dimension(200, 400));

        //setSize(General.GameSettings.MENU_WIDTH, General.GameSettings.MENU_HEIGHT);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //pack();
        //setVisible(true);
        //setLocationRelativeTo(null);
    }

    public GameMode getPanel() {
        return gp;
    }

    public void setbMenuBtnListener(MenuBtnListener listener) {
        this.bMenuBtnListener = listener;
    }
}
