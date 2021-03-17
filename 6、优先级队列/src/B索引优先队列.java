public class B索引优先队列 {
    public static void main(String[] args) {

        IndexMinPriorityQueue<String> queue = new IndexMinPriorityQueue<>(10);

        queue.insert(0, "A");
        queue.insert(1, "B");
        queue.insert(2, "C");
        queue.insert(3, "D");
        queue.insert(4, "E");
        queue.insert(5, "F");
        queue.insert(6, "G");

        queue.changeItem(2, "B");

        //通过循环从队列中获取最大的元素
        while (!queue.isEmpty()) {
            int max = queue.delMin();
            System.out.print(max + " ");
        }
    }
}

class IndexMinPriorityQueue<T extends Comparable<T>> {
    private T[] item;   //存储堆中的元素
    private int[] pq;   //保存每个元素items数组中的索引，pq数组需要堆有序
    private int[] qp;  //保存qp的逆序，pq的值作为索引，pq索引作为值
    private int N;      //记录堆中的元素个数

    public IndexMinPriorityQueue(int capacity) {
        this.item = (T[]) new Comparable[capacity + 1];
        this.pq = new int[capacity + 1];
        this.qp = new int[capacity + 1];
        this.N = 0;

        //默认情况下，队列中没有存储任何数据，让qp中的元素都为-1
        for (int i = 0; i < qp.length; i++) {
            qp[i] = -1;
        }
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    //判断堆中索引i处的元素是否小于索引j处的元素
    private boolean less(int i, int j) {
        return item[pq[i]].compareTo(item[pq[j]]) < 0;
    }

    //交换堆中i所以和j索引处的值
    private void exchange(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;

        //更新qp中的数据
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    //判断k对应的元素是否存在
    private boolean contains(int k) {
        return qp[k] != -1;
    }

    //把与索引i关联的元素修改为t
    public void changeItem(int i, T t) {
        //修改items数组中i位置的元素为t
        item[i] = t;

        //找到i在pq中出现的位置
        int k = qp[i];

        //堆调整
        sink(k);
        swim(k);
    }

    //删除索引i关联的元素
    private int minIndex() {
        return qp[1];
    }

    //删除索引i关联的元素
    public void delete(int i) {
        //找到i在pq中的索引
        int k = qp[i];

        //交换索引k处的值和索引N处的值
        exchange(k, N);

        //删除qp中的内容
        qp[pq[N]] = -1;

        //删除pq中的内容
        pq[N] = -1;

        //删除item中的内容
        item[k] = null;

        //元素的数量-1
        N--;

        //堆的调整
        sink(k);
        swim(k);
    }

    //往堆中插入一个元素,并关联索引i
    public void insert(int i, T t) {
        //判断i是否已经被关联，如果已经被关联，则不让插入
        if (contains(i)) {
            return;
        }
        //元素个数+1
        N++;

        //把数据存储到item对应的i位置处
        item[i] = t;

        //把i存储到pq中
        pq[N] = i;

        //通过qp来记录pq中的i
        qp[i] = N;

        //通过堆上浮完成堆的调整
        swim(N);
    }

    //删除推中最大的元素，并返回这个最大的元素
    public int delMin() {
        //获取最小元素关联的索引
        int minIndex = qp[1];

        //交换pq中的索引1处和最大索引处的元素
        exchange(1, N);

        //删除qp中对应的内容
        qp[pq[N]] = -1;

        //删除pq最大索引处的内容
        pq[N] = -1;

        //删除item中对应的内容
        item[minIndex] = null;

        //元素个数-1
        N--;

        //下沉调整
        sink(1);

        return minIndex;
    }

    //使用上浮算法，使索引k处的元素能在堆中处于一个正确的位置
    private void swim(int k) {
        while (k > 1) {
            if (less(k, k / 2)) {
                exchange(k, k / 2);
            }

            k = k / 2;
        }
    }

    //使用下沉算法，使索引k处的元素能在堆中处于一个正确的位置
    private void sink(int k) {
        while (2 * k <= N) {
            //找到子节点中的较小值
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

            //比较当前节点和较小值
            if (less(k, min)) {
                break;
            }

            exchange(k, min);
            k = min;
        }
    }
}
