package hemioliczoon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MergeSort<T extends Comparable<T>> {
    private List<T> list;
    private final IMerge<T> merger;
    private final ExecutorService threadPool = new ThreadPoolExecutor(
        4,
        8, 1000,
        TimeUnit.MILLISECONDS,
        new ArrayBlockingQueue<Runnable>(8)
    );

    public List<T> sort() {
        return _sort(this.list);
    }

    private List<T> _sort(List<T> list) {
        // TODO: sort this properly? maybe idk it does work like this
        if (list.size() <= 2) {
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

        Callable<List<T>> leftSort = new Callable<List<T>>() {
            @Override
            public List<T> call() throws Exception {
                return _sort(list.subList(0, middle));
            }
        };

        Callable<List<T>> rightSort = new Callable<List<T>>() {
            @Override
            public List<T> call() throws Exception {
                return _sort(list.subList(middle, list.size()));
            }
        };

        //List<T> left = _sort(list.subList(0, middle));
        try {
            Future<List<T>> left = this.threadPool.submit(leftSort);
            Future<List<T>> right = this.threadPool.submit(rightSort);

            return merger.merge(left.get(), right.get());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public MergeSort(List<T> list, IMerge<T> merger){
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
