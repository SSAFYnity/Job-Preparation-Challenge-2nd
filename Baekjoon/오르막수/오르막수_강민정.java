import java.io.*;
import java.util.Scanner;

public class 오르막수_강민정 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // 1 <= 자리 수 <= 1,000
        int[][] dp = new int[n + 1][10];

        // DP 초기화
        for(int i=0; i<=9; i++) {
            dp[0][i] = 1;
        }

        for(int i=1; i<=n; i++) {       // 자리 수
            for(int j=0; j<=9; j++) {
                for(int k=j; k<=9; k++) {
                    dp[i][j] += dp[i - 1][k] % 10007;   // 이전 자리 수에서 합
                }
            }
        }

        System.out.println(dp[n][0] % 10007);     // 오르막 수의 개수 출력
    }
}