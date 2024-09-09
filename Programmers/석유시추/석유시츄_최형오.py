import sys
sys.setrecursionlimit(10**6)  # 재귀 제한을 100만으로 설정


from collections import deque

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def solution(land):
    answer = []

    oil = []
    count = 0

    n = len(land)
    m = len(land[0])

    visited = [[0 for _ in range(m)] for _ in range(n)]

    def dfs(ry, rx, l):
        nonlocal count
        if rx < 0 or rx >= m or ry < 0 or ry >= n: return
        if land[ry][rx] == 1 and visited[ry][rx] == 0:
            visited[ry][rx] = l
            count += 1
            for i in range(4):
                nx = rx + dx[i]
                ny = ry + dy[i]
                dfs(ny, nx, l)

    for i in range(n):
        for j in range(m):
            if land[i][j] == 1 and visited[i][j] == 0:
                l = len(oil) + 1
                dfs(i, j , l)
                oil.append(count)
                count = 0

    for j in range(m):
        result = set()
        for i in range(n):
            result.add(visited[i][j])
        result = list(result)
        for j in range(len(result)):
            if result[j] > 0: result[j] = oil[result[j]-1]
        answer.append(sum(result))

    return max(answer)

solution([[0, 0, 0, 1, 1, 1, 0, 0], [0, 0, 0, 0, 1, 1, 0, 0], [1, 1, 0, 0, 0, 1, 1, 0], [1, 1, 1, 0, 0, 0, 0, 0], [1, 1, 1, 0, 0, 0, 1, 1]])