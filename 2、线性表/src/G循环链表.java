public class G循环链表 {

    public static void main(String[] args) throws Exception {
        Node<String> first = new Node<>("aa", null);
        Node<String> second = new Node<>("bb", null);
        Node<String> third = new Node<>("cc", null);
        Node<String> fourth = new Node<>("dd", null);
        Node<String> fifth = new Node<>("ee", null);
        Node<String> six = new Node<>("ff", null);
        Node<String> seven = new Node<>("gg", null);

        //完成结点之间的指向
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = six;
        six.next = seven;

        seven.next = first; //指向第一个结点，就产生了循环链表

    }




    private static class Node<T> {
        T item;
        Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}
