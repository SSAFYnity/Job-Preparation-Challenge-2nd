import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] adj = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(adj[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[s][e] = Math.min(adj[s][e], v);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int sp = Integer.parseInt(st.nextToken());
        int ep = Integer.parseInt(st.nextToken());

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[sp] = 0;
        Queue<Integer> pq = new PriorityQueue<>();
        pq.add(sp);

        while (!pq.isEmpty()) {
            Integer cur = pq.poll();
            for (int i = 1; i <= N; i++) {
                if (adj[cur][i] == Integer.MAX_VALUE)
                    continue;

                if (dist[i] > dist[cur] + adj[cur][i]) {
                    dist[i] = dist[cur] + adj[cur][i];
                    pq.add(i);
                }
            }
        }
        System.out.println(dist[ep] == Integer.MAX_VALUE ? 0 : dist[ep]);
    }
}