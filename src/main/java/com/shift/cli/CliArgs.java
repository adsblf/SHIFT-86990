package com.shift.cli;

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
    private final boolean shortStatistics;
    private final boolean fullStatistics;
    private final List<String> files;

    public CliArgs(String[] args) {
        ArrayList<String> arrayListArgs = new ArrayList<>(Arrays.asList(args));
        this.outputPath = getOutputPathFromArgs(arrayListArgs);
        this.prefix = getPrefixFromArgs(arrayListArgs);
        this.addingMode = isAddingMode(arrayListArgs);
        this.shortStatistics = isShortStatistics(arrayListArgs);
        this.fullStatistics = isFullStatistics(arrayListArgs);
        this.files = findAllFilesInArgs(arrayListArgs);
    }

    /**
     * Определяет путь для сохранения файлов, в зависимости от наличия параметра "-o" его аргумента.
     *
     * @param arrayListArgs - список параметров, полученных из командной строки при запуске программы (String[] args).
     * @return строка с путем для сохранения файлов.
     */
    private String getOutputPathFromArgs(ArrayList<String> arrayListArgs) {
        if (arrayListArgs.contains("-o"))
            return arrayListArgs.get(arrayListArgs.indexOf("-o") + 1);
        else
            return "";
    }

    /**
     * Определяет префикс, в зависимости от наличия параметра "-p" и его аргумента.
     *
     * @param arrayListArgs - список параметров, полученных из командной строки при запуске программы (String[] args).
     * @return строка с префиксом.
     */
    private String getPrefixFromArgs(ArrayList<String> arrayListArgs) {
        if (arrayListArgs.contains("-p"))
            return arrayListArgs.get(arrayListArgs.indexOf("-p") + 1);
        else
            return "";
    }

    /**
     * Определяет, указан ли режим добавления в файл (параметр "-a").
     *
     * @param arrayListArgs - список параметров, полученных из командной строки при запуске программы (String[] args).
     * @return boolean указан режим добавления или нет.
     */
    private boolean isAddingMode(ArrayList<String> arrayListArgs) {
        return arrayListArgs.contains("-a");
    }

    /**
     * Определяет, нужно ли отобразить краткую статистику (параметр "-s").
     *
     * @param arrayListArgs - список параметров, полученных из командной строки при запуске программы (String[] args).
     * @return boolean отобрать краткую статистику или нет.
     */
    private boolean isShortStatistics(ArrayList<String> arrayListArgs) {
        return arrayListArgs.contains("-s");
    }

    /**
     * Определяет, нужно ли отобразить полную статистику (параметр "-f").
     *
     * @param arrayListArgs - список параметров, полученных из командной строки при запуске программы (String[] args).
     * @return boolean отобрать полную статистику или нет.
     */
    private boolean isFullStatistics(ArrayList<String> arrayListArgs) {
        return arrayListArgs.contains("-f");
    }

    /**
     * Ищет все переданные файлы в формате .txt, данные из которых нужно отсортировать.
     *
     * @param arrayListArgs - список параметров, полученных из командной строки при запуске программы (String[] args).
     * @return список полных/относительных путей до файлов.
     */
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

    public boolean isShortStatistics() {
        return shortStatistics;
    }

    public boolean isFullStatistics() {
        return fullStatistics;
    }

    public List<String> getFiles() {
        return files;
    }
}
