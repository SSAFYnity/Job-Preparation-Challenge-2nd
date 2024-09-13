import sys
input = sys.stdin.readline

N = int(input())
classroom = [[0] * (N+1) for _ in range(N+1)]
students = {}
for _ in range(N**2):
    student_and_favorites = list(map(int, input().split()))
    student = student_and_favorites[0]
    favorites = student_and_favorites[1:]
    students[student] = favorites
    cur_space = [0, 0, 0, 0] # r, c, favorite_cnt, blank_cnt
    for r in range(1, N+1):
        for c in range(1, N+1):
            favorites_cnt, blank_cnt = 0, 0
            if not classroom[r][c]:
                for dr, dc in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
                    nr, nc = r + dr, c + dc
                    if 0 < nr <= N and 0 < nc <= N:
                        if classroom[nr][nc] in favorites:
                            favorites_cnt += 1
                        elif not classroom[nr][nc]:
                            blank_cnt += 1
                if cur_space[0] == 0 and cur_space[1] == 0:
                    cur_space = [r, c, favorites_cnt, blank_cnt]
                elif cur_space[2] < favorites_cnt:
                    cur_space = [r, c, favorites_cnt, blank_cnt]
                elif cur_space[2] == favorites_cnt and cur_space[3] < blank_cnt:
                    cur_space = [r, c, favorites_cnt, blank_cnt]

    classroom[cur_space[0]][cur_space[1]] = student


satisfaction_table = {0: 0, 1: 1, 2: 10, 3: 100, 4: 1000}
satisfaction = 0
for r in range(1, N+1):
    for c in range(1, N+1):
        student = classroom[r][c]
        favorites_cnt = 0
        for dr, dc in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
            nr, nc = r + dr, c + dc
            if 0 < nr <= N and 0 < nc <= N:
                if classroom[nr][nc] in students[student]:
                    favorites_cnt += 1
        satisfaction += satisfaction_table[favorites_cnt]

print(satisfaction)
