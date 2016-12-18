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
        GamePanel gp = new GamePanel();
        gp.setBorder(BorderFactory.createEmptyBorder(0, 0, 400, 200));
        add(gp);
        setSize(800, 600);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }



}
