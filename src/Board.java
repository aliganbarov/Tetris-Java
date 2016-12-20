import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by AliPC on 18-Dec-16.
 */
public class Board extends JFrame {
    private JFrame frame = new JFrame("TETRIS");

    public Board() {

        InitBoard();
    }

    public void InitBoard() {
        //create instance of game
        GamePanel gp = new GamePanel();
        gp.setLayout(new BorderLayout());

        //create holding panel for game
        JPanel gpHoldingPanel = new JPanel(new BorderLayout());
        gpHoldingPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        gpHoldingPanel.add(gp, BorderLayout.CENTER);
        add(gpHoldingPanel, BorderLayout.WEST);
        gp.setPreferredSize(new Dimension(200, 400));

        //make another class for statistics
        //create holding panel for statistics
        JPanel stasHoldingPanel = new JPanel(new BorderLayout());

        //create statistics panel
        JPanel stas = new JPanel(new BorderLayout(20, 20));

        //create and fill stas NORTH
        JPanel stasTop = new JPanel(new BorderLayout(10, 10));
        JLabel maxScoreText = new JLabel("Maximum Score: ");
        JLabel maxScore = new JLabel("1000    ");
        stasTop.add(maxScoreText, BorderLayout.WEST);
        stasTop.add(maxScore, BorderLayout.EAST);

        stas.add(stasTop, BorderLayout.NORTH);

        stasHoldingPanel.add(stas, BorderLayout.CENTER);
        add(stasHoldingPanel, BorderLayout.EAST);

        //add(gp);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);



    }



}
