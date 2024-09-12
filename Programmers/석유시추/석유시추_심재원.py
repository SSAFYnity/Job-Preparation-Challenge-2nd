def solution(land):
    answer = 0
    n, m = len(land), len(land[0])
    visited = [[0] * m for _ in range(n)]
    oil = {}
    idx = 1
    for i in range(n):
        for j in range(m):
            if land[i][j] and not visited[i][j]:
                visited[i][j] = idx
                stack = [(i, j)]
                cnt = 1
                while stack:
                    r, c = stack.pop()
                    for dr, dc in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
                        nr, nc = r + dr, c + dc
                        if 0 <= nr < n and 0 <= nc < m:
                            if land[nr][nc] and not visited[nr][nc]:
                                stack.append((nr, nc))
                                visited[nr][nc] = idx
                                cnt += 1
                oil[idx] = cnt
                idx += 1
    
    for j in range(m):
        amount_of_oil = 0
        oil_visited = {}
        for i in range(n):
            if land[i][j] and not oil_visited.get(visited[i][j]):
                amount_of_oil += oil[visited[i][j]]
                oil_visited[visited[i][j]] = True
        answer = max(answer, amount_of_oil)
                
    return answer
