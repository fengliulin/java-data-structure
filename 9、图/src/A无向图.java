import duilie.Queue;

public class A无向图 {
    public static void main(String[] args) {

    }
}

class Graph {

    /* 记录顶点数量 */
    private final int V;

    /* 记录边数量 */
    private int E;

    /* 邻接表 */
    private Queue<Integer>[] adj;

    public Graph(int v) {
        //初始化顶点数量
        V = v;

        //初始化边的数量
        E = 0;

        //初始化邻接表
        adj = new Queue[v];

        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Queue<>();
        }
    }

    //获取图中顶点的数量
    public int V() {
        return V;
    }

    //获取图中边的数量
    public int E() {
        return E;
    }

    //向图中添加一条边v-w
    public void addEdge(int v, int w) {
        /*
        在无向图中，边是没有方向的，所以该边既可以说是从v到w的边，
        又可以说是从w到v的边，因此，需要让w出现在v的邻接表中，并且
        还要让v出现在w的邻接表中
        */
        adj[v].enqueue(w);
        adj[w].enqueue(v);

        //边的数量+1
        E++;
    }

    //获取和顶点v相邻的所有顶点
    public Queue<Integer> adj(int v) {
        return adj[v];
    }
}
