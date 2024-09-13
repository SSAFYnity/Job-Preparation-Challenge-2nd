import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] map1 = new int[n][n];
        int[][] map2 = new int[n][n];
        boolean[][] visited = new boolean[n][n];

        for(int i=0; i<n; i++) {
            String str = sc.next();
            for(int j=0; j<n; j++) {
                switch(str.charAt(j)) {
                    case 'R':
                        map1[i][j] = 1;
                        map2[i][j] = 1;
                        break;
                    case 'G':
                        map1[i][j] = 2;
                        map2[i][j] = 1;
                        break;
                    case 'B':
                        map1[i][j] = 3;
                        map2[i][j] = 2;
                        break;
                }
            }
        }

        Queue<Rgb> que = new LinkedList<>();

        int[] ans = {0, 0};
        // 적록색약이 아닌 사람이 볼 경우
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(visited[i][j]) continue;
                que.add(new Rgb(map1[i][j], i, j));
                visited[i][j] = true;
                ans[0]++;
                while(que.size()>0) {
                    Rgb rgb = que.poll();
                    for(int k=0; k<4; k++) {
                        int r = rgb.r + dir[k][0];
                        int c = rgb.c + dir[k][1];
                        if(r < 0 || c < 0 || r >= n || c >= n) continue;
                        if(map1[r][c] == rgb.color && !visited[r][c]) {
                            que.add(new Rgb(rgb.color, r, c));
                            visited[r][c] = true;
                        }
                    }
                }
            }
        }
        // 적록색약인 사람이 볼 경우
        for(int i=0; i<n; i++) {
            Arrays.fill(visited[i], false);
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(visited[i][j]) continue;
                que.add(new Rgb(map2[i][j], i, j));
                visited[i][j] = true;
                ans[1]++;
                while(que.size()>0) {
                    Rgb rgb = que.poll();
                    for(int k=0; k<4; k++) {
                        int r = rgb.r + dir[k][0];
                        int c = rgb.c + dir[k][1];
                        if(r < 0 || c < 0 || r >= n || c >= n) continue;
                        if(map2[r][c] == rgb.color && !visited[r][c]) {
                            que.add(new Rgb(rgb.color, r, c));
                            visited[r][c] = true;
                        }
                    }
                }
            }
        }

        System.out.println(ans[0] + " " + ans[1]);

    }
    static class Rgb {
        int color;
        int r, c;

        public Rgb(int color, int r, int c) {
            this.color = color;
            this.r = r;
            this.c = c;
        }

    }
}
