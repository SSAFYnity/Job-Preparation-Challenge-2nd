import java.util.*;

class Solution {
    
    int n, m, total;
    int[][] land, oils, groups;
    boolean[][] visited;
    
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    
    public int solution(int[][] land) {
        
        n = land.length;
        m = land[0].length;
        
        this.land = land;
        oils = new int[n][m];
        groups = new int[n][m];
        visited = new boolean[n][m];
        
        fill();
        
        int max = 0;
        for (int i = 0; i < m; i++) {
            boolean[] drilled = new boolean[total + 1];
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (drilled[groups[j][i]]) {
                    continue;
                }
                sum += oils[j][i];
                drilled[groups[j][i]] = true;
            }
            max = Math.max(max, sum);
        }
        
        return max;
    }
    
    private void fill() {
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] || land[i][j] == 0) {
                    continue;
                }
                bfs(i, j, ++total);
            }
        }
    }
    
    private void bfs(int x, int y, int idx) {
        
        List<int[]> positions = new ArrayList<>();
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{ x, y });
        visited[x][y] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll(); 
            positions.add(cur);
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (isOutOfBounds(nx, ny) ||
                    visited[nx][ny] || 
                    land[nx][ny] == 0) {
                    continue;
                }
                q.offer(new int[]{ nx, ny });
                visited[nx][ny] = true;
            }
        }
        int totalOils = positions.size();
        for (int[] pos : positions) {
            oils[pos[0]][pos[1]] = totalOils;
            groups[pos[0]][pos[1]] = idx;
        }
    }
    
    private boolean isOutOfBounds(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m) {
            return true;
        }
        return false;
    }
}