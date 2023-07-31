import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class StatisticalAnalysisDecryption {

    private Cipher cipher;
    private String referenceText;

    public StatisticalAnalysisDecryption(Cipher cipher, String referenceText) {
        this.cipher = cipher;
        this.referenceText = referenceText;
    }

    public int findKey(String encryptedText) {
        char mostCommonChar = getMostCommonChar(encryptedText);
        char mostCommonCharInReference = getMostCommonChar(referenceText);
        int key = cipher.getAlphabet().indexOf(mostCommonChar) - cipher.getAlphabet().indexOf(mostCommonCharInReference);

        return key;
    }

    private char getMostCommonChar(String text) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        //порахуємо скільки разів зустрічається кожна буква
        for (char c : text.toCharArray()) {
            if (frequencyMap.containsKey(c)) {
                frequencyMap.put(c, frequencyMap.get(c) + 1);
            } else {
                frequencyMap.put(c, 1);
            }
        }

        //Знайдем букву яка чаще всього зустрічається
        char mostFrequentChar = Character.MIN_VALUE;
        int maxFrequency = Integer.MIN_VALUE;
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                mostFrequentChar = entry.getKey();
            }
        }

        return mostFrequentChar;
    }
}
