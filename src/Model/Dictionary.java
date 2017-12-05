package Model;

import java.util.ArrayList;

public class Dictionary {

    private static Dictionary dictionary = new Dictionary();

    public static Dictionary getInstance() {
        return dictionary;
    }

    private static final int UNIT_SIZE = 100;

    private ArrayList<Phrase> phrases;

    private Dictionary() {
        phrases = new ArrayList<>();
        loadDictionary();
    }

    public String toString() {
        return "Number of Units " + getNumberOfUnits() + "\nNumber of Phrases " + phrases.size();
    }

    public ArrayList<Phrase> getUnit(int unitNumber) {
        ArrayList<Phrase> unit = new ArrayList<>();
        int startIndex = (unitNumber - 1) * UNIT_SIZE;
        for (int i = startIndex; i < startIndex + UNIT_SIZE && i < phrases.size(); i++) {
            unit.add(phrases.get(i));
        }
        return unit;
    }

    public String getUnitString(int unit) {
        String s = "";
        int count = 1;
        ArrayList<Phrase> unitPhrases = getUnit(unit);
        for (Phrase phrase : unitPhrases) {
            s += String.format("%-5s%-60s%n%5s%-60s%n%n", count++ + ".", phrase.getEnglishText(), "", phrase.getTransliteration());
        }

        return s;
    }

    public String getSearchString(String query) {
        String perfectMatch = "";
        String containsWord = "";
        String beginsWith = "";
        String contains = "";

        for (int i = 0; i < phrases.size(); i++) {
            if (phrases.get(i).isPerfectMatch(query)) {
                perfectMatch += formatSearchResult(phrases.get(i), i + 1);
            } else if (phrases.get(i).containsWord(query)) {
                containsWord += formatSearchResult(phrases.get(i), i + 1);
            } else if (phrases.get(i).beginsWith(query)) {
                beginsWith += formatSearchResult(phrases.get(i), i + 1);
            } else if (phrases.get(i).contains(query)) {
                contains += formatSearchResult(phrases.get(i), i + 1);
            }
        }

        String result = perfectMatch + containsWord + beginsWith + contains;
        if (result.equals("")) {
            return "No results found";
        } else {
            return result;
        }
    }

    private String formatSearchResult(Phrase phrase, int count) {
        String position = "Unit " + Math.ceil((double) count / UNIT_SIZE) + ", " + "Phrase " + count % UNIT_SIZE;
        return String.format("%-70s%-20s%n%-70s%n%n", phrase.getEnglishText(), position, phrase.getTransliteration());
    }

    public int getNumberOfUnits() {
        return (int) Math.ceil((double) phrases.size() / UNIT_SIZE);
    }

    public boolean hasUnit(int unit) {
        return unit <= getNumberOfUnits();
    }

    public String escapeSingleQuotes(String str) {
        return str.replace("'", "\\'");
    }

    private void loadDictionary() {
        DataCollector collector = new DataCollector();
        collector.getPhrases(phrases);
    }
}
