package fpe;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CombinatorTests {

    private static final LinkedList<Integer> numbers = new LinkedList<>(1, 2, 3, 4, 5);

    @Test
    void testMap() {
        var mapResult = LinkedList.map( (x) -> x * x, numbers);
        Assertions.assertThat(mapResult).isNotNull();
        Assertions.assertThat(LinkedList.toString(mapResult)).isEqualTo("1 -> 4 -> 9 -> 16 -> 25");
    }

    @Test
    void testFilter() {
        var filterResults = LinkedList.filter((x) -> x % 2 == 0, numbers);
        Assertions.assertThat(filterResults).isNotNull();
        Assertions.assertThat(LinkedList.toString(filterResults)).isEqualTo("2 -> 4");
    }

    @Test
    void testReduce() {
        var sum = LinkedList.reduce((x, y) -> x + y, numbers);

        Assertions.assertThat(sum).isEqualTo(10);
    }

    @Test
    void testAny() {
        var anyResult = LinkedList.any((x) -> x % 2 == 0, numbers);
        Assertions.assertThat(anyResult).isTrue();
    }

    @Test
    void testAll() {
        var allResult = LinkedList.all((x) -> x % 2 == 0, numbers);
        Assertions.assertThat(allResult).isFalse();
    }

    @Test
    void testZipWith() {
        var zipWithResult = LinkedList.zipWith((x, y) -> x * y, numbers, numbers);
        Assertions.assertThat(zipWithResult).isNotNull();
        Assertions.assertThat(LinkedList.toString(zipWithResult)).isEqualTo("1 -> 4 -> 9 -> 16 -> 25");
    }
}
