package fpe;

import java.util.function.Function;

public class LinkedList<T> {
    private Node<T> contents;

    private static class Node<T> {
        public T head;
        public Node<T> tail;

        public Node() {
            this.head = null;
            this.tail = null;
        }

        private Node(int current, T... inputs) {
            if (current < inputs.length) {
                this.head = inputs[current];
                if (current + 1 == inputs.length)
                    this.tail = null;
                else
                    this.tail = new Node<>(current + 1, inputs);
            }
        }

        public Node(T... inputs) {
            this(0, inputs);
        }
    }

    public LinkedList(T... inputs) {
        this.contents = new Node<>(inputs);
    }

    private LinkedList(Node<T> n) {
        this.contents = n;
    }

    public T head() {
        if (this.contents == null) return null;
        else return this.contents.head;
    }

    public LinkedList<T> tail() {
        if (this.contents == null) return null;
        return new LinkedList<>(this.contents.tail);
    }


    public LinkedList<T> prepend(T newHead, LinkedList<T> tail) {
        Node<T> nh = new Node<>();
        nh.head = newHead;
        nh.tail = this.contents.tail;
        return new LinkedList<>(nh);
    }

    public Boolean isNull() {
        return this.contents == null;
    }

    public static <T> String toString(LinkedList<T> l) {
        if (l.tail().isNull()) return l.head().toString();
        return l.head().toString() + " -> " + toString(l.tail());
    }

    public <U> LinkedList<U> map(Function<T, U> f) {
        return null;
    }

    public LinkedList<T> filter(Function<T, Boolean> pred) {
        return null;
    }

    public T reduce(CombiningFunction<T, T, T> fn) {
        return null;
    }

    public Boolean any(Function<T, Boolean> pred) {
        return null;
    }

    public Boolean all(Function<T, Boolean> pred) {
        return null;
    }

    public <U, V> LinkedList<V> zipWith(CombiningFunction<T, U, V> fn, LinkedList<U> l2) {
        return null;
    }

    // Optional Functions and Combinators

    public LinkedList<T> scan(CombiningFunction<T, T, T> fn) {
        return null;
    }

    public LinkedList<T> take(Integer n) {
        return null;
    }

    public LinkedList<T> drop(Integer n) {
        return null;
    }

    public LinkedList<T> takeWhile(Function<T, Boolean> pred) {
        return null;
    }

    public LinkedList<T> dropWhile(Function<T, Boolean> pred) {
        return null;
    }
}
