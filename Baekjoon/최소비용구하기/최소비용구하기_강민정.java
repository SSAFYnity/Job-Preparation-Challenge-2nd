import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Arrays;

class 최소비용구하기_강민정 {
    static List<List<Node>> weightLst;
    static int n;       // 도시 갯수

    static class Node implements Comparable<Node> {
        int num;
        int weight;

        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;      // 비용으로 오름차순 정렬
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());        // 1 <= 도시 개수 <= 1,000
        int m = Integer.parseInt(br.readLine());        // 1 <= 버스 개수 <= 100,000
        int startNum;  // 출발점 도시 번호
        int endNum;    // 도착점 도시 번호
        weightLst = new ArrayList();

        // 리스트 초기화
        for(int i=0; i<n+1; i++) {
            weightLst.add(new ArrayList<Node>());
        }

        // 버스 정보 입력 받기
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());       // 출발 도시 번호
            int end = Integer.parseInt(st.nextToken());         // 도착 도시 번호
            int weight = Integer.parseInt(st.nextToken());       // 버스 비용

            weightLst.get(start).add(new Node(end, weight));    // 출발 도시와 도착 도시를 단방향 연결
        }

        // 구하고자 하는 구간 출발점의 도시번호와 도착점의 도시번호
        st = new StringTokenizer(br.readLine());
        startNum = Integer.parseInt(st.nextToken());
        endNum = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(startNum, endNum));     // 출발 도시에서 도착 도시까지 가는데 드는 최소 비용
    }

    public static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue();
        boolean[] visit = new boolean[n + 1];
        int[] dp = new int[n + 1];      // 최소 비용 배열
        Arrays.fill(dp, Integer.MAX_VALUE);     // 가중치 배열 초기화
        dp[start] = 0;      // 시작점은 가충치가 0
        pq.add(new Node(start, 0));     // 시작점을 우선순위 큐에 추가

        while(!pq.isEmpty()) {
            Node cur = pq.poll();      // 가장 비용이 낮은 도시부터 꺼내기

            if(visit[cur.num]) {      // 방문한 도시라면(시간초과 방지)
                continue;
            }

            visit[cur.num] = true;      // 우선순위 큐에 같은 도시 번호가 여러 개라도 가장 비용이 낮은 경로로 방문하게 됨

            for(Node nextNode : weightLst.get(cur.num)) {       // 현재 도시와 연결된 도시들 탐색
                if(visit[nextNode.num]) {       // 방문한 도시라면
                    continue;
                }

                if(dp[nextNode.num] > nextNode.weight + dp[cur.num]) {  // cur과 연결된 도시의 가중치 > (cur과 연결된 도시의 가중치 + 현재 도시의 가중치)
                    dp[nextNode.num] = nextNode.weight + dp[cur.num];
                    pq.add(new Node(nextNode.num, dp[nextNode.num]));
                }
            }
        }
        return dp[end];     // start 도시에서 end 도시로 가는 최소 비용 반환
    }
}