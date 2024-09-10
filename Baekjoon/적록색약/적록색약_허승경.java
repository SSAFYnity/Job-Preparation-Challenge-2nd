import java.io.*;
import java.util.*;

public class Main {
    static class Point{
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int [][] map = new int[n][n];
        int [][] redMap = new int[n][n];

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < n; j++){
                Character ch = str.charAt(j);
                if(ch.equals('B')){
                    map[i][j] = 0;
                    redMap[i][j] = 0;
                }else if(ch.equals('R')){
                    map[i][j] = 1;
                    redMap[i][j] = 1;
                }else if(ch.equals('G')){
                    map[i][j] = 2;
                    redMap[i][j] = 1;
                }
            }
        }

        int res = bfs(n, map);
        int redRes = bfs(n, redMap);

        System.out.println(res+ " " + redRes);
    }

    static int bfs(int n, int [][] graph){
        Queue<Point> que = new LinkedList<>();
        boolean [][] visited = new boolean[n][n];
        int count = 0;      // 영역 수

        int [] dx = {-1, 0, 1, 0};
        int [] dy = {0, 1, 0, -1};

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(visited[i][j]) continue;
                que.add(new Point(i, j));
                visited[i][j] = true;
                while(!que.isEmpty()){
                    Point p = que.poll();

                    for(int k = 0; k < 4; k++){
                        int tx = p.x + dx[k];
                        int ty = p.y + dy[k];

                        if(tx < 0 || tx >= n || ty < 0 || ty >= n) continue;
                        if(graph[p.x][p.y] != graph[tx][ty]) continue;
                        if(visited[tx][ty]) continue;
                        que.add(new Point(tx, ty));
                        visited[tx][ty] = true;
                    }
                }
                count++;

            }
        }
        return count;
    }
}
