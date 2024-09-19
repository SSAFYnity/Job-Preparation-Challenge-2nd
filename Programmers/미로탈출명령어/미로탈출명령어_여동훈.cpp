#include <string>
#include <vector>
#include <algorithm>
using namespace std;

string answer = "";
int N, M, X, Y, R, C, K;
int dy[] = {1,0,0,-1};
int dx[] = {0,-1,1,0}; // dlru
string alph = "dlru";
vector<vector<int>> map;
int getdist(int ay,int ax, int by,int bx){
    return abs(ay-by) + abs(ax-bx);
}
void dfs(int depth, int ny, int nx, string tmp){
    if(answer != "") return;
    if(getdist(ny,nx,R,C) + depth > K) return;
    if(depth == K && ny == R && nx == C){
        if(answer == "") answer = tmp;
        return;
    }
    else if(depth == K) return;
    
    for(int i = 0 ; i< 4; i++){
        int nexty = ny + dy[i];
        int nextx = nx + dx[i];
        if(nexty >= N || nextx >= M || nexty < 0 || nextx < 0) continue;
        dfs(depth + 1, nexty, nextx, tmp + alph[i]);
    }
}

string solution(int n, int m, int x, int y, int r, int c, int k) {
    N = n; M= m; X = x-1; Y = y-1; R = r-1; C = c-1; K = k;
    if(abs(getdist(x, y, r, c) - k) % 2 != 0){
        answer = "impossible";
        return answer;
    }
    dfs(0,X,Y, "");
    if(answer == "") answer = "impossible";
    
    return answer;
}