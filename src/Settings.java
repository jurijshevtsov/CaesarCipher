import java.util.Scanner;

public class Settings {
    private Alphabet alphabet;
    private CipherAction action;
    private DecryptionMethod decryptionMethod;
    private int key;
    private String inputFilePath;
    private String referenceFilePath;



    public Settings(String[] args) {
        parseArguments(args);

        if (alphabet == null) {
            askForAlphabet();
        }

        if (action == null) {
            askForAction();
        }

        if (inputFilePath == null) {
            askForInputFilePath();
        }

        if (action == CipherAction.ENCRYPT && key == 0) {
            askForKey();
        }

        if (action == CipherAction.DECRYPT && decryptionMethod == null) {
            askForDecryptionMethod();
        }

        if (action == CipherAction.DECRYPT && decryptionMethod == DecryptionMethod.KEY && key == 0) {
            askForKey();
        }


        if (action == CipherAction.DECRYPT && decryptionMethod == DecryptionMethod.STATISTICAL && referenceFilePath == null) {
            askForReferenceFilePath();
        }
    }

   // Гетери
    public Alphabet getAlphabet() {
        return alphabet;
    }

    public CipherAction getAction() {
        return action;
    }

    public DecryptionMethod getDecryptionMethod() {
        return decryptionMethod;
    }

    public int getKey() {
        return key;
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public String getReferenceFilePath() {
        return referenceFilePath;
    }

    private void parseArguments(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-alphabet":
                    try {
                        alphabet = Alphabet.valueOf(args[i+1].toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid language parameter. Please try again.");
                    }
                    break;
                case "-action":
                    try {
                        action = CipherAction.valueOf(args[i+1].toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid action parameter. Please try again.");
                    }
                    break;
                case "-inputFilePath":
                    inputFilePath = args[i+1];
                    break;
                case "-key":
                    try {
                        key = Integer.parseInt(args[i+1]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid key parameter. Please try again.");
                    }
                    break;
                case "-decryptionMethod":
                    try {
                        decryptionMethod = DecryptionMethod.valueOf(args[i+1].toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid decryption method parameter. Please try again.");
                    }
                    break;
                case "-referenceFilePath":
                    referenceFilePath = args[i+1];
                    break;
            }
        }
    }

    private void askForAlphabet() {
        while (alphabet == null) {
            System.out.println("Which language would you like to use? (Enter 'UKRAINIAN' or 'ENGLISH'):");
            try {
                Scanner languageScanner = new Scanner(System.in);
                alphabet = Alphabet.valueOf(languageScanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid language. Please try again.");
            }
        }
    }

    private void askForAction() {
        while (action == null) {
            System.out.println("Would you like to encrypt or decrypt a file? (Enter 'encrypt' or 'decrypt'):");
            try {
                Scanner actionScanner = new Scanner(System.in);
                action = CipherAction.valueOf(actionScanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid action. Please try again.");
            }
        }
    }

    private void askForInputFilePath() {
        System.out.println("Enter the path to the file you would like to encrypt/decrypt:");
        Scanner filePathScanner = new Scanner(System.in);
        inputFilePath = filePathScanner.nextLine();
    }

    private void askForKey() {
        while (key == 0) {
            System.out.println("Enter the encryption key:");
            try {
                Scanner keyScanner = new Scanner(System.in);
                key = Integer.parseInt(keyScanner.nextLine());
                System.out.println("Key entered: " + key); // Додаткове виведення для перевірки
            } catch (NumberFormatException e) {
                System.out.println("Invalid key. Please try again.");
            }
        }
    }

    private void askForDecryptionMethod() {
        while (decryptionMethod == null) {
            System.out.println("Which decryption method would you like to use? (Enter 'key', 'bruteForce' or 'statistical'):");
            try {
                Scanner methodScanner = new Scanner(System.in);
                decryptionMethod = DecryptionMethod.valueOf(methodScanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid method. Please try again.");
            }
        }
    }

    private void askForReferenceFilePath() {
        System.out.println("Enter the path to the reference file for statistical decryption:");
        Scanner referenceFilePathScanner = new Scanner(System.in);
        referenceFilePath = referenceFilePathScanner.nextLine();
    }
}
