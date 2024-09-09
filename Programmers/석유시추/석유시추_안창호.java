import java.util.;

class Solution {
    static boolean[][] visited;
    static int[][] dir = {{1,0},{-1,0},{0,-1},{0,1}};
    static int[] oil;  // 각 열에 포함된 오일의 양을 담은 배열
    static int r, c;
    public int solution(int[][] land) {
        r = land.length;
        c = land[0].length;
        visited = new boolean[r][c];
        oil = new int[c];

        for (int i = 0; i  r; i++) {
            for (int j = 0; j  c; j++) {
                if (!visited[i][j] && land[i][j] == 1) {
                    visited[i][j] = true;
                    bfs(land, i , j);
                }
            }
        }

        Arrays.sort(oil);
        return oil[oil.length - 1];
    }
    void bfs(int[][] land, int x, int y){
        SetInteger set = new HashSet();  // 석유 덩어리가 포함된 열 모음
        Queueint[] q = new LinkedList();
        q.add(new int[] {x, y});
        int cnt = 0;  // 석유 덩어리의 크기
        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            cnt++;
            set.add(tmp[1]);  // 열 저장

            for (int d = 0; d  4; d++) {
                int nx = tmp[0] + dir[d][0];
                int ny = tmp[1] + dir[d][1];

                if (nx  0  ny  0  nx = r  ny = c
                visited[nx][ny]  land[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                q.add(new int[] {nx, ny});
            }
        }
        for (int i  set) {
            oil[i] += cnt;
        }
    }
}
