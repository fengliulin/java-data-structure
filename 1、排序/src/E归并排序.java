import java.util.Arrays;

public class E归并排序 {
    public static void main(String[] args) {
//        Integer[] arr = {4, 6, 8, 7, 9, 2, 10, 1};
        Integer[] arr = {8,4,5,7,1,3,6,2};

        Merge.sort(arr);

        System.out.println(Arrays.toString(arr));
    }
}

class Merge {

    // 归并所需要的辅助数组
    private static Comparable[] assist;

    // 比较（v元素 < w元素）是否小于
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 对数组a种的元素进行排序
     */
    public static void sort(Comparable[] a) {
        // 1、初始化辅助数组assist
        assist = new Integer[a.length];

        // 2、定义一个lo变量，和hi变量，分别记录数组中最小的索引和最大的索引
        int lo = 0; /* 最小索引 */
        int hi = a.length - 1; /* 最大索引 */

        // 3、调用sort重载方法完成数组a中，从索引lo到索引hi的元素的排序
        sort(a, lo, hi);

    }

    /**
     * 对数组a种从0到hi的元素进行排序
     */
    public static void sort(Comparable[] a, int lo, int hi) {
        // 做安全性校验
        if (hi <= lo) {
            return;
        }

        //对lo到hi之间数组进行分为两个组
        int mid = (lo + hi) / 2;

        //分别每一组数据进行排序
        sort(a, lo, mid);
        sort(a, mid + 1, hi);

        //在把两个组中的数组进行归并
        merge(a, lo, mid, hi);
    }

    /**
     * 对数组种，从lo到mid为一组，从mid+1到hi为一组，对这两组数据进行归并
     */
    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        // 定义三个指针
        int i = lo;
        int p1 = lo;
        int p2 = mid + 1;

        // 遍历，移动p1指针和p2指针，比较对应索引处的值，找出小的那个，放到辅助数组的对应索引处
        while (p1 <= mid && p2 <= hi) {
            // 比较对应索引处的值
            if (less(a[p1], a[p2])) {
                assist[i++] = a[p1++];
            } else {
                assist[i++] = a[p2++];
            }
        }

        // 遍历，如果p1的指针没有走完，那么顺序移动p1指针，把对应的元素放到辅助数组的对应索引处
        while (p1 <= mid) {
            assist[i++] = a[p1++];
        }

        // 遍历，如果p2的指针没有走完，那么顺序移动p1指针，把对应的元素放到辅助数组的对应索引处
        while (p2 <= hi) {
            assist[i++] = a[p2++];
        }

        // 把辅助数组中的元素拷贝到原数组中
        for (int index = lo; index < hi; index++) {
            a[index] = assist[index];
        }
    }


    /**
     * 数组元素i和j交换位置
     */
    private static void each(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
