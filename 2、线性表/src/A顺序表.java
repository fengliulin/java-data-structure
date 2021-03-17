import javax.swing.*;
import java.util.Iterator;

public class A顺序表 {
    public static void main(String[] args) {
        SequenceList<String> sl = new SequenceList<>(1);

        //测试插入
        sl.insert("姚明");
        sl.insert("科比");
        sl.insert("麦迪");
        sl.insert(1, "詹姆斯");

        for (String s : sl) {
            System.out.println(s);
        }

        // 测试获取
        String result = sl.get(1);
        System.out.println(result);

        //测试删除
        String remove = sl.remove(0);
        System.out.println(remove);

        //测试清空
        sl.clear();
        System.out.println(sl.length());

    }
}

class SequenceList<T> implements Iterable<T> {

    /* 存储元素的数组 */
    private T[] elements;

    /* 记录当前顺序表中的元素个数 */
    private int N;

    /**
     * 初始化数组
     */
    public SequenceList(int capacity) {
        this.elements = (T[]) new Object[capacity]; //初始化数组
        this.N = 0; // 初始化长度
    }

    public void clear() {
        this.N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int length() {
        return N;
    }

    public T get(int i) {
        return elements[i];
    }

    public void insert(T t) {
        if (N == elements.length) {
            resize(elements.length * 2);
        }
        elements[N++] = t;
    }

    public void insert(int i, T t) {
        if (N == elements.length) {
            resize(elements.length * 2);
        }

        //把i索引处的元素及其后面的元素依次向后移动
        for (int index = this.N; index > i; index--) {
            elements[index] = elements[index - 1];
        }

        //再把t元素放到i索引处
        elements[i] = t;

        N++;

    }

    public T remove(int i) {
        //记录索引i处的值
        T current = elements[i];

        //索引i后面的元素依次向前移动一位即可
        for (int index = i; index < this.N - 1; index++) {
            elements[index] = elements[index + 1];
        }

        //元素个数-1
        this.N--;

        if (N < elements.length / 4) {
            resize(elements.length / 2);
        }

        return current;
    }

    public int indexOf(T t) {
        for (int i = 0; i < this.N; i++) {
            if (elements[i].equals(t)) {
                return i;
            }
        }

        return -1;
    }

    // 根据参数newSize，重置elements的大小
    public void resize(int newSize) {
        //定义一个临时数组，指向原数组
        T[] temp = elements;

        //创建新数组
        elements = (T[]) new Object[newSize];

        //把原数组的数据拷贝到新数组即可
        for (int i = 0; i < N; i++) {
            elements[i] = temp[i];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new SIterator();
    }

    private class SIterator implements Iterator {

        private int cursor;

        public SIterator() {
            this.cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor < N;
        }

        @Override
        public Object next() {
            return elements[cursor++];
        }
    }
}
