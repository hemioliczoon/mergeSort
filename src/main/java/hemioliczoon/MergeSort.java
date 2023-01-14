package hemioliczoon;

import java.util.ArrayList;
import java.util.List;

public class MergeSort<T extends Comparable<T>> {
    private List<T> list;
    private IMerge<T> merger;

    public List<T> sort() {
        return _sort(this.list);
    }

    private List<T> _sort(List<T> list) {
        if (list.size() <= 2) {
            // TODO: sort this properly
            if (list.size() < 2) {
                return list;
            }
            // sort here properly
            if (list.get(0).compareTo(list.get(1)) > 0) {
                T tmp = list.get(1);
                list.set(1, list.get(0));
                list.set(0, tmp);
            }
            return list;
        }
        int middle = list.size() / 2;

        List<T> left = _sort(list.subList(0, middle));
        List<T> right = _sort(list.subList(middle, list.size()));

        return merger.merge(left, right);
    }

    public MergeSort(List<T> list, IMerge<T> merger){
        // split in half.
        // call merge sort on each half
        // merge results
        this.list = list;
        this.merger = merger;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
