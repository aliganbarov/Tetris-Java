package Models;

/**
 * Created by AliPC on 30-Dec-16.
 */
public class HighScores {
    private int normalScore;
    private int casualScore;
    private int madnessScore;

    private FileManager fileManager;

    public HighScores() {
        fileManager = new FileManager();

        setNormalScore(fileManager.getNormalScore());
        setCasualScore(fileManager.getCasualScore());
        setMadnessScore(fileManager.getMadnessScore());
    }

    public int getNormalScore() {
        return normalScore;
    }

    public void setNormalScore(int normalScore) {
        this.normalScore = normalScore;
    }

    public int getCasualScore() {
        return casualScore;
    }

    public void setCasualScore(int casualScore) {
        this.casualScore = casualScore;
    }

    public int getMadnessScore() {
        return madnessScore;
    }

    public void setMadnessScore(int madnessScore) {
        this.madnessScore = madnessScore;
    }
}
