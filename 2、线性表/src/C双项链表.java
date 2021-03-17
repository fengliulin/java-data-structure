import java.util.Iterator;

public class C双项链表 {
    public static void main(String[] args) {
        LinkList<String> sl = new LinkList();
        //测试插入
        sl.insert("1");
        sl.insert("2");
        sl.insert("3");
        sl.insert("4");

        for (String o : sl) {
            System.out.println(o);
        }

    }
}

class TowWayLinkList<T> implements Iterable<T>{

    private Node head;
    private Node last;
    private int N;

    public TowWayLinkList() {
        //初始化头和尾结点
        this.head = new Node(null, null, null);
        this.last = null;

        //初始化元素个数
        this.N = 0;
    }

    //清空链表
    public void clear() {
        this.head.pre = null;
        this.head.next = null;
        this.head.item = null;
        this.last = null;
        this.N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int length() {
        return N;
    }

    public T getFirst() {
        if (isEmpty()) {
            return null;
        }

        return (T) this.head.next.item;
    }

    public T getLast() {
        if (isEmpty()) {
            return null;
        }

        return (T) last.item;
    }


    public void insert(T t) {

        if (isEmpty()) {
            //如果链表为空
            //创建新结点
            Node<T> newNode = new Node<>(head, t, null);
            //让新结点成为尾结点
            last = newNode;
            //让头结点指向尾结点
            head.next = last;
        } else {
            //如果链表不为空
            Node oldLast = last;

            //创建新的结点
            Node<T> newNode = new Node<>(oldLast, t, null);

            //让当前尾结点指向新结点
            oldLast.next = newNode;

            //让新结点最成为尾结点
            last = newNode;
        }

        //元素个数+1
        N++;
    }

    //指定位置处插入
    public void insert(int i, T t) {
        // 找到i位置的前一个结点
        Node pre = head;
        for (int index = 0; index < i; index++) {
            pre = pre.next;
        }

        //找到i位置的结点
        Node curr = pre.next;

        //创建新结点
        Node newNode = new Node(pre, t, curr);

        //让i位置的前一个结点的下一个结点变为新结点
        pre.next = newNode;

        //让i位置的前一个结点变为新结点
        curr.pre = newNode;

        //元素个数+1
        N++;
    }

    public T get(int i) {
        Node n = head.next;
        for (int index = 0; index < i; index++) {
            n = n.next;
        }

        return (T) n.item;
    }
    public T remove(int i) {
        // 找到i位置的前一个结点
        Node pre = head;
        for (int index = 0; index < i; index++) {
            pre = pre.next;
        }

        //找到i位置的结点
        Node curr = pre.next;

        //找到i位置的下一个结点
        Node nextNode = curr.next;

        //让i位置的前一个结点的下一个结点变为i位置的下一个结点
        pre.next = nextNode;

        //让i位置的下一个结点的上一个结点变为i位置的前一个结点
        nextNode.pre = pre;

        //元素的个数-1
        N--;

        return (T) curr.item;
    }

    public int indexOf(T t) {
        Node n = head;
        for (int i = 0; n.next != null; i++) {
            n = n.next;
            if (n.next.equals(t)) {
                return i;
            }
        }

        return -1;
    }



    @Override
    public Iterator iterator() {
        return new TIterator();
    }

    private class TIterator implements Iterator {
        private Node n;
        public TIterator() {
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

    private class Node<T> {
        private Node pre;
        private T item;
        private Node next;

        public Node(Node pre, T item, Node next) {
            this.pre = pre;
            this.item = item;
            this.next = next;
        }
    }
}
