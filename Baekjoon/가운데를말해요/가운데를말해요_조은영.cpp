#include <iostream>
#include <queue>
using namespace std;

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int N = 0;
	int input = 0;

	priority_queue<int> maxHeap;
	priority_queue<int, vector<int>, greater<int>> minHeap;

	cin >> N;

	for (int i = 0; i < N; i++) {
		cin >> input;

		// 크기가 같다면 최대힙에 먼저 넣기
		// 그렇지 않으면 최소힙에 넣기
		// 최대 힙의 top 값이 최소 힙의 top보다 크면 교환

		if (maxHeap.size() == minHeap.size()) {
			maxHeap.push(input);
		}
		else {
			minHeap.push(input);
		}
		if (!maxHeap.empty() && !minHeap.empty()) {
			if (maxHeap.top() > minHeap.top()) {
				int maxTop = maxHeap.top();
				maxHeap.pop();
				int minTop = minHeap.top();
				minHeap.pop();
				maxHeap.push(minTop);
				minHeap.push(maxTop);
			}
		
		}
		
		cout << maxHeap.top() << "\n";
	}

	

	return 0;
}