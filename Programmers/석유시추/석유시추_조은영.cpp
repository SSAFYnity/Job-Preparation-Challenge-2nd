#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int dx[4] = { 0,0,1,-1 };
int dy[4] = { 1,-1,0,0 };
bool visited[500][500] = { false, };
int landIdx[500][500];
vector<int>landSize;
int cnt = 0, idx = 0;

bool isRange(int x, int y, int n, int m) {
    return x >= 0 && x < n&& y >= 0 && y < m;
}

int DFS(int x, int y, vector<vector<int>>& land, int n, int m) {
    int cnt = 1;
    visited[x][y] = true;
    landIdx[x][y] = idx;

    for (int i = 0; i < 4; i++) {
        int nextX = x + dx[i];
        int nextY = y + dy[i];

        // 범위 안이고 석유이고 방문하지 않았다면
        if (isRange(nextX, nextY, n, m) && land[nextX][nextY] == 1 && !visited[nextX][nextY]) {
            cnt += DFS(nextX, nextY, land, n, m);
        }
    }
    return cnt;
}

int solution(vector<vector<int>> land) {

    int answer = 0;
    int n = land.size();
    int m = land[0].size();

    fill(&landIdx[0][0], &landIdx[0][0] + 500 * 500, -1);

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            // 석유가 있고 아직 방문 안했다면
            if (land[i][j] == 1 && !visited[i][j]) {
                int res = DFS(i, j, land, n, m);
                landSize.push_back(res);
                idx++;
            }
        }

    }

    // 시추하기
    // 열 - 행 순으로 탐색
    for (int j = 0; j < m; j++) {
        int sum = 0;
        vector<bool>landVisited(landSize.size(), false);
        for (int i = 0; i < n; i++) {
            int value = landIdx[i][j];
            if (value != -1 && !landVisited[value]) {
                landVisited[value] = true;
                sum += landSize[value];
            }
        }
        answer = max(answer, sum);
    }

    return answer;
}