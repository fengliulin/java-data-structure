import java.util.Arrays;

public class D希尔排序 {
    public static void main(String[] args) {
//        Integer[] arr = {4, 6, 8, 7, 9, 2, 10, 1};
        Integer[] arr = {9,1,2,5,7,4,8,6,3,5};

        Shell.sort(arr);

        System.out.println(Arrays.toString(arr));
    }
}

class Shell {

    /**
     * 对数组a种的元素进行排序
     *
     * @param a
     */
    public static void sort(Integer[] a) {
        // 1、根据数组a的长度，确定增长量h的初始值
        int h = 1;
        while (h < a.length / 2) {
            h = 2 * h + 1;
        }

        //2、希尔排序
        while (h >= 1) {
            // 排序
            // 2.1、找到待插入的元素,初始位置
            for (int i = h; i < a.length; i++) {
                // 2.2、把待插入的元素插入到有序数列中
                for (int j = i; j >= h; j -= h) {
                    // 待插入的元素是a[j],比较a[j]和a[j-h]
                    if (greater(a[j - h], a[j])) {
                        // 交换元素
                        each(a, j - h, j);
                    } else {
                        break;
                    }
                }
            }


            // 减小h的值
            h = h / 2;
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
