import sys
input = sys.stdin.readline

dt = [(1, 0), (-1, 0), (0, 1), (0, -1)]


def nGR(r, c):
    s = [(r, c)]

    while s:
        r, c = s.pop()
        visited1[r][c] = 1
        for dr, dc in dt:
            nr, nc = r + dr, c + dc
            if 0 <= nr < n and 0 <= nc < n:
                if not visited1[nr][nc] and pic[r][c] == pic[nr][nc]:
                    s.append((nr, nc))


def GR(r, c):
    s = [(r, c)]

    while s:
        r, c = s.pop()
        visited2[r][c] = 1
        for dr, dc in dt:
            nr, nc = r + dr, c + dc
            if 0 <= nr < n and 0 <= nc < n and not visited2[nr][nc]:
                if pic[r][c] == pic[nr][nc]:
                    s.append((nr, nc))
                elif pic[r][c] == 'G' and pic[nr][nc] == 'R':
                    s.append((nr, nc))
                elif pic[r][c] == 'R' and pic[nr][nc] == 'G':
                    s.append((nr, nc))


n = int(input())
pic = [input().rstrip() for _ in range(n)]
ngr, gr = 0, 0
visited1 = [[0] * n for _ in range(n)]
visited2 = [[0] * n for _ in range(n)]
for i in range(n):
    for j in range(n):
        if not visited1[i][j]:
            nGR(i, j)
            ngr += 1
        if not visited2[i][j]:
            GR(i, j)
            gr += 1

print(ngr, gr)
