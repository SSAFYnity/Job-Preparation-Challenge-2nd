import java.util.*;
import java.io.*;
 
class Solution { // 팩토리얼과 최대공약수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        for (int i = 1; i <= tc; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int answer = gcdFactorial(n, k);
            sb.append("#" + i + " " + answer + "\n");
        }
        System.out.println(sb);
    }
 
    // n!과 k의 최대공약수 계산
    public static int gcdFactorial(int n, int k) {
        int gcd = 1;
        for (int p = 2; p <= k; p++) {
            if (k % p == 0) {
                int powerInK = 0;
                while (k % p == 0) {
                    powerInK++;
                    k /= p;
                }
 
                int powerInNFactorial = 0;
                int multiple = p;
                while (multiple <= n) {
                    powerInNFactorial += n / multiple;
                    if (multiple > n / p) break; // Overflow 방지
                    multiple *= p;
                }
 
                gcd *= Math.pow(p, Math.min(powerInK, powerInNFactorial));
            }
        }
        return gcd;
    }
}