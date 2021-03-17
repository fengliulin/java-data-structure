import java.util.Arrays;

public class C插入排序 {
    public static void main(String[] args) {
        Integer[] arr = {4, 6, 8, 7, 9, 2, 10, 1};

        Bubble.sort(arr);

        System.out.println(Arrays.toString(arr));
    }
}

class Insertion {

    /**
     * 对数组a种的元素进行排序
     *
     * @param a
     */
    public static void sort(Integer[] a) {
        for (int i = 1; i < a.length; i++) { // 待排序的元素
            for (int j = i; j > 0; j--) { // 倒序查找
                // 比较索引j处的值和索引j-1处的值，如果索引j-1处的值比索引j处的值大，则交换数据
                // 如果不大，那么就找到合适的位置，退出循环
                if (a[j - 1] > a[j]) {
                    each(a, j-1, j);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 比较v元素是否大于w元素
     *
     * @param v
     * @param w
     * @return
     */
    private static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    /**
     * 数组元素i和j交换位置
     *
     * @param a
     * @param i
     */
    private static void each(Comparable[] a, int i, int j) {
        Comparable temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
