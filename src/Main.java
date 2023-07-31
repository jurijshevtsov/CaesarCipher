// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        System.out.printf("Hello Cipher!");

        Settings settings = new Settings(args);
        CipherAction action = settings.getAction();
        String inputFilePath = settings.getInputFilePath();
        Alphabet alphabet = settings.getAlphabet();
        int key = settings.getKey();
        DecryptionMethod decryptionMethod = settings.getDecryptionMethod();
        String referenceFilePath = settings.getReferenceFilePath();

        Cipher cipher = new Cipher(alphabet);
        FileReaderService fileReaderService = new FileReaderService();
        FileWriterService fileWriterService = new FileWriterService();
        DecryptionHandler decryptionHandler = new DecryptionHandler();
        EncryptionHandler encryptionHandler = new EncryptionHandler();


        switch (action) {
            case ENCRYPT:
                encryptionHandler.handleEncryption(inputFilePath, cipher, key, fileReaderService, fileWriterService);
                break;
            case DECRYPT:
                switch (decryptionMethod) {
                    case KEY:
                        decryptionHandler.handleKeyDecryption(inputFilePath, cipher, key, fileReaderService, fileWriterService);
                        break;
                    case BRUTE_FORCE:
                        decryptionHandler.handleBruteForceDecryption(inputFilePath, cipher, fileReaderService, fileWriterService);
                        break;
                    case STATISTICAL:
                        decryptionHandler.handleStatisticalDecryption(inputFilePath, referenceFilePath, cipher, fileReaderService, fileWriterService);
                        break;
                }
                break;
        }

    }
}
