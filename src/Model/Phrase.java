package Model;

public class Phrase {
    private String persianText;
    private String transliteration;
    private String englishText;

    public Phrase(String transliteration, String english) {
        persianText = "Sorry, Persian text is not implemented yet";
        this.transliteration = transliteration;
        this.englishText = english;
    }

    public Phrase(String transliteration, String english, String persianText) {
        this.persianText = persianText;
        this.transliteration = transliteration;
        this.englishText = english;
    }

    public String toString() {
        return this.englishText + ", " + this.transliteration;
    }

    public String getPersianText() {
        return persianText;
    }

    public String getTransliteration() {
        return transliteration;
    }

    public String getEnglishText() {
        return englishText;
    }

    public boolean isPerfectMatch(String query) {
        return englishText.equalsIgnoreCase(query) || transliteration.equalsIgnoreCase(query);
    }

    public boolean containsWord(String query) {
        String[] englishWords = englishText.split(" ");
        String[] transliterationWords = transliteration.split(" ");
        for (String word : englishWords) {
            if (word.equalsIgnoreCase(query)) {
                return true;
            }
        }
        for (String word : transliterationWords) {
            if (word.equalsIgnoreCase(query)) {
                return true;
            }
        }
        return false;
    }

    public boolean beginsWith(String query) {
        if (englishText.length() >= query.length()) {
            String englishBeginning = englishText.substring(0, query.length());
            if (englishBeginning.equalsIgnoreCase(query)) {
                return true;
            }
        }
        if (transliteration.length() >= query.length()) {
            String transliterationBeginning = transliteration.substring(0, query.length());
            if (transliterationBeginning.equalsIgnoreCase(query)) {
                return true;
            }
        }

        return false;
    }

    public boolean contains(String query) {
        query = query.toLowerCase();
        return englishText.toLowerCase().contains(query) || transliteration.toLowerCase().contains(query);
    }
}

