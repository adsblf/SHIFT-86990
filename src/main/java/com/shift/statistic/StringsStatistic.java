package com.shift.statistic;

import java.util.Comparator;
import java.util.List;

/**
 * Класс для сбора статистики из списка объектов типа String.
 */

public class StringsStatistic extends Statistic <String> {
    private String minString;
    private String maxString;

    public StringsStatistic(List<String> stringsList) {
        super(stringsList);
        this.minString = min(stringsList);
        this.maxString = max(stringsList);
    }

    /**
     * Функция поиска минимального элемента в списке.
     *
     * @param stringsList - список элементов.
     * @return минимальный элемент списка.
     */
    protected String min(List<String> stringsList) {
        return stringsList.stream()
                .min(Comparator.comparing(String::length))
                .orElse("");
    }

    /**
     * Функция поиска максимального элемента в списке.
     *
     * @param stringsList - список элементов.
     * @return максимальный элемент списка.
     */
    protected String max(List<String> stringsList) {
        return stringsList.stream()
                .max(Comparator.comparing(String::length))
                .orElse("");
    }

    /**
     * Возвращает строку, содержащую количество элементов в списке.
     *
     * @return строка содержащая количество элементов в списке.
     */
    public String getShortStatistic() {
        return "Количество элементов: " + super.getSize();
    }

    /**
     * Возвращает строку, содержащую:
     * 1. Количество элементов в списке.
     * 2. Самая длинная строка и ее длина.
     * 3. Самая короткая строка и ее длина.
     *
     * @return строка содержащая количество элементов, самую длинную и короткую строки в списке и их длину.
     */
    public String getFullStatistics() {
        return getShortStatistic() +
                "\nСамая длинная строка: \"" + maxString + "\" - количество символов: " + maxString.length() +
                "\nСамая короткая строка: \"" + minString + "\" - количество символов: " + minString.length();
    }
}
