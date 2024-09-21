import java.util.*;

class Solution {
    static int[] dx = {1, 0, 0, -1}; // 하 좌 우 상(d l r u)
    static int[] dy = {0, -1, 1, 0};
    static String answer = "impossible";
    static int nn, nm, nr, nc;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        nn = n;
        nm = m;
        nr = r;
        nc = c;

        dfs(x, y, k, "");

        String res = "";
        for(int i = 0; i < answer.length(); i++) {
            Character tmp = answer.charAt(i);
            if (tmp == '0') res += "d";
            if (tmp == '1') res += "l";
            if (tmp == '2') res += "r";
            if (tmp == '3') res += "u";
        }
        return res.equals("") ? "impossible" : res;
    }

    public void dfs(int x, int y, int cnt, String res) {
        if (cnt < 0 || !answer.equals("impossible")) return;

        int distance = Math.abs(nr - x) + Math.abs(nc - y);

        if (cnt < distance || (cnt - distance)%2 == 1) return;


        if (cnt == 0) {
            if ((x == nr) && (y == nc)) {
                answer = res;
            }
            return;
        }

        for(int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx <= 0 || nx > nn || ny <= 0 || ny > nm) continue;

            String tmp = Integer.toString(d);
            if (answer.equals("impossible")) {
                dfs(nx, ny, cnt - 1, res + tmp);
            }
        }

    }
}