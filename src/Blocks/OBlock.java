package Blocks;

import Blocks.Block;

import java.awt.*;

/**
 * Created by AliPC on 18-Dec-16.
 */
public class OBlock extends Block {
    private double xDimensions[] = {80, 100, 80, 100};
    private double yDimensions[] = {0, 0, 20, 20};

    private Color[] color;

    private boolean isRotated;

    public OBlock() {
        isRotated = false;
        color = new Color[4];
        numberOfSquares = 4;
        atRotateStage = 1;

        color[0] = new Color(50, 50, 50);
        color[1] = new Color(40, 60, 60);
        color[2] = new Color(30, 80, 80);
        color[3] = new Color(20, 100, 100);
    }

    @Override
    public void setxDimensions(double x1, double x2, double x3, double x4) {
        xDimensions[0] = x1;
        xDimensions[1] = x2;
        xDimensions[2] = x3;
        xDimensions[3] = x4;

        /*
        //check borders
        if (xDimensions[0] <= 0) {
            xDimensions[0] = xDimensions[2] = 0;
            xDimensions[1] = xDimensions[3] = 20;
        }
        if (xDimensions[1] >= width - blockSize) {
            xDimensions[1] = xDimensions[3] = width - blockSize;
            xDimensions[0] = xDimensions[2] = xDimensions[1] - blockSize;
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
        //check borders
        if (yDimensions[0] <= 0) {
            yDimensions[0] = yDimensions[1] = 0;
            yDimensions[2] = yDimensions[3] = yDimensions[0] + blockSize;
        }
        if (yDimensions[2] >= height - blockSize) {
            yDimensions[2] = yDimensions[3] = height - blockSize;
            yDimensions[0] = yDimensions[1] = yDimensions[2] - blockSize;
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
    public boolean reachedBottom() {
        for (int i = 0; i < numberOfSquares; i++ ){
            if (yDimensions[i] == height - blockSize) {
                return true;
            }
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
    public int getNumberOfSquares() {
        return 4;
    }
}
