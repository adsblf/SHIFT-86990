package com.shift.statistic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Класс для сбора статистики из списка объектов, унаследованных от Number.
 *
 * @param <T> тип элемента, унаследованного от Number.
 */

public class NumbersStatistic<T extends Number> extends Statistic<T> {
    private T maxNum;
    private T minNum;
    private BigDecimal sumNum;
    private BigDecimal averageNum;

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

    public BigDecimal getSumNum() {
        return sumNum;
    }

    public BigDecimal getAverageNum() {
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
     * @return сумма элементов типа BigDecimal.
     */
    private BigDecimal sumNum(List<T> arrayList) {
        return arrayList.stream()
                .filter(Objects::nonNull)
                .map(NumbersStatistic::convertToBigDecimal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Ищет среднее значение чисел в списке.
     *
     * @param arrayList - список элементов.
     * @return среднее значение типа BigDecimal.
     */
    private BigDecimal averageNum(List<T> arrayList) {
        return sumNum(arrayList)
                .divide(new BigDecimal(arrayList.size()), RoundingMode.HALF_UP);
    }

    /**
     * Конвертирует объекты типа Number в BigDecimal
     *
     * @param obj - любой объект
     * @return приведенное число к типу BigDecimal
     * @exception IllegalArgumentException - если не удалось привести объект к типу BigDecimal
     */
    private static BigDecimal convertToBigDecimal(Object obj) {
        if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;
        } else if (obj instanceof BigInteger) {
            return new BigDecimal((BigInteger) obj);
        } else if (obj instanceof Number) {
            return new BigDecimal(((Number) obj).doubleValue());
        } else {
            throw new IllegalArgumentException("Непонятный тип данных, я с таким не умею работать =(");
        }
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
