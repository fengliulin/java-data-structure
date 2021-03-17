public class B有序符号表 {
    public static void main(String[] args) {
        OrderlySymbolTable<Integer, String> OrderlySymbolTable = new OrderlySymbolTable<>();
        OrderlySymbolTable.put(1,"A");
        OrderlySymbolTable.put(2,"B");
        OrderlySymbolTable.put(3,"C");
        OrderlySymbolTable.put(4,"D");
        OrderlySymbolTable.put(5,"E");

        System.out.println("插入完毕后，元素个数为" + OrderlySymbolTable.size());

        OrderlySymbolTable.put(2,"替换测试");

        System.out.println("替换完毕后的元素的个数"+ OrderlySymbolTable.size());

        System.out.println(OrderlySymbolTable.get(2));

        OrderlySymbolTable.delete(2);

        System.out.println("删除完毕后元素的个数" + OrderlySymbolTable.size());
    }
}

class OrderlySymbolTable<Key extends Comparable<Key>, Value> {

    private class Node<Key, Value> {
        private Key key;
        private Value value;
        private Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node head;
    private int N;

    public OrderlySymbolTable() {
        this.head = new Node(null, null, null);
        this.N = 0;
    }

    /**
     * 在符号表中插入键值对
     */
    public void put(Key key, Value value) {
        Node<Key, Value> current = head.next;
        Node<Key, Value> pre = head;
        while (current != null && key.compareTo(current.key) > 0) {
            //变换当前结点和前一个结点即可
            pre = current;
            current = current.next;
        }

        //如果当前结点current的键和插入的key一样，则替换
        if (current != null && key.compareTo(current.key) == 0) {
            current.value = value;
            return;
        }

        //如果当前结点current的键和要插入的key不一样，把新的结点插入到current之前
        Node<Key, Value> newNode = new Node<>(key, value, null);
        newNode.next = current;
        pre.next = newNode;

        //元素的个数+1
        N++;
    }

    public void delete(Key key) {
        //找到键为key的键值结点，把该结点从链表中删除
        /*Node n = head;
        while (n.next != null) {
            //判断n结点的下一个结点的键是否为key，如果是就删除该结点
            if (n.next.key.equals(key)) {
                n.next = n.next.next;
                N--;
                return;
            }

            //变换n
            n = n.next;
        }*/

//找到键为key的键值结点，把该结点从链表中删除
        Node current = head.next;
        Node pre = null;

        Node tempNode = null;

        while (current != null) {
            tempNode = current.next;

            if (current.key.equals(key)) {
                pre.next = current.next;
//                current.next = null;
                N--;
                return;
            }
            pre = current;
            current = tempNode;
        }
    }

    public Value get(Key key) {
        //找到键为key的键值结点

        Node<Key, Value> n = head;
        while (n.next != null) {
            n = n.next;
            if (n.key.equals(key)) {
                return n.value;
            }
        }
        return null;
    }



    public int size() {
        return N;
    }
}
