#include<iostream>
using namespace std;
int N, M;

int fixed_sit[41];
int dp[41];
//int visited[41];
void init() {
	cin >> N >> M;
	for (int i = 0; i < M; i++) {
		int num;
		cin >> num;
		num--;
		fixed_sit[num] = 1;
		//visited[num] = 1;
	}
}
unsigned int result = 0;
//void dfs(int depth) {
//	if (fixed_sit[depth] == 1) { // 고정석이면 다음 인덱스로 넘어감
//		dfs(depth + 1);
//		return;
//	}
//	if (depth == N) {
//		result++;
//		return;
//	}
//	for (int i = -1; i <= 1; i++) {
//		int next_idx = depth + i;
//		if (next_idx < 0 || next_idx >= N) continue;
//		if (visited[next_idx] == 1) continue;
//		visited[next_idx] = 1; // 방문처리
//		dfs(depth + 1);
//		visited[next_idx] = 0; // 방문 취소
//	}
//	return;
//}

void make_dp() {
	// 현재꺼는 전에꺼 더하기 전전에꺼
	// 고정 좌석이라면 전에꺼 더하기
	// 전의 인덱스가 고정 좌석이라면 전에꺼 더하기
	dp[0] = 1;
	if (fixed_sit[0] == 1 || fixed_sit[1] == 1) dp[1] = 1;
	else dp[1] = 2;
	for (int i = 2; i < N; i++) {
		if (fixed_sit[i - 1] == 1 || fixed_sit[i] == 1) dp[i] = dp[i - 1];
		else dp[i] = dp[i - 2] + dp[i - 1];
	}
	result = dp[N - 1];
}


int main() {
	init();
	//dfs(0);
	make_dp();
	cout << result;
}