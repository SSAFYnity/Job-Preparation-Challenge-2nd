#include <iostream>
#include <cstring>
#include <algorithm>

using namespace std;

int main() {
	int N;
	int map[1001][3];
	int cost[3];

	memset(map, 0, sizeof(map));
	cin >> N;

	for (int i = 1; i <= N; i++) {
		cin >> cost[0] >> cost[1] >> cost[2];
		map[i][0] = min(map[i - 1][1], map[i - 1][2]) + cost[0];
		map[i][1] = min(map[i - 1][0], map[i - 1][2]) + cost[1];
		map[i][2] = min(map[i - 1][0], map[i - 1][1]) + cost[2];
	}
	cout << min(min(map[N][0], map[N][1]), map[N][2]);

	return 0;
}