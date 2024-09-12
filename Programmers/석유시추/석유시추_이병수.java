import java.util.*;

class Solution {
    static int n;
    static int m;
    static boolean[][] visited;
    static int[] sizeLand;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    
    public int solution(int[][] land) {
        int answer = 0;
        n = land.length;
        m = land[0].length;
        sizeLand = new int[n * m + 1];
        visited = new boolean[n][m];
        int idx = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (land[i][j] == 1 && !visited[i][j]){
                    idx++;
                    bfs(land, i, j, idx);
                }
            }
        }
        
        for (int i = 0; i < m; i++){
            int sum = 0;
            boolean[] checked = new boolean[idx + 1];
            for (int j = 0; j < n; j++){
                if (land[j][i] != 0 && !checked[land[j][i]]){
                    sum += sizeLand[land[j][i]];
                    checked[land[j][i]] = true;
                }
            }
            answer = Math.max(answer, sum);
        }
        return answer;
    }
    
    static void bfs(int[][] land, int r, int c, int idx){
        int cnt = 0;
        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[] {r, c});
        visited[r][c] = true;
        while (!queue.isEmpty()){
            Integer[] pos = queue.poll();
            land[pos[0]][pos[1]] = idx;
            cnt++;
            int nr, nc;
            for (int i = 0; i < 4; i++){
                nr = pos[0] + dr[i];
                nc = pos[1] + dc[i];
                if (0 <= nr && nr < n && 0 <= nc && nc < m && 
                   !visited[nr][nc] && land[nr][nc] == 1){
                    queue.add(new Integer[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
        sizeLand[idx] = cnt;
    }
}
