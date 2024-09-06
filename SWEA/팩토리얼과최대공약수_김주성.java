import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 팩토리얼과최대공약수_김주성 {
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        N_arr = new int[T+1];
        K_arr = new int[T+1];
        for (int i = 1; i <= T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N_arr[i] = Integer.parseInt(st.nextToken());
            K_arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void pro(int t) {
        int N = N_arr[t], K = K_arr[t];
        ans = 1;

        if (N >= K) {
            ans = K;
            return;
        }
        for (int n = N; n > 1; n--) {
            if (K % n == 0) {
                ans *= n;
                K /= n;
            }
        }
    }

    static int T, ans;
    static int[] N_arr, K_arr;

    public static void main(String[] args) throws IOException {
        input();
        for (int t = 1; t <= T; t++){
            pro(t);
            System.out.println("#" + t +" " + ans);
        }
    }
}
