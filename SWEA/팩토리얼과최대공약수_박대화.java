import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for(int tk = 1; tk <= t; tk++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if(n >= k) {
                System.out.println("#" + tk + " " + k);
            }else {
                System.out.println("#" + tk + " " + gcd(n, k));
            }
        }
    }

    public static int gcd(int n, int k) {
        int result = 1;
        for(int i = n; i >= 1; i--) {
            if(k % i == 0) {
                result *= i;
                k /= i;
            }
        }
        return result;
    }
}