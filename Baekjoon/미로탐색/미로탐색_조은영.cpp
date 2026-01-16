#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int arr[101][101] = { 0, };

bool isRange(int x, int y, int height, int width) {
	return (x >= 0 && x < height&& y >= 0 && y < width);
}

int main() {

	int n = 0, m = 0;
	int input = 0;

	queue<pair<int,int>>q;

	int dx[4] = {1,-1,0,0}; // 상하좌우
	int dy[4] = { 0,0,-1,1 };


	cin >> n >> m;

	
	vector<vector<bool>>visited(n, vector<bool>(m, 0));
	vector<vector<int>>dist(n, vector<int>(m, 0));
	
	for (int i = 0; i < n; i++) {
		string st;
		cin >> st;
		for (int j = 0; j < st.size(); j++) {
			arr[i][j] = st[j] - '0';
		}
	}


	q.push(make_pair(0,0));
	visited[0][0] = true;
	dist[0][0] = 1;

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;

		q.pop();

		// 사방탐색
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			// 범위 안이고 1이고 방문한적이 없으면 방문처리, 큐 삽입, 거리 추가
			if (isRange(nx, ny, n, m) && !visited[nx][ny] && arr[nx][ny]==1) {
				visited[nx][ny] = true;
				q.push(make_pair(nx, ny));
				dist[nx][ny] = dist[x][y] + 1;
			}

		}

		
	}

	cout << dist[n - 1][m - 1] << "\n";

	return 0;
}