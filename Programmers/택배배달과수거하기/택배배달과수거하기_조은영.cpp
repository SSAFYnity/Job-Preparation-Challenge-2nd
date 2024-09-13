#include <string>
#include <vector>

using namespace std;

long long solution(int cap, int n, vector<int> deliveries, vector<int> pickups) {
    long long answer = 0;
    int deliverSum = 0, pickupSum = 0;

    for (int i = n - 1; i >= 0; i--) {
        int cnt = 0; // �պ� Ƚ��
        deliverSum += deliveries[i];
        pickupSum += pickups[i];

        // ���� �� �� �ϳ��� ���������� �湮
        while (deliverSum > 0 || pickupSum > 0) {
            deliverSum -= cap;
            pickupSum -= cap;
            cnt++;
        }
        answer += (i + 1) * cnt * 2; // �պ��̹Ƿ� *2
    }


    return answer;
}