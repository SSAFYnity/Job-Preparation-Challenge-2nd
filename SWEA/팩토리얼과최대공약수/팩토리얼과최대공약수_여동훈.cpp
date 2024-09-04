#include<iostream>
#include<math.h>
using namespace std;
int gcd(int a, int b) {
	int result = 1;
	for (int i = min(a, b); i >= 2 && b > 1; i--) {
		if (i > b) continue;
		if (b % i == 0) { // 나누어지면, 나눔.
			result *= i;
			b /= i;
		}
	}
	return result;
}
int main() {

	int T;
	cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		int a, b;
		cin >> a >> b;
		int result = gcd(a, b);
		cout << "#" << tc << " " << result <<'\n';
	}
}
////////////////////////// 팩토리얼과 최대 공약수
// #include<iostream>
// #include<math.h>
// #include<vector>

// using namespace std;
// bool check(vector<int> vc) {
// 	for (int i = 0; i < vc.size(); i++) {
// 		if (vc[i] != 1) {
// 			return false;
// 		}
// 	}
// 	return true;
// }
// long long gcd(long long a, long long b) {
// 	if (a >= b) return b;
// 	long long result = 1;
// 	// 돌면서 나누어 지는가?? // 돌면서 안나뉘어지면 쌓음.
// 	vector<int> vc;
// 	double r = sqrt(b)+ 1;
// 	int local_b = b;
// 	for (int i = 2; i < r; i++) {
// 		if (local_b == 1) break;
// 		while (1) {
// 			if (local_b % i == 0) { // 해당 수로 나누어 떨어지면
// 				vc.push_back(i);
// 				local_b /= i;
// 			}
// 			else break;
// 		}
// 	}
// 	if (local_b != 1) // r 범위를 초과하는 소수 한개 존재.
// 	{
// 		if (a >= local_b) {
// 			result *= a;
// 		}
// 	}

// 	// 맨뒤에 있는 친구 보다 작다면 
	
// 	for (long long i = 2; i <= a; i++) {
// 		int now = i; // 저장
// 		if (check(vc)) break;
// 		for (long long j = 0; j < vc.size(); j++) { // 나누어지면 나누고 now값이 1이 된다면 넘어감 (최대 30번)
// 			if (vc[j] == 1) continue;
// 			if (now % vc[j] == 0) { // 나누어 떨어지면
// 				now /= vc[j]; // 나누고
// 				result *= vc[j]; // 결과에 추가
// 				vc[j] = 1;
// 			}
// 			if (now == 1) break;
// 		}
// 		// 나머지는 필요가 없음. 소수값이 남을 텐데 이미 벡터에 있었다면 나눴겠지.
// 	}
// 	return result;
// }
// int main() {

// 	int T;
// 	cin >> T;
// 	for (int tc = 1; tc <= T; tc++) {
// 		long long a, b;
// 		cin >> a >> b;
// 		long long result = gcd(a, b);
// 		cout << "#" << tc << " " << result <<'\n';
// 	}
// }