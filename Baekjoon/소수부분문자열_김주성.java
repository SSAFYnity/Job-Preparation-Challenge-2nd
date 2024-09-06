import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 소수부분문자열_김주성 {

    static void calcuPrime() {
        isPrime = new boolean[100001];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int num = 2; num < Math.sqrt(100001); num++) {
            if (isPrime[num]) {
                for (int i = num*num; i < 100001; i += num) {
                    isPrime[i] = false;
                }
            }
        }
    }

    static void pro() {
        ans = -1;
        for (int s = 5; s >= 1; s--) {
            for (int i = 0; i <= input.length() - s; i++) {
                int num = Integer.parseInt(input.substring(i,i + s));
                if(isPrime[num]) ans = Math.max(ans, num);
            }
            if (ans != -1) return;
        }
    }

    static boolean[] isPrime;
    static int ans;
    static String input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        calcuPrime();
        while(!input.equals("0")) {
            pro();
            System.out.println(ans);
            input = br.readLine();
        }
    }
}
