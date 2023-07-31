public enum Alphabet {
    UKRAINIAN("абвгдеєжзиіїйклмнопрстуфхцчшщьюя.,\"\":-!? "),
    ENGLISH("abcdefghijklmnopqrstuvwxyz.,\"\":-!? ");

    private final String characters;

    Alphabet(String characters) {
        this.characters = characters;
    }

    public String getCharacters() {
        return this.characters;
    }
}
