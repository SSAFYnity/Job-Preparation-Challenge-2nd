import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int count = 1; // 답
        int[] arr = new int[n]; // 현재 의자가 놓인 배열
        int[] dp = new int[n+1]; // vip석 전까지 길이가 a개일 때 앉을 수 있는 경우의 수
        dp[0] = 1;
        dp[1] = 1;
        if (n >= 2) dp[2] = 2;
        for (int i = 3; i<dp.length; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i<m; i++) {
            int a = Integer.parseInt(br.readLine())-1;
            arr[a] = -1; // 고정석 표시
        }
        // 앉을 수 있는 경우의 수 구하기
        int temp = 0;
        for (int i = 0; i<n; i++) {
            if (arr[i] == -1) {
                count *= dp[temp];
                temp = 0;
            }
            else if (i==n-1) {
                temp++;
                count *= dp[temp];
            }
            else {
                temp++;
            }
        }
        System.out.println(count);
    }
}
