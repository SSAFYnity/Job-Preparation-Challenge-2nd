from collections import deque

N, M = map(int, input().split())
board = [list(map(int, input())) for _ in range(N)]
visited = [[0] * M for _ in range(N)]

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

q = deque()
q.append((0, 0))
visited[0][0] = 1
while q:
    r, c = q.popleft()
    for k in range(4):
        nr = r + dr[k]
        nc = c + dc[k]
        if 0 <= nr < N and 0 <= nc < M:
            if board[nr][nc] == 1 and visited[nr][nc] == 0:
                q.append((nr, nc))
                visited[nr][nc] = visited[r][c] + 1

print(visited[N-1][M-1])