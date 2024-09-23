#include <iostream>
#include <algorithm>

using namespace std;

int n, m;
int dp[41];
int isFixed[41] = { 0, };
long long ans = 1;

void init() {
    fill(dp, dp + 41, 1);
}

void input() {
    cin >> n >> m;
    for (int i = 0; i < m; i++) {
        int tmp;
        cin >> tmp;
        isFixed[tmp] = 1;
    }
}

void solution() {
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i - 2] + dp[i - 1];
    }

    int idx = 1;
    int cnt = 0;
    while (true) {
        if (idx > n) break;

        cnt = 0;
        while (!isFixed[idx] && idx <= n) {
            idx++;
            cnt++;
        }
        ans *= dp[cnt];
        idx++;
    }

    cout << ans;
}

int main() {
    init();
    input();
    solution();
    return 0;
}