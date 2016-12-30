package Models.Blocks;

import java.awt.*;

/**
 * Created by AliPC on 18-Dec-16.
 */
public class ZBlock extends Block {

    private double xDimensions[] = {80, 100, 100, 120};
    private double yDimensions[] = {0, 0, 20, 20};

    /*
            01
             23

     */

    private Color[] color;

    private boolean isRotated;

    public ZBlock() {
        isRotated = false;
        color = new Color[4];
        numberOfSquares = 4;
        atRotateStage = 1;

        color[0] = new Color(244, 66, 66);
        color[0] = new Color(230, 60, 60);
        color[1] = new Color(220, 50, 50);
        color[2] = new Color(210, 40, 40);
    }

    @Override
    public void setxDimensions(double x1, double x2, double x3, double x4) {
        xDimensions[0] = x1;
        xDimensions[1] = x2;
        xDimensions[2] = x3;
        xDimensions[3] = x4;
    }

    @Override
    public void setyDimensions(double y1, double y2, double y3, double y4) {
        yDimensions[0] = y1;
        yDimensions[1] = y2;
        yDimensions[2] = y3;
        yDimensions[3] = y4;
    }


    @Override
    public void rotateRight() {
        if (atRotateStage == 1) {
            rotateToStage2();
        }
        else {
            rotateToStage1();
        }
    }

    @Override
    public void rotateLeft() {
        if (atRotateStage == 1) {
            rotateToStage2();
        }
        else {
            rotateToStage1();
        }
    }

    //set to stage 1
    @Override
    public void rotateToStage1() {
        setxDimensions(xDimensions[0] - blockSize, xDimensions[1],
                xDimensions[2] + blockSize, xDimensions[3] + blockSize * 2);
        setyDimensions(yDimensions[0], yDimensions[1] - blockSize,
                yDimensions[2], yDimensions[3] - blockSize);
        atRotateStage = 1;
        if (isOutOfBorder()) {
            rotateToStage2();
        }
    }

    //set to stage 2
    @Override
    public void rotateToStage2() {
        setxDimensions(xDimensions[0] + blockSize, xDimensions[1],
                xDimensions[2] - blockSize, xDimensions[3] - blockSize * 2);
        setyDimensions(yDimensions[0], yDimensions[1] + blockSize,
                yDimensions[2], yDimensions[3] + blockSize);
        atRotateStage = 2;
        if (isOutOfBorder()) {
            rotateToStage1();
        }
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
        for (int i = 0; i < numberOfSquares; i++ ){
            if (yDimensions[i] > height - blockSize) {
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
        return numberOfSquares;
    }




}
