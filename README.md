# Exploring Functional Programming in Java with List Combinators

In many functional programming languages, there exist a large library of pre-defined "higher order functions" for operating
on linked lists in a way that is flexible and declarative. It is possible to implement some of these functions in Java,
and doing so can be a good practice in recursion and working with linked lists, but will also grow your perspective a bit.

### Table of Contents
* [Higher Order Functions, Combinators, and Lambda Expressions](#higher-order-functions-combinators-and-lambda-expressions)
* [Higher Order Functions and Lambdas in Java](#higher-order-functions-and-lambdas-in-java)
* [Your Task](#your-task)
* [Project Structure and Testing](#project-structure-and-testing)

## Higher Order Functions, Combinators, and Lambda Expressions

A higher order function is a function that _takes a function as input_ or _returns a function as output_. What could the
purpose of this possibly be? Consider this snippet of Python code that transforms a list of numbers 1 through 10
into a list of the squares of 1 through 10:

```python
numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

def square(x):
    return x * x

# squares contains [1, 4, 9, 16, 25, 36, 49, 64, 81, 100]
squares = list(map(square, numbers))
```

The `map` function is a _list combinator_. It is a "higher order function" because it takes a function (in this case
`square`) as input. It is a _combinator_ because its sole purpose is combining its input in an interesting and meaningful
way. The purpose of `map` is to take a list of "things", lets say they have the generic type `a`, and a function that
transforms a single "thing" of type `a` to a "thing" of type `b` (where `a` and `b` may or may not be the same), and
return a list of "things" of type `b`.

In the notation that is common in functional programming, we say that the generic
type signature of this function (assuming that the function from `a` to `b` comes _before_ the list of `a`s) is
`(a -> b) -> [a] -> [b]`. Note that in this type signature, right-pointing arrows separate the types of parameters and
outputs. If read expressively, this might be pronounced: "Take a function from `a` to `b` and a list of `a`s, and return a
list of `b`s." You may notice that the `->` symbol does not differentiate between inputs and outputs. This is intentional,
and has a good purpose which we won't explore right now, though admittedly it is confusing in our case.

Take a moment to consider the alternative to the code snippet above, which instead uses a for loop to achieve the same
effect:

```python
numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

squares = []

for number in numbers:
    squares.append(number * number)
```

... or in Java

```java
int[] numbers = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

int[] squares = new int[numbers.length];

for (int i = 0; i < squares.length; i++) {
    squares[i] = numbers[i] * numbers[i];
}
```

The `map` function is a simple abstraction over this common pattern of operations over list-like data structures, and
if we use _lambda expressions_, the abstraction becomes notably much shorter to use than the alternative. A lambda
expression is an expression in code that describes the body of a function without formally defining it. Consider the
following example:

```python
numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
squares = list(map(lambda x: x * x, numbers))
```

The iterative code from above that took 3 lines with explicit mapping logic to achieve this effect can now be reduced to 1.
The funny looking `lambda x: x * x` is, unsurprisingly, a lambda expression. Before we had to explicitly define
a function called `square` that we would probably use only once, for the purpose of being passed to `map`. This is messy
and makes using higher order functions unpleasant. If we had to explicitly define one-line functions every time
we wanted to use higher order functions, much of their utility and elegance would be lost. Lambda expressions provide
a solution by allowing us to directly pass the body of a short function to a higher order function rather than passing
the name of a function defined earlier. Consider the following analogy: explicitly defined and named variables are to
literal values as explicitly defined and named functions are to lambda expressions. Imagine how annoying it would be
if every time you wanted to pass a value like `1` or `"hello"` to a function, you were forced to store it in
a variable before-hand and give it a name, then pass the variable to your function.

## Higher Order Functions and Lambdas in Java

As of Java 8, Java supports many features that make patterns like the one described above more convenient to apply.
One of these features is lambda expressions. The following is valid Java code:

```java
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(1);
        numbers.add(3);
        numbers.add(5);
        
        numbers.forEach( (x) -> { System.out.println(x + 1); } );
    }
}
```

Though not particularly useful, this program uses a higher order method called `forEach` defined on `ArrayList`s
and passes it a lambda that prints a number plus 1. The resultant effect is that the even numbers 2 through 6 are printed.

Now let's take a look at how we can define a method that accepts a lambda expression as input. In this example, we write
a higher order method called `stringify` that takes a function that transforms an integer to a string, as well as an
`ArrayList` of integers, and returns an `ArrayList` of strings.

```java
import java.util.ArrayList;
import java.util.function.*;

public class LambdaExample {
    private static ArrayList<String> stringify(Function<Integer, String> fn, ArrayList<Integer> al) {
        ArrayList<String> strings = new ArrayList<String>();
        for (Integer i : al) {
            strings.add(fn.apply(i));
        }

        return strings;
    }

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        ArrayList<String> strings = stringify( (x) -> Integer.toString(x), numbers);

        for (String s : strings) {
            System.out.println(s);
        }
    }
}
```

The parameter type `Function<Integer, String>` specifies that `fn` should be a function that takes an `Integer` and returns
a `String`. We apply `fn` to an `Integer` with `fn.apply`.

## Your Task

In this exercise, you will use the above information and your knowledge of linked lists and recursion to implement
a set of linked list combinators, much like those which are typically available in functional programming languages.

A brief list of the combinators you will define along with their (functional) type signatures, descriptions, and
examples in a made up syntax is given here:

Note: While doing these exercises, do not modify anything in place; assume everything is **immutable**.

* `map :: (a -> b) -> [a] -> [b]`
    - Applies a function on every element of a list to produce a new list.
    - Reminder: The new list should have the same size as the original.
    - Example: `map (x -> x + 1) [1, 2, 3]` should return `[2, 3, 4]`
* `filter :: (a -> bool) -> [a] -> [a]`
    - Applies a _predicate_ function to every element of a list and produces a new list of values that satisfy the predicate.
    - Note: a _predicate_ function is a function that takes some value and returns a boolean; you can think of it as a "test".
    - Example: `filter (x -> x % 2 == 0) [1, 2, 3, 4]` should return `[2, 4]`
* `reduce :: (a -> a -> a) -> [a] -> a`
    - Applies a combining function to each element of a list to produce a single value. This may seem confusing, but consider the given example to see how simple it can be in practice.
    - Example: `reduce (x, y -> x + y) [1, 2, 3, 4]` should return `10` i.e. the sum of the list.
* `any :: (a -> bool) -> [a] -> bool`
    - Applies a predicate function to each element of a list and returns whether any element of the list satisfies the predicate
    - Example: `any (x -> x % 2 == 0) [1, 2, 3, 4]` should return `true`
* `all :: (a -> bool) -> [a] -> bool`
    - Applies a predicate function to each element of a list and returns whether every element of the list satisfies the predicate
    - Example: `all (x -> x & 2 == 0) [1, 2, 3, 4]` should return `false`
* `zipWith :: (a -> b -> c) -> [a] -> [b] -> [c]`
    - Applies a combining function to two lists pair-wise to produce a new list. If the two lists are of different sizes, extraneous elements are ignored.
    - Example: `zipWith (x, y -> x + y) [1, 2, 3] [4, 5, 6]` should return `[5, 7, 9]`

Some extra list combinators/functions to try implementing for your entertainment:

* `scan :: (a -> a -> a) -> [a] -> [a]`
    - Like `reduce`, but instead of reducing to a single value, each element of the produced list is the progressive result of reduction
    - Example: `scan (x, y -> x + y) [1, 2, 3, 4]` should return `[1, 3, 6, 10]` i.e. `[1, 2 + 1, 3 + 2 + 1, 4 + 3 + 2 + 1]`
* `take :: int -> [a] -> [a]`
    - Return the first `n` elements of the input list
    - Example: `take 2 [1, 2, 3, 4]` should return `[1, 2]`
* `drop :: int -> [a] -> [a]`
    - Like take but the other way around
    - Example: `drop 2 [1, 2, 3, 4]` should return `[3, 4]`
* `takeWhile :: (a -> bool) -> [a] -> [a]`
    - Return as many elements from the beginning of the list as possible which satisfy the given predicate
    - Example: `takeWhile (x -> x % 2 == 0) [2, 4, 6, 7, 8]` should return `[2, 4, 6]`
* `dropWhile :: (a -> bool) -> [a] -> [a]`
    - `takeWhile` but the other way around
    - Example: `dropWhile (x -> x % 2 == 0) [2, 4, 6, 7, 8]` should return `[7, 8]`

For your convenience, a generic linked list class has been provided along with some convenience methods. Here
are some of the important ones:

```java
// Initialize with variable number of inputs
LinkedList<Integer> list = new LinkedList<Integer>(1, 2, 3, 4, 5);

// Prepend to an existing list
list = LinkedList.prepend(0, list);

// Print lists in the following format: 1 -> 2 -> 3 -> 4
System.out.println(LinkedList.toString(list));
```

## Project Structure and Testing

The project code lives in the `app/src/main/java/fpe` directory. You will implement the combinators described above
as static methods in the `LinkedList` class. A class called `App` exists so that you may experiment with your
code and test it as you please. You can compile and run the code in the main method of `App` using the command
`./gradlew --console plain run` on Linux and Mac operating systems, and `.\gradlew --console plain run` on Windows.

This project comes with a few JUnit tests so that you may confirm that your solutions work as intended. You can
run them using `./gradlew test` or `.\gradlew test` depending on whether you're running Linux/Mac or Windows respectively.

You are encouraged to view the unit tests in `app/src/test/java/fpe/CombinatorTests.java`. They give good examples
of how to use the lambda syntax to test your combinators as you write them. You may copy that code into your main method
to experiment.

# Notes on Notation and Naming Conventions

Naming conventions and notations you may be unfamiliar with are used throughout this exercise and project. The reason
for this is to introduce you to notation and conventions that are common in functional programming. You do not have to
embrace them, but it is worth becoming familiar with them. For example, you may be used to working with linked lists
via a `Node` class that contains some data with a reference to the next node in the list. In functional programming,
this implementation detail is not often expressed explicitly. Instead, we consider a `Node` a linked list in its own
right, and say that every linked list is either empty (i.e. null) or contains a "head" (some value) and a "tail"
(which is another linked list). Implementation wise, there is no difference between our LinkedList class and
your `Node` class, but this is more common in functional programming contexts.
