import duilie.Queue;

public class I加权无向图_实现 {
    public static void main(String[] args) {

    }
}

class EdgeWeightedGraph {

    private final int V; //记录顶点数量
    private int E; //记录边数量
    private Queue<Edge>[] adj; //邻接表

    //创建一个含有V个顶点的空加权无向图


    public EdgeWeightedGraph(int v) {
        V = v;
        E = 0;
        this.adj = new Queue[v];

        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Queue<Edge>();
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

    //向加权无向图中添加一条边e
    public void addEdge(Edge e) {
        //需要让边E同时出现在E这个边的两个顶点的邻接表中
        int v = e.either();
        int w = e.other(v);

        adj[v].enqueue(e);
        adj[w].enqueue(e);

        //边的数量+1
        E++;
    }

    //获取和顶点v关联的所有边
    public Queue<Edge> adj(int v) {
        return adj[v];
    }

    //获取加权无向图的所有边
    public Queue<Edge> edges() {
        //创建一个队列对象，存储所有的边
        Queue<Edge> allEdges = new Queue<>();

        //遍历图中的每一个顶点，找到该顶点的邻接表存储了该顶点关联的每一条边

        //因为这是无向图，所以通一条边同时出现在了它关联的两个顶点的邻接表中，需要让一条表只记录一次
        for (int v = 0; v < V; v++) {
            //遍历v顶点的邻接表，找到每一条和v关联的边
            for (Edge e : adj(v)) {
                if (e.other(v) < v) {
                    allEdges.enqueue(e);
                }
            }
        }

        return allEdges;
    }


}