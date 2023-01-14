package hemioliczoon;

import java.util.List;

public interface IMerge<T> {
    public List<T> merge(List<T> listA, List<T> listB);
}