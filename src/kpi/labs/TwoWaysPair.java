package kpi.labs;

public class TwoWaysPair<T> {
    private final T first;
    private final T second;

    public TwoWaysPair(T first, T second) {
        if (first == null) {
            throw new IllegalArgumentException();
        }
        if (second == null) {
            throw new IllegalArgumentException();
        }
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final TwoWaysPair<?> that = (TwoWaysPair<?>) o;
        if (that.first.equals(first)) {
            return that.second.equals(second);
        } else {
            return that.first.equals(second) && that.second.equals(first);
        }
    }

    @Override
    public int hashCode() {
        return first.hashCode() + second.hashCode();
    }
}
