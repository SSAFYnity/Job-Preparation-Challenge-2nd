import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < test_case; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // 1. 최소 공배수 구하기 -> 최소 공배수를 넘어서 계속 값을 유추하고 있으면 유효하지 않은 수임으로 -1 출력
            int lcm = M>N? (M*N/gcd(M,N)) : (M*N/gcd(N,M));
            // 2.
            int ans = -1;
            int n = 0;
            while (n*M < lcm) {
                if((n*M+x-y)%N == 0) {
                    ans = n*M+x;
                    break;
                }
                else  n++;
            }
            System.out.println(ans);
        }
    }
    public static int  gcd (int A, int B) {
        if(A%B == 0) return B;
        else return gcd(B, A%B);
    }
}