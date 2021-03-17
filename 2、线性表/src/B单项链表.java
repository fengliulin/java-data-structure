import java.util.Iterator;

public class B单项链表 {
    public static void main(String[] args) {
        LinkList<String> sl = new LinkList();
        //测试插入
        sl.insert("1");
        sl.insert("2");
        sl.insert("3");
        sl.insert("4");

        sl.reverse();
        for (String o : sl) {
            System.out.println(o);
        }

        System.out.println("分割");

    }
}

class LinkList<T> implements Iterable<T> {

    private Node head;
    private int N;

    public LinkList() {
        //初始化头结点
        this.head = new Node(null, null);

        //初始化元素个数
        this.N = 0;
    }


    //反转链表
    public void reverse() {
        if (isEmpty()) {
            return;
        }

//       reverse(head.next);

        Node pre = null;
        Node current = head;

        Node temp = null;

        while (current != null) {
            temp = current.next; // 存储变量迭代循环

            if (pre == head){  //指针位置交换， 如果是头结点就pass
                current.next = null;
            } else {
                current.next = pre;
            }
            pre = current;
            current = temp;
        }

        head.next = pre;

    }


    public Node reverse(Node currentNode) {

        //递归结束条件，把头指针指向最后一个结点，并返回最后一个结点
        if (currentNode.next == null) {
            head.next = currentNode;
            return currentNode;
        }

        Node nextNode = reverse(currentNode.next);

        //归回来的下一个结点的指针，指向归回来的当前的结点的指针，完成了链表的反转
        nextNode.next = currentNode;

        // 必须当前的结点为空，因为递归的变量是局部变量，如果不为空，那么就会出现无法预知的错误
        currentNode.next = null;

        return currentNode;

    }


    //反转指定的结点curr
     /*public Node reverse(Node curr) {
       if (curr.next == null) {
            head.next = curr;
            return curr;
        }

        //递归的去反转当前的结点的下一个结点,返回值就是链表反转后，当前结点的上一个结点
        Node pre = reverse(curr.next);

        //让返回的结点下一个结点变为当前结点curr
        pre.next = curr;

        //把当前结点的下一个结点变为null
        curr.next = null;

        return curr;*/

        /*while (true) {

        }
return null;
    }*/

    //清空链表
    public void clear() {
        this.head.next = null;
        this.N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int length() {
        return N;
    }

    public T get(int i) {
        //通过循环，从头结点开始往后找，依次找i次，就可以找到对应的元素
        Node n = this.head.next;
        for (int index = 0; index < i; index++) {
            n = n.next;
        }

        return (T) n.item;
    }

    public void insert(T t) {

        //找到当前最后一个结点
        Node n = head;
        while (n.next != null) {
            n = n.next;
        }

        //创建新结点，保存元素t
        Node newNode = new Node(t, null);

        //让当前最后一个结点指向新的结点
        n.next = newNode;

        //元素的个数+1
        N++;
    }

    public void insert(int i, T t) {
        //找到i位置前一个结点
        Node pre = head;
        for (int index = 0; index <= i - 1; index++) {
            pre = pre.next;
        }

        //找到i位置的结点
        Node curr = pre.next;

        //创建新的结点，并且新结点需要指向原来i位置的结点
        Node newNode = new Node(t, curr);

        //原来i位置的前一个结点指向新结点即可
        pre.next = newNode;

        //元素个数+1
        N++;
    }

    public T remove(int i) {
        //找到i位置的前一个结点
        Node pre = head;
        for (int index = 0; index <= i - 1; index++) {
            pre = pre.next;
        }

        //要找到i位置的结点
        Node curr = pre.next;

        //找到i位置的下一个结点
        Node nextNode = curr.next;

        //前一个结点指向下一个结点
        pre.next = nextNode;


        //元素个数-1
        N--;

        return (T) curr.item;
    }

    public int indexOf(T t) {
        //从头结点开始，依次找到每一个结点，取出item，和t比较
        Node n = head;
        for (int i = 0; n.next != null; i++) {
            n = n.next;
            if (n.item.equals(t)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Iterator iterator() {
        return new LIterator();
    }

    private class LIterator implements Iterator {
        private Node n;

        public LIterator() {
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
        public T item;
        public Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}
