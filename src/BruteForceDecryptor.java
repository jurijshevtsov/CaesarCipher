public class BruteForceDecryptor {

    private Cipher cipher;

    public BruteForceDecryptor(Cipher cipher) {
        this.cipher = cipher;
    }

    public int decrypt(String ciphertext) {
        for (int key = 1; key < cipher.getAlphabetSize(); key++) {
            String decryptedText = cipher.decrypt(ciphertext, key);
           // System.out.println("key = " + key + " decryptedText =  " +decryptedText); //додав тимчасово щоб перевірити припущення
            if (isDecryptionSuccessful(decryptedText)) {
                return key;
            }
        }
        return -1;
    }

    private boolean isDecryptionSuccessful(String text) {
        //return text.contains(". "); //багато ложних спрацювань
        String[] sentences = text.split(",");
        int index = 0;

        for (String sentence : sentences) {
            if (sentences.length < 2) {
                return false;
            }

            // Пропустіть першу стрічку
            if (index++ == 0) {
                continue;
            }

            if (sentence.isEmpty()) {
                continue;
            }

            char firstChar = sentence.charAt(0);
            if (!(firstChar == ' ' || firstChar == '\t' || firstChar == '\n' || firstChar == '\r' || firstChar == '\f')) {
                return false;
            }
        }
        return true;
    }
}
