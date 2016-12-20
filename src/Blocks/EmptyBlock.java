package Blocks;

import Blocks.Block;

import java.awt.*;

/**
 * Created by AliPC on 18-Dec-16.
 */
public class EmptyBlock extends Block {

    @Override
    public int getNumberOfSquares() {
        return 0;
    }

    @Override
    public boolean reachedBottom() {
        return false;
    }


    public void setxDimensions(double x1, double x2, double x3, double x4) {

    }


    public void setyDimensions(double y1, double y2, double y3, double y4) {

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
    public double[] getxDimensions() {
        return new double[0];
    }

    @Override
    public double[] getyDimensions() {
        return new double[0];
    }

    @Override
    public Color[] getColor() {
        return null;
    }

    @Override
    public boolean isOutOfBorder() {
        return false;
    }
}
