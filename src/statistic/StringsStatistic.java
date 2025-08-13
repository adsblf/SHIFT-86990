package statistic;

import java.util.Comparator;
import java.util.List;

public class StringsStatistic extends Statistic <String> {
    private String minString;
    private String maxString;

    public StringsStatistic(List<String> stringsList) {
        super(stringsList);
        this.minString = min(stringsList);
        this.maxString = max(stringsList);
    }


    protected String min(List<String> stringsList) {
        return stringsList.stream()
                .min(Comparator.comparing(String::length))
                .orElse("");
    }

    protected String max(List<String> stringsList) {
        return stringsList.stream()
                .max(Comparator.comparing(String::length))
                .orElse("");
    }

    public String getShortStatistic() {
        return "Количество элементов: " + super.getSize();
    }

    public String getFullStatistics() {
        return getShortStatistic() +
                "\nМаксимум: \"" + maxString + "\" Количество элементов: " + maxString.length() +
                "\nМинимум: \"" + minString + "\" Количество элементов: " + minString.length();
    }
}
