import java.util.*;

class Solution {
    static int[] di = {1, 0, 0, -1};
    static int[] dj = {0, -1, 1, 0};
    static String answer = "";
    static boolean isEnd = false;
    
    // d, l, r, u
    
    public void dfs(int n, int m, int x, int y, int r, int c, int k, int cnt, String path) {
        if(x == r && y == c && cnt == k) {
            answer = path;
            isEnd = true;
            return;
        }
        if ((k - cnt) < (Math.abs(x - r) + Math.abs(y - c))) {
            return;
        }
        
        for(int d = 0; d < 4; d++) {
            int nx = x + di[d];
            int ny = y + dj[d];

            if(nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                if(d == 0) { // 하
                    dfs(n, m, nx, ny, r, c, k, cnt + 1, path + "d");
                    if (isEnd) return;
                }
                else if(d == 1) { // 좌
                    dfs(n, m, nx, ny, r, c, k, cnt + 1, path + "l");
                    if (isEnd) return;
                }
                else if(d == 2) { // 우
                    dfs(n, m, nx, ny, r, c, k, cnt + 1, path + "r");
                    if (isEnd) return;
                }
                else { // 상
                    dfs(n, m, nx, ny, r, c, k, cnt + 1, path + "u");
                    if (isEnd) return;
                }
            }
        }
        
    }
    
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {  
        int dist = Math.abs(x - r) + Math.abs(y - c);
        
        if (k % 2 == dist % 2 && dist <= k) {
            dfs(n, m, x, y, r, c, k, 0, "");
        }
        else {
            answer = "impossible";
        }
        
        return answer;
    }
}