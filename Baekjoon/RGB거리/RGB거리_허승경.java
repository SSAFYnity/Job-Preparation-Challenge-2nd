import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int [][] map = new int[n+1][3];   // 비용 지도
        int [][] dp = new int[n+1][3];      // RGB, 각각의 최소값

        StringTokenizer st;
        for(int i = 1; i < n+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        map[0][0] = map[0][1] = map[0][1] = dp[0][0] = dp[0][1] = dp[0][2] = 0;  // 0으로 초기화

        for(int i = 1; i < n+1; i++){
            dp[i][0] = map[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);    // 현재 비용 + 이전 다른 색 비용 중 최솟값
            dp[i][1] = map[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = map[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
        }

        System.out.println(Math.min(Math.min(dp[n][0], dp[n][1]), dp[n][2]));

    }
}
