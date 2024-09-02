package boj.silver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 소수부분문자열_김현진 {
	static boolean[] isPrime = new boolean[100001];
	static int len = isPrime.length;

	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		PrimeNumber();

		while (true) {
			String str = sc.readLine();
			if (str.equals("0"))
				return;

			int max = -1;

			for (int i = 0; i < str.length(); i++) {
				for (int j = 1; j <= 5 && i + j <= str.length(); j++) {
					int num = Integer.parseInt(str.substring(i, i + j));
					if (num >= 2 && num <= 100000 && isPrime[num]) {
						max = Math.max(max, num);
					}
				}
			}
			System.out.println(max);
		}
	}

	public static void PrimeNumber() {
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false;

		for (int i = 2; i * i <= len; i++) {
			if (isPrime[i]) {
				for (int j = i * i; j < len; j += i) {
					isPrime[j] = false;
				}
			}
		}
	}
}
