#include <vector>
#include <algorithm>
using namespace std;

vector<int> solution(int n, long long left, long long right) {
  vector<int> ans;
    long long int x, y;

    for (long long i = left; i <= right; ++i) {
        int x = i / n;
        int y = i % n;
        ans.push_back(max(x, y) + 1);
    }
    
    return ans;
}