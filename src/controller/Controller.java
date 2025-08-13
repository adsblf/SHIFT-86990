package controller;

import cli.CliArgs;
import fileio.*;
import datasort.DataSort;
import statistic.*;

import java.util.HashMap;
import java.util.List;

public class Controller {
    public static void runApplication(String[] args) {
        CliArgs cliArgs = new CliArgs(args);
        List<String> data = FileReader.readDataFromTxt(cliArgs.getFiles());
        HashMap<String, List<?>> sortedData = DataSort.sortData(data);

        writeData(cliArgs, sortedData);
        getStatistics(cliArgs, sortedData);
    }

    private static void getStatistics(CliArgs cliArgs, HashMap<String, List<?>> sortedData) {
        if (cliArgs.isFullStatistics() && cliArgs.isBriefStatistics()) {
            showShortStatistic(sortedData);
            System.out.println();
            showFullStatistics(sortedData);
        } else if (cliArgs.isBriefStatistics()) {
            showShortStatistic(sortedData);
        } else if (cliArgs.isFullStatistics()) {
            showFullStatistics(sortedData);
        }
    }

    private static void showShortStatistic(HashMap<String, List<?>> sortedData) {
        System.out.println(
                "Краткая статистика:\n" +
                "Integers:\n" + new NumbersStatistic<>((List<Integer>) sortedData.get("int")).getShortStatistic() + "\n\n" +
                        "Floats:\n" + new NumbersStatistic<>((List<Float>) sortedData.get("float")).getShortStatistic() + "\n\n" +
                        "Strings:\n" + new StringsStatistic((List<String>) sortedData.get("String")).getShortStatistic() + "\n"
        );

    }

    private static void showFullStatistics(HashMap<String, List<?>> sortedData) {
        System.out.println(
                "Полная статистика:\n" +
                "Integers:\n" + new NumbersStatistic<>((List<Integer>) sortedData.get("int")).getFullStatistics() + "\n\n" +
                        "Floats:\n" + new NumbersStatistic<>((List<Float>) sortedData.get("float")).getFullStatistics() + "\n\n" +
                        "Strings:\n" + new StringsStatistic((List<String>) sortedData.get("String")).getFullStatistics() + "\n"
        );
    }

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
