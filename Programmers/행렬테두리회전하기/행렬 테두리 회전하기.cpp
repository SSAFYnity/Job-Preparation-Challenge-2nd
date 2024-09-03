#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(int rows, int columns, vector<vector<int>> queries) {
    vector<int> answer;
    int matrix[rows+1][columns+1];
    set<int> res;
    for(int i=1;i<=rows;++i){
        for (int j = 1; j <= columns; ++j) {
            matrix[i][j]= columns*(i-1)+j;
        }
    }

    for (int i = 0; i < queries.size(); ++i) {
        vector<int> v;
        int rmin=queries[i][0],rmax=queries[i][2];
        int cmin=queries[i][1],cmax=queries[i][3];
        int prev,curr;

            prev=matrix[rmin+1][cmin];

            for (int j = cmin; j < cmax; ++j) {
                curr=matrix[rmin][j];
                v.push_back(prev);
                matrix[rmin][j]=prev;
                prev=curr;
            }
            for (int j = rmin; j < rmax; ++j) {
                curr=matrix[j][cmax];
                v.push_back(prev);
                matrix[j][cmax]=prev;
                prev=curr;
            }
            for (int j = cmax; j > cmin; j--) {
                curr=matrix[rmax][j];
                v.push_back(prev);
                matrix[rmax][j]=prev;
                prev=curr;
            }
            for (int j = rmax; j > rmin; j--) {
                curr=matrix[j][cmin];
                v.push_back(prev);
                matrix[j][cmin]=prev;
                prev=curr;
            }
        sort(v.begin(),v.end());
        answer.push_back(v[0]);
    }
   
    return answer;
}
