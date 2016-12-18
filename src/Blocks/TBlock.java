package Blocks;

import Blocks.Block;

import java.awt.*;

/**
 * Created by AliPC on 18-Dec-16.
 */
public class TBlock extends Block {

    private double xDimensions[] = {0, 20, 40, 20};
    private double yDimensions[] = {0, 0, 0, 20};

    /*
            012
             3
     */

    private Color[] color;

    private boolean isRotated;

    public TBlock() {
        isRotated = false;
        color = new Color[4];

        color[0] = new Color(255, 20, 200);
        color[1] = new Color(240, 40, 180);
        color[2] = new Color(220, 60, 160);
        color[3] = new Color(200, 80, 140);
    }

    @Override
    public void setxDimensions(double x1, double x2, double x3, double x4) {
        xDimensions[0] = x1;
        xDimensions[1] = x2;
        xDimensions[2] = x3;
        xDimensions[3] = x4;

        /*
        //check for borders
        if (!isRotated) {
            if (xDimensions[0] <= 0) {
                xDimensions[0] = 0;
                xDimensions[1] = xDimensions[3] = xDimensions[0] + blockSize;
                xDimensions[2] = xDimensions[1] + blockSize;
            }
            if (xDimensions[2] >= width - blockSize) {
                xDimensions[2] = width - blockSize;
                xDimensions[1] = xDimensions[3] = xDimensions[2] - blockSize;
                xDimensions[0] = xDimensions[1] - blockSize;
            }
        }
        */
    }

    @Override
    public void setyDimensions(double y1, double y2, double y3, double y4) {
        yDimensions[0] = y1;
        yDimensions[1] = y2;
        yDimensions[2] = y3;
        yDimensions[3] = y4;

        /*
        //check for borders
        if(!isRotated) {
            if (yDimensions[0] <= 0) {
                yDimensions[0] = yDimensions[1] = yDimensions[2] = 0;
                yDimensions[3] = yDimensions[0] + blockSize;
            }
            if (yDimensions[3] >= height - blockSize) {
                yDimensions[3] = height - blockSize;
                yDimensions[0] = yDimensions[1] = yDimensions[2] = yDimensions[3] - blockSize;
            }
        }
        */
    }

    @Override
    public void rotateRight() {

    }

    @Override
    public void rotateLeft() {

    }

    @Override
    public void rotateToStage1() {

    }

    @Override
    public void rotateToStage2() {

    }

    @Override
    public void rotateToStage3() {

    }

    @Override
    public void rotateToStage4() {

    }

    @Override
    public boolean isOutOfBorder() {
        for (int i = 0; i < numberOfSquares; i++) {
            if (xDimensions[i] < 0 || xDimensions[i] > (width - blockSize)) {
                return true;
            }
            if (yDimensions[i] < 0 || yDimensions[i] > (height - blockSize)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setxDimensions(double x1, double x2, double x3, double x4, double x5) {

    }

    @Override
    public void setyDimensions(double y1, double y2, double y3, double y4, double y5) {

    }

    @Override
    public boolean reachedBottom() {
        if (yDimensions[3] == height - blockSize) {
            return true;
        }
        return false;
    }

    @Override
    public double[] getxDimensions() {
        return xDimensions;
    }

    @Override
    public double[] getyDimensions() {
        return yDimensions;
    }

    @Override
    public Color[] getColor() {
        return color;
    }

    @Override
    public int getType() {
        return 1;
    }

    @Override
    public int getNumberOfSquares() {
        return 4;
    }
}
