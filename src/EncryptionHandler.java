public class EncryptionHandler {

    public void handleEncryption (String inputFilePath, Cipher cipher, int key, FileReaderService fileReaderService, FileWriterService fileWriterService) {
        String textToEncrypt = fileReaderService.readFile(inputFilePath);
        if (textToEncrypt != null) {
            String encryptedText = cipher.encrypt(textToEncrypt, key);
            String outputFilePath = inputFilePath.replace(".txt", "") + "_ENCRYPT.txt";
            boolean isWritten = fileWriterService.writeFile(outputFilePath, encryptedText);
            if (!isWritten) {
                System.out.println("Failed to write to file: " + outputFilePath);
            }
        } else {
            System.out.println("Couldn't read file for encryption: " + inputFilePath);
        }
    }

}
