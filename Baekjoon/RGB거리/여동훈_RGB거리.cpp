#include <iostream>
using namespace std;

int N;
int map[1000][3]; // 집을 색칠하는데 드는 비용
int dp[1000][3];
int result = 1e9;
void init() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < 3; j++) {
			cin >> map[i][j];
		}
	}
}
void solved() {
	for (int i = 0; i < 3; i++) {
		dp[0][i] = map[0][i];
	}
	for (int i = 1; i < N; i++) {
		for (int j = 0; j < 3; j++) {
			int minV = 1e9;
			for (int k = 0; k < 3; k++) {
				if (k == j) continue;
				int tmp = map[i][j] + dp[i - 1][k];
				minV = minV > tmp ? tmp : minV;
			}
			dp[i][j] = minV; // 최솟값적용
		}
	}
	for (int i = 0; i < 3; i++) {
		result = result > dp[N - 1][i] ? dp[N - 1][i] : result;
	}
}
int main() {
	init();
	solved();
	cout << result;
}