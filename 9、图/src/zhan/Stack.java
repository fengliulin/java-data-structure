package zhan;

import java.util.Iterator;

public class Stack<T> implements Iterable<T> {

    private Node head;
    private int N;

    public Stack() {
        this.head = new Node(null, null);
        this.N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    //弹栈
    public T pop() {
        //找到首结点指向的第一个结点
        Node oldFirst = head.next;
        if (oldFirst == null) {
            return null;
        }

        //让首结点指向原来第一个结点的下一个结点
        head.next = oldFirst.next;
        oldFirst.next = null;  //虽然虚拟机回收，但是手动释放内存
        System.gc(); //或者调用

        //让元素个数-1
        N--;

        return oldFirst.item;
    }

    //压栈
    public void push(T t) {
        // 找到首结点指向的第一个结点
        Node oldFirst = head.next;

        //创建新结点
        Node newNode = new Node(t, null);

        //让首结点指向新结点
        head.next = newNode;

        //让新结点指向原来第一个结点
        newNode.next = oldFirst;

        //元素个数+1
        N++;
    }

    @Override
    public Iterator iterator() {
        return new SIterator();
    }

    private class SIterator implements Iterator {
        private Node n;

        public SIterator() {
            this.n = head;
        }

        @Override
        public boolean hasNext() {
            return n.next != null;
        }

        @Override
        public Object next() {
            n = n.next;
            return n.item;
        }
    }

    private class Node {
        private T item;
        private Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}
