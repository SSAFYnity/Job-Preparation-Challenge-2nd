import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i<t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int lcm = m*n / gcd(m, n);
            int year = x;
            while (year <= lcm)  {
                if (year % n == y) {
                    sb.append(year+1 + "\n");
                    break;
                }
                year += m;
            }
            if (year > lcm) {
                sb.append(-1 + "\n");
            }
        }
        System.out.println(sb);
    }

    public static int gcd(int a, int b) {
        if (b==0) return a;
        return gcd(b, a%b);
    }
}