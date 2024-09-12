import sys
sys.setrecursionlimit(1000000)
input = sys.stdin.readline

N = int(input())

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

arr = []

f_cnt, s_cnt = 0 , 0

for _ in range(N):
    arr.append(list(input()))


visited = [[False for _ in range(N)] for _ in range(N)]

def bfs(x, y):
    if x < 0 or x >= N or y < 0 or y >= N: return
    n_Color = arr[x][y]
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if (0 <= nx < N) and (0 <= ny < N):
            if arr[nx][ny] == n_Color and visited[nx][ny] == False:
                visited[nx][ny] = True
                bfs(nx, ny)

for i in range(N):
    for j in range(N):
        if visited[i][j] == False:
            bfs(i, j)
            f_cnt += 1

for i in range(N):
    for j in range(N):
        if arr[i][j]=='R':
            arr[i][j]='G'

visited = [[0 for _ in range(N)] for _ in range(N)]

for i in range(N):
    for j in range(N):
        if visited[i][j] == False:
            bfs(i, j)
            s_cnt += 1


print(f_cnt, s_cnt)