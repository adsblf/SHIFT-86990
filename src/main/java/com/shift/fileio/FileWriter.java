package main.java.com.shift.fileio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Класс для записи данных в файл
 */
public class FileWriter {
    /**
     * Отвечает за запись переданного списка данных в указанный .txt-файл.
     *
     * @param arrayList - список данных для записи.
     * @param outputPath - путь для записи.
     * @param prefix - префикс в названии файла, например "prefix-".
     * @param fileName - имя файла.
     * @param addingMode - режим добавления в файл.
     * @param <T> - тип переданных данных для записи.
     */
    public static <T> void writeDataToTxt(List<T> arrayList, String outputPath, String prefix, String fileName, boolean addingMode) {
        if (arrayList.isEmpty())
            return;

        // Создать директории, если они отсутствуют.
        File f = new File(outputPath);
        if (!f.exists())
            f.mkdirs();

        try {
            File outputFile = new File(outputPath);
            Path path;

            // Если переданный путь абсолютен => добавить к пути имя файла с префиксом.
            // Если переданный путь относителен => получить текущую директорию, добавить относительный путь, добавить
            // имя файла с префиксом.
            if (outputFile.isAbsolute()) {
                path = Paths.get(outputFile.getAbsolutePath(), prefix + fileName);
            } else {
                String curDir = System.getProperty("user.dir");
                path = Paths.get(curDir,outputPath, prefix + fileName);
            }

            if (addingMode)
                if(!Files.exists(path))
                    Files.write(path, arrayList.stream().map(Object::toString).toList());
                else
                    Files.write(path, arrayList.stream().map(Object::toString).toList(), StandardOpenOption.APPEND);
            else
                Files.write(path, arrayList.stream().map(Object::toString).toList());
        } catch (IOException e) {
            System.out.println("Не удалось записать файл: " + (prefix + fileName) + " в директорию: " + outputPath);
            System.out.println(e.getMessage());
        }

    }
}
