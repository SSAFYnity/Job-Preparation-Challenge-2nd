package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S6064 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			int m = M;
			int n = N;
			int remainder = -1;
			
			while (remainder != 0) {
				remainder = Math.max(m, n) % Math.min(m, n);
				m = Math.min(m, n);
				n = remainder;
			}
			
			int k;

			boolean getResult = false;
			if (x == M && y == N) {
				System.out.println(M * N / m);
				getResult = true;
			} else if (x == M) {
				k = x;
				while (k <= M * N / m) {
					if (k % N == y) {
						System.out.println(k);
						getResult = true;
						break;
					}
					k += M;
				}
			} else if (y == N) {
				k = y;
				while (k <= M * N / m) {
					if (k % M == x) {
						System.out.println(k);
						getResult = true;
						break;
					}
					k += N;
				}
			} else {
				k = Math.max(x, y);
				int d = (k == x) ? M : N;
				while (k <= M * N / m) {
					if (k % M == x && k % N == y) {
						System.out.println(k);
						getResult = true;
						break;
					}
					k += d;
				}
			}
			
			if (!getResult) {
				System.out.println(-1);
			}
		}
	}
}
