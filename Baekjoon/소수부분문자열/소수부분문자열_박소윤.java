import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static final int MAX = 100_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean[] isNotPrime = new boolean[MAX + 1];
        for (int i = 2; i <= MAX; i++) {
            if (!isNotPrime[i]) {
                for (int j = i * 2; j <= MAX; j+=i) {
                    if (!isNotPrime[j]) {
                        isNotPrime[j] = true;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        String input;
        while (!(input = br.readLine()).equals("0")) {
            int max = 0;
            int length = Math.min(input.length(), 6);
            for (int i = 1; i < length; i++) {
                for (int j = 0; j < input.length() - (i - 1); j++) {
                    int num = Integer.parseInt(input.substring(j, j + i));
                    if (!isNotPrime[num]) { // 소수라면
                        max = Math.max(max, num);
                    }
                }
            }
            sb.append(max).append('\n');
        }
        System.out.println(sb);
    }
}