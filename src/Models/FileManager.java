package Models;

import General.GameSettings;
import com.sun.scenario.Settings;

import java.io.*;

/**
 * Created by AliPC on 31-Dec-16.
 */
public class FileManager {
    private String fileName;
    //private HighScores highScores;
    private String line;

    private int normalScore;
    private int casualScore;
    private int madnessScore;

    public FileManager() {
        fileName = GameSettings.fileName;
        //highScores = new HighScores();

        try {
            FileReader fileReader = new FileReader(fileName);


            BufferedReader bufferedReader = new BufferedReader(fileReader);

            line = bufferedReader.readLine();
            normalScore = Integer.parseInt(line);

            line = bufferedReader.readLine();
            casualScore = Integer.parseInt(line);

            line = bufferedReader.readLine();
            madnessScore = Integer.parseInt(line);

            bufferedReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Didn't find file");
            normalScore = 0;
            casualScore = 0;
            madnessScore = 0;
        }
        catch (IOException e) {
            System.out.println("Error in reading file");
        }
    }

    public void newRecord(int maxScore, char gameMode) {
        switch (gameMode) {
            case 'n':
                normalScore = maxScore;
                break;
            case 'c':
                casualScore = maxScore;
                break;
            case 'm':
                madnessScore = maxScore;
                break;
        }

        try {
            FileWriter fileWriter = new FileWriter(fileName);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(normalScore + "\n");
            bufferedWriter.write(casualScore + "\n");
            bufferedWriter.write(madnessScore + "\n");

            bufferedWriter.close();

        }
        catch (IOException e) {
            System.out.println("Failed to write to file");
        }
    }

    public int getNormalScore() {
        return normalScore;
    }

    public int getCasualScore() {
        return casualScore;
    }

    public int getMadnessScore() {
        return madnessScore;
    }
}
