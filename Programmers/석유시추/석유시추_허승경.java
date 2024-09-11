import java.util.*;

public class Solution {
    static class Point{
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static boolean [][] visited;
    static int [] memoization;
    public static int solution(int[][] land) {
        int answer = 0;
        int n = land.length;
        int m = land[0].length;
        visited = new boolean[n][m];
        memoization = new int[m];

        for(int j = 0; j < m; j++){                 // 1. 영역 구하기
            for(int i= 0; i < n; i++){
                if(visited[i][j] || land[i][j] == 0) continue;
                bfs(land, i, j);
            }
        }

        for(int i = 0; i < m; i++){                // 2. 저장 된 값 중 최대 구하기
            answer = Math.max(answer, memoization[i]);
        }

        return answer;
    }
    public static void bfs(int [][] land, int i, int j){
        int n = land.length;
        int m = land[0].length;
        Queue<Point> que = new LinkedList<>();
        Set<Integer> set = new LinkedHashSet<>();         // 현재의 석유 덩어리가 존재하는 열
        que.add(new Point(i, j));
        visited[i][j] = true;
        int count = 1;
        int [] dx = {-1, 0, 1, 0};
        int [] dy = {0, 1, 0, -1};

        while(!que.isEmpty()){
            Point p = que.poll();
            set.add(p.y);           // 현재의 석유 덩어리가 존재하는 열 추가

            for (int k = 0; k < 4; k++) {
                int tx = p.x + dx[k];
                int ty = p.y + dy[k];

                if (tx < 0 || tx >= n || ty < 0 || ty >= m) continue;
                if (visited[tx][ty] || land[tx][ty] == 0) continue;
                que.add(new Point(tx, ty));
                visited[tx][ty] = true;
                count++;
            }
        }
        for(int current : set){
            memoization[current] += count;          // 해당 열에 계속 더해나가기
        }
    }

}
