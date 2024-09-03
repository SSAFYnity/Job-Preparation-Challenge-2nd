import java.io.*;
import java.util.*;

// [BOJ] RGB 거리
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine()); //집의 수
        int[][] dp = new int[N+1][3];

        for (int i=1; i<N+1; i++){ // i번째 집
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            dp[i][0] = Math.min(dp[i-1][1],dp[i-1][2]) + r;
            dp[i][1] = Math.min(dp[i-1][0],dp[i-1][2]) + g;
            dp[i][2] = Math.min(dp[i-1][0],dp[i-1][1]) + b;
        }
        System.out.println(Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2])));
    }

}