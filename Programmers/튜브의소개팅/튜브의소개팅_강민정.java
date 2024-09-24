import java.util.PriorityQueue;
import java.util.Arrays;

class 튜브의소개팅_강민정 {
    class Node implements Comparable<Node> {        // 좌표를 나타내는 클래스
        int x;      // 행
        int y;      // 열
        int distance;       // 이동 거리
        int talk;   // 대화 시간

        Node(int x, int y, int distance, int talk) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.talk = talk;
        }

        @Override
        public int compareTo(Node o) {
            return this.talk - o.talk;      // 대화 시간이 짧은 순으로
        }
    }

    static final int[] DX = new int[]{-1, 0, 0, 1};
    static final int[] DY = new int[]{0, -1, 1, 0};

    static int row;     // 세로 길이
    static int col;     // 가로 길이
    static int[][] time_map;
    static int s;       // 튜브가 참을 수 있는 대화 시간의 총합
    static int[] answer = new int[2];

    /*
        m: 2 <= 파티장의 세로 길이 <= 50
        n: 2 <= 파티장의 가로 길이 <= 50
        s: 1 <= 튜브가 참을 수 있는 대화 시간의 총합 <= 2^31-1
        time_map: 친구와의 대화에 필요한 시간 t가 담긴 2차원 배열
    */
    public int[] solution(int m, int n, int s, int[][] time_map) {
        row = m;
        col = n;
        this.time_map = time_map;
        this.s = s;
        Arrays.fill(answer, Integer.MAX_VALUE);

        bfs();

        return answer;      // {튜브가 이동해야하는 경로의 길이, 대화 시간의 총합}
    }

    public void bfs() {
        PriorityQueue<Node> pq = new PriorityQueue();
        int[][] distance = new int[row][col];       // 각 위치별로 최소 이동 거리를 기록할 배열
        for(int i=0; i<row; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);        // 최대값으로 배열 초기화
        }
        pq.add(new Node(0, 0, 0, 0));

        while(!pq.isEmpty()) {      // 원소가 있을 동안에
            Node cur = pq.poll();      // 대화 시간이 짧은 순으로 꺼내기

            if(cur.x == row - 1 && cur.y == col - 1) {      // 도착
                // 기존 이동 거리보다 짧거나 이동거리는 같고, 대화 시간은 짧을 때
                if(answer[0] > cur.distance || (cur.distance == answer[0] && cur.talk < answer[1])) {
                    answer[0] = cur.distance;       // 이동 거리 갱신
                    answer[1] = cur.talk;    // 대화 시간 갱신
                }
                continue;
            }

            for(int d=0; d<4; d++) {     // 사방 탐색
                int nx = DX[d] + cur.x;
                int ny = DY[d] + cur.y;
                long newTalk;
                int newDistance = cur.distance + 1;

                if(!isValid(nx, ny)) {      // 좌표가 유효하지 않다면
                    continue;
                }

                newTalk = (long)cur.talk + (long)time_map[nx][ny];      // 새로운 대화
                if(time_map[nx][ny] == -1 || newTalk > s || distance[nx][ny] <= newDistance) {     // 지나갈 수 없거나 대화 시간을 초과하거나 이동 거리가 멀다면
                    continue;
                }
                distance[nx][ny] = newDistance;        // 현재 위치까지 오는 데 걸리는 이동 거리 기록
                pq.add(new Node(nx, ny, newDistance, Math.toIntExact(newTalk)));
            }
        }

    }

    /*
        좌표가 유효하다면 true 반환
        nx: 세로
        ny: 가로
    */
    public boolean isValid(int nx, int ny) {
        return 0 <= nx && 0 <= ny && nx < row && ny < col;
    }
}