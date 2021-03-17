import com.sun.source.tree.WhileLoopTree;

import java.util.Scanner;

public class B并查集优化 {
    public static void main(String[] args) {
//创建并查集对象
        UF uf = new UF(5);
        System.out.println("默认情况下，并查集中有：" + uf.count() + "个分组");

        //从控制台录入两个要合并的元素，调用union方法合并，观察合并后并查集中的分组是否减少
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入第一个要合并的元素：");
            int p = sc.nextInt();
            System.out.println("请输入第二个要合并的元素：");
            int q = sc.nextInt();

            //判断这个两个元素是否已经在同一组了
            if (uf.connected(p, q)) {
                System.out.println(p + "元素和" + q + "元素已经在同一个组中了");
                continue;
            }

            uf.union(q, q);

            System.out.println("当前并查集中还有：" + uf.count() + "个分组");
        }
    }
}

class UF_Tree {

    /* 记录节点元素和该元素所在分组的标识 */
    private int[] eleAndGroup;

    /* 记录并查集中数据的分组个数 */
    private int count;

    public UF_Tree(int count) {
        //初始化分组的数量，默认情况下，有N个分组
        this.count = count;

        //初始化eleAndGroup数组
        this.eleAndGroup = new int[count];

        /* 初始化eleAndGroup中的元素及其所在组的标识符,
        让eleAndGroup数组的索引作为并查集的每个节点的元素，并且
        让每个索引处的值（该元素所在组的标识符）就是该索引 */

        for (int i = 0; i < eleAndGroup.length; i++) {
            eleAndGroup[i] = i;
        }
    }

    //获取当前并查集中的数据右多少个分组
    public int count() {
        return this.count;
    }

    //判断并查集中元素p和元素q是否在同一分组中
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    //元素p所在的分组的标志符
    public int find(int p) {

        /*if (p == eleAndGroup[p]) {
            return p;
        }

        find(eleAndGroup[p]);*/

        while (true) {
            if (p == eleAndGroup[p]) {
                return p;
            }
            p = eleAndGroup[p];
        }
    }

    //把p元素所在分组和q元素所在分组合并
    public void union(int p, int q) {
        //找到p元素和q元素所在组对应的树的根节点
        int pRoot = find(p);
        int qRoot = find(q);

        //如果p和q已经在同一个分组，则不需要合并了
        if (pRoot == qRoot) {
            return;
        }

        //让p所在的树的根的节点的父节点为q所在树的根节点即可
        eleAndGroup[pRoot] = qRoot;

        //组的数量-1
        this.count--;
    }
}
