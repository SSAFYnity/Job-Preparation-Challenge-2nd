import java.util.*;

public class 석유시추_김수빈 {
    static class Solution {
        // public 해당 메서드 다른 클래스나 외부에서 접근 가능
        // void 반환값이 없음
        public int bfs(int[][] land, int sr, int sc, int[][] visited, Set<Integer> columns) {
            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};
            int[] area = {};
            // Queue<int[]> 인터페이스로 클래스들이 가져야 할 기능의 계약 정의하며 구체적 동작은 구현 X
            // LinkedList는 Queue의 인터페이스를 구현한 클래스 중 하나로 구체적인 기능(메서드) 제공
            // Queue를 인터페이스를 사용하고 LinkedList를 사용하여 객체 생성하는 이유는 유연성 때문
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{sr, sc});
            visited[sr][sc] = 1;
            columns.add(sc);
            int cnt = 1;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int cr = cur[0];
                int cc = cur[1];

                // 상하좌우 탐색
                for (int i = 0; i < 4; i++) {
                    int nr = cr + dr[i];
                    int nc = cc + dc[i];
                    // 범위 안이고 방문하지 않고 석유가 있다면
                    if (nr >= 0 && nr < land.length && nc >= 0 && nc < land[0].length && visited[nr][nc] == 0 && land[nr][nc] == 1) {
                        queue.add(new int[]{nr, nc});
                        visited[nr][nc] = 1;
                        cnt++;
                        columns.add(nc);
                    }
                }
            }

            return cnt;
        }

        public int solution(int[][] land) {
            int maxOil = 0;
            int[][] visited = new int[land.length][land[0].length];
            int[] oilInColumns = new int[land[0].length];
            for (int r = 0; r < land.length; r++) {
                for (int c = 0; c < land[0].length; c++) {
                    if (land[r][c] == 1 && visited[r][c] == 0) {
                        // 석유 덩어리 포함된 열 기록
                        Set<Integer> columns = new HashSet<>();
                        int oilSize = bfs(land, r, c, visited, columns);
                        for (int col : columns) {
                            oilInColumns[col] += oilSize;
                        }
                    }
                }
            }

            // 각 열에서 최대 석유량 계산
            for (int oil : oilInColumns) {
                maxOil = Math.max(maxOil, oil);
            }
            return maxOil;
        }
    }

    public static void main(String[] args) {
        int[][] land = {
            {0, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 1, 1, 0, 0},
            {1, 1, 0, 0, 0, 1, 1, 0},
            {1, 1, 1, 0, 0, 0, 0, 0},
            {1, 1, 1, 0, 0, 0, 1, 1}
        };

        Solution solution = new Solution();
        int result = solution.solution(land);

        System.out.println(result);
    }
}
