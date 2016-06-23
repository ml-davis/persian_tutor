package Model;

import java.util.ArrayList;
import java.util.Random;

public class QuizPhrases {

    private static QuizPhrases quizPhrases = new QuizPhrases();

    public static QuizPhrases getInstance() {
        return quizPhrases;
    }

    private Settings settings = Settings.getInstance();

    private ArrayList<Phrase> currentPhrases;
    private Phrase currentPhrase;
    private int quizSize;
    private int phraseCounter;
    private ArrayList<Phrase> unknownPhrases;

    private QuizPhrases() {
        currentPhrases = new ArrayList<>();
        phraseCounter = 0;
        unknownPhrases = new ArrayList<>();
    }

    public void loadPhrases() {
        currentPhrases.clear();
        phraseCounter = 0;

        if (unknownPhrases.size() > 0) {
            currentPhrases.addAll(unknownPhrases);
        } else {
            Dictionary dictionary = Dictionary.getInstance();
            for (int unit : settings.getIncludedUnits()) {
                currentPhrases.addAll(dictionary.getUnit(unit));
            }
        }

        unknownPhrases.clear();
        quizSize = currentPhrases.size();
    }

    public String getQuestion() {
        loadNextPhrase();

        switch (settings.getQuestionType()) {
            case TRANSLITERATION: return currentPhrase.getTransliteration();
            case ENGLISH: return currentPhrase.getEnglishText();
            case PERSIAN: return currentPhrase.getPersianText();
        }

        return "Error";
    }

    public ArrayList<String> getAnswers() {
        ArrayList<String> answers = new ArrayList<>();
        ArrayList<Settings.PhraseType> answerTypes = settings.getAnswerTypes();

        if (answerTypes.contains(Settings.PhraseType.ENGLISH)) {
            answers.add(currentPhrase.getEnglishText());
        }
        if (answerTypes.contains(Settings.PhraseType.TRANSLITERATION)) {
            answers.add(currentPhrase.getTransliteration());
        }
        if (answerTypes.contains(Settings.PhraseType.PERSIAN)) {
            answers.add(currentPhrase.getPersianText());
        }

        return answers;

    }

    public void addUnknownPhrase(Phrase unknownPhrase) {
        unknownPhrases.add(unknownPhrase);
    }

    public String getPhraseCounterString() {
        if (phraseCounter > quizSize) {
            phraseCounter = 0;
        }

        return phraseCounter++ + "/" + quizSize;
    }

    public String getUnknownPhraseCounterString() {
        return String.valueOf(unknownPhrases.size());
    }

    public String getPercentCorrectString() {

        int numberOfQuestionsAsked = phraseCounter - 1;
        int amountCorrect = numberOfQuestionsAsked - unknownPhrases.size();

        if (numberOfQuestionsAsked != 0) {
            double percentCorrect = (double) amountCorrect/numberOfQuestionsAsked * 100;
            return String.format("%3.2f%%", percentCorrect);
        } else {
            return "-";
        }
    }

    private void loadNextPhrase() {
        if (currentPhrases.size() == 0) {
            loadPhrases();
        }

        Random random = new Random();
        int index = random.nextInt(currentPhrases.size());
        currentPhrase = currentPhrases.remove(index);
    }

    public Phrase getCurrentPhrase() {
        return currentPhrase;
    }

    public int size() {
        return currentPhrases.size();
    }

    public ArrayList<Phrase> getUnknownPhrases() {
        return unknownPhrases;
    }
}
