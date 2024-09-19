import sys
input = sys.stdin.readline
from collections import deque

N, M = map(int, input().split())
map = [list(map(int, input().strip())) for _ in range(N)]

visited = [[0] * M for _ in range(N)]

q = deque()
q.append((0, 0))
visited[0][0] = 1

while q:
    r, c = q.popleft()
    if r == N-1 and c == M-1:
        print(visited[N-1][M-1])
        break

    for dr, dc in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
        nr, nc = r + dr, c + dc
        if 0 <= nr < N and 0 <= nc < M:
            if map[nr][nc] and not visited[nr][nc]:
                q.append((nr, nc))
                visited[nr][nc] = visited[r][c] + 1
