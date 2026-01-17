import java.io.*;
import java.util.*;

public class 카잉달력_여창영 {
    static int tc, T, M, N, x, y;
    static StringBuilder sb;

    public static int GCD(int num1, int num2){
        if(num2 == 0){
            return num1;
        }
        return GCD(num2, num1 % num2);
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            int result = -1;
            int num = 0;
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            int LCM = (M * N) / GCD(M,N);
            while(num * M < LCM){
                if((num * M + x - y)%N == 0){
                    result = num * M + x;
                    break;
                }
                num++;
            }
            sb.append(result).append('\n');
        }
    }

    public static void main(String args[]) throws IOException {
        pre();
        System.out.print(sb);
    }
}