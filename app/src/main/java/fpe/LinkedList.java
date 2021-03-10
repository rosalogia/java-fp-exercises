package fpe;

import java.util.function.*;

/**
 * Custom linked list class for the purposes of this exercise implementing a
 * variety of convenience methods.
 * <br><br>
 * Implemented using an internal Node class.
 * <br><br>
 * Note that an empty list is represented
 * by a list whose Node object is {@code null}, rather than a list whose Node object contains
 * a {@code null} head and a {@code null} tail.
 *
 * @param <T> the type of elements held by the list
 */
public class LinkedList<T> {
    /**
     * The Node object containing the data held by the list and a reference
     * to the next item in the list.
     */
    private final Node<T> contents;

    /**
     * Internal data representation class for {@code LinkedList<T>}
     * @param <T> the type of element held by the node
     */
    private static class Node<T> {
        /**
         * Object contained by this node.
         */
        private T head;
        /**
         * Reference to the next node in the list.
         */
        private Node<T> tail;

        /**
         * Constructs an empty node whose head and tail are both {@code null}.
         */
        public Node() {
            this.head = null;
            this.tail = null;
        }

        /**
         * Private constructor that implements the creation of a list from a
         * variable number of arguments. If the end of the varargs have been
         * reached, the tail of the node being constructed is set to {@code null}.
         * @param current current index of varargs, incremented with each recursive call.
         * @param inputs varargs treated as an array to be converted to a list.
         */
        private Node(int current, T... inputs) {
            if (current < inputs.length) {
                this.head = inputs[current];
                if (current + 1 == inputs.length)
                    this.tail = null;
                else
                    this.tail = new Node<>(current + 1, inputs);
            }
        }

        /**
         * Public interface for calling the private varargs constructor.
         * @param inputs varargs to be converted to a list.
         */
        public Node(T... inputs) {
            this(0, inputs);
        }

        /**
         * Returns an incomplete string representation of the list containing this
         * node. For example, if the current node contains the Integer 5 and points to
         * a node containing the Integer 6 which points to {@code null}, the output is "5, 6]".
         * This is because this method exists solely to be called by the toString method
         * implemented for the {@code LinkedList<T>} class.
         * @return an incomplete string representation of this node and those that follow.
         */
        public String toString() {
            if (this.tail == null) return this.head.toString() + "]";
            return this.head.toString() + ", " + this.tail.toString();
        }
    }

    /**
     * Constructs a list of type T from a variable number of arguments of type T.
     * <br><br>
     * Example usage:
     * <pre>
     *     {@code
     * LinkedList<Integer> list = new LinkedList<>(1, 2, 3, 4, 5);
     *     }
     * </pre>
     * @param inputs varargs to be converted to a list.
     */
    public LinkedList(T... inputs) {
        this.contents = new Node<>(inputs);
    }

    /**
     * Private constructor that accepts a node to be assigned to the current list's contents.
     * @param node the node which is to become the contents of the list.
     */
    private LinkedList(Node<T> node) {
        this.contents = node;
    }

    /**
     * Constructs an empty {@code LinkedList<T>}.
     */
    public LinkedList() {
        this.contents = null;
    }

    // Convenience methods

    /**
     * Creates a new Node containing a given object which points to a provided tail.
     * <br><br>
     * Example usage:
     * <pre>
     *     {@code
     * Node<T> numbers = new Node<>(1, 2, 3, 4, 5);
     * Node<T> moreNumbers = prepend(6, numbers);
     *     }
     * </pre>
     * @param item the item to be prepended to the list represented by the tail node
     * @param tail a node representing the list to be prepended to
     * @param <T> the type of element held by the node being operated on
     * @return a new {@code Node<T>} with the prepended item as its head and the given tail as its tail
     */
    private static <T> Node<T> prepend(T item, Node<T> tail) {
        Node<T> nh = new Node<>();
        nh.head = item;
        nh.tail = tail;
        return nh;
    }

    // Public interface for LinkedLists.
    // You do not need to (and should not)
    // use these methods in your implementations.

