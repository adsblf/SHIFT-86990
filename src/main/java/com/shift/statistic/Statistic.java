package com.shift.statistic;

import java.util.List;

/**
 * Абстрактный базовый класс для анализа списков объектов произвольного типа.
 * Общая структура:
 * 1. Размер списка.
 * 2. Минимальный элемент.
 * 3. Максимальный элемент.
 * 4. Вывод краткой статистики.
 * 5. Вывод полной статистики.
 *
 * @param <T> - тип элементов, для которых собирается статистика.
 */
public abstract class Statistic<T> {
    private int size;

    public Statistic(List<T> arrayList) {
        this.size = arrayList.size();
    }

    public int getSize() {
        return size;
    }

    /**
     * Вычисляет минимальное значение среди элементов списка.
     *
     * @param arrayList - список элементов.
     * @return минимальное значение в списке.
     */
    protected abstract T min(List<T> arrayList);

    /**
     * Вычисляет максимальное значение среди элементов списка.
     *
     * @param arrayList - список элементов.
     * @return максимальное значение в списке.
     */
    protected abstract T max(List <T> arrayList);

    /**
     * Генерирует строку, содержащую краткую статистику по элементам в списке.
     *
     * @return краткая строка со статистикой.
     */
    public abstract String getShortStatistic();

    /**
     * Генерирует строку, содержащую полную статистику по элементам в списке.
     *
     * @return полная строка со статистикой.
     */
    public abstract String getFullStatistics();
}
