import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int [][] map = new int [rows][columns];
        int k = 1;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                map[i][j] = k++;
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < queries.length; i++){
            list.add(rotation(queries[i][0]-1,queries[i][1]-1,queries[i][2]-1,queries[i][3]-1, map));
        }

        return list.stream().mapToInt(x->x).toArray();

    }

    public int rotation(int x1, int y1, int x2, int y2, int [][] map){

        int [][] d = new int [][]{{1,0},{0,1},{-1,0},{0,-1}};
        int temp = map[x1][y1];
        int ans  = temp;
        map[x1][y1] = map[x1+1][y1];
        int nowX = x1+1; int nowY = y1;
        int i = 0;
        while(nowX != x1 || nowY != y1) {
            ans = Math.min(ans, map[nowX][nowY]);
            if(nowX+d[i][0] > x2 || nowY+d[i][1] > y2 || nowX+d[i][0] < x1 || nowY+d[i][1] < y1){
                i = (i+1)%4;
            }
            int nx = nowX + d[i][0];
            int ny = nowY + d[i][1];
            map[nowX][nowY] = map[nx][ny];
            nowX = nx; nowY = ny;
        }
        map[nowX][nowY+1] = temp;

        return ans;
    }
}