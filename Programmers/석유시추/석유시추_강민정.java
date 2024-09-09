import java.util.Deque;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

class 석유시추_강민정 {
    class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static boolean[][] visit;
    static int row;
    static int col;

    public int solution(int[][] land) {
        int answer = 0;     // 최대 석유량
        Map<Integer, Integer> map = new HashMap();      // 그룹 번호 : 석유 덩어리 크기
        row = land.length;      // 땅의 가로 너비
        col = land[0].length;   // 땅의 세로 너비
        visit = new boolean[row][col];
        int group = 1;

        for(int i=0; i<row; i++) {      // 행
            for(int j=0; j<col; j++) {      // 열
                if(visit[i][j] || land[i][j] == 0) {    // 방문했거나 석유가 없다면
                    continue;
                }

                int totalCnt = bfs(i, j, land, group++);
                map.put(group - 1, totalCnt);       // 그룹 번호 : 석유 덩어리 크기
            }
        }

        for(int i=0; i<col; i++) {          // 열
            Set<Integer> set = new HashSet();   // 시추관과 닿은 석유 그룹 번호 집합
            int tmp = 0;        // 획득한 덩어리 수
            for(int j=0; j<row; j++) {      // 행
                if(land[j][i] == 0) {       // 석유가 아니면
                    continue;
                } else if(!set.contains(land[j][i])) {   // 처음 닿은 석유 그룹 번호라면
                    set.add(land[j][i]);        // 석유가 속한 그룹을 방문 처리
                    tmp += map.get(land[j][i]);     // 석유 덩어리 크기를 더한다
                }

            }
            answer = Math.max(answer, tmp);
        }
        return answer;
    }

    /*
        석유 덩어리의 크기를 반환하는 메서드
        x: 시작 행
        y: 시작 열
        land: 입력으로 받은 땅
        group: 같은 석유 덩어리를 나타낼 번호
    */
    public int bfs(int x, int y, int[][] land, int group) {
        int totalCnt = 0;       // 석유 덩어리 크기
        Deque<Node> que = new LinkedList();
        que.add(new Node(x, y));
        visit[x][y] = true;

        while(!que.isEmpty()) {
            Node node = que.poll();
            land[node.x][node.y] = group;  // 인접한 석유는 같은 group 번호를 가짐
            totalCnt++;     // 석유 덩어리의 크기 증가

            for(int i=0; i<4; i++) {        // 사방탐색
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(!isValid(nx, ny)) {      // 배열의 범위를 벗어나면
                    continue;
                }

                if(visit[nx][ny] || land[nx][ny] == 0) {      // 방문했거나 석유가 없으면
                    continue;
                }

                visit[nx][ny] = true;
                que.add(new Node(nx, ny));
            }
        }
        return totalCnt;
    }

    /*
        인덱스 범위가 유효하면 true 반환
    */
    public boolean isValid(int nx, int ny) {
        return 0 <= nx && nx < row && 0 <= ny && ny < col;
    }
}