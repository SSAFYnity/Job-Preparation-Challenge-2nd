import java.io.*;

public class 소수부분문자열_안창호 {
    static String str;
    static int len;
    static int answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            answer = 0;
            str = br.readLine();
            len = str.length();
            if (len == 1 && Integer.parseInt(str) == 0) return; // 마지막 줄에 0이 주어지는 경우 종료

            findNum();

            System.out.println(answer);
        }
    }
    static void findNum() {
        for (int i = 0; i < len; i++) {
            for (int j = i; j < i + 5; j++) { // 문제에서 요구하는 소수의 범위가 2 ~ 100,000 이기 때문에 5자리 까지만 탐색
                if (j >= len) break; // 문자열의 범위를 벗어나면 break
                int num = Integer.parseInt(str.substring(i, j + 1));

                if (isPrime(num)) {
                    answer = Math.max(answer, num);
                }
            }
        }
    }

    static boolean isPrime(int num) {
        if (num < 2) return false;

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }

        return true;
    }
}
