package Model;

import java.util.ArrayList;

public class Settings {
    public static Settings settings = new Settings();

    public static Settings getInstance() {
        return settings;
    }

    public enum PhraseType {ENGLISH, TRANSLITERATION, PERSIAN}

    private PhraseType questionType;
    private ArrayList<PhraseType> answerTypes;
    private ArrayList<Integer> includedUnits;

    private Settings() {
        questionType = PhraseType.TRANSLITERATION;
        answerTypes = new ArrayList<>();
        answerTypes.add(PhraseType.ENGLISH);
        includedUnits = new ArrayList<>();
    }

    public String toString() {
        String s = "";

        s += "\nQuestion: \n\t" + questionType + "\n";
        s += "Answer Types: \n";
        for (PhraseType type : answerTypes) {
            s += "\t" + type + "\n";
        }
        s += "Included Units: \n";
        for (int unit : includedUnits) {
            s += "\t" + unit + "\n";
        }

        return s;
    }

    public PhraseType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(PhraseType questionType) {
        this.questionType = questionType;
    }

    public ArrayList<PhraseType> getAnswerTypes() {
        return answerTypes;
    }

    public void setAnswerTypes(ArrayList<PhraseType> answerTypes) {
        this.answerTypes = answerTypes;
    }

    public ArrayList<Integer> getIncludedUnits() {
        return includedUnits;
    }

    public void setIncludedUnits(ArrayList<Integer> includedUnits) {
        this.includedUnits = includedUnits;
    }

}
