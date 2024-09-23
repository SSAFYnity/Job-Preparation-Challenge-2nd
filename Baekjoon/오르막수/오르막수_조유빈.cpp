#include <iostream>

using namespace std;

#define MOD 10007

int n;
int dp[10];

void input() {
    cin >> n;
}

void solution() {
    for (int i = 0; i < 10; i++) dp[i] = 1;

    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < 10; j++) {
            for (int k = j + 1; k < 10; k++) {
                dp[j] += dp[k];
            }
            dp[j] %= MOD;
        }
    }

    int ans = 0;
    for (int i = 0; i < 10; i++) ans += dp[i];
    ans %= MOD;
    cout << ans;
}

int main() {
    input();
    solution();
}