    /**
     * Returns whether or not this list's contents are {@code null}. Note that a LinkedList is only empty
     * when its member node, contents, is {@code null}. A LinkedList with an initialised contents node
     * whose head and tail are {@code null} is not considered empty.
     * @return {@code true} if the list is considered empty, i.e. its contents are {@code null}
     */
    public boolean isEmpty() {
        return this.contents == null;
    }

    /**
     * Returns the object at the beginning of this list if one exists. Otherwise returns {@code null}.
     * @return the item at the beginning of this list if it exists
     */
    public T head() {
        if (this.contents == null || this.contents.head == null) return null;
        return this.contents.head;
    }

    /**
     * Returns a {@code LinkedList<T>} containing every element of this list except the first one. If
     * the list is empty or doesn't have a tail, an empty list is returned.
     * @return a list of every item in this list besides the first item.
     */
    public LinkedList<T> tail() {
        if (this.contents == null || this.contents.tail == null) return new LinkedList<>();
        return new LinkedList<>(this.contents.tail);
    }

    /**
     * Returns a string representation of this list in the form "[1, 2, 3, 4, 5]" or "[]" if the list is empty.
     * @return a string representation of this list
     */
    public String toString() {
        if (contents == null)
            return "[]";
        else
            return "[" + this.contents.toString();
    }

    // Method templates for you to implement
    // Note that these methods are STATIC and work on Node<T> objects

    /**
     * @hidden
     */
    // The following is a method that never gets called
    // but is written to demonstrate how you may use the Node<T> object
    private static <T, U> void forEach(Function<T, U> f, Node<T> l) {
        // Almost always a good idea to use l == null as a base case
        // Trying to access l.head or l.tail if l is null will result
        // in NPE.
        if (l == null) return;
        // l.head is the object of type T at the beginning of the list
        f.apply(l.head);
        // l.tail is the remainder of the list, represented by a Node<T>
        forEach(f, l.tail);
    }

    /**
     * @hidden
     */
    private static <T, U> Node<U> map(Function<T, U> f, Node<T> l) {
        return null;
    }

    /**
     * @hidden
     */
    private static <T> Node<T> filter(Function<T, Boolean> pred, Node<T> l) {
        return null;
    }

    /**
     * @hidden
     */
    private static <T, U> U reduce(BiFunction<T, T, U> fn, Node<T> l) {
        return null;
    }

    /**
     * @hidden
     */
    private static <T> Boolean any(Function<T, Boolean> pred, Node<T> l) {
        return null;
    }

    /**
     * @hidden
     */
    private static <T> Boolean all(Function<T, Boolean> pred, Node<T> l) {
        return null;
    }

    /**
     * @hidden
     */
    private static <T, U, V> Node<V> zipWith(BiFunction<T, U, V> fn, Node<T> l1, Node<U> l2) {
        return null;
    }

    /**
     * @hidden
     */
    // Optional Functions and Combinators
    private static <T, U> Node<U> scan(BiFunction<T, T, U> fn, Node<T> l) {
        return null;
    }

    /**
     * @hidden
     */
    private static <T> Node<T> take(Integer n, Node<T> l) {
        return null;
    }

    /**
     * @hidden
     */
    private static <T> Node<T> drop(Integer n, Node<T> l) {
        return null;
    }

    /**
     * @hidden
     */
    private static <T> Node<T> takeWhile(Function<T, Boolean> pred, Node<T> l) {
        return null;
    }

    /**
     * @hidden
     */
    private static <T> Node<T> dropWhile(Function<T, Boolean> pred, Node<T> l) {
        return null;
    }

    // Public non-static wrappers for fluent chaining

    /**
     * Applies a function to every element of this list and returns the resultant list
     * @param f a function from T to U
     * @param <U> the type returned by the function f, and the type of the list that gets returned by this method
     * @return a new linked list whose elements are the product of applying f to every element in this list
     */
    public <U> LinkedList<U> map(Function<T, U> f) {
        if (this.isEmpty()) return new LinkedList<>();
        return new LinkedList<>(map(f, this.contents));
    }

