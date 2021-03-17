import duilie.Queue;

public class C广度优先搜索 {
    public static void main(String[] args) {
        Graph G = new Graph(13);
        G.addEdge(0,5);
        G.addEdge(0,1);
        G.addEdge(0,2);
        G.addEdge(0,6);
        G.addEdge(5,3);
        G.addEdge(5,4);
        G.addEdge(3,4);
        G.addEdge(4,6);

        G.addEdge(7, 8);

        G.addEdge(9, 11);
        G.addEdge(9, 10);
        G.addEdge(9, 12);
        G.addEdge(11, 12);

        BreadthFirstSearch search = new BreadthFirstSearch(G, 0);

        int count = search.count();
        System.out.println("与起点0相通的顶点数量为：" + count);

        boolean marked1 = search.marked(5);
        System.out.println("顶点5和顶点0是否相通" + marked1);

        boolean marked2 = search.marked(7);
        System.out.println("顶点7和顶点0是否相通" +marked2);
    }
}

class BreadthFirstSearch {

    /* 索引代表顶点，值表示当前顶点是否已经被搜索 */
    private boolean[] marked;

    /* 记录有多少个顶点与s顶点相通 */
    private int count;

    /* 用来存储待搜索邻接表的点 */
    private Queue<Integer> waitSearch;

    //构造广度优先搜索对象，使用广度优先搜索找出G图中s顶点的所有想通节点
    public BreadthFirstSearch(Graph G, int s) {
        //初始化marked数组,顶点数量有多少就有多少个数组
        this.marked = new boolean[G.V()];

        //初始化顶点s想通的顶点的数量
        this.count = 0;

        this.waitSearch = new Queue<Integer>();

        bfs(G, s);
    }

    //使用广度优先搜索找出G图中v顶点的所有相通顶点
    private void bfs(Graph G, int v) {
        //把当前顶点v标识为搜索
        marked[v] = true;

        //让顶点v进入队列，待搜索
        waitSearch.enqueue(v);

        //通过循环，如果队列不为空，则从队列中弹出一个待搜索顶点进行搜索
        while (!waitSearch.isEmpty()) {
            //弹出一个待搜索的顶点
            Integer wait = waitSearch.dequeue();

            //遍历wait顶点的邻接表
            for (Integer w : G.adj(wait)) {
                if (!marked[w]) {
                    bfs(G, w);
                }
            }
        }

        //让想通的顶点+1
        count++;
    }

    //判断w顶点与s顶点是否想通
    public boolean marked(int w) {
        return marked[w];
    }

    //获取与顶点s想通的所有顶点总数
    public int count() {
        return count;
    }
}


