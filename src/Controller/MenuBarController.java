package Controller;

import View.PopupWindow;
import javafx.fxml.FXML;

public class MenuBarController {

    @FXML
    public void loadSettingsWindow() {
        PopupWindow window = new PopupWindow("Preferences are not implemented yet");
        window.display();
    }
}
