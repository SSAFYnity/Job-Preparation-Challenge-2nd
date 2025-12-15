import java.io.*;
import java.util.*;

public class 팩토리얼과최대공약수_여창영 {
    static int a, b;
    static StringBuilder sb;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int getGCD() {
        int result = 1;
        for (int i = Math.min(a, b); i >= 2 && b > 1; i--) {
            if (i > b)
                continue;
            if (b % i == 0) {
                result *= i;
                b /= i;
            }
        }
        return result;
    }

    public static void pre() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            pre();
            sb.append('#').append(t + " " + getGCD() + "\n");
        }

        System.out.print(sb);
    }
}