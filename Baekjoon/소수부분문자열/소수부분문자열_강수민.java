import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class Main {
    private static final int MAX = 100_001;
    private static boolean[] prime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        setPrime();

        String s;

        while (!(s = br.readLine()).equals("0")) {
            int largestPrime = 0; // 정수형으로 변경
            HashSet<Integer> foundPrimes = new HashSet<>(); // 중복 체크용

            // 부분 문자열 검사
            for (int length = 1; length <= Math.min(s.length(), 6); length++) {
                for (int start = 0; start <= s.length() - length; start++) {
                    String sub = s.substring(start, start + length);
                    if (isPrime(sub) && !foundPrimes.contains(Integer.parseInt(sub))) {
                        int num = Integer.parseInt(sub);
                        largestPrime = Math.max(largestPrime, num);
                        foundPrimes.add(num); // 중복 방지
                    }
                }
            }

            sb.append(largestPrime == 0 ? "0" : largestPrime).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private static boolean isPrime(String numStr) {
        if (numStr.charAt(0) == '0') {
            return false;
        }
        int num = Integer.parseInt(numStr);
        return num >= 2 && num < MAX && prime[num];
    }

    private static void setPrime() {
        prime = new boolean[MAX];
        for (int i = 2; i < MAX; i++) {
            prime[i] = true;
        }

        for (int i = 2; i * i < MAX; i++) {
            if (prime[i]) {
                for (int j = i * i; j < MAX; j += i) {
                    prime[j] = false;
                }
            }
        }
    }
}