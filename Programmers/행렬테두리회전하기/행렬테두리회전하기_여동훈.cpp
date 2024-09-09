#include <string>
#include <vector>
#include <utility>

using namespace std;
int map[101][101];
const int dy[] = {1,0,-1,0}; // 하 우 상 좌
const int dx[] = {0,1,0,-1};
void make_map(int n, int m) {
    int t = 1;
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            map[i][j] = t++;
        }
    }
}
void rotate(vector<int> input_queries, vector<int> & answer) {

      pair<int, int> start = { input_queries[0],input_queries[1] };
    pair<int, int> end = { input_queries[2],input_queries[3] };

    pair<int, int> now_loc = { input_queries[0],input_queries[1] };

    int minV = map[now_loc.first][now_loc.second]; // 최솟값 초기화
    int tmp = map[now_loc.first][now_loc.second]; // 현재 값 저장
    int dir = 0;
    while (1) {
        pair<int, int > next_loc = { now_loc.first + dy[dir], now_loc.second + dx[dir] };
        if (next_loc == start) { // 시작 지점으로 돌아오면
            map[now_loc.first][now_loc.second] = tmp;
            now_loc = next_loc;
            break;
        }
        if (next_loc.first > end.first || next_loc.first < start.first || next_loc.second > end.second || next_loc.second < start.second) { // 범위 초과시 방향 바꾸기
            dir = (dir + 1) % 4;
            continue;
        }
        minV = minV > map[next_loc.first][next_loc.second] ? map[next_loc.first][next_loc.second] : minV;
        map[now_loc.first][now_loc.second] = map[next_loc.first][next_loc.second];
        now_loc = next_loc;
    }
    answer.push_back(minV);
}

vector<int> solution(int rows, int columns, vector<vector<int>> queries) {
    vector<int> answer;
    make_map(rows, columns);
    for (int i = 0; i < queries.size(); i++) {
        rotate(queries[i], answer);
    }


    return answer;
}