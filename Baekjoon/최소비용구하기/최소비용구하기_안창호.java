import java.io.*;
import java.util.*;

public class 최소비용구하기_안창호 {
    static int N, M;
    static List<List<Node>> bus;
    static int[] dist; // 출발지에서 각 정점으로 가는 최단거리를 담은 배열
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        bus = new ArrayList<>();
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            bus.add(new ArrayList<>());
        }

        for (int i = 0 ; i < M; i++) {
            String[] str = br.readLine().split(" ");
            int s = Integer.parseInt(str[0]);
            int e = Integer.parseInt(str[1]);
            int c = Integer.parseInt(str[2]);

            bus.get(s).add(new Node(e, c));
        }

        String[] str = br.readLine().split(" ");
        int start = Integer.parseInt(str[0]);
        int end = Integer.parseInt(str[1]);

        System.out.println(dijkstra(start, end));
        br.close();
    }
    static int dijkstra (int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>(((o1, o2) -> o1.cost - o2.cost));
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curEnd = cur.end;

            if (!visited[curEnd]) {
                visited[curEnd] = true;

                for (Node node : bus.get(curEnd)) {
                    if (visited[node.end]) continue;

                    if (dist[node.end] > dist[curEnd] + node.cost) {
                        dist[node.end] = dist[curEnd] + node.cost;
                        pq.add(new Node(node.end, dist[node.end]));
                    }
                }
            }

        }
        return dist[end];
    }

    static class Node {
        int end;
        int cost;

        Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
}
