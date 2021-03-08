package fpe;

@FunctionalInterface
public interface CombiningFunction<T, U, V> {
    public V f(T a, U b);
}
