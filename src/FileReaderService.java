import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReaderService {

    public String readFile(String filePath) {
        Path path = Paths.get(filePath);
        try {
            return new String(Files.readAllBytes(path)).toLowerCase(); //костиль щоб перевести текст в нижній регістр. Не хлчу в алфавіті додавати великі букви
        } catch (IOException e) {
            System.out.println("Failed to read file: " + e.getMessage());
            return null;
        }
    }


}
