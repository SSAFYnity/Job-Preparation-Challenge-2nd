import java.util.*;
import java.io.*;


public class 최소비용구하기_김수빈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());  // 도시 수
        int M = Integer.parseInt(br.readLine());  // 버스 수

        ArrayList<ArrayList<int[]>> busGraph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            busGraph.add(new ArrayList<>());
        }

        // 출발 도시 번호, 도착 도시 번호, 버스 비용
        for (int i = 0; i < M; i++) {
            String input = br.readLine();
            // stream은 원소를 한번에 하나씩 처리
            int[] busInfo = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
            busGraph.get(busInfo[0]).add(new int[]{busInfo[1], busInfo[2]});
        }

        String input = br.readLine();
        String[] check = input.split(" ");
        int start = Integer.parseInt(check[0]); // 최초 출발
        int end = Integer.parseInt(check[1]);  // 최종 도착

        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        dijkstra(N, start, distance, busGraph);
        int ans = distance[end];
        System.out.println(ans);

    }

    public static void dijkstra(int N, int start, int[] distance, ArrayList<ArrayList<int[]>> busGraph) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int cityNum = current[0];
            int currentDist = current[1];
            // 기록 거리보다 크면 무시
            if (currentDist > distance[cityNum]) {
                continue;
            }
            // 연결된 도시 경로 탐색
            for(int[] edge : busGraph.get(cityNum)) {
                int nextCity = edge[0];
                int nextDist = edge[1];
                // 더 짧은 경로 발견 시 업데이트
                if (distance[cityNum] + nextDist < distance[nextCity]) {
                    distance[nextCity] = distance[cityNum] + nextDist;
                    pq.add(new int[]{nextCity, distance[nextCity]});
                }

            }
        }
    }
}