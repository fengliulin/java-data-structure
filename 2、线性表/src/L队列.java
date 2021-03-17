import java.util.ArrayList;
import java.util.Iterator;

public class L队列 {
    public static void main(String[] args) {

        Queue<String> q = new Queue<>();
        
        q.enqueue("a");
        q.enqueue("b");
        q.enqueue("c");
        q.enqueue("d");
        q.enqueue("e");

        for (String str : q) {
            System.out.println(str);
        }

        String result = q.dequeue();
        System.out.println("出队元素是：" + result);
        System.out.println("剩余元素个数：" + q.size());


    }
}

class Queue<T> implements Iterable<T> {
    private Node head;
    private int N;
    private Node last;

    public Queue() {
        this.head = new Node(null, null);
        this.last = null;
        this.N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    /**
     * 往队列中插入一个元素
     */
    public void enqueue(T t) {
       //当前尾结点为null
       if (last == null) {
           last = new Node(t, null);
           head.next = last;
       } else {
           //当前尾结点last不等于null
           Node oldLast = last;
           last = new Node(t, null);
           oldLast.next = last;
       }

       //元素个数+1
        N++;
    }

    /**
     * 从队列中拿出一个元素
     */
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }

        Node oldFirst = head.next;
        head.next = oldFirst.next;

        N--;

        //因为队列其实是在删除元素，因此如果队列中的元素被删除完了，需要重置last=null
        if (isEmpty()) {
            last = null;
        }

        return oldFirst.item;
    }

    @Override
    public Iterator iterator() {
        return new QIterator();
    }

    private class QIterator implements Iterator {

        private Node n;

        public QIterator() {
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