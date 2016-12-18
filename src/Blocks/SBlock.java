package Blocks;

import Blocks.Block;

import java.awt.*;

/**
 * Created by AliPC on 18-Dec-16.
 */
public class SBlock extends Block {
    private double xDimensions[] = {40, 20, 20, 20, 0};
    private double yDimensions[] = {0, 0, 20, 40, 40};

    /*
           10
           2
          43
     */

    private Color[] color;

    private boolean isRotated;

    public SBlock() {
        isRotated = false;
        color = new Color[5];

        color[0] = new Color(0, 0, 0);
        color[0] = new Color(0, 20, 20);
        color[1] = new Color(0, 40, 40);
        color[2] = new Color(0, 60, 60);
        color[3] = new Color(0, 80, 80);
    }

    @Override
    public void setxDimensions(double x1, double x2, double x3, double x4) {

    }

    @Override
    public void setyDimensions(double y1, double y2, double y3, double y4) {

    }

    @Override
    public void setxDimensions(double x1, double x2, double x3, double x4, double x5) {
        xDimensions[0] = x1;
        xDimensions[1] = x2;
        xDimensions[2] = x3;
        xDimensions[3] = x4;
        xDimensions[4] = x5;

        /*
        //check for borders
        if (!isRotated) {
            if (xDimensions[4] <= 0) {
                xDimensions[4] = 0;
                xDimensions[3] = xDimensions[2] = xDimensions[1] = xDimensions[4] + blockSize;
                xDimensions[0] = xDimensions[3] + blockSize;
            }
            if (xDimensions[0] >= width - blockSize) {
                xDimensions[0] = width - blockSize;
                xDimensions[3] = xDimensions[2] = xDimensions[1] = xDimensions[0] - blockSize;
                xDimensions[4] = xDimensions[3] - blockSize;
            }
        }
        */
    }

    @Override
    public void setyDimensions(double y1, double y2, double y3, double y4, double y5) {
        yDimensions[0] = y1;
        yDimensions[1] = y2;
        yDimensions[2] = y3;
        yDimensions[3] = y4;
        yDimensions[4] = y5;

        /*
        if (!isRotated) {
            if (yDimensions[1] <= 0) {
                yDimensions[0] = yDimensions[1] = 0;
                yDimensions[2] = yDimensions[0] + blockSize;
                yDimensions[4] = yDimensions[3] = yDimensions[2] + blockSize;
            }
            if (yDimensions[4] >= height - blockSize) {
                yDimensions[3] = yDimensions[4] = height - blockSize;
                yDimensions[2] = yDimensions[3] - blockSize;
                yDimensions[1] = yDimensions[0] = yDimensions[2] - blockSize;
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
    public boolean reachedBottom() {
        if (yDimensions[4] == height - blockSize) {
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
        return 2;
    }

    @Override
    public int getNumberOfSquares() {
        return 5;
    }
}
