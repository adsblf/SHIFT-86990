package cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс предназначен для хранения параметров вводимых из командной строки при запуске программы:
 * "-o" - устанавливает путь для сохранения файлов;
 * "-p" - задает префикс для названия файлов;
 * "-a" - режим добавления в файл;
 * "-s" - краткая статистика (количество записанных элементов);
 * "-f" - полная статистика (для строк: количество, макс и мин длина / для чисел: количество, макс, мин, сумм, ср.зн).
 */
public class CliArgs {
    private final String outputPath;
    private final String prefix;
    private final boolean addingMode;
    private final boolean briefStatistics;
    private final boolean fullStatistics;
    private final List<String> files;

    public CliArgs(String[] args) {
        ArrayList<String> arrayListArgs = new ArrayList<>(Arrays.asList(args));
        this.outputPath = getOutputPathFromArgs(arrayListArgs);
        this.prefix = getPrefixFromArgs(arrayListArgs);
        this.addingMode = isAddingMode(arrayListArgs);
        this.briefStatistics = isBriefStatistics(arrayListArgs);
        this.fullStatistics = isFullStatistics(arrayListArgs);
        this.files = findAllFilesInArgs(arrayListArgs);
    }

    private String getOutputPathFromArgs(ArrayList<String> arrayListArgs) {
        if (arrayListArgs.contains("-o"))
            return arrayListArgs.get(arrayListArgs.indexOf("-o") + 1);
        else
            return "";
    }

    private String getPrefixFromArgs(ArrayList<String> arrayListArgs) {
        if (arrayListArgs.contains("-p"))
            return arrayListArgs.get(arrayListArgs.indexOf("-p") + 1);
        else
            return "";
    }

    private boolean isAddingMode(ArrayList<String> arrayListArgs) {
        return arrayListArgs.contains("-a");
    }

    private boolean isBriefStatistics(ArrayList<String> arrayListArgs) {
        return arrayListArgs.contains("-s");
    }

    private boolean isFullStatistics(ArrayList<String> arrayListArgs) {
        return arrayListArgs.contains("-f");
    }

    private List<String> findAllFilesInArgs(ArrayList<String> arrayListArgs) {
        return arrayListArgs.stream().filter(str -> str.contains(".txt")).toList();
    }

    public String getOutputPath() {
        return outputPath;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean isAddingMode() {
        return addingMode;
    }

    public boolean isBriefStatistics() {
        return briefStatistics;
    }

    public boolean isFullStatistics() {
        return fullStatistics;
    }

    public List<String> getFiles() {
        return files;
    }
}
