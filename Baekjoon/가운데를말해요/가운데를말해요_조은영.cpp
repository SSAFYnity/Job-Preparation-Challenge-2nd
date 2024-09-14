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

		// ũ�Ⱑ ���ٸ� �ִ����� ���� �ֱ�
		// �׷��� ������ �ּ����� �ֱ�
		// �ִ� ���� top ���� �ּ� ���� top���� ũ�� ��ȯ

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