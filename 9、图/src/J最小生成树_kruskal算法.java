import duilie.MinPriorityQueue;
import duilie.Queue;
import tree.UF_Tree_Weighted;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class J最小生成树_kruskal算法 {
    public static void main(String[] args) throws Exception {
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

        //创建一个KruskalMST对象，计算加权无向图中的最小生成树
        KruskalMST primMST = new KruskalMST(G);


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


class KruskalMST {

    //保存最小生成树的所有边
    private Queue<Edge> mst;

    //索引代表顶点，使用uf.connect(v,w)可以判断顶点v和顶点w是否在同一颗树中，使用uf.union(v,w)可以把顶点v所在的树和顶点w所在的树合并
    private UF_Tree_Weighted uf;

    //存储图中所有的边，使用最小优先队列，对边按照权重进行排序
    private MinPriorityQueue<Edge> pq;

    //根据一副加权无向图，创建最小生成树计算对象
    public KruskalMST(EdgeWeightedGraph G) {
        mst = new Queue<Edge>();
        uf = new UF_Tree_Weighted(G.V());
        pq = new MinPriorityQueue<>(G.E() + 1);

        //把图中所有的边存储到pq中
        for (Edge e : G.edges()) {
            pq.insert(e);
        }

        //遍历pq队列，拿到最小权重的边，进行处理
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            //找到权重最小的边
            Edge e = pq.delMin();
            //找到该边的两个顶点
            int v = e.either();
            int w = e.other(v);

            //判断这两个顶点是否已经在同一颗树中,如果在一颗树中，不对该边做处理，如果不再一颗树中，则让两个顶点属于的两颗树合并成一颗树
            if (uf.connected(v, w)) {
                continue;
            }

            uf.union(v , w);

            //让边e进入到mst队列中
            mst.enqueue(e);
        }

    }

    //获取最小生成树的所有边
    public Queue<Edge> edges() {
        return mst;
    }
}
