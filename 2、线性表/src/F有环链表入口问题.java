public class F有环链表入口问题 {

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
        Node<String> entrance = getEntrance(first);
        System.out.println("first链表是否有环：" + entrance.item);
    }



    public static Node getEntrance(Node<String> first) {
        Node<String> fast = first;
        Node<String> slow = first;
        Node<String> temp = null;

        // 遍历链表，先找到环(快慢指针相遇),准备一个临时指针，指向链表的首结点，
        // 继续遍历，直到慢指针和临时指针相遇，那么相遇时所指向的结点就是环的入口
        while (fast != null && fast.next != null) {
            //变换快慢指针
            fast = fast.next.next;
            slow = slow.next;

            if (fast.equals(slow)) {
                temp = first;
                continue;
            }

            //让临时结点变换
            if (temp != null) {
                temp = temp.next;
                //判断临时指针是否和慢指针相遇
                if (temp.equals(slow)) {
                    break;
                }
            }
        }
        return temp;
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
