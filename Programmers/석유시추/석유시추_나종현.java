package com.jonghyun.algorithm.challenge;

import java.util.ArrayDeque;
import java.util.Queue;

public class 석유시추_나종현 {
    static boolean[][] visited;
    static int[] point;
    static int[] dirY = {0, 1, 0, -1};
    static int[] dirX = {1, 0, -1, 0};
    
    public static void bfs(int[][] land, int y, int x, int m, int n){
        Queue<int[]> q = new ArrayDeque<>();
        boolean[] row = new boolean[m];
        q.offer(new int[]{y, x});
        visited[y][x] = true;
        row[x] = true;
        int size = 1;
        
        while(!q.isEmpty()){
            int nowY = q.peek()[0];
            int nowX = q.peek()[1];
            q.poll();
            
            for(int d=0; d<4; d++){
                int nextY = nowY + dirY[d];
                int nextX = nowX + dirX[d];
                
                if(nextY >= n || nextY < 0 || nextX >= m || nextX < 0 || land[nextY][nextX] == 0 || visited[nextY][nextX]){
                    continue;
                }
                q.offer(new int[]{nextY, nextX});
                visited[nextY][nextX] = true;
                row[nextX] = true;
                size++;
            }    
        }
        for(int i=0; i<m; i++){
            if(row[i]){
                point[i] += size;
            }
        }
    }
    public int solution(int[][] land) {
        int answer = 0;
        int n = land.length;
        int m = land[0].length;
        visited = new boolean[n][m];
        point = new int[m];
        
        for(int x=0; x<m; x++){
            for(int y=0; y<n; y++){
                if(land[y][x] == 1 && !visited[y][x]){
                    bfs(land, y, x, m, n);
                }
            }
        }
        
        for(int i=0; i<m; i++){
            answer = Math.max(answer, point[i]);
        }
        return answer;
    }
}
