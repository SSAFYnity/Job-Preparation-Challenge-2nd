import java.util.*;

class Solution {
    static int n, m;
    
    static class Pair{
        int y;
        int x;
        
        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    
    public int solution(int[][] land) {
        int answer = 0;
        n = land.length;
        m = land[0].length;
        int[] cnt = new int[m];
        
        for (int i = 0; i<n; i++) {
            for (int j = 0; j<m; j++) {
                if (land[i][j]==1) {
                    LinkedList<Pair> q = new LinkedList<>();
                    Set<Integer> array = new HashSet<>();  
                    q.add(new Pair(i, j));
                    land[i][j] = 0;
                    int count = 1;
                    array.add(j);
                    
                    int[] dy = {0, 1, 0, -1};
                    int[] dx = {1, 0, -1, 0};
                    
                    while (!q.isEmpty()) {
                        Pair curr = q.poll();
                        int y = curr.y, x = curr.x;
                        
                        for (int k = 0; k<4; k++) {
                            int ny = y+dy[k];
                            int nx = x+dx[k];
                            if (ny>=0 && ny<n && nx>=0 && nx<m && land[ny][nx]==1) {
                                land[ny][nx] = 0;
                                count++;
                                array.add(nx);
                                q.add(new Pair(ny, nx));
                            }
                        }
                    }
                    for (int arr: array) {
                        cnt[arr] += count;
                    }
                    
                }
            }
        }
        for (int i = 0; i<m; i++) {
            answer = Math.max(answer, cnt[i]);
        }
        return answer;
    }
}