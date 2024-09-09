#include<iostream>
#include<cstring>
#include<queue>
using namespace std;
int N;
char c_map[100][101];
struct loc {
	int y, x;
};
	
void init() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> c_map[i];
	}
}
const int dy[] = { -1,0,1,0 };
const int dx[] = { 0,-1,0,1 };

int visited[100][100] = { 0 };
int cnt[2] = { 0 };
void bfs(int idx) {
	memset(visited, 0, sizeof(visited));
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (visited[i][j] == 1)continue;
			queue<loc> q;
			char now_color = c_map[i][j];
			q.push({ i,j });
			visited[i][j] = 1;
			cnt[idx]++;
			while (!q.empty()) {
				loc now = q.front(); q.pop();
				for (int i = 0; i < 4; i++) {
					int ny = now.y + dy[i];
					int nx = now.x + dx[i];
					if (ny >= N || nx >= N || ny < 0 || nx < 0) continue;
					if (visited[ny][nx] == 1) continue;
					if (c_map[ny][nx] != now_color) continue;
					visited[ny][nx] = 1;
					q.push({ ny,nx });
				}

			}
		}
	}
}
int main() {
	init();
	bfs(0);
	for (int i = 0; i < N; i++) { // 빨간색을 초록색으로 통일
		for (int j = 0; j < N; j++) {
			if (c_map[i][j] == 'R') {
				c_map[i][j] = 'G';
			}
		}
	}
	bfs(1);

	cout << cnt[0] << " " << cnt[1];
}