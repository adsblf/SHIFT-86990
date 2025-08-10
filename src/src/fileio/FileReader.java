package fileio;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для чтения данных из файла
 */

public class FileReader {
    public static List<String> readDataFromTxt(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Не удалось открыть файл: " + filePath);
            System.out.println("Ошибка: " + e.getMessage());
            return Collections.EMPTY_LIST;
        }
    }

    public static List<String> readDataFromTxt(List<String> filePaths) {
        return filePaths.stream()
                .flatMap(filePath -> readDataFromTxt(filePath).stream())
                .collect(Collectors.toList());
    }
}
