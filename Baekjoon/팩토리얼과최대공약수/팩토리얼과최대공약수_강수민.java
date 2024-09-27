package D4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SWEA_7466_팩토리얼과최대공약수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long N = Long.parseLong(st.nextToken());
            long K = Long.parseLong(st.nextToken());
            sb.append('#').append(t).append(' ').append(gcdFactorial(N, K)).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private static long gcdFactorial(long n, long k) {
        // K의 소인수 분해
        Map<Long, Long> primeFactors = primeFactorization(k);

        long gcd = 1;

        // 각 소인수에 대해 N!에서의 개수를 계산
        for (Map.Entry<Long, Long> entry : primeFactors.entrySet()) {
            long prime = entry.getKey();
            long countInK = entry.getValue();
            long countInNFactorial = countPrimeInFactorial(n, prime);

            // GCD 계산
            long minCount = Math.min(countInK, countInNFactorial);
            gcd *= Math.pow(prime, minCount);
        }

        return gcd;
    }

    private static Map<Long, Long> primeFactorization(long k) {
        Map<Long, Long> factors = new HashMap<>();
        for (long i = 2; i * i <= k; i++) {
            while (k % i == 0) {
                factors.put(i, factors.getOrDefault(i, 0L) + 1);
                k /= i;
            }
        }
        if (k > 1) {
            factors.put(k, factors.getOrDefault(k, 0L) + 1);
        }
        return factors;
    }

    private static long countPrimeInFactorial(long n, long prime) {
        long count = 0;
        while (n > 0) {
            n /= prime;
            count += n;
        }
        return count;
    }
}
