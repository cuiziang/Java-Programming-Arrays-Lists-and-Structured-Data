import edu.duke.*;

public class WordPlay {

	public boolean isVowel(char ch) {
        if ("aeiou".indexOf(Character.toLowerCase(ch)) != -1) {
            return true;
        } else {
            return false;
        }
    }

    public String replaceVowels(String phrase, char ch) {
        StringBuilder s = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            if ("aeiou".indexOf(Character.toLowerCase(s.charAt(i))) != -1) {
                s.setCharAt(i, ch);
            }
        }
        return s.toString();
    }

    public String emphasize(String phrase, char ch) {
        StringBuilder s = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            if (s.charAt(i) == ch) {
                if (i % 2 == 0) {
                    s.setCharAt(i, '*');
                }
                if (i % 2 == 1) {
                    s.setCharAt(i, '+');
                }
            }
        }
        return s.toString();
    }
}

