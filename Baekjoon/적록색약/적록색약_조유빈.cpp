#include <iostream>
#include <cstring>
#include <string>
#include <queue>

using namespace std;

struct Node {
    int y, x;
};
queue<Node> q;

int n, ans1, ans2;
int used[101][101];
string map[101];
char nowColor;

int dy[] = {-1, 1, 0, 0};
int dx[] = {0, 0, -1, 1};

void input() {
    cin >> n;
    for (int i = 0; i < n; i++)
        cin >> map[i];
}

void bfs(int y, int x, int flag) {
    q.push({y, x});
    used[y][x] = 1;

    while(!q.empty()){
        Node now = q.front();
        nowColor = map[now.y][now.x];
        q.pop();

        for (int i = 0; i < 4; i++) {
            int ny = now.y + dy[i];
            int nx = now.x + dx[i];

            if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
            if (used[ny][nx]==1) continue;
            if (flag == 0 && map[ny][nx] != nowColor) continue;
            if (flag == 1 && map[ny][nx] != nowColor) {
                if (nowColor == 'B' || map[ny][nx] == 'B') continue;
            }
            used[ny][nx] = 1;
            q.push({ny, nx});
        }
    }
}

int main() {
    input();

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (used[i][j] == 0) {
                ans1++;
                bfs(i, j, 0);
            }
        }
    }

    memset(used, 0, sizeof(used));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (used[i][j] == 0) {
                ans2++;
                bfs(i, j, 1);
            }
        }
    }

    cout << ans1 << " " << ans2;

    return 0;
}