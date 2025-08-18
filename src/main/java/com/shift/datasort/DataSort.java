package com.shift.datasort;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Класс для сортировки данных в зависимости от их типа, сортирует на 3 категории:
 * 1. float
 * 2. int
 * 3. String
 */
public class DataSort {
    /**
     * Извлекает числа с плавающей точкой из переданного списка.
     *
     * @param data - список строк.
     * @return List<Float> - список Float.
     */
    public static List<Float> extractFloatsFromList(List<String> data) {
        return data.stream()
                .map(str -> {
                    if (str.contains(".")) {
                        try {
                            return Float.valueOf(str);
                        } catch (Exception e) {
                            return null;
                        }
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Извлекает целочисленные значения из переданного списка.
     *
     * @param data - список строк.
     * @return List<Integer> - список Integer.
     */
    public static List<Integer> extractIntegersFromList(List<String> data) {
        return data.stream()
                .map(str -> {
                    try {
                        return Integer.valueOf(str);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Извлекает строки из переданного списка. Строками считается все, кроме целых
     * чисел и чисел с плавающей точкой.
     *
     * @param data - список строк.
     * @return List<String> - список String.
     */
    public static List<String> extractStringsFromList(List<String> data) {
        return data.stream()
                .map(str -> {
                    try {
                        Float.valueOf(str);
                        return null;
                    } catch (Exception e) {
                        return str;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Создает мапу <"тип объекта": List<?>> из переданного списка строк. Список ключей:
     * 1. "float"
     * 2. "int"
     * 3. "String"
     *
     * @param data - список строк.
     * @return HashMap<String, List<?>> - мапа, состоящая из отсортированных данных.
     */
    public static HashMap<String, List<?>> sortData(List<String> data) {
        return new HashMap<>()
        {{
            put("float", extractFloatsFromList(data));
            put("int", extractIntegersFromList(data));
            put("String", extractStringsFromList(data));
        }};
    }
}
