package fpe;

@FunctionalInterface
public interface CombiningFunction<T, U, V> {
    public V combine(T a, U b);
}
