public class I加权无向图_加权边表示 {
}

class Edge implements Comparable<Edge> {

    private final int v; //顶点1
    private final int w; //顶点2
    private final double weight; //当前边的权重

    //通过顶点v和w，以及权重weight值构造一个边对象
    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * @return 获取边的权重值
     */
    public double getWeight() {
        return weight;
    }

    //获取边上的一个点
    public int either() {
        return v;
    }

    //获取边上除了顶点vertex外的另外一个顶点
    public int other(int vertex) {
        if (vertex == v) {
            return w;
        } else {
            return v;
        }
    }

    @Override
    public int compareTo(Edge weight) {
        int cmp = -999;
        //如果当前边的权重值大，则让cmp=1；
        if (this.getWeight() > weight.getWeight()) {
            cmp = 1;
        }

        //如果当前边的权重值小，则cpm=-1;
        if (this.getWeight() < weight.getWeight()) {
            cmp = -1;
        }

        //如果当前边的权重值和that边的权重一样大，则cpm=0
        if (this.getWeight() == weight.getWeight()) {
            cmp = 0;
        }

        return cmp;
    }
}
