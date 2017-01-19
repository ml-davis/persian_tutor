package Controller;

import Main.Main;
import Model.Dictionary;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class BrowserController implements Initializable {

    private Dictionary dictionary = Dictionary.getInstance();

    @FXML
    private Text unitText;

    @FXML
    private TextField searchField;

    @FXML
    private TextArea textArea;

    @FXML
    private Pagination unitSelector;

    @FXML
    public void initializeQuiz() {
        Main.setPage(getClass().getResource("/View/QuizSettings.fxml"));
    }

    @FXML
    public void search() {
        String query = searchField.getText();
        int unitNumber = unitSelector.getCurrentPageIndex() + 1;
        if (query.equals("")) {
            textArea.setText(dictionary.getUnitString(unitNumber));
        } else {
            textArea.setText(dictionary.getSearchString(query));
        }
    }

    @FXML
    private TextArea createPage(int pageIndex) {
        textArea = new TextArea();
        textArea.setText(dictionary.getUnitString(pageIndex + 1));
        textArea.setEditable(false);
        textArea.setPrefHeight(Integer.MAX_VALUE);
        unitText.setText("Unit " + (pageIndex + 1));
        return textArea;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        unitSelector.setPageCount(dictionary.getNumberOfUnits());
        unitSelector.setPageFactory(this::createPage);
    }
}
