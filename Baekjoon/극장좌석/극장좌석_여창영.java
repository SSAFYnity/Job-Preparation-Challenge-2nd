import java.io.*;
import java.util.*;

public class 극장좌석_여창영 {
    static int N, M, result;
    static int dp[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void dynamic() throws IOException{
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int beforeSeat = 0;
        for (int i = 0; i < M; i++) {
            int temp = Integer.parseInt(br.readLine());
            result *= dp[temp - beforeSeat - 1];
            beforeSeat = temp;
        }
        result *= dp[N - beforeSeat];

    }

    public static void pre() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        result = 1;

        dp = new int[41];
    }

    public static void main(String[] args) throws IOException {
        pre();
        dynamic();
        System.out.println(result);
    }

}