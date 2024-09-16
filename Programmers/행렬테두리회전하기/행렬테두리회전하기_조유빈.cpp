#include <string>
#include <vector>

using namespace std;

int map[101][101];

int getMin(int a, int b) {
    return a < b ? a : b;
}

int rotate(int x1, int y1, int x2, int y2) {
    int min;
    int tmp = map[x1][y1];
    min = tmp;

    for (int i = x1; i < x2; i++) {
        min = getMin(min, map[i][y1]);
        map[i][y1] = map[i + 1][y1];
    }
    for (int i = y1; i < y2; i++) {
        min = getMin(min, map[x2][i]);
        map[x2][i] = map[x2][i + 1];
    }
    for (int i = x2; i > x1; i--) {
        min = getMin(min, map[i][y2]);
        map[i][y2] = map[i - 1][y2];
    }
    for (int i = y2; i > y1; i--) {
        min = getMin(min, map[x1][i]);
        map[x1][i] = map[x1][i - 1];
    }
    map[x1][y1 + 1] = tmp;
    return min;
}

vector<int> solution(int rows, int columns, vector<vector<int>> queries) {
    vector<int> answer;

    int num = 1;
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns; j++) {
            map[i][j] = num++;
        }
    }

    for (int i = 0; i < queries.size(); i++) {
        int x1 = queries[i][0] - 1;
        int y1 = queries[i][1] - 1;
        int x2 = queries[i][2] - 1;
        int y2 = queries[i][3] - 1;

        answer.push_back(rotate(x1, y1, x2, y2));
    }

    return answer;
}