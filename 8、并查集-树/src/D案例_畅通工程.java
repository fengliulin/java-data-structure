import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D案例_畅通工程 {
    public static void main(String[] args) throws Exception {
        //构建一个缓冲读取流
        BufferedReader br = new BufferedReader(new InputStreamReader(D案例_畅通工程.class.getClassLoader().getResourceAsStream("file_temp/traffice_project.txt")));

        //读取第一行数据20
        int totalNumber = Integer.parseInt(br.readLine());

        //构建一个并查集对象
        UF_Tree_Weighted uf = new UF_Tree_Weighted(totalNumber);

        //读取第二行数据7
        int roadNumber = Integer.parseInt(br.readLine());

        //循环读取7条道路
        for (int i = 1; i <= roadNumber; i++) {
            String line = br.readLine();
            String[] str = line.split(" ");
            int p = Integer.parseInt(str[0]);
            int q = Integer.parseInt(str[1]);

            //调用并查集对象的union方法让两个城市相通
            uf.union(p, q);
        }

        //获取当前并查集中分组的数量-1就可以得到还需要修建的道路的树目
        int roads = uf.count() - 1;

        System.out.println("还需要修建" + roads + "条道路，才能实现畅通工程");
    }
}

class Traffic_Project_Test {

}