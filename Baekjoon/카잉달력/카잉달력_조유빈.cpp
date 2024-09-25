#include <iostream>

using namespace std;

int t, m, n, x, y, k;

void input() {
    cin >> m >> n >> x >> y;
}

void solution() {
    while (true) {
        if (x == y) {
            k = x;
            break;
        }
        else if (x > m * n) {
            break;
        }

        if (x > y) {
            y += n;
        }
        else {
            x += m;
        }
    }
    cout << k << "\n";
}

int main() {
    cin >> t;
    for (int i = 0; i < t; i++) {
        k = -1;
        input();
        solution();
    }
    return 0;
}