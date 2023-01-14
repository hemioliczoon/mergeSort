package hemioliczoon;

import java.util.ArrayList;
import java.util.List;

public class Merge<T extends Comparable<T>> implements IMerge<T> {

    @Override
    public List<T> merge(List<T> listA, List<T> listB) {
        System.out.println("input " + listA);
        System.out.println("input " + listB);
        List<T> result = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;
        T leftItem;
        T rightItem;

        while (leftIndex != listA.size() && rightIndex != listB.size()) {
            leftItem = listA.get(leftIndex);
            rightItem = listB.get(rightIndex);
            System.out.println(leftItem + " " + rightItem + " " + leftItem.compareTo(rightItem));
            // if this is negative -> it is less than
            if (leftItem.compareTo(rightItem) > 0) {
                result.add(rightItem);
                rightIndex += 1;
            } else {
                result.add(leftItem);
                leftIndex += 1;
            }
        }

        if (leftIndex != listA.size()) {
            addRest(result, listA, leftIndex);
        } else {
            addRest(result, listB, rightIndex);
        }

        System.out.println("result " + result);
        return result;
    }

    // idk if this would do it in place since idk java
    private void addRest(List<T> result, List<T> toAdd, int startIndex) {
        // perhaps should rewrite this with for loop
        while (startIndex < toAdd.size()) {
            result.add(toAdd.get(startIndex));
            startIndex += 1;
        }
    }
}
