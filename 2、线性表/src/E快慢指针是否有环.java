public class E快慢指针是否有环 {

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

        //产生环
        seven.next = third;

        //查找环的入口结点
        boolean circle = isCircle(first);
        System.out.println("first链表是否有环：" + circle);
    }


    public static boolean isCircle(Node<String> first) {
        //定义快慢指针
        Node<String> fast = first;
        Node<String> slow = first;

        //遍历链表,如果快慢指针指向了同一个结点，那证明有环
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast.equals(slow)) { //判断快慢指针是否相遇
                return true;
            }
        }

        return false;
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
