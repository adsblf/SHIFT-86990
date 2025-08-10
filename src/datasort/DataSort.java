package datasort;

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

    public static HashMap<String, List<?>> sortData(List<String> data) {
        return new HashMap<>()
        {{
            put("float", extractFloatsFromList(data));
            put("int", extractIntegersFromList(data));
            put("String", extractStringsFromList(data));
        }};
    }
}
