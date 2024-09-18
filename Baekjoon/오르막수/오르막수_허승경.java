import java.io.*;

public class Main {
    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int mod = 10007;

        int [][] dp = new int[10001][10];

        for(int i = 0; i < 10; i++){
            dp[1][i] = 1;
        }

        for(int i = 2; i < 10001; i++){
            for(int j = 0; j < 10; j++){
                for(int k = 0; k <= j; k++){
                    dp[i][j] = (dp[i][j]+dp[i-1][k]) % mod;
                }
            }
        }

        int result = 0;
        for(int i = 0; i < 10; i++){
            result = (result+dp[n][i]) % mod;
        }

        System.out.println(result);
    }
}
