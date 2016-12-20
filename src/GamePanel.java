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
public class GamePanel extends JPanel implements ActionListener, KeyListener {
    Timer t = new Timer(50, this);
    private double velX = 0;
    private double velY = 0;
    private double x1 = 0;
    private double y1 = 0;
    private double x2 = 20;
    private double y2 = 0;
    private Block newBlock = new IBlock();
    private Block[][] existingBlocks;
    private double xDimensions[] = {0, 0, 0, 0, 0, 0, 0};
    private double yDimensions[] = {0, 0, 0, 0, 0, 0, 0};

    protected double width = 200;
    protected double height = 400;
    private int blockSize = 20;

    private int maxY = -1;
    private int numbOfBottom = 0;
    private int[] bottomCoords = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};


    public GamePanel() {
        t.start();
        addKeyListener(this);
        setFocusable(true);
        setVisible(true);
        setFocusTraversalKeysEnabled(false);
        existingBlocks = new Block[blockSize * 20][blockSize * 10];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                existingBlocks[i][j] = new EmptyBlock();
            }
        }
        //setBorder(BorderFactory.createLineBorder(Color.black));
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 0));
        setBorder(border);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;


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
                    g2.setColor(existingBlocks[i][j].getColor()[0]);
                    g2.fill(new Rectangle2D.Double(i * 20, j * 20, 20, 20));
                }
            }
        }

    }

    public void actionPerformed(ActionEvent e) {
        repaint();

        if (newBlock.reachedBottom()) {
            //adding to existing blocks
            saveBlock();
        }

        xDimensions = newBlock.getxDimensions();
        yDimensions = newBlock.getyDimensions();

        //set new coordinates for type 1
        newBlock.setxDimensions(xDimensions[0] + velX, xDimensions[1] + velX, xDimensions[2] + velX, xDimensions[3] + velX);
        newBlock.setyDimensions(yDimensions[0] + velY, yDimensions[1] + velY, yDimensions[2] + velY, yDimensions[3] + velY);

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
            //System.out.println("Overlap for block " + newBlock.getClass() + " moving back");
            MoveBack();

            /*
            //check bottom block
            setMinY();  //get number of bottom blocks and coordinates
            System.out.println("maxY: " + maxY + ", numbOfBottom: " + numbOfBottom);
            //check if one lower block is not empty
            while(numbOfBottom > 0) {
                int y = bottomCoords[--numbOfBottom];
                int x = bottomCoords[--numbOfBottom];
                if (existingBlocks[x][y + 1].getClass() != (new EmptyBlock()).getClass()) {
                    saveBlock();
                    break;
                }
            }
            */

            //check if on top of existing block
            //get new coordinates of new block
            xDimensions = newBlock.getxDimensions();
            yDimensions = newBlock.getyDimensions();

            for (int i = 0; i < newBlock.getNumberOfSquares(); i++) {
                Double xD = xDimensions[i] / 20;
                Double yD = yDimensions[i] / 20;
                int x = xD.intValue();
                int y = yD.intValue();
                //System.out.println("x: " + x + ", y: " + y);
                //check one square lower, if not empty save new block
                if (existingBlocks[x][y + 1].getClass() != (new EmptyBlock()).getClass()) {
                    saveBlock();
                    break;
                }
            }
        }

        checkFullRow();
        velX = 0;
        velY = 0;
    }

    public void setMinY() {
        xDimensions = newBlock.getxDimensions();
        yDimensions = newBlock.getyDimensions();
        numbOfBottom = 0;
        maxY = -1;
        for (int i = 0; i < newBlock.getNumberOfSquares(); i++) {
            Double yD = yDimensions[i] / 20;
            int y = yD.intValue();
            if (maxY < y) {
                maxY = y;
            }
        }

        //count bottom blocks
        for (int i = 0; i < newBlock.getNumberOfSquares(); i++) {
            Double yD = yDimensions[i] / 20;
            int y = yD.intValue();
            Double xD = xDimensions[i] / 20;
            int x = xD.intValue();
            if (maxY == y) {
                bottomCoords[numbOfBottom++] = x;
                bottomCoords[numbOfBottom++] = y;
            }
        }
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
        moveOneLineDown(i);
    }

    public void moveOneLineDown(int emptyRow) {
        System.out.println("Empty Row: " + emptyRow);
        for (int j = emptyRow; j > 0; j--){
            for (int i = 0; i < 20; i++) {
                existingBlocks[i][j] = existingBlocks[i][j - 1];
            }
        }
    }

    public void up() {
        velY = -20;
        velX = 0;
    }

    public void down() {
        velY = 20;
        velX = 0;
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
        int op = r.nextInt(7);
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
        if (code == KeyEvent.VK_UP) {
            //up();
            newBlock.rotateRight();
            if (checkOverlap()) {
                //System.out.println("Overlap");
                newBlock.rotateLeft();
            }
        }
        if (code == KeyEvent.VK_W) {
            newBlock.rotateLeft();
            if (checkOverlap()) {
                //System.out.println("Overlap");
                newBlock.rotateRight();
            }
        }
        if (code == KeyEvent.VK_DOWN) {
            down();
        }
        if (code == KeyEvent.VK_RIGHT) {
            right();
        }
        if (code == KeyEvent.VK_LEFT) {
            left();
        }
    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
}
