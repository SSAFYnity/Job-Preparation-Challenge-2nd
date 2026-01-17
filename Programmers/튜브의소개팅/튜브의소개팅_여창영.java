import java.io.*;
import java.util.*;

public class 튜브의소개팅_여창영 {
    static int N,M,S;
    static int answer[];
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};
    static int moveCnt[][];
    static long dist[][];
    static Deque<Pos> que;
    
    public static void dijkstra(int time_map[][]){
        dist = new long[M][N];
        for(int i = 0 ; i < M; ++i){
            for(int j = 0; j < N; ++j){
                dist[i][j] = Integer.MAX_VALUE;
                
            }            
        }
            
        dist[0][0] = 0;
        
        while(!que.isEmpty()){
            Pos cur = que.poll();
            int x = cur.x;
            int y = cur.y;
            
            if(x == M-1 && y == N-1){
                if(dist[x][y] <= S){
                    break;
                }
            }
            for(int idx = 0; idx < 4; idx++){
                int nextX = x + dx[idx];
                int nextY = y + dy[idx];
                
                if(isValid(nextX, nextY)){
                    // 범위 내부
                    if(time_map[nextX][nextY] == -1){
                        continue;
                    }
                    if(dist[nextX][nextY] > dist[x][y] + time_map[nextX][nextY]){
                        dist[nextX][nextY] = dist[x][y] + time_map[nextX][nextY];
                        que.add(new Pos(nextX, nextY));
                        moveCnt[nextX][nextY] = moveCnt[x][y] + 1;
                    }
                }
            }
        }
    }
    
    public static boolean isValid(int x,int y){
        return x >= 0 && x < M && y >= 0 && y < N;
    }
    
    public int[] solution(int m, int n, int s, int[][] time_map) {
        N = n;
        M = m;
        S = s;
        
        moveCnt = new int[m][n];
        que = new ArrayDeque<>();
        que.add(new Pos(0,0));
      
        dijkstra(time_map);
        
        answer = new int[2];
        answer[0] = moveCnt[m-1][n-1];
        answer[1] = (int)dist[m-1][n-1];
      
        return answer;
    }
    
    static class Pos{
        int x, y;
        public Pos(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
}
