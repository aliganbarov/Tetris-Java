package Models.Blocks;

import java.awt.*;

/**
 * Created by AliPC on 18-Dec-16.
 */
public abstract class Block {
    protected double width = 200;
    protected double height = 400;
    protected int blockSize = 20;
    protected int numberOfSquares;
    protected int atRotateStage;

    public abstract void setxDimensions(double x1, double x2, double x3, double x4);    //for type 1 blocks
    public abstract void setyDimensions(double y1, double y2, double y3, double y4);    //for type 1 blocks
    public abstract boolean reachedBottom();
    public abstract double[] getxDimensions();
    public abstract double[] getyDimensions();
    public abstract Color[] getColor();
    public abstract int getNumberOfSquares();
    public abstract void rotateRight();
    public abstract void rotateLeft();
    public abstract void rotateToStage1();
    public abstract void rotateToStage2();
    public abstract void rotateToStage3();
    public abstract void rotateToStage4();

    public abstract boolean isOutOfBorder();

    public int getBlockSize() {
        return blockSize;
    }
}
