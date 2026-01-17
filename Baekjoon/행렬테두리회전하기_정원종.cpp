#include <string>
#include <vector>

using namespace std;

int map[110][110];

int min(int A, int B) { return A < B ? A : B; }


int Turning(int x, int y, int x2, int y2)
{
  int min_num;
  int start = map[x][y];
  min_num = start;
  for (int i = x; i < x2; i++)
  {
    min_num = min(min_num, map[i][y]);
    map[i][y] = map[i + 1][y];
  }
  for (int i = y; i < y2; i++)
  {
    min_num = min(min_num, map[x2][i]);
    map[x2][i] = map[x2][i + 1];
  }
  for (int i = x2; i > x; i--)
  {
    min_num = min(min_num, map[i][y2]);
    map[i][y2] = map[i - 1][y2];
  }
  for (int i = y2; i > y; i--)
  {
    min_num = min(min_num, map[x][i]);
    map[x][i] = map[x][i - 1];
  }
  map[x][y + 1] = start;
  return min_num;
}

vector<int> solution(int rows, int columns, vector<vector<int>> queries) 
{
  vector<int> ans;
  int num = 1;

  for (int i = 0; i < rows; i++)
  {
    for (int j = 0; j < columns; j++)
    {
      map[i][j] = num++;
    }
  }
  
  for (int i = 0; i < queries.size(); i++)
  {
    int x1 = queries[i][0]; x1--;
    int y1 = queries[i][1]; y1--;
    int x2 = queries[i][2]; x2--;
    int y2 = queries[i][3]; y2--;

    ans.push_back(Turning(x1, y1, x2, y2));
  }

  return ans;
}