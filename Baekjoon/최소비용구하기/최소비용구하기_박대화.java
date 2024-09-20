package 최소비용구하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int n;

    public static int dijkstra(int s, int e, ArrayList<ArrayList<int[]>> list) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((Comparator.comparingInt(o -> o[1])));
        boolean[] visited = new boolean[n + 1];
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[s] = 0;

        pq.offer(new int[]{s, 0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(visited[cur[0]]) continue;

            visited[cur[0]] = true;

            if(cur[0] == e) {
                return cur[1];
            }

            for(int[] d : list.get(cur[0])) {
                if(!visited[d[0]] && distance[d[0]] > distance[cur[0]] + d[1]) {
                    distance[d[0]] = distance[cur[0]] + d[1];
                    pq.offer(new int[]{d[0], distance[d[0]]});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<int[]>> list = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list.get(start).add(new int[]{end, weight});
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int result = dijkstra(s, e, list);

        System.out.println(result);
    }
}
