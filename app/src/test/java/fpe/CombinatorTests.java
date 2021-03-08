package fpe;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CombinatorTests {

    private static final LinkedList<Integer> numbers = new LinkedList<>(1, 2, 3, 4, 5);

    @Test
    void testMap() {
        var mapResult = numbers.map(x -> x * x);
        Assertions.assertThat(mapResult).isNotNull();
        Assertions.assertThat(LinkedList.toString(mapResult)).isEqualTo("1 -> 4 -> 9 -> 16 -> 25");
    }

    @Test
    void testFilter() {
        var filterResults = numbers.filter(x -> x % 2 == 0);
        Assertions.assertThat(filterResults).isNotNull();
        Assertions.assertThat(LinkedList.toString(filterResults)).isEqualTo("2 -> 4");
    }

    @Test
    void testReduce() {
        var sum = numbers.reduce((x, y) -> x + y);
        Assertions.assertThat(sum).isEqualTo(15);
    }

    @Test
    void testAny() {
        var anyResult = numbers.any(x -> x % 2 == 0);
        Assertions.assertThat(anyResult).isTrue();
    }

    @Test
    void testAll() {
        var allResult = numbers.all(x -> x % 2 == 0);
        Assertions.assertThat(allResult).isFalse();
    }

    @Test
    void testZipWith() {
        var zipWithResult = numbers.zipWith((x, y) -> x * y, numbers);
        Assertions.assertThat(zipWithResult).isNotNull();
        Assertions.assertThat(LinkedList.toString(zipWithResult)).isEqualTo("1 -> 4 -> 9 -> 16 -> 25");
    }
}
