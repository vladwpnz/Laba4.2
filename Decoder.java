import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Decoder {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    private static final Pattern VOWELS_PATTERN = Pattern.compile("[aeiou]");
    private static final Pattern CONSONANTS_PATTERN = Pattern.compile("[^aeiou]");

    public static String decode(String message) {
        String[] words = message.split(" ");
        StringBuilder decodedMessage = new StringBuilder();

        for (String word : words) {
            decodedMessage.append(decodeWord(word));
            decodedMessage.append(" ");
        }

        return decodedMessage.toString().trim();
    }

    private static String decodeWord(String word) {
        Matcher vowelsMatcher = VOWELS_PATTERN.matcher(word);
        Matcher consonantsMatcher = CONSONANTS_PATTERN.matcher(word);

        if (vowelsMatcher.find()) {
            return decodeWordWithVowelSubstitution(word);
        } else if (consonantsMatcher.find()) {
            return decodeWordWithConsonantSubstitution(word);
        } else {
            return word;
        }
    }

    private static String decodeWordWithVowelSubstitution(String word) {
        StringBuilder decodedWord = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (c == 'a') {
                decodedWord.append("1");
            } else if (c == 'e') {
                decodedWord.append("2");
            } else if (c == 'i') {
                decodedWord.append("3");
            } else if (c == 'o') {
                decodedWord.append("4");
            } else if (c == 'u') {
                decodedWord.append("5");
            } else {
                decodedWord.append(c);
            }
        }

        return decodedWord.toString();
    }

    private static String decodeWordWithConsonantSubstitution(String word) {
        StringBuilder decodedWord = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            int nextIndex = ALPHABET.indexOf(c) + 1;
            if (nextIndex >= ALPHABET.length()) {
                nextIndex = 0;
            }

            decodedWord.append(ALPHABET.charAt(nextIndex));
        }

        return decodedWord.toString();
    }

    public static void main(String[] args) {
        String message1 = "Laba";
        String message2 = "Chetverta";

        System.out.println("Декодування повідомлення 1: " + Decoder.decode(message1));
        System.out.println("Декодування повідомлення 2: " + Decoder.decode(message2));
    }
}
