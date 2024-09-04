import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            if (N >= K) {
                sb.append('#').append(t).append(' ').append(K).append('\n');
                continue;
            }
            sb.append('#').append(t).append(' ').append(getGcd(N, K)).append('\n');
        }
        System.out.println(sb);
    }

    private static int getGcd(int N, int K) {
        int gcd = 1;
        for (int i = N; i > 1; i--) {
           if (K % i == 0) {
               gcd = gcd * i;
               K = K / i;
           }
        }
        return gcd;
    }
}