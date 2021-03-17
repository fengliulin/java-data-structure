public class A符号表 {
    public static void main(String[] args) {
        SymbolTable<Integer, String> symbolTable = new SymbolTable<>();
        symbolTable.put(1,"A");
        symbolTable.put(2,"B");
        symbolTable.put(3,"C");
        symbolTable.put(4,"D");
        symbolTable.put(5,"E");

        System.out.println("插入完毕后，元素个数为" + symbolTable.size());

        symbolTable.put(2,"替换测试");

        System.out.println("替换完毕后的元素的个数"+ symbolTable.size());

        System.out.println(symbolTable.get(2));

        symbolTable.delete(2);

        System.out.println("删除完毕后元素的个数" + symbolTable.size());
    }
}

class SymbolTable<Key, Value> {

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

    public SymbolTable() {
        this.head = new Node(null, null, null);
        this.N = 0;
    }

    public void put(Key key, Value value) {
        // 符号表中已经存在了key的键值对，那么只需要找到该结点，替换值value即可
        Node n = head;
        while (n.next != null) {
            //变换n
            n = n.next;

            //判断n结点存储的键是否为key，如果是，替换n结点的值
            if (n.key.equals(key)) {
                n.value = value;
                return;
            }
        }

        /*如果符号表中不存在key的键值对，只需要创建新结点，保存插入的键值对，
        把新结点插入到链表的头部，head.next = 新结点即可*/
        Node newNode = new Node(key, value, null);
        Node oldFirst = head.next;
        head.next = newNode;
        newNode.next = oldFirst;

        //元素个数+1
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
                current.next = null;
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
