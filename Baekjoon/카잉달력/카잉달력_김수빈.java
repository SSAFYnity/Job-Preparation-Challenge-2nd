import java.io.*;
import java.util.*;

public class 카잉달력_김수빈 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++) {
            String input = br.readLine();
            String[] stringArray = input.split(" ");
            int M = Integer.parseInt(stringArray[0]);
            int N = Integer.parseInt(stringArray[1]);
            int x = Integer.parseInt(stringArray[2]);
            int y = Integer.parseInt(stringArray[3]);

            int[][] calendar = new int[M + 1][N + 1];

            calendar[1][1] = 1;

            int m = 1;
            int n = 1;

            while (true) {
                int tempM;
                int tempN;
                // 마지막 년도
                if (m + 1 == M && n + 1 == N) {
                    calendar[m + 1][n + 1] = calendar[m][n] + 1;
                    break;
                }
                // 조건에 따라 다음 해 숫자 선정
                if (m < M) {
                    tempM = m + 1;
                } else {
                    tempM = 1;
                }
                if (n < N) {
                    tempN = n + 1;
                } else {
                    tempN = 1;
                }
                // 다음 해 바꾸기
                calendar[tempM][tempN] = calendar[m][n] + 1;
                m = tempM;
                n = tempN;
            }
            if (calendar[x][y] != 0) {
                System.out.println(calendar[x][y]);
            } else {
                System.out.println(-1);
            }
        }
    }
}
