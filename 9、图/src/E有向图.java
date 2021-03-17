import duilie.Queue;

public class E有向图 {
    public static void main(String[] args) {

    }
}

class Digraph {

    /* 记录顶点数量 */
    private final int V;

    /* 记录边数量 */
    private int E;

    /* 邻接表 */
    private Queue<Integer>[] adj;

    /**
     * 创建一个包含v个定点但不包含边的有向图
     * @param v
     */
    public Digraph(int v) {
        V = v;
        E = 0;
        adj = new Queue[v]; //创建一个可以存放队列的数组

        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Queue<Integer>(); //给每个数组空间生成一个空的队列对象
        }
    }

    /**
     * 获取图中顶点的数量
     * @return
     */
    public int V() {
        return V;
    }

    /**
     * 获取图中边的数量
     * @return
     */
    public int E() {
        return E;
    }

    /**
     * 向有向图中添加一条边
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        /*
        只需要让定点w出现在顶点v的邻接表中，因为边是有方向的，
        最终，顶点v的邻接表中存储的相邻顶点的含义是：v -> 其他顶点
        */

        adj[v].enqueue(w);
        E++;
    }

    /**
     * 获取由v指出的边所链接的所有顶点
     * @param v
     * @return
     */
    public Queue<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * 该图的反向图
     * @return
     */
    private Digraph reverse() {
        //创建有向图对象
        Digraph r = new Digraph(V);
        for (int v = 0; v < V; v++) {
            //获取由该顶点v指出的所有边
            for (Integer w : adj[v]) { //原图中表示的是由顶点v -> w的边
                r.addEdge(w, v);
            }
        }
        return r;
    }
}
