import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;

import static java.lang.System.exit;

/**
 * Created by AliPC on 18-Dec-16.
 */
public class Menu extends Application{
    private Board board;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TETRIS GAME");

        Label title = new Label("TETRIS");
        title.getStyleClass().add("title");
        Button newGameBtn = new Button("New Game");
        Button newUserBtn = new Button("New User");
        Button topScoresBtn = new Button("Top Scores");
        Button exitBtn = new Button("Exit");

        newGameBtn.setMaxWidth(Settings.BUTTON_WIDTH);
        newUserBtn.setMaxWidth(Settings.BUTTON_WIDTH);
        topScoresBtn.setMaxWidth(Settings.BUTTON_WIDTH);
        exitBtn.setMaxWidth(Settings.BUTTON_WIDTH);

        newGameBtn.setOnAction(e -> {
            primaryStage.setScene(new GameModeScene().getScene());
        });

        exitBtn.setOnAction(e -> exit(1));

        VBox vBox = new VBox();
        vBox.setSpacing(Settings.LINE_SPACING);
        vBox.getChildren().addAll(title, newGameBtn, newUserBtn, topScoresBtn, exitBtn);

        Scene menuScene = new Scene(vBox, Settings.MENU_WIDTH, Settings.MENU_HEIGHT);
        menuScene.getStylesheets().add("CSS/Menu.css");

        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
