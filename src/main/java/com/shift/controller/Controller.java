package com.shift.controller;

import com.shift.cli.CliArgs;
import com.shift.fileio.*;
import com.shift.datasort.DataSort;
import com.shift.statistic.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

/**
 * Класс предназначен для управления логикой программы и сбора воедино всех ее классов.
 */

public class Controller {
    /**
     * Запускает приложение с заданными аргументами командной строки.
     * 1. Создает объект {@link CliArgs}, содержащий переданные в командной строке параметры.
     * 2. Считывает данные из указанных файлов с помощью {@link FileReader#readDataFromTxt(List)}.
     * 3. Сортирует данные по типу при помощи {@link DataSort#sortData(List)}.
     * 4. Записывает отсортированные данные в файл.
     * 5. Выводит статистику (если -s или -f).
     *
     * @param args - массив строковых аргументов переданных в командной строке.
     */
    public static void runApplication(String[] args) {
        CliArgs cliArgs = new CliArgs(args);
        List<String> data = FileReader.readDataFromTxt(cliArgs.getFiles());
        HashMap<String, List<?>> sortedData = DataSort.sortData(data);
        writeData(cliArgs, sortedData);
        getStatistics(cliArgs, sortedData);
    }

    /**
     * Функция, которая отвечает за вызов соответствующих методов по отображению статистики
     * в зависимости от переданных параметров.
     *
     * @param cliArgs - объект содержащий параметры из cli.
     * @param sortedData - мапа, содержащая отсортированные данные (int, float, String).
     */
    private static void getStatistics(CliArgs cliArgs, HashMap<String, List<?>> sortedData) {
        if (cliArgs.isFullStatistics() && cliArgs.isShortStatistics()) {
            showShortStatistic(sortedData);
            System.out.println();
            showFullStatistics(sortedData);
        } else if (cliArgs.isShortStatistics()) {
            showShortStatistic(sortedData);
        } else if (cliArgs.isFullStatistics()) {
            showFullStatistics(sortedData);
        }
    }

    /**
     * Вывод краткой статистики в консоль.
     *
     * @param sortedData - мапа, содержащая отсортированные данные (int, float, String).
     */
    private static void showShortStatistic(HashMap<String, List<?>> sortedData) {
        System.out.println(
                "Краткая статистика:\n" +
                        "Integers:\n" + new NumbersStatistic<>((List<BigInteger>) sortedData.get("int")).getShortStatistic() + "\n\n" +
                        "Floats:\n" + new NumbersStatistic<>((List<BigDecimal>) sortedData.get("float")).getShortStatistic() + "\n\n" +
                        "Strings:\n" + new StringsStatistic((List<String>) sortedData.get("String")).getShortStatistic() + "\n"
        );
    }

    /**
     * Вывод полной статистики в консоль.
     *
     * @param sortedData - мапа, содержащая отсортированные данные (int, float, String).
     */
    private static void showFullStatistics(HashMap<String, List<?>> sortedData) {
        System.out.println(
                "Полная статистика:\n" +
                        "Integers:\n" + new NumbersStatistic<>((List<BigInteger>) sortedData.get("int")).getFullStatistics() + "\n\n" +
                        "Floats:\n" + new NumbersStatistic<>((List<BigDecimal>) sortedData.get("float")).getFullStatistics() + "\n\n" +
                        "Strings:\n" + new StringsStatistic((List<String>) sortedData.get("String")).getFullStatistics() + "\n"
        );
    }

    /**
     * Записывает данные в файлы в соответствии с их типом с помощью
     * {@link FileWriter#writeDataToTxt(List, String, String, String, boolean)}
     *
     * @param cliArgs - объект содержащий параметры из cli.
     * @param sortedData - мапа, содержащая отсортированные данные (int, float, String).
     */
    private static void writeData(CliArgs cliArgs, HashMap<String, List<?>> sortedData) {
        // Запись String
        FileWriter.writeDataToTxt(sortedData.get("String"),
                cliArgs.getOutputPath(),
                cliArgs.getPrefix(),
                "strings.txt",
                cliArgs.isAddingMode());

        // Запись Integer
        FileWriter.writeDataToTxt(sortedData.get("int"),
                cliArgs.getOutputPath(),
                cliArgs.getPrefix(),
                "integers.txt",
                cliArgs.isAddingMode());

        // Запись Float
        FileWriter.writeDataToTxt(sortedData.get("float"),
                cliArgs.getOutputPath(),
                cliArgs.getPrefix(),
                "floats.txt",
                cliArgs.isAddingMode());
    }
}
