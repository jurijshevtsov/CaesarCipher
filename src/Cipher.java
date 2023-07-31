public class Cipher {

    private Alphabet alphabet;

    public Cipher(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    public String encrypt(String plaintext, int key) {
        StringBuilder ciphertext = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            if (alphabet.getCharacters().contains(String.valueOf(c))) {
                int oldPosition = alphabet.getCharacters().indexOf(c);
                int newPosition = (oldPosition + key) % alphabet.getCharacters().length();
                ciphertext.append(alphabet.getCharacters().charAt(newPosition));
            }
        }
        return ciphertext.toString();
    }

    public String decrypt(String ciphertext, int key) {
        StringBuilder plaintext = new StringBuilder();
        for (char c : ciphertext.toCharArray()) {
            if (alphabet.getCharacters().contains(String.valueOf(c))) {
                int oldPosition = alphabet.getCharacters().indexOf(c);
                int newPosition = (oldPosition - key) % alphabet.getCharacters().length();
                if (newPosition < 0) {
                    newPosition += alphabet.getCharacters().length();
                }
                plaintext.append(alphabet.getCharacters().charAt(newPosition));
            }
        }
        return plaintext.toString();
    }

    public int getAlphabetSize() {
        return alphabet.getCharacters().length();
    }

    public String getAlphabet() {
        return this.alphabet.getCharacters();
    }

}