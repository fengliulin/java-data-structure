public class H约瑟夫问题 {

    public static void main(String[] args) throws Exception {

        //解决约瑟夫问题

        //1、构建循环列表，包含41个结点，分别存储1到41之间的值
        Node<Integer> first = null; //记录首结点
        Node<Integer> pre = null;   //记录前一个结点

        for (int i = 1; i <= 41; i++) {
            //如果是第一个结点
            if (i == 1) {
                first = new Node<>(i, null);
                pre = first;
                continue;
            }

            //如果不是第一个结点
            Node<Integer> newNode = new Node<>(i, null);
            pre.next = newNode;
            pre = newNode;


            //如果是最后一个结点，那么需要让最后一个结点的下一个结点变为first,编程循环链表了
            if (i == 41) {
                pre.next = first;
            }
        }

        //2、需要count计数器，模拟报数
        int count = 0;

        //3、遍历循环链表
        Node<Integer> current = first;  //记录每次遍历拿到的结点，默认从首次开始
        Node<Integer> before = null;    //记录当前结点的上一个结点

        while (current != current.next) {
            //模拟报数
            count++;

            //判断当前报数是不是为3
            if (count == 3) {
                //如果是3,把当前结点删除，打印当前结点，重置count=0,让当前结点current后移
                before.next = current.next;
                System.out.print(current.item + "，");
                count = 0;
                current = current.next;
            } else {
                //如果不是3，让before变为当前结点，让当前结点后移
                before = current; //记录前一个结点
                current = current.next; //记录下一次循环的结点
            }
        }
        System.out.println();
        //打印最后一个元素
        System.out.println("测试" + current.item);
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
