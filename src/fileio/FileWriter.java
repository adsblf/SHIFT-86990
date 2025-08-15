package fileio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileWriter {
    public static <T> void writeDataToTxt(List<T> arrayList, String outputPath, String prefix, String fileName, boolean addingMode) {
        if (arrayList.isEmpty())
            return;

        File f = new File(outputPath);
        if (!f.exists())
            f.mkdirs();

        try {
            File outputFile = new File(outputPath);
            Path path;

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
