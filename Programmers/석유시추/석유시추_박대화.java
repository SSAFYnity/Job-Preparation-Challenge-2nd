import java.util.*;

class Solution {
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};
    
    public int bfs(int i, int j, boolean[][] visited, int cnt, int[][] land, ArrayList<ArrayList<Integer>> line) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i, j});
        visited[i][j] = true;
        land[i][j] = cnt;
        
        int oil = 1;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            if(!line.get(cur[1]).contains(cnt)) {
                line.get(cur[1]).add(cnt);
            }
            
            for(int d = 0; d < 4; d++) {
                int ni = cur[0] + di[d];
                int nj = cur[1] + dj[d];
                
                if(ni >= 0 && ni < land.length && nj >= 0 && nj < land[0].length && !visited[ni][nj] && land[ni][nj] == 1) {
                    q.offer(new int[]{ni, nj});
                    visited[ni][nj] = true;
                    land[ni][nj] = cnt;
                    oil++;
                }
            }
        }
        
        return oil;
    }
    
    public int solution(int[][] land) {
        
        int row = land.length;
        int col = land[0].length;
        
        boolean[][] visited = new boolean[row][col];
        int cnt = 1;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<ArrayList<Integer>> line = new ArrayList<>();
        for(int i = 0; i < col; i++) {
            line.add(new ArrayList<>());
        }
        
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(land[i][j] == 1 && !visited[i][j]){
                    int oil = bfs(i, j, visited, cnt, land, line);
                    map.put(cnt, oil);
                    cnt++;
                }
            }
        }
        
        int max = 0;
        for(int i = 0; i < col; i++) {
            int tmp = 0;
            for(int j = 0; j < line.get(i).size(); j++) {
                tmp += map.get(line.get(i).get(j));
            }
            max = Math.max(max, tmp);
        }
        
        return max;
    }
}