package com.shift.statistic;

import java.util.Comparator;
import java.util.List;

/**
 * Класс для сбора статистики из списка объектов, унаследованных от Number.
 *
 * @param <T> тип элемента, унаследованного от Number.
 */

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

    /**
     * Функция поиска минимального элемента в списке.
     *
     * @param arrayList - список элементов.
     * @return минимальный элемент типа <T>.
     */
    protected T min(List<T> arrayList) {
        return arrayList.stream()
                .min(Comparator.comparingDouble(Number::doubleValue))
                .orElse(null);
    }

    /**
     * Функция поиска максимального элемента в списке.
     *
     * @param arrayList - список элементов.
     * @return максимальный элемент типа <T>.
     */
    protected T max(List<T> arrayList) {
        return arrayList.stream()
                .max(Comparator.comparingDouble(Number::doubleValue))
                .orElse(null);
    }

    /**
     * Суммирует все элементы списка.
     *
     * @param arrayList - список элементов.
     * @return сумма элементов типа double.
     */
    private double sumNum (List<T> arrayList) {
        return arrayList.stream()
                .mapToDouble(Number::doubleValue)
                .sum();
    }

    /**
     * Ищет среднее значение чисел в списке.
     *
     * @param arrayList - список элементов.
     * @return среднее значение типа double.
     */
    private double averageNum (List<T> arrayList) {
        return arrayList.stream()
                .mapToDouble(Number::doubleValue)
                .average()
                .orElse(0.0);
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
     * 2. Максимальное значение.
     * 3. Минимальное значение.
     * 4. Сумму.
     * 5. Среднее.
     *
     * @return строка содержащая количество элементов, максимум, минимум, сумма, среднее.
     */
    public String getFullStatistics() {
        return getShortStatistic() +
                "\nМаксимум: " + maxNum +
                "\nМинимум: " + minNum +
                "\nСумма: " + sumNum +
                "\nСреднее: " + averageNum;
    }
}
