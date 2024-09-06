#include <iostream>

using namespace std;

int main() {
	int t;
	cin >> t;
	for (int tc = 1; tc <= t; tc++) {
		cout << "#" << tc << " ";
		int N, K;
		cin >> N >> K;

		int ans = 1;
		if (N >= K) {
			ans = K;
		}
		else {
			for (int i = N; i > 0; i--) {
				if (K % i == 0) {
					ans *= i;
					K /= i;
				}
			}
		}
		cout << ans << endl;
	}
}