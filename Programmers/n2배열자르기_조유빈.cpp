#include <string>
#include <vector>

using namespace std;

vector<int> solution(int n, long long left, long long right) {
    vector<int> answer;
    
    int x = 0;
    int y = 0;
    
    for (long long i = left; i < right + 1; i++) {
        x = i / n;
        y = i % n;
        
        int ans = (x > y) ? (x + 1) : (y + 1);
        answer.push_back(ans);
    }
    
    return answer;
}