public class A堆 {
    public static void main(String[] args) {
        Heap<String> heap = new Heap<>(10);
        heap.insert("A");
        heap.insert("B");
        heap.insert("C");
        heap.insert("D");
        heap.insert("E");
        heap.insert("F");
        heap.insert("G");

        //通过循环从堆中删除数据
        String result = null;
        while ((result = heap.delMax()) != null) {
            System.out.print(result + " ");
        }

    }
}

class Heap<T extends Comparable<T>> {

    private T[] item;
    private int N;

    public Heap(int capacity) {
        this.item = (T[]) new Comparable[capacity + 1];
        N = 0;
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

    //删除推中最大的元素，并返回这个最大的元素
    public T delMax() {
        T max = item[1];

        //交换所以1处的元素和最大索引处的元素，让完全二叉树右侧的元素变为临时节点
        exchange(1, N);

        //最大索引处的元素删除掉
        item[N] = null;

        //元素个数-1
        N--;

        //通过下沉调整堆，让堆重新有序
        sink(1);
        return max;
    }

    //往堆中插入一个元素
    public void insert(T t) {
        item[++N] = t;
        swim(N);
    }

    //使用上浮算法，使索引k处的元素能在堆中处于一个正确的位置
    private void swim(int k) {
        //通过循环，不断的比较当前节点的值和其父节点的值，如果父节点的值比当前节点的值小，则交换
        while (k > 1) {
            //比较当前节点和其父节点
            if (less(k / 2, k)) {
                exchange(k / 2, k);
            }
            k = k / 2;
        }

    }

    //使用下沉算法，使索引k处的元素能在堆中处于一个正确的位置
    private void sink(int k) {
        //通过循环不断的对比当前k节点和其左子节点2*k以及右子节点2k+1处中的较大值的元素大小，如果当前节点小，则需要交换位置
        while (2 * k <= N) {
            //获取当前节点的子节点中的较大节点
            int max; //记录较大节点所在的索引
            if (2 * k + 1 <= N) {
                if (less(2 * k, 2 * k +1)) {
                    max = 2 * k + 1;
                } else {
                    max = 2 * k;
                }
            } else {
                max = 2 * k;
            }

            //比较当前节点和较大节点的值
            if (!less(k, max)) {
                break;
            }

            //交换k索引处的值和max索引处的值
            exchange(k, max);

            //变换k的值
            k = max;
        }
    }
}
