#include <string>
#include <vector>

using namespace std;

vector<int> solution(int n, long long left, long long right) {
    vector<int> answer;

    // 1. n행 n열 2차원 배열
    vector<vector<int>> board;

    // 2. i행 i열까지 i로 채우기
    for (int i = 1; i <= n; i++) {
        int value = i; // 채울 값
        vector<int> v; // 임시 vector
        for (int j = 1; j <= n; j++) {
            // i행i열을 넘어가면 value 증가
            if (j > i) value++;

            v.push_back(value);
        }
        board.push_back(v);
    }

    int rowStart = left / n; // left가 있는 행 위치
    int rowEnd = right / n; // right가 있는 행 위치

    for (int i = rowStart; i <= rowEnd; i++) {
        if (i == rowStart) {
            for (int pos = left - n * rowStart; pos < n; pos++)
                answer.push_back(board[i][pos]);
        }
        else if (i == rowEnd) {
            for (int pos = 0; pos <= right - n * rowEnd; pos++)
                answer.push_back(board[i][pos]);
        }
        else {
            answer.insert(answer.end(), board[i].begin(), board[i].end());
        }
    }

    return answer;
}