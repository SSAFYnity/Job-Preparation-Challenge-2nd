import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static List<List<int[]>> routes = new ArrayList<>();
    private static int[] costs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        costs = new int[N+1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        for(int i=0; i<N+1; i++) routes.add(new ArrayList<>());
        for(int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            routes.get(from).add(new int[]{to, cost});
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        Dijkstra(from, to);

        System.out.println(costs[to]);
    }

    private static void Dijkstra(int from, int to) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        q.offer(new int[]{from, 0});
        costs[from] = 0;
        while(!q.isEmpty()) {
            int[] tmp = q.poll();
            if(tmp[0] == to) break;

            for(int i=0; i<routes.get(tmp[0]).size(); i++) {
                int[] route = routes.get(tmp[0]).get(i);
                int next = route[0];
                int cost = route[1];
                if(costs[next] > tmp[1] + cost) {
                    costs[next] = tmp[1] + cost;
                    q.offer(new int[]{next, costs[next]});
                }
            }
        }
    }
}
