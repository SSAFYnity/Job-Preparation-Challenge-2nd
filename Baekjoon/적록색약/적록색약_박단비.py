from collections import deque

N = int(input())
board = [list(input()) for _ in range(N)]

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

normal_cnt = 0
special_cnt = 0

def normal_bfs(r, c, color):
    q = deque()
    q.append((r, c))
    visited[r][c] = 1
    while q:
        r, c = q.popleft()
        for k in range(4):
            nr = r + dr[k]
            nc = c + dc[k]
            if 0 <= nr < N and 0 <= nc < N:
                if visited[nr][nc] == 0 and board[nr][nc] == color:
                    q.append((nr, nc))
                    visited[nr][nc] = 1

def special_bfs(r, c, color):
    q = deque()
    q.append((r, c))
    visited[r][c] = 1
    while q:
        r, c = q.popleft()
        for k in range(4):
            nr = r + dr[k]
            nc = c + dc[k]
            if 0 <= nr < N and 0 <= nc < N and visited[nr][nc] == 0:
                if color == 'R' or color == 'G':
                    if board[nr][nc] == 'R' or board[nr][nc] == 'G':
                        q.append((nr, nc))
                        visited[nr][nc] = 1
                elif color == 'B':
                    if board[nr][nc] == 'B':
                        q.append((nr, nc))
                        visited[nr][nc] = 1


visited = [[0] * N for _ in range(N)]
for i in range(N):
    for j in range(N):
        if visited[i][j] == 0:
            normal_cnt += 1
            normal_bfs(i, j, board[i][j])

visited = [[0] * N for _ in range(N)]
for i in range(N):
    for j in range(N):
        if visited[i][j] == 0:
            special_cnt += 1
            special_bfs(i, j, board[i][j])

print(normal_cnt, special_cnt)