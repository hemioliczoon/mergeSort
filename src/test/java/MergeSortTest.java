import hemioliczoon.Merge;
import hemioliczoon.MergeSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MergeSortTest {
    @Test
    void sort() {
        List<Integer> input = new ArrayList<>(Arrays.asList(4, 5, 9, 4, 1, 3));

        MergeSort<Integer> sorter = new MergeSort<>(input, new Merge<>());

        List<Integer> output = sorter.sort();

        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 3, 4, 4, 5, 9));

        System.out.println(output);
        System.out.println(expected);
        assertArrayEquals(expected.toArray(), output.toArray());
    }

    @Test
    void merge() {
        List<Integer> left = new ArrayList<>(Arrays.asList(1,3));
        List<Integer> right = new ArrayList<>(Arrays.asList(2,4));

        Merge<Integer> merger = new Merge<>();
        List<Integer> output = merger.merge(left, right);

        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

        assertArrayEquals(expected.toArray(), output.toArray());
    }

    @Test
    void empty() {
        List<Integer> input = new ArrayList<>();
        MergeSort<Integer> sorter = new MergeSort<>(input, new Merge<>());

        List<Integer> sorted = sorter.sort();
        assertEquals(sorted.size(), 0);
    }
}
