public class A最大优先队列 {
    public static void main(String[] args) {

        MaxPriorityQueue<String> queue = new MaxPriorityQueue<>(10);

        queue.insert("A");
        queue.insert("B");
        queue.insert("C");
        queue.insert("D");
        queue.insert("E");
        queue.insert("F");
        queue.insert("G");

        //通过循环从队列中获取最大的元素
        while (!queue.isEmpty()) {
            String max = queue.delMax();
            System.out.print(max + " ");
        }
    }
}

class MaxPriorityQueue<T extends Comparable<T>> {
    private T[] item;
    private int N;

    public MaxPriorityQueue(int capacity) {
        this.item = (T[]) new Comparable[capacity + 1];
        this.N = 0;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    //判断堆中索引i处的元素是否小于索引j处的元素
    private boolean less(int i, int j) {
        return item[i].compareTo(item[j]) < 0;
    }

    //交换堆中i所以和j索引处的值
    private void exchange(int i, int j) {
        T temp = item[i];
        item[i] = item[j];
        item[j] = temp;
    }

    //往堆中插入一个元素
    public void insert(T t) {
        item[++N] = t;
        swim(N);
    }

    //删除推中最大的元素，并返回这个最大的元素
    public T delMax() {
        T max = item[1];
        exchange(1, N);
        N--;
        sink(1);
        return max;
    }


    //使用上浮算法，使索引k处的元素能在堆中处于一个正确的位置
    private void swim(int k) {
        while (k > 1) {
            if (less(k / 2, k)) {
                exchange(k / 2, k);
            }
            k = k / 2;
        }
    }

    //使用下沉算法，使索引k处的元素能在堆中处于一个正确的位置
    private void sink(int k) {
        while (2 * k <= N) {
            int max;
            if (2 * k + 1 <= N) {
                if (less(2 * k, 2 * k + 1)) {
                    max = 2 * k + 1;
                } else {
                    max = 2 * k;
                }
            } else {
                max = 2 * k;
            }

            if (!less(k, max)) {
                break;
            }

            exchange(k, max);

            k = max;
        }
    }
}
