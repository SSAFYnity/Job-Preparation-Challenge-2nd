import java.io.*;

public class 미로탈출명령어_여창영{
    class Solution {
        static int dx[] = {1, 0, 0, -1};
        static int dy[] = {0, -1, 1, 0};
        static String dir[] = {"d", "l", "r", "u"};
        static int srcX, srcY, destX, destY, sizeN, sizeM, depthK;
        static int maze[][];
        static String ans;
        
        public void dfs(int x, int y, int depth, String output){
            int idx;
            
            if(!ans.equals("")){
                return;
            }
            
            if(calDis(x, y, destX, destY) + depth > depthK){
                return;
            }
            
            if(depth == depthK){
                if(x == destX && y == destY && ans.equals("")){
                    ans = output;
                }
                return;
            }
            
            for(idx = 0; idx < 4; idx++){
                int nextX = x + dx[idx];
                int nextY = y + dy[idx];
                
                if(isValid(nextX, nextY)){
                    String nextOutput = output + dir[idx];
                    dfs(nextX, nextY, depth + 1, nextOutput);
                }
            }
        }
        
        public int calDis(int x1,int y1,int x2,int y2){
            return Math.abs(x2 - x1) + Math.abs(y2 - y1);
        }
        
        public boolean isValid(int x,int y){
            if(x >= 1 && x <= sizeN && y >= 1 && y <= sizeM){
                return true;
            }
            return false;
        }
        
        public String solution(int n, int m, int x, int y, int r, int c, int k) {
            String answer = "";
            
            if(Math.abs(calDis(x, y, r, c) - k) % 2 != 0){
                answer = "impossible";
                return answer;
            }
            
            maze = new int[n + 1][m + 1];
            
            srcX = x;
            srcY = y;
            destX = r;
            destY = c;
            sizeN = n;
            sizeM = m;
            depthK = k;
            
            ans = "";
            dfs(srcX, srcY, 0, "");
            
            if(ans.equals("")){
                ans = "impossible";
            }
            
            return ans;
        }
    }
}