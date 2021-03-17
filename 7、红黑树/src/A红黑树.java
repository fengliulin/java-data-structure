public class A红黑树 {
    public static void main(String[] args) {
        //创建红黑树
        RedBlackTree<String, String> tree = new RedBlackTree<>();

        //往树中插入元素
        tree.put("1","张一");
        tree.put("2","张二");
        tree.put("3","张三");

        //从树中获取元素
        String r1 = tree.get("1");
        System.out.println(r1);

        String r2 = tree.get("2");
        System.out.println(r2);

        String r3 = tree.get("3");
        System.out.println(r3);

    }
}

class RedBlackTree<Key extends Comparable<Key>, Value> {
    private class Node<Key, Value> {
        private Node left;
        private Node right;
        private Key key;
        private Value value;

        /* 由其父节点指向它的链接的颜色 */
        private boolean color; // 红：true，黑false

        public Node(Key key, Value value, Node left, Node right, boolean color) {
            this.left = left;
            this.right = right;
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    private Node root;
    private int N;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    //判断当前节点的父节点指向链接是否为红色
    private boolean isRed(Node current) {
        if (current == null) {
            return false; // 返回，证明空链接是黑色
        }

        return current.color == RED;
    }

    //左旋转调整
    private Node rotateLeft(Node h) {
        //获取h节点的右子节点，表示x
        Node<Key, Value> x = h.right;

        //让x节点的左子节点称为h节点的右子节点
        h.right = x.left;

        //让h成为x节点的左子节点
        x.left = h;

        //让x节点的color属性等于h节点的color属性
        x.color = h.color;

        //让h节点的color属性变为红色
        h.color = RED;

        return x;
    }

    //右旋调整
    private Node rotateRight(Node h) {
        //获取h节点的左子节点，表示x
        Node x = h.left;

        //让x节点的右子节点成为h节点的左子节点
        h.left = x.right;

        //让h节点成为x节点的右子节点
        x.right = h;

        //让x节点的color属性等于h节点的color属性
        x.color = h.color;

        //让h节点的color属性为红色
        h.color = RED;

        return x;
    }

    //颜色反转，相当于完成拆分4-节点
    private void flipColors(Node h) {
        //当前节点变为红色
        h.color = RED;

        //左子节点和右子节点变为黑色
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    //在整个树上完成插入操作
    public void put(Key key, Value val) {
        root = put(root, key, val);

        //跟节点的颜色总是黑色
        root.color = RED;
    }

    //在指定树中，完成插入操作，并返回添加元素后的新的树
    private Node put(Node<Key, Value> h, Key key, Value val) {
        //判断h是否为空，如果为空则返回一个红色的节点就可以
        if (h == null) {
            N++;
            return new Node(key, val, null, null, RED);
        }

        //比较h节点的键和key的大小
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            //继续往左
            h.left = put(h.left, key, val);
        } else if (cmp > 0) {
            //继续往右
            h.right = put(h.right, key, val);
        } else if (cmp == 0) {
            //相等，替换值
            h.value = val;
        }

        //进行左旋:当前节点h的左子节点为黑色，右子节点为红色，需要左旋
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }

        //进行右旋转:当前节点h的左子节点和左子节点的节点都为红色，需要右旋
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }

        //颜色反转:当前节点的左子节点和右子节点都为红色时，需要颜色反转
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        return h;
    }

    //根据key，从树中找出对应的值
    public Value get(Key key) {
        return (Value) get(root, key);
    }

    //从指定树current中，找出key对应的值
    private Value get(Node<Key, Value> current, Key key) {
        if (current == null) {
            return null;
        }

        //比较current节点的键和key的大小
        int cmp = key.compareTo(current.key);
        if (cmp < 0) {
            return (Value) get(current.left, key);
        } else if (cmp > 0) {
            return (Value) get(current.right, key);
        } else if (cmp == 0) {
            return current.value;
        }
        return null;
    }

    //获取树中元素的个数
    public int size() {
        return N;
    }
}

