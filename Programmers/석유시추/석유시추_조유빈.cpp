#include <string>
#include <vector>
#include <cstring>
#include <algorithm>

using namespace std;

int M, N;
int Map[501][501];
int Visit[501][501];

int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

int Area = 0;
int Check[501];
int Ans[501];


void Dfs(int x, int y) {
    for(int i = 0; i < 4; i++)
    {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if(nx < 1 || ny < 1 || nx > N || ny > M) continue;
        if(Visit[nx][ny] == 1) continue;
        if(Map[nx][ny] == 0) continue;

        if(Check[ny] == 0)
            Check[ny] = 1;

        Visit[nx][ny] = 1;
        Area++;
        Dfs(nx, ny);
    }
}



int solution(vector<vector<int>> land) {
    N = land.size();
    M = land[0].size();

    for(int i = 1; i <= N; i++)
        for(int j = 1; j <= M; j++)
            Map[i][j] = land[i - 1][j - 1];

    for(int i = 1; i <= M; i++)
    {
        for(int j = 1; j <= N; j++)
        {
            if(Map[j][i] == 0) continue;
            if(Visit[j][i] == 1) continue;

            Visit[j][i] = 1;
            Check[i] = 1;
            Area++;

            Dfs(j, i);

            for(int k = 1; k <= M; k++)
            {
                if(Check[k] == 1)
                {
                    Check[k] = 0;
                    Ans[k] += Area;
                }
            }

            Area = 0; 
        }
    }

    int Max = -1;
    for(int i = 1; i <= M; i++)
    {
        Max = max(Max, Ans[i]);
    }
    return Max;
}
