#include <vector>

using namespace std;

int solution(int n, vector<int> cores) {
    int answer = 0;

    long long st = 0;
    long long en = 2e10;
    long long work, mid;
    while (st <= en) {
        mid = (st + en) / 2;
        work = cores.size();
        for (int i = 0; i < cores.size(); i++) {
            work += mid / cores[i];
            if (work >= n) break;
        }

        if (work >= n) en = mid - 1;
        else st = mid + 1; 
    }

    work = cores.size();
    for (int i = 0; i < cores.size(); i++) {
        work += en / cores[i];
    }

    for (int i = 0; i < cores.size(); i++) {
        if ((en + 1) % cores[i] == 0) work++;
        if (work == n) return i + 1;
    }
    
    return answer;
}