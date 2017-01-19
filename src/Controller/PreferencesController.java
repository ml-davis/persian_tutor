package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class PreferencesController implements Initializable {

    @FXML
    private TextField fontSizeField;

    @FXML
    private static int fontSize = 10;

    @FXML
    public void increaseFontSize() {
        if (fontSize < 18) {
            fontSizeField.setText(String.valueOf(++fontSize));
        }
    }

    @FXML
    public void decreaseFontSize() {
        if (fontSize > 6) {
            fontSizeField.setText(String.valueOf(--fontSize));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fontSizeField.setText(String.valueOf(fontSize));
    }

    public static int getFontSize() {
        return fontSize;
    }
}
