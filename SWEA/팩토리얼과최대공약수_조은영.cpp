#include <iostream>
#include <map>
#include <algorithm>
#include <cmath>
using namespace std;

map<int, int> calculator(int n) {

	map<int, int>mp;

	for (int i = 2; i * i <= n; i++) {
		if (n % i == 0) {
			mp[i]++;
			n /= i;
		}
	}
	if (n > 1) {
		mp[n]++;
	}

	return mp;
}

int countPrime(int n, int prime) {
	int cnt = 0;

	while (n > 0) {
		n /= prime;
		cnt += n;
	}

	return cnt;
}

int main() {

	int T = 0, N = 0, K = 0;

	cin >> T;

	for (int t = 1; t <= T; t++) {
		cin >> N >> K;

		map<int, int> factor = calculator(K);

		int gcd = 1;

		for (auto& f : factor) {
			int prime = f.first;
			int cnt = f.second;
			int result = countPrime(N, prime);

			gcd *= pow(prime, min(cnt, result));
		}

		cout << "#" << t << " " << gcd << "\n";
	}

	return 0;
}