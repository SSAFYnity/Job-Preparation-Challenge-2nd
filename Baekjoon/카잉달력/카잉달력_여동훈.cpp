#include<iostream>
using namespace std;
int N, M, x, y;

void init() {
	cin >> N >> M >> x >> y;
}
int solved() {
	
	int cnt = x; // x로 맞춰놓기
	int ny = x % M;
	if (ny == 0) ny = M;
	int visited[40001] = { 0 };
	visited[ny] = 1;
	while (1) {
		if (ny == y) {
			return cnt;
		}
		ny = (ny + N) % M;
		if (ny == 0) ny = M;
		cnt += N;
		if (visited[ny] == 1) break;
		visited[ny] = 1;
	}
	return -1;
}

int main() {
	int t = 0;
	cin >> t;
	for (int tc = 0; tc < t; tc++) {
		init();
		int result = solved();
		cout << result << endl;
	}


}