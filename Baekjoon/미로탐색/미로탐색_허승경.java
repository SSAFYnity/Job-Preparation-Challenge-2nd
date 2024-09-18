import java.io.*;
import java.lang.reflect.Array;
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
    static int n, m;
    static int [][] map;
    static boolean [][] visited;
    static int [] dx = {-1, 0, 1, 0};
    static int [] dy = {0, 1, 0, -1};
    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(Character.toString(str.charAt(j)));
            }
        }
        bfs();

        System.out.println(map[n-1][m-1]);
    }

    static void bfs(){
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(0, 0));
        visited[0][0] = true;

        while(!que.isEmpty()){
            Point p = que.poll();

            for(int k = 0; k <4; k++){
                int tx = p.x + dx[k];
                int ty = p.y + dy[k];

                if(tx >= 0 && tx < n && ty >= 0 && ty < m){
                    if(!visited[tx][ty] && map[tx][ty] == 1){
                        map[tx][ty] = map[p.x][p.y]+1;
                        if(tx == n-1 && ty == m-1) return;
                        visited[tx][ty] = true;
                        que.add(new Point(tx, ty));
                    }
                }
            }
        }
    }
}
