package statistic;

import java.util.Comparator;
import java.util.List;

public class NumbersStatistic<T extends Number> extends Statistic<T> {
    private T maxNum;
    private T minNum;
    private double sumNum;
    private double averageNum;

    public NumbersStatistic(List<T> arrayList) {
        super(arrayList);
        this.maxNum = max(arrayList);
        this.minNum = min(arrayList);
        this.sumNum = sumNum(arrayList);
        this.averageNum = averageNum(arrayList);
    }

    public T getMaxNum() {
        return maxNum;
    }

    public T getMinNum() {
        return minNum;
    }

    public double getSumNum() {
        return sumNum;
    }

    public double getAverageNum() {
        return averageNum;
    }

    protected T min(List<T> arrayList) {
        return arrayList.stream()
                .min(Comparator.comparingDouble(Number::doubleValue))
                .orElse(null);
    }

    protected T max(List<T> arrayList) {
        return arrayList.stream()
                .max(Comparator.comparingDouble(Number::doubleValue))
                .orElse(null);
    }

    private double sumNum (List<T> arrayList) {
        return arrayList.stream()
                .mapToDouble(Number::doubleValue)
                .sum();
    }

    private double averageNum (List<T> arrayList) {
        return arrayList.stream()
                .mapToDouble(Number::doubleValue)
                .average()
                .orElse(0.0);
    }

    public String getShortStatistic() {
        return "Количество элементов: " + super.getSize();
    }

    public String getFullStatistics() {
        return getShortStatistic() +
                "\nМаксимум: " + maxNum +
                "\nМинимум: " + minNum +
                "\nСумма: " + sumNum +
                "\nСреднее: " + averageNum;
    }
}
