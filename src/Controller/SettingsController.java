package Controller;

import Main.Main;
import Model.Dictionary;
import Model.Settings;
import View.PopupWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    private Settings settings = Settings.getInstance();

    @FXML
    private RadioButton englishQuestion, transliterationQuestion, persianQuestion;

    @FXML
    private CheckBox englishAnswer, transliterationAnswer, persianAnswer;

    @FXML
    private HBox unitBox;

    @FXML
    private CheckBox[] includedUnits = new CheckBox[20];

    @FXML
    public void englishQuestionPressed() {
        englishAnswer.setSelected(false);
        englishAnswer.setDisable(true);
        transliterationAnswer.setDisable(false);
        persianAnswer.setDisable(false);
    }

    @FXML
    public void transliterationQuestionPressed() {
        englishAnswer.setDisable(false);
        transliterationAnswer.setSelected(false);
        transliterationAnswer.setDisable(true);
        persianAnswer.setDisable(false);
    }

    @FXML
    public void persianQuestionPressed() {
        englishAnswer.setDisable(false);
        transliterationAnswer.setDisable(false);
        persianAnswer.setSelected(false);
        persianAnswer.setDisable(true);
    }

    @FXML
    public void cancelPressed() {
        // TODO: make sure cancel doesn't destroy progress of quiz
        Main.setPage(Main.getPreviousPage());
    }

    @FXML
    public void applyPressed() {

        if (settingsPageIncomplete()) {
            return;
        }

        setQuestionType();
        setAnswerTypes();
        setIncludedUnits();

        Main.setPage(getClass().getResource("/View/QuizzerPage.fxml"));

        System.out.println(settings);
    }

    private boolean settingsPageIncomplete() {
        if (!englishAnswer.isSelected() && !transliterationAnswer.isSelected() && !persianAnswer.isSelected()) {
            PopupWindow window = new PopupWindow("Please select an answer type");
            window.display();
            return true;
        }
        for (CheckBox unit : includedUnits) {
            if (unit.isSelected()) {
                return false;
            }
        }
        PopupWindow window = new PopupWindow("Please select your included units");
        window.display();

        return true;
    }

    private void setQuestionType() {
        if (englishQuestion.isSelected()) {
            settings.setQuestionType(Settings.PhraseType.ENGLISH);
        } else if (transliterationQuestion.isSelected()) {
            settings.setQuestionType(Settings.PhraseType.TRANSLITERATION);
        } else if (persianQuestion.isSelected()) {
            settings.setQuestionType(Settings.PhraseType.PERSIAN);
        }
    }

    private void setAnswerTypes() {
        ArrayList<Settings.PhraseType> answerTypes = new ArrayList<>();

        if (englishAnswer.isSelected()) {
            answerTypes.add(Settings.PhraseType.ENGLISH);
        }
        if (transliterationAnswer.isSelected()) {
            answerTypes.add(Settings.PhraseType.TRANSLITERATION);
        }
        if (persianAnswer.isSelected()) {
            answerTypes.add(Settings.PhraseType.PERSIAN);
        }

        settings.setAnswerTypes(answerTypes);
    }

    private void setIncludedUnits() {
        ArrayList<Integer> units = new ArrayList<>();

        for (int i = 0; i < includedUnits.length; i++) {
            if (includedUnits[i].isSelected()) {
                units.add(i + 1);
            }
        }

        settings.setIncludedUnits(units);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addUnitButtons();
        checkDefaultButtons();
    }

    private void checkDefaultButtons() {
        switch (settings.getQuestionType()) {
            case TRANSLITERATION:
                transliterationQuestion.setSelected(true);
                transliterationQuestionPressed();
                break;
            case ENGLISH:
                englishQuestion.setSelected(true);
                englishQuestionPressed();
                break;
            case PERSIAN:
                persianQuestion.setSelected(true);
                persianQuestionPressed();
                break;
        }
        for (Settings.PhraseType answerType : settings.getAnswerTypes()) {
            switch (answerType) {
                case TRANSLITERATION: transliterationAnswer.setSelected(true); break;
                case ENGLISH: englishAnswer.setSelected(true); break;
                case PERSIAN: persianAnswer.setSelected(true); break;
            }
        }
        for (int unit: settings.getIncludedUnits()) {
            includedUnits[unit - 1].setSelected(true);
        }
    }

    private void addUnitButtons() {
        int index = 0;
        for (int i = 1; i <= 4; i++) {
            VBox vBox = new VBox();
            vBox.setSpacing(5);
            for (int j = 1; j <= 5; j++) {
                HBox hBox = new HBox();
                includedUnits[index] = new CheckBox();
                if (!Dictionary.getInstance().hasUnit(index + 1)) {
                    includedUnits[index].setDisable(true);
                }
                Label label = new Label("Unit " + (index + 1));
                hBox.getChildren().addAll(includedUnits[index], label);
                vBox.getChildren().add(hBox);

                index++;
            }
            unitBox.getChildren().add(vBox);
        }
    }
}
