public class A最小优先队列 {
    public static void main(String[] args) {

        MinPriorityQueue<String> queue = new MinPriorityQueue<>(10);

        queue.insert("A");
        queue.insert("B");
        queue.insert("C");
        queue.insert("D");
        queue.insert("E");
        queue.insert("F");
        queue.insert("G");

        //通过循环从队列中获取最大的元素
        while (!queue.isEmpty()) {
            String max = queue.delMin();
            System.out.print(max + " ");
        }
    }
}

class MinPriorityQueue<T extends Comparable<T>> {
    private T[] item; //存储堆中的元素
    private int N; //记录堆中的元素个数

    public MinPriorityQueue(int capacity) {
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

    //删除推中最小的元素，并返回这个最小的元素
    public T delMin() {
        T min = item[1];
        exchange(1, N);
        N--;
        sink(1);
        return min;
    }


    //使用上浮算法，使索引k处的元素能在堆中处于一个正确的位置
    private void swim(int k) {
        //通过循环比较当前节点和其父节点大小
        while (k > 1) {
            if (less(k, k / 2)) {
                exchange(k, k / 2);
            }

            k = k / 2;
        }
    }

    //使用下沉算法，使索引k处的元素能在堆中处于一个正确的位置
    private void sink(int k) {
        //通过循环比较当前节点和子节点中的较小值
        while (2 * k <= N) {
            //1、找到子节点中的较小值
            int min;
            if (2 * k + 1 <= N) {
                if (less(2 * k, 2 * k + 1)) {
                    min = 2 * k;
                } else {
                    min = 2 * k + 1;
                }
            } else {
                min = 2 * k;
            }

            //2、判断当前节点和较小值的大小
            if (less(k, min)) {
                break;
            }

            exchange(k, min);

            k = min;
        }
    }
}
