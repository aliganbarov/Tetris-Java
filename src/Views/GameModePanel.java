package Views;

import General.GameSettings;
import Listeners.CasualModeListener;
import Listeners.NormalModeListener;

import javax.swing.*;
import java.awt.*;

/**
 * Created by AliPC on 30-Dec-16.
 */
public class GameModePanel extends JPanel{
    private JFrame frame;

    private JLabel title;
    private JButton normalBtn;
    private JButton casualBtn;
    private JButton madnessBtn;
    private JPanel btnHolder;

    private NormalModeListener normalModeListener;
    private CasualModeListener casualModeListener;

    public GameModePanel(JFrame frame) {
        this.frame = frame;
        ClearFrame();

        title = new JLabel("Game Modes");
        normalBtn = new JButton("Normal");
        casualBtn = new JButton("Casual");
        madnessBtn = new JButton("Madness");
        btnHolder = new JPanel(new GridBagLayout());

        title.setFont(new Font("Helvetica", Font.BOLD, 50));
        normalBtn.setPreferredSize(new Dimension(GameSettings.BTN_WIDTH, GameSettings.BTN_HEIGHT));
        casualBtn.setPreferredSize(new Dimension(GameSettings.BTN_WIDTH, GameSettings.BTN_HEIGHT));
        madnessBtn.setPreferredSize(new Dimension(GameSettings.BTN_WIDTH, GameSettings.BTN_HEIGHT));

        //listeners
        normalBtn.addActionListener(e -> {
            if (normalModeListener != null) {
                normalModeListener.newNormalGame();
            }
        });
        casualBtn.addActionListener(e -> {
            if (casualModeListener != null) {
                casualModeListener.newCasualMode();
            }
        });

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        //Fill btnHolder
        gc.gridy = 0;
        gc.gridx = 0;
        gc.insets = new Insets(5, 5, 5, 5);
        btnHolder.add(normalBtn, gc);

        gc.gridy = 1;
        btnHolder.add(casualBtn, gc);

        gc.gridy = 2;
        btnHolder.add(madnessBtn, gc);

        //Fill Views.GameModePanel
        gc.gridx = 0;
        gc.gridy = 0;
        add(title, gc);

        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridy = 1;
        add(btnHolder, gc);


    }

    public void ClearFrame() {
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
    }

    public void setNormalModeListener(NormalModeListener listener) {
        this.normalModeListener = listener;
    }

    public void setCasualModeListener(CasualModeListener listener) {
        this.casualModeListener = listener;
    }
}
