package Blocks;

import Blocks.Block;

import java.awt.*;

/**
 * Created by AliPC on 18-Dec-16.
 */
public class LBlock extends Block {
    private double xDimensions[] = {80, 80, 80, 100};
    private double yDimensions[] = {0, 20, 40, 40};

    private Color[] color;

    private boolean isRotated;

    public LBlock() {
        isRotated = false;
        color = new Color[4];
        numberOfSquares = 4;
        atRotateStage = 1;
        color[0] = new Color(200, 244, 66);
        color[1] = new Color(190, 240, 60);
        color[2] = new Color(180, 220, 50);
        color[3] = new Color(160, 200, 40);
    }

    public double[] getxDimensions() {
        return xDimensions;
    }

    public double[] getyDimensions() {
        return yDimensions;
    }

    public void setxDimensions(double x1, double x2, double x3, double x4) {
        xDimensions[0] = x1;
        xDimensions[1] = x2;
        xDimensions[2] = x3;
        xDimensions[3] = x4;

        /*
        //check for borders
        if (!isRotated) {
            if (xDimensions[0] <= 0) {
                xDimensions[0] = xDimensions[1] = xDimensions[2] = 0;
                xDimensions[3] = blockSize;
            }
            if (xDimensions[3] >= width - blockSize) {
                xDimensions[3] = width - blockSize;
                xDimensions[0] = xDimensions[1] = xDimensions[2] = xDimensions[3] - blockSize;
            }
        }
        */

    }

    public void setyDimensions(double y1, double y2, double y3, double y4) {
        yDimensions[0] = y1;
        yDimensions[1] = y2;
        yDimensions[2] = y3;
        yDimensions[3] = y4;

        /*
        //check for borders
        if (!isRotated) {
            if (yDimensions[0] <= 0) {
                yDimensions[0] = 0;
                yDimensions[1] = yDimensions[0] + blockSize;
                yDimensions[2] = yDimensions[3] = yDimensions[1] + blockSize;
            }
            if (yDimensions[3] >= height - blockSize) {
                yDimensions[2] = yDimensions[3] = height - blockSize;
                yDimensions[1] = yDimensions[2] - blockSize;
                yDimensions[0] = yDimensions[1] - blockSize;
            }
        }
        */
    }

    @Override
    public void rotateRight() {
        //System.out.println("Rotate from " + atRotateStage + " to " + (atRotateStage + 1));
        if (atRotateStage == 1) {
            rotateToStage2();
        }
        else if (atRotateStage == 2) {
            rotateToStage3();
        }
        else if (atRotateStage == 3) {
            rotateToStage4();
        }
        else if (atRotateStage == 4){
            rotateToStage1();
        }
    }

    @Override
    public void rotateLeft() {
        //System.out.println("Rotate from " + atRotateStage + " to " + (atRotateStage - 1));
        if (atRotateStage == 1) {
            rotateToStage4();
        }
        else if (atRotateStage == 2) {
            rotateToStage1();
        }
        else if (atRotateStage == 3) {
            rotateToStage2();
        }
        else if (atRotateStage == 4) {
            rotateToStage3();
        }
    }

    @Override
    public void rotateToStage1() {
        //right rotation from 4 to 1
        if (atRotateStage == 4) {
            setxDimensions(xDimensions[0], xDimensions[1] - blockSize,
                    xDimensions[2] - blockSize * 2, xDimensions[3] - blockSize);
            setyDimensions(yDimensions[0] - blockSize * 2, yDimensions[1] - blockSize,
                    yDimensions[2], yDimensions[3] + blockSize);
            atRotateStage = 1;
            if (isOutOfBorder()) {
                rotateLeft();
            }
        }

        //left rotation from 2 to 1
        if (atRotateStage == 2) {
            setxDimensions(xDimensions[0] - blockSize * 2, xDimensions[1] - blockSize,
                    xDimensions[2], xDimensions[3] + blockSize);
            setyDimensions(yDimensions[0], yDimensions[1] + blockSize,
                    yDimensions[2] + blockSize * 2, yDimensions[3] + blockSize);
            atRotateStage = 1;
            if (isOutOfBorder()) {
                rotateRight();
            }
        }
    }

    @Override
    public void rotateToStage2() {
        //right rotation from 1 to 2
        if (atRotateStage == 1) {
            setxDimensions(xDimensions[0] + blockSize * 2, xDimensions[1] + blockSize,
                    xDimensions[2], xDimensions[3] - blockSize);
            setyDimensions(yDimensions[0], yDimensions[1] - blockSize,
                    yDimensions[2] - blockSize * 2, yDimensions[3] - blockSize);
            atRotateStage = 2;
            if(isOutOfBorder()) {
                rotateLeft();
            }
        }

        //left rotation from 3 to 2
        if (atRotateStage == 3) {
            setxDimensions(xDimensions[0], xDimensions[1] - blockSize,
                    xDimensions[2] - blockSize * 2, xDimensions[3] - blockSize);
            setyDimensions(yDimensions[0] - blockSize * 2, yDimensions[1] - blockSize,
                    yDimensions[2], yDimensions[3] + blockSize);
            atRotateStage = 2;
            if (isOutOfBorder()) {
                rotateRight();
            }
        }
    }

    @Override
    public void rotateToStage3() {
        //right rotation from 2 to 3
        if (atRotateStage == 2) {
            setxDimensions(xDimensions[0], xDimensions[1] + blockSize,
                    xDimensions[2] + blockSize * 2, xDimensions[3] + blockSize);
            setyDimensions(yDimensions[0] + blockSize * 2, yDimensions[1] + blockSize,
                    yDimensions[2], yDimensions[3] - blockSize);
            atRotateStage = 3;
            if(isOutOfBorder()) {
                rotateLeft();
            }
        }

        //left rotation from 4 to 3
        if (atRotateStage == 4) {
            setxDimensions(xDimensions[0] + blockSize * 2, xDimensions[1] + blockSize,
                    xDimensions[2], xDimensions[3] - blockSize);
            setyDimensions(yDimensions[0], yDimensions[1] - blockSize,
                    yDimensions[2] - blockSize * 2, yDimensions[3] - blockSize);
            atRotateStage = 3;
            if (isOutOfBorder()) {
                rotateRight();
            }
        }
    }

    @Override
    public void rotateToStage4() {
        //right rotation from 3 to 4
        if (atRotateStage == 3){
            setxDimensions(xDimensions[0] - blockSize * 2, xDimensions[1] - blockSize,
                    xDimensions[2], xDimensions[3] + blockSize);
            setyDimensions(yDimensions[0], yDimensions[1] + blockSize,
                    yDimensions[2] + blockSize * 2, yDimensions[3] + blockSize);
            atRotateStage = 4;
            if(isOutOfBorder()) {
                rotateLeft();
            }
        }

        //left rotation from 1 to 4
        if (atRotateStage == 1) {
            setxDimensions(xDimensions[0], xDimensions[1] + blockSize,
                    xDimensions[2] + blockSize * 2, xDimensions[3] + blockSize);
            setyDimensions(yDimensions[0] + blockSize * 2, yDimensions[1] + blockSize,
                    yDimensions[2], yDimensions[3] - blockSize);
            atRotateStage = 4;
            if (isOutOfBorder()) {
                rotateRight();
            }
        }
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

    public boolean reachedBottom() {
        for (int i = 0; i < numberOfSquares; i++ ){
            if (yDimensions[i] > height - blockSize) {
                return true;
            }
        }
        return false;
    }

    public Color[] getColor() {
        return color;
    }

    @Override
    public int getNumberOfSquares() {
        return 4;
    }
}
