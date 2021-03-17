import java.util.Arrays;

public class A冒泡排序 {
    public static void main(String[] args) {
        Integer[] arr = {4, 5, 6, 3, 2, 1};

        Bubble.sort(arr);

        System.out.println(Arrays.toString(arr));
    }
}

class Bubble {

    /**
     * 对数组a种的元素进行排序
     *
     * @param a
     */
    public static void sort(Comparable[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                // 比较索引j和索引j+1
                if (greater(a[j], a[j + 1])) {
                    each(a, j, j + 1);
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
