#include <iostream>
#include <queue>
#include <vector>
using namespace std;

int N, M; // 도시 갯수 , 버스 갯수
struct info {
    int end, cost;
};
vector<info> bus[1001];
int start_city, end_city;

int visited[1001];
vector<int> dist(1001, INT_MAX); // Stores the minimum distance from the source

void init() {
    cin >> N >> M;
    for (int i = 0; i < M; i++) {
        int s, e, cost;
        cin >> s >> e >> cost;
        bus[s].push_back({ e,cost });
    }
    cin >> start_city >> end_city;
}

void dijkstra(int start) {
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    pq.push({ 0, start }); // Start with the source node and distance 0
    dist[start] = 0;

    while (!pq.empty()) {
        int curNode = pq.top().second;
        pq.pop();

        if (visited[curNode]) continue;
        visited[curNode] = 1;

        for (const info& edge : bus[curNode]) {
            int nextNode = edge.end;
            int newDist = dist[curNode] + edge.cost;
            if (newDist < dist[nextNode]) {
                dist[nextNode] = newDist;
                pq.push({ newDist, nextNode });
            }
        }
    }
}

int main() {
    init();
    dijkstra(start_city);
    cout << dist[end_city] << endl;

    return 0;
}