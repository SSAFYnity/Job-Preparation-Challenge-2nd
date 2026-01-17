#include <iostream>
#include <vector>
#include <queue>
#define MAX 1e9
using namespace std;

int dist[1001];
vector<pair<int,int>>v[1001];

void dijkstra(int start) {
	priority_queue<pair<int, int>>pq;

	pq.push({ 0, start });

	while (!pq.empty()) {
		int cost = -pq.top().first;
		int node = pq.top().second;
		pq.pop();

		if (dist[node] < cost) continue;

		for (int i = 0; i < v[node].size(); i++) {
			int nextCost = v[node][i].second + cost; // 다음 도시까지의 비용
			int nextNode = v[node][i].first; 

			if (dist[nextNode] > nextCost) {
				dist[nextNode] = nextCost;
				pq.push({ -nextCost, nextNode });
			}
		}
	}
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int N = 0, M = 0, A=0, B=0, from=0, to=0, cost=0;

	cin >> N;
	cin >> M;

	fill(dist, dist + N+1, MAX);
	
	for (int i = 0; i < M; i++) {
		cin >> from >> to >> cost;
		v[from].push_back({ to, cost });
	}

	cin >> A >> B;

	dijkstra(A);

	cout << dist[B] << "\n";

	return 0;
}