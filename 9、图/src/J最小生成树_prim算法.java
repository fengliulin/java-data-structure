import duilie.IndexMinPriorityQueue;
import duilie.Queue;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class J最小生成树_prim算法 {
    public static void main(String[] args) throws Exception{

        //准备一副加权无向图
        BufferedReader br = new BufferedReader(new InputStreamReader(J最小生成树_prim算法.class.getClassLoader().getResourceAsStream("file_temp/min_create_tree_test.txt")));
        int total = Integer.parseInt(br.readLine());
        EdgeWeightedGraph G = new EdgeWeightedGraph(total);

        int edgeNumbers = Integer.parseInt(br.readLine());
        for (int e = 1;e<=edgeNumbers;e++){
            String line = br.readLine();//4 5 0.35

            String[] strs = line.split(" ");

            int v = Integer.parseInt(strs[0]);
            int w = Integer.parseInt(strs[1]);

            double weight = Double.parseDouble(strs[2]);

            //构建加权无向边
            Edge edge = new Edge(v, w, weight);
            G.addEdge(edge);

        }

        //创建一个PrimMST对象，计算加权无向图中的最小生成树
        PrimMST primMST = new PrimMST(G);


        //获取最小生成树中的所有边
        Queue<Edge> edges = primMST.edges();

        //遍历打印所有的边
        for (Edge e : edges) {
            int v = e.either();
            int w = e.other(v);
            double weight = e.getWeight();
            System.out.println(v+"-"+w+" :: "+weight);

        }

    }
}

class PrimMST {

    /* 索引：顶点；值：当前顶点和最小生成树之间的最短边 */
    private Edge[] edgeTo;

    /* 索引：顶点；值：当前顶点和最小生成树之间的最短边的权重*/
    private double[] distTo;

    /* 索引：顶点；值：当前顶点已经在树中，值为true，否则false */
    private boolean[] marked;

    //存放树中顶点与非树中顶点之间的有效横切边
    private IndexMinPriorityQueue<Double> pq;

    //根据一副无权无向图，创建最小生成树计算对象
    public PrimMST(EdgeWeightedGraph G) {
        this.edgeTo = new Edge[G.V()];
        this.distTo = new double[G.V()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        marked = new boolean[G.V()];

        pq = new IndexMinPriorityQueue<Double>(G.V());

        /*
        默认让顶点0进入到树中，但是树中只有一个顶点0，
        因此，0顶点默认没有和其他的顶点相连，所以让distTo对应位置处的值存储0.0
        */
        distTo[0] = 0.0;
        pq.insert(0,0.0);

        //遍历索引最小优先队列，拿到最小和N切边对应的顶点，把该顶点假如到最小生成树中
        while (!pq.isEmpty()) {
            visit(G, pq.delMin());
        }
    }

    //将顶点v添加到最小生成树中，并且更新数据
    private void visit(EdgeWeightedGraph G, int v) {
        //把顶点v添加到最小生成树中
        marked[v] = true;

        //更新数据
        for (Edge e : G.adj(v)) {
            //获取e边的另外一个顶点（当前顶点是v）
            int w = e.other(v);

            //判断另外一个顶点是不是已经在树中，如果在树中，则不做任何处理，如果不在树中，则更新处理
            if (marked[w]) {
                continue;
            }

            //判断边e的权重是否小于从w顶点到树中已经存在的最小边的权重
            if (e.getWeight() < distTo[w]) {
                //更新数据
                edgeTo[w] = e;
                distTo[w] = e.getWeight();

                if (pq.contains(w)) {
                    pq.changeItem(w, e.getWeight());
                } else {
                    pq.insert(w, e.getWeight());
                }
            }
        }
    }

    //获取最小生成树的所有边
    public Queue<Edge> edges() {
        Queue<Edge> allEdges = new Queue<>();
        for (int i = 0; i < edgeTo.length; i++) {
            if (edgeTo[i] != null) {
                allEdges.enqueue(edgeTo[i]);
            }
        }

        return allEdges;
    }
}
