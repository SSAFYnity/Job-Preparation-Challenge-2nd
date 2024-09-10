import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int dis;
        int num;

        public Node(int dis, int num){
            this.dis = dis;
            this.num = num;
        }

        @Override
        public int compareTo(Node o) {
            return this.dis - o.dis;
        }
    }

    static List<List<Node>> list = new ArrayList<>();
    static int n;
    static int [] result;
    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        for(int i = 0; i < n+1; i++){
            list.add(new ArrayList<>());
        }
        result = new int[n+1];
        Arrays.fill(result, Integer.MAX_VALUE);

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int sn = Integer.parseInt(st.nextToken());
            int en = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            list.get(sn).add(new Node(dis, en));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);

        System.out.println(result[end]);
    }

    static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, start));
        boolean [] visited = new boolean[n+1];
        result[start] = 0;

        while(!pq.isEmpty()){
            Node node = pq.poll();

            if(visited[node.num]) continue;

            visited[node.num] = true;

            for(int i = 0; i < list.get(node.num).size(); i++){
                int dis = list.get(node.num).get(i).dis;
                int num = list.get(node.num).get(i).num;

                if(visited[num]) continue;
                if(result[num] > node.dis + dis){
                    result[num] = node.dis + dis;
                    pq.add(new Node(node.dis + dis, num));
                }
            }
        }
    }
}
