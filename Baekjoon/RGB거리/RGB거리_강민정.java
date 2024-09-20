import java.io.*;
import java.util.StringTokenizer;

class Main {
    static int[][] arr;
    static int[][] dp;
    static int n;       // 2 <= 집의 수 <= 1,000

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = Integer.MAX_VALUE;
        n = Integer.parseInt(br.readLine());
        arr = new int[n][3];
        dp = new int[n][3];

        // 집별로 RGB 비용 입력받기
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = 0;
            while(st.hasMoreTokens()) {
                arr[i][idx++] = Integer.parseInt(st.nextToken());
            }
        }

        // 첫 번째 집의 RGB 비용
        dp[0][0] = arr[0][0];
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][2];

        dfs(0, n - 1);      // 첫 번째 집에서 R을 먼저 고르기
        dfs(1, n - 1);      // 첫 번째 집에서 G를 먼저 고르기
        dfs(2, n - 1);      // 첫 번째 집에서 B를 먼저 고르기

        // 마지막 집까지 다 칠했을 때 최소 비용 구하기
        for(int i=0; i<3; i++) {
            answer = Math.min(answer, dp[n - 1][i]);
        }

        System.out.println(answer);     // 모든 집을 칠하는 비용의 최솟값
    }

    /*
        color: 0=빨강, 1=초록, 2=파랑
        depth: 몇 번째 집
     */
    public static int dfs(int color, int depth) {
        if(dp[depth][color] == 0) {     // 아직 depth번째 집에 color를 사용하지 않았을 때
            if (color == 0) {        // 빨간 색
                dp[depth][0] = Math.min(dfs(1, depth - 1), dfs(2, depth - 1)) + arr[depth][0];
            } else if (color == 1) {     // 초록 색
                dp[depth][1] = Math.min(dfs(0, depth - 1), dfs(2, depth - 1)) + arr[depth][1];
            } else {        // 파란 색
                dp[depth][2] = Math.min(dfs(0, depth - 1), dfs(1, depth - 1)) + arr[depth][2];
            }
        }
        return dp[depth][color];
    }
}