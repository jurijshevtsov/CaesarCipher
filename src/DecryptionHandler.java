public class DecryptionHandler {
    public void handleKeyDecryption(String inputFilePath, Cipher cipher, int key, FileReaderService fileReaderService, FileWriterService fileWriterService) {
        String textToDecrypt = fileReaderService.readFile(inputFilePath);
        if (textToDecrypt != null) {
            String decryptedText = cipher.decrypt(textToDecrypt, key);
            String outputFilePath = inputFilePath.replace(".txt", "") + "_DECRYPT_" + key + ".txt";
            boolean isWritten = fileWriterService.writeFile(outputFilePath, decryptedText);
            if (!isWritten) {
                System.out.println("Failed to write to file: " + outputFilePath);
            }
        } else {
            System.out.println("Couldn't read file for decryption: " + inputFilePath);
        }
    }

    public void handleBruteForceDecryption(String inputFilePath, Cipher cipher, FileReaderService fileReaderService, FileWriterService fileWriterService) {
        BruteForceDecryptor bruteForceDecryptor = new BruteForceDecryptor(cipher);
        String textToDecrypt = fileReaderService.readFile(inputFilePath);
        int successfulKey = bruteForceDecryptor.decrypt(textToDecrypt);
        if (successfulKey != -1) {
            String decryptedText = cipher.decrypt(textToDecrypt, successfulKey);
            String outputFilePath = inputFilePath.replace(".txt", "") + "_DECRYPT_BF_" + successfulKey + ".txt";
            boolean isWritten = fileWriterService.writeFile(outputFilePath, decryptedText);
            if (!isWritten) {
                System.out.println("Failed to write to file: " + outputFilePath);
            }
        } else {
            System.out.println("Brute force decryption was not successful. Please try another method.");
        }
    }

    public void handleStatisticalDecryption(String inputFilePath, String referenceFilePath, Cipher cipher, FileReaderService fileReaderService, FileWriterService fileWriterService) {
        String statistics = fileReaderService.readFile(referenceFilePath);
        String textToDecrypt = fileReaderService.readFile(inputFilePath);
        if (statistics.isEmpty() || statistics.isBlank()) {
            System.out.println("Error: Cannot read the file with statistical data");
            return;
        }
        StatisticalAnalysisDecryption statisticalAnalysisDecryption = new StatisticalAnalysisDecryption(cipher, statistics);
        Integer possibleKey = statisticalAnalysisDecryption.findKey(textToDecrypt);

        if (possibleKey == null) {
            System.out.println("Error: Failed to find the decryption key with statistical analysis.");
            return;
        }

        String decryptedText = cipher.decrypt(textToDecrypt, possibleKey);
        String outputFilePath = inputFilePath.replace(".txt", "") + "_DECRYPT_STAT_" + possibleKey + ".txt";

        if (!fileWriterService.writeFile(outputFilePath, decryptedText)) {
            System.out.println("Error: Failed to write decrypted text to file.");
        }
    }
}
