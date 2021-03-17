import zhan.Stack;

public class H拓扑排序 {
    public static void main(String[] args) {
        Digraph digraph = new Digraph(6);
        digraph.addEdge(0, 2);
        digraph.addEdge(0, 3);
        digraph.addEdge(2, 4);
        digraph.addEdge(3, 4);
        digraph.addEdge(4, 5);
        digraph.addEdge(1, 3);

        TopoLogical topoLogical = new TopoLogical(digraph);

        Stack<Integer> order = topoLogical.order();
        StringBuffer sb = new StringBuffer();
        for (Integer w : order) {
            sb.append(w + "->");
        }
        String str = sb.toString();
        int index = str.lastIndexOf("->");
        str.substring(0, index);
        System.out.println(str);
    }
}

class TopoLogical {

    private Stack order; //顶点的拓扑排序

    //构造拓扑排序对象
    public TopoLogical(Digraph G) {
        //创建一个检测有向环的对象
        DirectedCycle cycle = new DirectedCycle(G);

        //判断G图中有没有环，如果没有环，则进行顶点排序：创建一个顶点排序对象
        if (!cycle.hasCycle()) {
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G);
            order = depthFirstOrder.reversePost();
        }

    }


    //判断图G是否有环
    public boolean isCycle() {
        return order == null;
    }

    //获取拓扑排序的所有顶点
    public Stack order() {
        return order;
    }

}
