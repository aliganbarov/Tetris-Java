package Controllers;

import GameModes.GameMode;
import General.GameSettings;
import Listeners.*;
import Views.Board;
import Views.GameModePanel;
import Views.GameStatisticsPanel;
import Views.MenuPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by AliPC on 30-Dec-16.
 */
public class Menu {
    JFrame frame = new JFrame("TETRIS");

    private MenuPanel menuPanel;

    private GameModePanel gameModePanel;

    private Board board;

    private GameMode gameMode;
    private JPanel gmHolder;

    private GameStatisticsPanel gameStatisticsPanel;
    private JPanel gspHolder;
    private JPanel gamePanel;

    public Menu() {
        frame.setLayout(new BorderLayout());

        menuPanel = new MenuPanel();

        frame.setFocusable(true);
        frame.requestFocusInWindow();

        gmHolder = new JPanel(new BorderLayout());
        gspHolder = new JPanel(new BorderLayout());
        gamePanel = new JPanel(new BorderLayout());


        //register NewGameListener to menuPanel
        menuPanel.setNewGameListener(new NewGameListener() {
            @Override
            public void newGameWindow() {
                ClearFrame();
                gameModePanel = new GameModePanel(frame);
                frame.add(gameModePanel, BorderLayout.CENTER);
                UpdateFrame();

                //register NormalModeListener to gameModePanel
                gameModePanel.setNormalModeListener(new NormalModeListener() {
                    @Override
                    public void newNormalGame() {
                        ClearFrame();
                        board = new Board('n');
                        frame.add(board, BorderLayout.CENTER);
                        UpdateFrame();
                        frame.addKeyListener(board.getPanel());

                        //register MenuBtnListener btn listener
                        board.setbMenuBtnListener(new MenuBtnListener() {
                            @Override
                            public void backToMenu() {
                                ClearFrame();
                                frame.add(menuPanel);
                                UpdateFrame();
                            }
                        });
                    }
                });

                //register CasualModeListener
                gameModePanel.setCasualModeListener(new CasualModeListener() {
                    @Override
                    public void newCasualMode() {
                        ClearFrame();
                        board = new Board('c');
                        frame.add(board, BorderLayout.CENTER);
                        UpdateFrame();
                        frame.addKeyListener(board.getPanel());

                        //register MenuBtnListener btn listener
                        board.setbMenuBtnListener(new MenuBtnListener() {
                            @Override
                            public void backToMenu() {
                                ClearFrame();
                                frame.add(menuPanel);
                                UpdateFrame();
                            }
                        });
                    }
                });



            }
        });

        frame.add(menuPanel, BorderLayout.CENTER);





        frame.setSize(GameSettings.MENU_WIDTH, GameSettings.MENU_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void ClearFrame() {
        frame.getContentPane().removeAll();
    }

    public void UpdateFrame() {
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        new Menu();
    }
}
