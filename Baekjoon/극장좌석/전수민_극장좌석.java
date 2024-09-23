import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        if(N == 1) {
            System.out.println(1);
            return;
        }
        int [] dp = new int [N+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i <= N; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        int ans = 1;
        int vip_recent = 0;
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int vip_now = Integer.parseInt(st.nextToken());
            ans *= dp[vip_now - vip_recent-1];
            vip_recent = vip_now;
        }
        ans *= dp[N-vip_recent];
        System.out.println(ans);
    }
}