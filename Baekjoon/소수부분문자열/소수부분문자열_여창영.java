import java.io.*;
import java.util.*;

public class 소수부분문자열_여창영 {
    static StringBuilder sb;

    public static boolean isPrime(int num) {
        if (num < 2 && num > 100000) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void findPrime(String input) {
        int num, len, answer;

        len = input.length();
        answer = Integer.MIN_VALUE;

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len && j - i < 6; j++) {
                String substr = input.substring(i, j);
                num = Integer.parseInt(substr);

                if (isPrime(num)) {
                    answer = Math.max(answer, num);
                }
            }
        }
        sb.append(answer).append('\n');
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        String input = br.readLine();

        while (!input.equals("0")) {
            findPrime(input);
            input = br.readLine();
        }
    }

    public static void main(String args[]) throws IOException {
        pre();
        System.out.print(sb);
    }
}
