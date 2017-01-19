package Controller;

//import View.PopupWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuBarController {

    @FXML
    public void loadSettingsWindow() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Preferences.fxml"));
        Parent root = null;

        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Preferences");
        if (root != null) {
            stage.setScene(new Scene(root, 500, 300));
        }
        stage.showAndWait();

    }
}
