package binaryTree;

import binaryTree.duilie.Queue;

import java.util.stream.Stream;

public class A二叉查找树 {
    public static void main(String[] args) {
        BinaryTree<String, String> b1 = new BinaryTree<>();
        b1.put("E","5");
        b1.put("B","2");
        b1.put("G","7");
        b1.put("A","1");
        b1.put("D","4");
        b1.put("F","6");
        b1.put("H","8");
        b1.put("C","3");


        //测试后序遍历
        Queue<String> keys = b1.layerErgodic();
        for (String key : keys) {
            String value = b1.get(key);
            System.out.println(key + "=" +value);
        }

        //最大深度
        System.out.println(b1.maxDepth());


    }
}

class BinaryTree<Key extends Comparable<Key>, Value> {


    //前序遍历
    //获取整个树中所有的键
    public Queue<Key> preErgodic() {
        Queue<Key> keys = new Queue<>();
        preErgodic(root, keys);
        return keys;
    }

    //前序遍历
    private void preErgodic(Node<Key, Value> current, Queue<Key> keys) {
        if (current == null) {
            return;
        }

        //把current节点key放入到队列keys中
        keys.enqueue(current.key);

        //递归遍历current节点的左子树
        if (current.left != null) {
            preErgodic(current.left, keys);
        }

        //递归遍历current节点的右子树
        if (current.right != null) {
            preErgodic(current.right, keys);
        }
    }




    //-----------------------------------------------------

    //中序遍历

    //使用中序遍历获取树中所有的键
    public Queue<Key> midErgodic(){
        Queue<Key> keys = new Queue<>();
        midErgodic(root, keys);
        return keys;
    }

    public void midErgodic(Node<Key, Value> current, Queue<Key> keys) {
        if (current == null) {
            return;
        }

        //先递归，把左子树中的键放到keys中
        if (current.left != null) {
            midErgodic(current.left, keys);
        }

        //把当前节点current的键放到keys中
        keys.enqueue(current.key);

        //在递归，把右子树中的键放到keys中
        if (current.right != null) {
            midErgodic(current.right, keys);
        }
    }




    //-----------------------------------------------------

    //后序遍历
    public Queue<Key> afterErgodic() {
        Queue<Key> keys = new Queue<>();
        afterErgodic(root, keys);
        return keys;
    }

    private void afterErgodic(Node<Key, Value> current, Queue<Key> keys) {
        if (current == null) {
            return;
        }

        //通过递归把左子树的所有键放入到keys中
        if (current.left !=null) {
            afterErgodic(current.left, keys);
        }

        //通过递归把右子树的所有键放入到keys中
        if (current.right != null) {
            afterErgodic(current.right, keys);
        }

        //把current节点放入到keys中
        keys.enqueue(current.key);
    }




    //-----------------------------------------------------
    //层序遍历

    public Queue<Key> layerErgodic() {
        Queue<Key> keys = new Queue<>();
        Queue<Node> nodes = new Queue<>();

        //默认往队列中放入根节点
        nodes.enqueue(root);
        while (!nodes.isEmpty()) {
            //从队列中弹出一个节点，把key放入到keys中
            Node<Key, Value> current = nodes.dequeue();
            keys.enqueue(current.key);

            //判断当前节点还有没有左子节点，如果有，则放入到nodes中
            if (current.left != null) {
                nodes.enqueue(current.left);
            }

            //判断当前节点还有没有右子节点，如果有，则放入到nodes中
            if (current.right != null) {
                nodes.enqueue(current.right);
            }
        }

        return keys;
    }



    //获取整个树的最大深度
    public int maxDepth() {
        return maxDepth(root);
    }

    //获取指定树current的最大深度
    public int maxDepth(Node current) {
        if (current == null) {
            return 0;
        }

        int max = 0;        //current节点的最大深度
        int maxLeft=0;      //左子树的最大深度
        int maxRight = 0;   //右子树的最大深度

        //计算current节点左子树最大深度
        if (current.left != null) {
            maxLeft = maxDepth(current.left);
        }

        //计算current节点右子树最大深度
        if (current.right != null) {
            maxRight = maxDepth(current.right);
        }

        //比较左子树最大深度和右子树最大深度，取较大值+1即可
        max = maxLeft < maxRight ? maxLeft + 1 : maxRight + 1;

        //结果返回给上一层调用的左或者右子树
        return max;
    }



    //查找最大的键
    public Key max() {
        return max(root).key;
    }

    //在指定的current中，找到最大的键所在
    public Node<Key, Value> max(Node current) {
        if (current.right != null) {
            return max(current.right);
        } else if (current.right == null) {
            return current;
        }

        return null;
    }

    //查找整个树中最小的键
    public Key min() {
        return min(root).key;
    }

