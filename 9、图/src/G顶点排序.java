import zhan.Stack;

public class G顶点排序 {
    public static void main(String[] args) {

    }
}

class DepthFirstOrder {
    private boolean[] marked; //索引代表顶点，值表示当前顶点是否已经被搜索
    private Stack<Integer> reversePost; //使用栈，存储顶点序列

    //创建一个顶点排序对象，生成顶点线性序列；
    public DepthFirstOrder(Digraph G) {
        marked = new boolean[G.V()];
        reversePost = new Stack<Integer>();

        //遍历图中的每一个顶点，让每一个顶点作为入口，完成一次深度优先搜索
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    //基于深度优先搜索，生成顶点线性序列
    private void dfs(Digraph G, int v) {
        //标记当前v已经被搜索
        marked[v] = true;

        //通过循环深度搜索顶点v
        for (Integer w : G.adj(v)) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }

        //让顶点v进栈
        reversePost.push(v);
    }

    //获取顶点线性序列
    public Stack<Integer> reversePost() {
        return reversePost;
    }
}

