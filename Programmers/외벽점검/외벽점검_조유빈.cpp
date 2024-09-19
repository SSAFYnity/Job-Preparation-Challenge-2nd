#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int answer = 2e9;
bool selected[8];
vector<int> v;

bool cmp(int a, int b) {
    if (a > b) return true;
    return false; 
}

void check(int n, vector<int> weak, vector<int> dist) {
    for (int i = 0; i < weak.size() - 1; i++) {
        int idx = 0;
        int cnt;
        bool flag = false;

        for (int j = 0; (j < v.size()) && (j + 1 < answer) && (flag == false); j++) {
            int end = weak[idx] + dist[v[j]];
            while (end >= weak[idx]) {
                idx++;
                if (idx == weak.size()) {
                    cnt = j + 1;
                    flag = true;
                    break;
                }
            }
        }
        if (flag == true) answer = min(answer, cnt);

        int startValue = weak[0];
        for (int j = 1; j < weak.size(); j++) {
            weak[j - 1] = weak[j];
        }
        weak[weak.size() - 1] = startValue + n;
    }
}

void dfs(int cnt, int n, vector<int> weak, vector<int> dist) {
    if (cnt == dist.size()) {
        check(n, weak, dist);
        return;
    }

    for (int i = 0; i < dist.size(); i++) {
        if (selected[i] == true) continue;
        selected[i] = true;
        v.push_back(i);
        dfs(cnt + 1, n, weak, dist);
        v.pop_back();
        selected[i] = false;
    }
}

int solution(int n, vector<int> weak, vector<int> dist) {
    answer = 2e9;
    sort(dist.begin(), dist.end(), cmp);
    vector<int> tmp = weak;

    int l = tmp.size();
    for (int i = 0; i < l - 1; i++) { // 왜 size - 1 일가
        int start = tmp[0];
        int end = tmp[l - 1];
        if (end - start <= dist[0]) return 1;

        int startValue = tmp[0];
        for (int j = 1; j < l; j++) {
            tmp[j - 1] = tmp[j];
        }
        tmp[l - 1] = startValue + n;
    }

    dfs(0, n, weak, dist);
    if (answer == 2e9) return -1;
    return answer;
}