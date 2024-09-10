import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 팩토리얼과최대공약수_김현진 {
	static int T;
	static long N, K;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(in.readLine().trim());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Long.parseLong(st.nextToken());
			K = Long.parseLong(st.nextToken());

			long gcd = getFactorialGCD(N, K);
			System.out.println("#" + test_case + " " + gcd);
		}
	}

	private static long getFactorialGCD(long N, long K) {
		Map<Long, Integer> primeFactorsK = primeFactorize(K);
		long gcd = 1;

		for (Map.Entry<Long, Integer> entry : primeFactorsK.entrySet()) {
			long prime = entry.getKey();
			int countInK = entry.getValue();

			int countInFactorial = countPrimeInFactorial(N, prime);

			gcd *= Math.pow(prime, Math.min(countInK, countInFactorial));
		}

		return gcd;
	}

	private static Map<Long, Integer> primeFactorize(long num) {
		Map<Long, Integer> factors = new HashMap<>();

		for (long i = 2; i * i <= num; i++) {
			while (num % i == 0) {
				factors.put(i, factors.getOrDefault(i, 0) + 1);
				num /= i;
			}
		}

		if (num > 1) {
			factors.put(num, 1);
		}
		return factors;
	}

	private static int countPrimeInFactorial(long N, long prime) {
		int count = 0;
		while (N > 0) {
			count += N / prime;
			N /= prime;
		}
		return count;
	}
}