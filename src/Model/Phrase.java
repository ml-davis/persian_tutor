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

    private boolean queryContainsWord(String query, String comparison) {
        String[] words = comparison.split(" ");
        for (String word : words) {
            if (word.equalsIgnoreCase(query)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsWord(String query) {
        return queryContainsWord(query, englishText) || queryContainsWord(query, transliteration);
    }

    private boolean queryBeginsWith(String query, String comparison) {
        if (comparison.length() >= query.length()) {
            String beginning = comparison.substring(0, query.length());
            if (beginning.equalsIgnoreCase(query)) {
                return true;
            }
        }
        return false;
    }

    public boolean beginsWith(String query) {
        return queryBeginsWith(query, englishText) || queryBeginsWith(query, transliteration);
    }

    public boolean contains(String query) {
        query = query.toLowerCase();
        return englishText.toLowerCase().contains(query) || transliteration.toLowerCase().contains(query);
    }
}

