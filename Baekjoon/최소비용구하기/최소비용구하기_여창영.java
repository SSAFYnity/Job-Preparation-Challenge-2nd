import java.io.*;
import java.util.*;

public class 최소비용구하기_여창영 {
    static int N, M,src,dest;
    static int distance[];
    static boolean isVisited[];
    static List<City> connection[];
    static PriorityQueue<City> pq;

    public static void Dijkstra() {
        pq.add(new City(src, 0));

        while (!pq.isEmpty()) {
            City cur = pq.poll();

            if (isVisited[cur.next]) {
                continue;
            }
            isVisited[cur.next] = true;

            for (City conn : connection[cur.next]) {
                if (!isVisited[conn.next] && distance[conn.next] > conn.weight + cur.weight) {
                    distance[conn.next] = conn.weight + cur.weight;
                    pq.add(new City(conn.next, distance[conn.next]));
                }
            }
        }
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        distance = new int[N + 1];
        isVisited = new boolean[N + 1];
        connection = new List[N + 1];
        pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            connection[i] = new ArrayList<>();
            distance[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int U = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            connection[V].add(new City(U, W));
        }

        st = new StringTokenizer(br.readLine());
        src = Integer.parseInt(st.nextToken());
        dest = Integer.parseInt(st.nextToken());
    }
    
    public static void main(String args[]) throws IOException {
        pre();
        Dijkstra();
        System.out.println(distance[dest]);
    }
    
    static class City implements Comparable<City> {
        int next, weight;

        public City() {
        }

        public City(int next,int weight){
            this.next=next;
            this.weight=weight;
        }

        @Override
        public int compareTo(City o) {
            return this.weight - o.weight;
        }
    }
}