    /**
     * Creates a new list which only contains elements of this list which satisfy the predicate function
     * @param pred a boolean function on T that determines whether an element should be kept in the resultant list
     * @return a list only containing elements of this list which satisfy the predicate
     */
    public LinkedList<T> filter(Function<T, Boolean> pred) {
        if (this.isEmpty()) return new LinkedList<>();
        return new LinkedList<>(filter(pred, this.contents));
    }

    /**
     * Reduces every element in this list to a single value by combining each element from left to right with the provided combining function
     * @param fn a function that takes two values of T and combines them into some value of type U (which may be T)
     * @param <U> the type of the resulting reduction on the list.
     * @return a single value of type U which is the product of combining every value in this list with the function fn
     */
    public <U> U reduce(BiFunction<T, T, U> fn) {
        if (this.isEmpty()) return null;
        return reduce(fn, this.contents);
    }

    /**
     * Determines whether any values in this list satisfy the given predicate
     * @param pred a boolean function on T to test each element of this list against
     * @return whether any values in this list satisfy the given predicate
     */
    public Boolean any(Function<T, Boolean> pred) {
        if (this.isEmpty()) return null;
        return any(pred, this.contents);
    }

    /**
     * Determines whether all values in this list satisfy the given predicate
     * @param pred a boolean function on T to test each element of this list against
     * @return whether all values in this list satisfy the given predicate
     */
    public Boolean all(Function<T, Boolean> pred) {
        if (this.isEmpty()) return null;
        return all(pred, this.contents);
    }

    /**
     * Combines this list with another list using a given combining function
     * @param fn a function from T and U to V with which elements of this list and elements of l2 are combined
     * @param l2 a list to combine this list with
     * @param <U> the type of the list with which this list is being combined
     * @param <V> the type of the resulting list
     * @return a list containing the result of an element-wise combination of this list and the second list via the given combining function
     */
    public <U, V> LinkedList<V> zipWith(BiFunction<T, U, V> fn, LinkedList<U> l2) {
        if (this.isEmpty()) return new LinkedList<>();
        return new LinkedList<>(zipWith(fn, this.contents, l2.contents));
    }

    /**
     * Creates a new list whose elements are the result of progressively reducing each element with the next from left to right
     * @param fn a function that takes two values of T and combines them into some value of type U (which may be T)
     * @return a list whose elements are the result of progressively reducing each element of this list with the next
     */
    public <U> LinkedList<U> scan(BiFunction<T, T, U> fn) {
        if (this.isEmpty()) return new LinkedList<>();
        return new LinkedList<>(scan(fn, this.contents));
    }

    /**
     * Creates a new list containing only the first {@code n} elements of this list
     * @param n the number of elements from the beginning of this list to keep in the resultant list
     * @return a list containing the first {@code n} elements of this list
     */
    public LinkedList<T> take(Integer n) {
        if (this.isEmpty()) return new LinkedList<>();
        return new LinkedList<>(take(n, this.contents));
    }

    /**
     * Creates a new list with the first {@code n} elements of this list removed
     * @param n the number of elements from the beginning of this list to remove from the resultant list
     * @return a list with the first {@code n} elements of this list
     */
    public LinkedList<T> drop(Integer n) {
        if (this.isEmpty()) return new LinkedList<>();
        return new LinkedList<>(drop(n, this.contents));
    }

    /**
     * Creates a new list containing only those elements from the beginning of this list which satisfy the given predicate
     * @param pred a boolean function on T to test elements at the beginning of this list against
     * @return a list containing elements at the beginning of this list which satisfy the given predicate.
     */
    public LinkedList<T> takeWhile(Function<T, Boolean> pred) {
        if (this.isEmpty()) return new LinkedList<>();
        return new LinkedList<>(takeWhile(pred, this.contents));
    }

    /**
     * Creates a new list those elements from the beginning of this list which satisfy the given predicate removed
     * @param pred a boolean function on T to test elements at the beginning of this list against
     * @return a list with elements at the beginning of this list which satisfy the given predicate removed.
     */
    public LinkedList<T> dropWhile(Function<T, Boolean> pred) {
        if (this.isEmpty()) return new LinkedList<>();
        return new LinkedList<>(dropWhile(pred, this.contents));
    }
}
