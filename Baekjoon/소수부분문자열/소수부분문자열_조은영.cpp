#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
using namespace std;

int main() {

	string input;
	bool isPrime[100001];

	fill(isPrime, isPrime + 100001, true);
	
	vector<int>v;

	for (int i = 2; i <= 100000; i++) {
		if (isPrime[i]) {
			v.push_back(i);
			for (int j = i * 2; j <= 100000; j += i) {
				isPrime[j] = false;
			}
		}
	}

	sort(v.rbegin(), v.rend());

	while (cin >> input) {

		if (input == "0") break;

		for (int i = 0; i < v.size(); i++) {
			if (input.find(to_string(v[i])) != string::npos) {
				cout << v[i] << "\n";
				break;
			}
		}

	}

	return 0;
}