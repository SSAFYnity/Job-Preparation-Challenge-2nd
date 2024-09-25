import java.io.*;
import java.util.StringTokenizer;

public class 카잉달력_강민정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());     // 테스트케이스 수

        for(int tc=0; tc<t; tc++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());    // 1 <= m <= 40,000
            int n = Integer.parseInt(st.nextToken());    // 1 <= n <= 40,000
            int x = Integer.parseInt(st.nextToken()) - 1;    // 1 <= x <= m
            int y = Integer.parseInt(st.nextToken()) - 1;    // 1 <= y <= n
            int answer = -1;     // 몇 번째 해인지

            for(int num=x; num<m*n; num+=m) {    // x는 +m의 주기를 가진다
                if(num % n == y) {       // 나머지가 y랑 같으면
                    answer = ++num;      // 나머지가 0이 되는 것을 피할려고 -1을 해줬으니까 다시 +1
                    break;
                }
            }
            bw.write(answer + "\n");
        }
        bw.flush();
    }
}