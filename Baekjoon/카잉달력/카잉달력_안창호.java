import java.io.*;
public class 카잉달력_안창호 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String[] str = br.readLine().split(" ");
            int M = Integer.parseInt(str[0]);
            int N = Integer.parseInt(str[1]);
            int x = Integer.parseInt(str[2]);
            int y = Integer.parseInt(str[3]);

            // y를 N과 일치시킨다
            if (y == N) y = 0;

            int lcm = lcm(M, N);
            int ans = -1;

            for (int i = 0; i * M + x <= lcm; i++) {
                int year = i * M + x;
                if (year % N == y) {
                    ans = year;
                    break;
                }
            }

            System.out.println(ans);
        }
    }
    static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
    static int gcd (int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
}
