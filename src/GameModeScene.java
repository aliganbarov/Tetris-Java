import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Created by AliPC on 25-Dec-16.
 */
public class GameModeScene {
    private Scene scene;
    private VBox vBox;
    private Board board;

    public GameModeScene() {
        vBox = new VBox();
        vBox.setSpacing(Settings.LINE_SPACING);

        scene = new Scene(vBox, Settings.MENU_WIDTH, Settings.MENU_HEIGHT);
        scene.getStylesheets().add("CSS/Menu.css");

        Label label = new Label("Select Mode");
        label.getStyleClass().add("title");
        Button normalBtn = new Button("Normal");
        Button casualBtn = new Button("Casual");
        Button madnessBtn = new Button("Madness");

        normalBtn.setMaxWidth(Settings.BUTTON_WIDTH);
        casualBtn.setMaxWidth(Settings.BUTTON_WIDTH);
        madnessBtn.setMaxWidth(Settings.BUTTON_WIDTH);

        normalBtn.setOnAction(e -> board = new Board('n'));

        vBox.getChildren().addAll(label, normalBtn, casualBtn, madnessBtn);
    }

    public Scene getScene() {
        return scene;
    }
}
