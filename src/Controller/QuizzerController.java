package Controller;

import Main.Main;
import Model.QuizPhrases;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class QuizzerController implements Initializable {

    @FXML
    private Text phraseCounter, unknownPhraseCounter, percentCorrect, question, answer1, answer2;

    @FXML
    private CheckBox unknownPhraseCheckBox;

    @FXML
    private TextField textField;

    @FXML
    private VBox mainPanel;

    private QuizPhrases phrases = QuizPhrases.getInstance();

    @FXML
    public void quitClicked() {
        Main.setPage(getClass().getResource("/View/BrowserPage.fxml"));
    }

    @FXML
    public void optionsClicked() {
        Main.setPage(getClass().getResource("/View/SettingsPage.fxml"));
    }

    @FXML
    public void showSolution() {
        ArrayList<String> answers = phrases.getAnswers();
        answer1.setText(answers.get(0));
        if (answers.size() > 1) {
            answer2.setText(answers.get(1));
        }
    }

    @FXML
    public void getQuestion() {
        updateUnknownPhrases();
        if (phrases.size() == 0) {
            phrases.getPhraseCounterString();
            Main.setPage(getClass().getResource("/View/UnknownPhrasePage.fxml"));
        } else {
            question.setText(phrases.getQuestion());
            answer1.setText("");
            answer2.setText("");
            phraseCounter.setText(phrases.getPhraseCounterString());
            percentCorrect.setText(phrases.getPercentCorrectString());
            textField.setText("");
            textField.requestFocus();
        }
    }

    private void updateUnknownPhrases() {
        if (unknownPhraseCheckBox.isSelected()) {
            phrases.addUnknownPhrase(phrases.getCurrentPhrase());
            unknownPhraseCheckBox.setSelected(false);
            System.out.println("Adding: '" + phrases.getCurrentPhrase() + "' to unknown phrases");
        }
        unknownPhraseCounter.setText(phrases.getUnknownPhraseCounterString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        answer2 = new Text();
        textField = new TextField();
        textField.setPrefWidth(1000);
        phrases.loadPhrases();
        getQuestion();
        loadMainPanel();
    }

    private void loadMainPanel() {
        if (phrases.getAnswers().size() > 1) {
            mainPanel.getChildren().add(answer2);
        }
        mainPanel.getChildren().add(textField);
    }
}
