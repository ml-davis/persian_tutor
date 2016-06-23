package Controller;

import Model.Phrase;
import Model.QuizPhrases;
import View.PopupWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import Main.Main;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UnknownPhraseController implements Initializable {

    @FXML
    private TextArea textArea;

    @FXML
    private Text percentCorrect;

    private QuizPhrases quizPhrases = QuizPhrases.getInstance();

    public void goToBrowserPage() {
        Main.setPage(getClass().getResource("/View/BrowserPage.fxml"));
    }

    @FXML
    public void initializeQuiz() {
        if (quizPhrases.getUnknownPhrases().size() == 0) {
            PopupWindow window = new PopupWindow("There are no unknown phrases to be quizzed on.\n" +
                    "Click exit to return to main page.");
            window.display();
        } else {
            Main.setPage(getClass().getResource("/View/QuizzerPage.fxml"));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int count = 1;
        percentCorrect.setText("Percent Correct = " + quizPhrases.getPercentCorrectString());
        if (quizPhrases.getUnknownPhrases().size() == 0) {
            textArea.setText("Congratulations!! You solved them all!");
        } else {
            for (Phrase phrase : quizPhrases.getUnknownPhrases()) {
                textArea.appendText(count++ + "\t" + phrase.getEnglishText() + "\n\t" + phrase.getTransliteration() +
                        "\n\n");
            }
        }
    }
}