    //在指定树中找出最小键所在的节点
    public Node<Key, Value> min(Node current) {

        //需要判断current还有没有左子节点,如果有继续向左找，如果没有，则current就是最小键所在的节点
        if (current.left != null) {
            return current =  min(current.left);
        } else if (current.left == null) {
            return current;
        }

        return null;
    }


    private Node root; //根结点
    private int N;

    /**
     * 向树中插入一个键值对
     *//*
    public void put(Key key, Value value) {
        Node newNode = new Node(key, value, null, null);
        if (root == null) {
            root = newNode;
        }
         put(root, newNode);
    }

    /**
     * 向树中插入一个键值对
     */
    public void put(Key key, Value value) {

        root = put(root, key, value);
    }

    /**
     * 给执行树x上，添加键值对，并返回添加后的新树
     */
    private Node put(Node<Key, Value> current, Key key, Value value) {

        if (current == null) {
            N++;
            return new Node(key, value, null, null);
        }

        //如果x子树为空
        int cmp = key.compareTo(current.key);


        //如果x子树不为空
        if (cmp > 0) {
            //如果key大于x节点的键，则继续找x节点的右子树
            current.right = put(current.right, key, value);

        } else if (cmp < 0) {

            //如果key小于x节点的键，则继续找x节点的左子树
            current.left = put(current.left, key, value);

        } else if (cmp == 0) {
            //如果key等于x节点的键，组替换x节点的值为value
            current.value = value;
        }

        return current;
    }


    //这是往后查找，找到位置插入

    /**
     给执行树x上，添加键值对，并返回添加后的新树
     /*
     private void put(Node<Key, Value> x, Node<Key, Value> newNode) {

     //如果x子树为空
     int cmp = newNode.key.compareTo(x.key);


     //如果x子树不为空
     if (cmp > 0) {
     if (x.right == null) { //跟节点
     N++;
     x.right = newNode;

     }else {
     //如果key大于x节点的键，则继续找x节点的右子树
     put(x.right, newNode);
     }

     } else if (cmp < 0) {

     if (x.left == null) { //跟节点
     N++;
     x.left = newNode;
     } else {
     //如果key小于x节点的键，则继续找x节点的左子树
     put(x.left, newNode);
     }

     } else if (cmp == 0){
     //如果key等于x节点的键，组替换x节点的值为value
     x.value = newNode.value;
     }

     //        return x;
     //比较x节点的键和key的大小
     }*/

    /**
     * 根据key，从树中找出对应的值
     */
    public Value get(Key key) {
        return (Value) get(root, key);
    }

    /**
     * 根据key，从树中找出对应的值
     */
    public Value get(Node<Key, Value> current, Key key) {
        //x树为null
        if (current == null) {
            return null;
        }
        //x树不为null

        int cmp = key.compareTo(current.key);

        //比较key和x节点的大小
        if (cmp > 0) {
            //如果key大于x节点的键，则继续找x节点的右子树
            return (Value) get(current.right, key);
        } else if (cmp < 0) {
            //如果key小于x节点的键，则继续找x节点的左子树
            return (Value) get(current.left, key);
        } else if (cmp == 0){
            //如果key等于x节点的键，就找到了键key的节点，只需要返回x节点的值即可
            return current.value;
        }
        return null;
    }

    /**
     * 删除树中对应的键值对
     */
    public void delete(Key key) {
        delete(root, key);
    }

    /**
     * 删除指定树x上的key的键值对，并返回删除后的新树
     */
    private Node delete(Node<Key, Value> current, Key key) {
        //current为null
        if (current == null) {
            return null;
        }

        //current不为空继续查找
        int cmp = key.compareTo(current.key);

        if (cmp > 0) {
            current.right = delete(current.right, key);
        } else if (cmp < 0) {
            current.left = delete(current.left, key);
        } else if (cmp == 0){
            //找到了，就删除，current当前节点
            //让元素个数-1
            N--;

            //找到右子树中最小的节点
            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }

            //查找左子树的最小key
            Node minNode = current.right;
            while (minNode.left != null) {
                minNode = minNode.left;
            }

            //删除右子树最小的节点
            Node n = current.right;
            while (n.left != null) {
                if (n.left.left == null) {
                    n.left = null;
                } else {
                    //变换n节点
                    n = n.left;
                }
            }


            //让current节点的左子树成为minNode的左子树
            minNode.left = current.left;

            //让current节点的右子树成为minNode的右子树
            minNode.right = current.right;

            //让current的节点的父节点指向minNode
            current = minNode;


            return current;
        }

        return current;
    }




    public int size() {
        return N;
    }

    private class Node<Key, Value> {
        protected Key key;
        protected Value value;
        protected Node left;
        protected Node right;

        public Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }


    }
}
