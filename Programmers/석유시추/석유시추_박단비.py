from collections import deque


def solution(land):
    answer = 0
    n = len(land)
    m = len(land[0])
    # 일단 bfs를 돌아서 각 덩어리 개수를 세는데
    # bfs 돌때 나온 열을 set에 넣어놨다가 bfs 다 돌고는 열을 키로 하는 딕셔너리에 덩어리 수 기록해놓기?

    dict = {}

    dr = [-1, 1, 0, 0]
    dc = [0, 0, -1, 1]

    visited = [[0] * m for _ in range(n)]

    def bfs(r, c):
        q = deque()
        q.append((r, c))
        visited[r][c] = 1
        cnt = 1
        c_set = set()
        c_set.add(c)
        while q:
            r, c = q.popleft()
            for k in range(4):
                nr = r + dr[k]
                nc = c + dc[k]
                if 0 <= nr < n and 0 <= nc < m:
                    if visited[nr][nc] == 0 and land[nr][nc] == 1:
                        q.append((nr, nc))
                        c_set.add(nc)
                        visited[nr][nc] = 1
                        cnt += 1
        for idx in c_set:
            if idx not in dict:
                dict[idx] = [cnt]
            else:
                dict[idx].append(cnt)

    for i in range(n):
        for j in range(m):
            if visited[i][j] == 0 and land[i][j] == 1:
                bfs(i, j)

    for k, v in dict.items():
        answer = max(answer, sum(v))

    return answer