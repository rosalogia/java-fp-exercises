package fpe;

import java.util.function.Function;

public class LinkedList<T> {
    private T head;
    private LinkedList<T> tail;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    private LinkedList(int current, T... inputs) {
        if (current < inputs.length) {
            this.head = inputs[current];
            if (current + 1 == inputs.length)
                this.tail = null;
            else
                this.tail = new LinkedList<>(current + 1, inputs);
        }
    }

    public LinkedList(T... inputs) {
        this(0, inputs);
    }

    public static <T> LinkedList<T> prepend(T newHead, LinkedList<T> tail) {
        LinkedList<T> nh = new LinkedList<>();
        nh.head = newHead;
        nh.tail = tail;
        return nh;
    }

    public static <T> String toString(LinkedList<T> l) {
        if (l.tail == null) return l.head.toString();
        return l.head.toString() + " -> " + toString(l.tail);
    }

    public static <T, U> LinkedList<U> map(Function<T, U> f, LinkedList<T> l) {
        if (l == null) return null;
        return prepend(f.apply(l.head), map(f, l.tail));
    }

    public static <T> LinkedList<T> filter(Function<T, Boolean> pred, LinkedList<T> l) {
        if (l == null) return null;
        if (pred.apply(l.head)) {
            return prepend(l.head, filter(pred, l.tail));
        } else {
            return filter(pred, l.tail);
        }
    }

    public static <T> T reduce(CombiningFunction<T, T, T> fn, LinkedList<T> l) {
        if (l == null) return null;
        if (l.tail == null) return l.head;
        return fn.combine(l.head, reduce(fn, l.tail));
    }

    public static <T> Boolean any(Function<T, Boolean> pred, LinkedList<T> l) {
        if (l == null) return false;
        if (pred.apply(l.head)) return true;
        return any(pred, l.tail);
    }

    public static <T> Boolean all(Function<T, Boolean> pred, LinkedList<T> l) {
        if (l == null) return true;
        if (!pred.apply(l.head)) return false;
        return all(pred, l.tail);
    }

    public static <T, U, V> LinkedList<V> zipWith(CombiningFunction<T, U, V> fn, LinkedList<T> l1, LinkedList<U> l2) {
        if (l1 == null || l2 == null) return null;
        return prepend(fn.combine(l1.head, l2.head), zipWith(fn, l1.tail, l2.tail));
    }

    // Optional Functions and Combinators

    public static <T> LinkedList<T> scan(CombiningFunction<T, T, T> fn, LinkedList<T> l) {
        return null;
    }

    public static <T> LinkedList<T> take(Integer n, LinkedList<T> l) {
        return null;
    }

    public static <T> LinkedList<T> drop(Integer n, LinkedList<T> l) {
        return null;
    }

    public static <T> LinkedList<T> takeWhile(Function<T, Boolean> pred, LinkedList<T> l) {
        return null;
    }

    public static <T> LinkedList<T> dropWhile(Function<T, Boolean> pred, LinkedList<T> l) {
        return null;
    }
}
