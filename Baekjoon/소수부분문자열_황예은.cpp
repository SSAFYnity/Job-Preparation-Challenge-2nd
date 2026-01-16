#include<iostream>
#include<algorithm>
#include<string>
#include<vector>

using namespace std;

const int MAX_SIZE = 100000;
vector<bool> isPrime(MAX_SIZE + 1, true); // 시작은 모든칸이 소수로 판단

void Eratos() {
	isPrime[0] = isPrime[1] = false;
	
	for (int i = 2; i*i <= MAX_SIZE; i++) {
		if (isPrime[i]) {
			for (int j = i * i; j <= MAX_SIZE; j += i) {
				isPrime[j] = false;
			}
		}
	}
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);

	Eratos(); // 소수 세팅
	string str;
	cin >> str;

	while (str != "0") {
		/*
		2보다 크거나 같고, 100,000보다 작거나 같은 소수만 소수
		100,000은 소수가 아니므로 애초에 고려X
		99999까지만 고려 => 소수인 문자열 최대 길이 = 5
		*/
		int maxPrim{};
		
		for (int len = 1; len <= 5; len++) {
			for (int pos = 0; pos + len < str.size(); pos++) {
				string tmp = str.substr(pos, len); // 문자열 len 길이만큼 자르기
				int num = stoi(tmp); // 숫자로 바꾸기
				if (isPrime[num] && maxPrim < num) maxPrim = num; // 소수 최대값 갱신
			}
		}

		cout << maxPrim << "\n";
		cin >> str;
	}
	return 0;
}