#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

// 이전 값
struct Position {
	int x;
	int y;
	int blank_cnt;
	int like_cnt;
};

int dx[4] = { 0,0,1,-1 };
int dy[4] = { 1,-1,0,0 };
int N = 0;	
vector<int>v[401];
int arr[20][20] = { 0, };
int student = 0, like_student = 0;

bool isRange(int x, int y) {
	if (x >= 0 && x < N && y >= 0 && y < N) return true;
	return false;
}

// 자리 정하기
void search_position() {

	Position best = {-1,-1,-1,-1};

	for (int n = 0; n < N; n++) {
		for (int m = 0; m < N; m++) {
			if (arr[n][m] == 0) {
				// 인접한 칸중에 빈칸, 좋아하는 학생 수 구하기
				int blank_cnt = 0, like_cnt = 0;

				for (int k = 0; k < 4; k++) {
					int nextX = n + dx[k];
					int nextY = m + dy[k];
					if (isRange(nextX, nextY)) {
						if (arr[nextX][nextY] == 0) blank_cnt++;
						if (find(v[student].begin(), v[student].end(), arr[nextX][nextY]) != v[student].end()) like_cnt++;
					}
				}

				if (like_cnt > best.like_cnt ||
					(like_cnt == best.like_cnt && blank_cnt > best.blank_cnt) ||
					(like_cnt == best.like_cnt && blank_cnt == best.blank_cnt && n < best.x) ||
					(like_cnt == best.like_cnt && blank_cnt == best.blank_cnt && n == best.x && m < best.y)) {
					best = { n, m, blank_cnt, like_cnt };
				}
			}

		}
	}

	arr[best.x][best.y] = student;
}

int cal() {
	int total = 0;

	for (int r = 0; r < N; r++) {
		for (int c = 0; c < N; c++) {
			int student = arr[r][c];
			int like_count = 0;

			for (int k = 0; k < 4; k++) {
				int nextX = r + dx[k];
				int nextY = c + dy[k];
				if (isRange(nextX, nextY) && find(v[student].begin(), v[student].end(), arr[nextX][nextY]) != v[student].end()) {
					like_count++;
				}
			}

			if (like_count == 1) total += 1;
			else if (like_count == 2) total += 10;
			else if (like_count == 3) total += 100;
			else if (like_count == 4) total += 1000;
		}
	}

	return total;
}


int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> N;

	for (int i = 0; i < N * N; i++) {
		cin >> student;

		for (int j = 0; j < 4; j++) {
			cin >> like_student;
			v[student].push_back(like_student);
		}
		search_position();
	}

	cout << cal() << "\n";

	return 0;
}