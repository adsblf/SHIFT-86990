package statistic;

import java.util.List;

public abstract class Statistic<T> {
    private int size;

    public Statistic(List<T> arrayList) {
        this.size = arrayList.size();
    }

    public int getSize() {
        return size;
    }

    protected abstract T min(List<T> arrayList);

    protected abstract T max(List <T> arrayList);

    public abstract String getShortStatistic();

    public abstract String getFullStatistics();
}
