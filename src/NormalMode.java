import Blocks.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * Created by AliPC on 18-Dec-16.
 */
public class NormalMode extends JPanel implements ActionListener, KeyListener {
    Timer t = new Timer(1000 / Settings.FPS, this);
    private double velX = 0;
    private double velY = 0;
    private Block newBlock = new IBlock();
    private Block[][] existingBlocks;
    private double xDimensions[] = {0, 0, 0, 0, 0, 0, 0};
    private double yDimensions[] = {0, 0, 0, 0, 0, 0, 0};

    protected double width = 200;
    protected double height = 400;
    private int blockSize = 20;
    private boolean isStarted;

    private int currentScore;
    private FullRowListener fullRowListener;

    private int nextBlock = 0;
    private NextBlockListener nextBlockListener;


    public NormalMode() {
        t.start();
        addKeyListener(this);
        setFocusable(true);
        setVisible(true);
        setFocusTraversalKeysEnabled(false);
        existingBlocks = new Block[blockSize * 20][blockSize * 10];
        setBackground(new Color(255, 255, 255));

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                existingBlocks[i][j] = new EmptyBlock();
            }
        }
        //setBorder(BorderFactory.createLineBorder(Color.black));
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 0));
        setBorder(border);

        currentScore = 0;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;


        // write text if game not started
        if (!isStarted) {
            Font s = new Font("SansSerif", Font.BOLD, 12);
            setFont(s);
            g2.drawString("Press S to START!", 50, 200);
        }



        Color []color = newBlock.getColor();
        xDimensions = newBlock.getxDimensions();
        yDimensions = newBlock.getyDimensions();

        //paint new block
        for (int k = 0; k < newBlock.getNumberOfSquares(); k++) {
            g2.setColor(color[k]);
            g2.fill(new Rectangle2D.Double(xDimensions[k], yDimensions[k], 20, 20));
        }

        //painting existing blocks
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (existingBlocks[i][j].getClass() != (new EmptyBlock()).getClass()) {
                    //g2.setColor(Color.BLACK);
                    //g2.draw(new Rectangle2D.Double(i * 20, j * 20, 20, 20));
                    g2.setColor(existingBlocks[i][j].getColor()[0]);        //or setPaint
                    g2.fill(new Rectangle2D.Double(i * 20, j * 20, 20, 20));
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        repaint();

        xDimensions = newBlock.getxDimensions();
        yDimensions = newBlock.getyDimensions();

        //set new coordinates
        newBlock.setxDimensions(xDimensions[0] + velX, xDimensions[1] + velX, xDimensions[2] + velX, xDimensions[3] + velX);
        newBlock.setyDimensions(yDimensions[0] + velY, yDimensions[1] + velY, yDimensions[2] + velY, yDimensions[3] + velY);

        if (newBlock.reachedBottom()) {
            //move back
            MoveBack();
            //adding to existing blocks
            saveBlock();
        }

        //check if out of borders
        boolean isOutOfBorder = newBlock.isOutOfBorder();
        if (isOutOfBorder) {
            //move back
            MoveBack();
        }

        //check if overlap
        xDimensions = newBlock.getxDimensions();
        yDimensions = newBlock.getyDimensions();
        boolean isOverlap = checkOverlap();

        if (isOverlap) {
            MoveBack();

            //check if on top of existing block
            //get new coordinates of new block
            xDimensions = newBlock.getxDimensions();
            yDimensions = newBlock.getyDimensions();

            for (int i = 0; i < newBlock.getNumberOfSquares(); i++) {
                Double xD = xDimensions[i] / 20;
                Double yD = yDimensions[i] / 20;
                int x = xD.intValue();
                int y = yD.intValue();
                //check one square lower, if not empty save new block
                if (existingBlocks[x][y + 1].getClass() != (new EmptyBlock()).getClass()) {
                    saveBlock();
                    break;
                }
            }
        }

        checkFullRow();
        velX = 0;
        //velY = 0;
    }

    public void MoveBack() {
        newBlock.setxDimensions(xDimensions[0] - velX, xDimensions[1] - velX, xDimensions[2] - velX, xDimensions[3] - velX);
        newBlock.setyDimensions(yDimensions[0] - velY, yDimensions[1] - velY, yDimensions[2] - velY, yDimensions[3] - velY);
    }

    public boolean checkOverlap() {
        xDimensions = newBlock.getxDimensions();
        yDimensions = newBlock.getyDimensions();
        for (int i = 0; i < newBlock.getNumberOfSquares(); i++) {
            Double xD = xDimensions[i]/20;
            int x = xD.intValue();
            Double yD = yDimensions[i]/20;
            int y = yD.intValue();
            //System.out.println("existingBlocks[" + x + "][" + y + "] Class: " + existingBlocks[x][y].getClass());
            if (existingBlocks[x][y].getClass() != (new EmptyBlock()).getClass()) {
                //System.out.println("Overlap, return true");
                return true;
            }
        }
        return false;
    }

    public void saveBlock() {
        //add to existing blocks
        xDimensions = newBlock.getxDimensions();
        yDimensions = newBlock.getyDimensions();
        Color[] color = newBlock.getColor();
        // add new block
        for(int i = 0; i < newBlock.getNumberOfSquares(); i++) {
            Double bottomD = yDimensions[i]/20;
            int yBlock = bottomD.intValue();
            Double rightD= xDimensions[i]/20;
            int xBlock = rightD.intValue();
            //System.out.println("Saved at x: " + xBlock + ", y: " + yBlock);
            //existingBlocks[xBlock][yBlock] = newBlock;
            existingBlocks[xBlock][yBlock] = new NotEmptyBlock(color[i]);
        }

        //create new block
        newBlock = setNewBlock();

        //check for game over condition
        checkForGameOver();
    }

    public void checkForGameOver() {
        //if new created block overlaps, then game over, clear board
        if (checkOverlap()) {
            clearBoard();
            //addGameOverText();
            t.stop();           //stop timer
        }
    }

    public void addGameOverText() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JLabel text = new JLabel("GAME OVER!");
        panel.add(text, BorderLayout.EAST);
        add(panel, BorderLayout.EAST);
        System.out.println("New panel at : " + panel.getX() + ", " + panel.getY());
        System.out.println("New text at : " + text.getX() + ", " + text.getY());
        repaint();
    }

    public void clearBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                //System.out.println("Clearing " + i + ", " + j);
                existingBlocks[i][j] = new EmptyBlock();
            }
        }
        //clear new Block
        for (int i = 0; i < newBlock.getNumberOfSquares(); i++ ){
            newBlock = new EmptyBlock();
        }
    }

    public void checkFullRow() {
        boolean rowIsFull = false;
        for (int j = 0; j < 20; j++ ) {
            for (int i = 0; i < 10; i++) {
                //System.out.println("Checking " + i + " " + j + " eB " + existingBlocks[i][j].getClass());
                if (existingBlocks[i][j].getClass() != (new EmptyBlock()).getClass()) {
                    rowIsFull = true;
                }
                else {
                    rowIsFull = false;
                    break;
                }
            }
            if (rowIsFull) {
                removeRow(j);
            }
            rowIsFull = false;
        }
    }

    public void removeRow(int i) {
        for (int j = 0; j < width / 20; j++) {
            existingBlocks[j][i] = new EmptyBlock();
        }
        currentScore++;
        if (fullRowListener != null) {
            fullRowListener.currentScore(currentScore);
        }
        moveOneLineDown(i);

    }

    public void moveOneLineDown(int emptyRow) {
        for (int j = emptyRow; j > 0; j--){
            for (int i = 0; i < 20; i++) {
                existingBlocks[i][j] = existingBlocks[i][j - 1];
            }
        }
    }

    public void left() {
        velX = -20;
        //velY = 0;
    }

    public void right() {
        velX = 20;
        //velY = 0;
    }

    public Block setNewBlock() {
        Random r = new Random();
        //int op = r.nextInt(7);
        int op = nextBlock;
        nextBlock = r.nextInt(7);
        if (nextBlockListener != null) {
            nextBlockListener.nextBlock(nextBlock);
        }
        //System.out.println(op);
        //return new ZBlock();

        ///*
        switch(op) {
            case 0:
                return new IBlock();
            case 1:
                return new LBlock();
            case 2:
                return new ZBlock();
            case 3:
                return new SBlock();
            case 4:
                return new OBlock();
            case 5:
                return new TBlock();
            case 6:
                return new JBlock();
        }

        return new LBlock();
        //*/

    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_E) {
            t.setDelay((1000 / Settings.FPS) /10);
            newBlock.rotateRight();
            if (checkOverlap()) {
                newBlock.rotateLeft();
            }
        }
        if (code == KeyEvent.VK_Q) {
            t.setDelay((1000 / Settings.FPS) /10);
            newBlock.rotateLeft();
            if (checkOverlap()) {
                newBlock.rotateRight();
            }
        }
        if (code == KeyEvent.VK_S) {
            //accelerate
            velY = 20;
            isStarted = true;
            t.setDelay((1000 / Settings.FPS) /10);
        }
        if (code == KeyEvent.VK_D) {
            right();
        }
        if (code == KeyEvent.VK_A) {
            left();
        }
    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_S) {
            //decelerate
            t.setDelay(1000 / Settings.FPS);
        }
        if (code == KeyEvent.VK_E) {
            t.setDelay(1000 / Settings.FPS);
        }
        if (code == KeyEvent.VK_Q) {
            t.setDelay(1000 / Settings.FPS);
        }
    }

    public void setFullRowListener(FullRowListener listener) {
        this.fullRowListener = listener;
    }

    public void setNextBlockListener(NextBlockListener listener) {
        this.nextBlockListener = listener;
    }
}
