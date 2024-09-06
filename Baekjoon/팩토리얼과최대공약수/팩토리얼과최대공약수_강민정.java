import java.io.*;
import java.util.StringTokenizer;

class 팩토리얼과최대공약수_강민정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());    // 테스트 케이스 수

        for(int tc=1; tc<=t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());   // 1 <= n <= 10^9
            int k = Integer.parseInt(st.nextToken());   // 1 <= k <= 10^9
            int answer = 1;     // 최대 공약수

            if(n >= k) {
                answer = k;
            } else {        // 최대 공약수 구하기
                for (int i = n; i > 0; i--) {     // 최대 공약수를 빨리 찾기 위해 뒤에서부터 찾음
                    if (k % i == 0) {       // i가 k의 약수라면
                        answer *= i;
                        k /= i;
                    }
                }
            }
            bw.write("#" + tc + " " + answer + "\n");       // 두 수의 최대 공약수
        }
        bw.flush();
    }
}