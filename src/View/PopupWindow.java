package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupWindow {

    private Stage stage;

    public PopupWindow(String message) {
        stage = new Stage();
        VBox window = new VBox();
        window.setId("app");
        window.setPadding(new Insets(25, 25, 25, 25));
        window.setSpacing(25);
        window.setAlignment(Pos.CENTER);

        Label label = new Label(message);

        Button okButton = new Button("OK");
        okButton.setOnAction(e -> stage.close());

        window.getChildren().addAll(label, okButton);

        int width = 400, height = 200;
        Scene scene = new Scene(window, width, height);
        scene.getStylesheets().add("Style/DarkTheme.css");

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
    }

    public void display() {
        stage.showAndWait();
    }
}
