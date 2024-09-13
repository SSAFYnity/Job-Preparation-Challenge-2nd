# 학생 번호를 하나 받고 각 칸마다 인접 4방향에 좋아하는 학생 번호 몇개 있는지, 몇개 비어있는지 확인
# 최대값이랑 비교하면서 최대값이랑 같거나 큰거만 남겨두기
# 개수 다 찾고 정렬해서 위치 정하기

N = int(input())
board = [[0] * N for _ in range(N)]
likes_info = {}
seat_info = {}

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

# 자리 정하기
for _ in range(N**2):
    student, *likes = map(int, input().split())
    able_position = [] # 학생이 앉을 수 있는 자리 후보
    likes_info[student] = likes

    # 모든 칸을 살펴보면서 각 칸의 상하좌우에 좋아하는 학생 번호 있는지 확인
    for i in range(N):
        for j in range(N):
            if board[i][j] == 0:
                like_cnt = 0 # board[i][j] 상하좌우에 좋아하는 학생 수
                blank_cnt = 0 # board[i][j] 상하좌우에 비어있는 자리 수
                for k in range(4):
                    nr = i + dr[k]
                    nc = j + dc[k]
                    if 0 <= nr < N and 0 <= nc < N:
                        # 근처에 좋아하는 학생 있으면
                        if board[nr][nc] in likes:
                            like_cnt += 1
                        # 근처에 빈자리 있으면
                        if board[nr][nc] == 0:
                            blank_cnt += 1
                able_position.append([like_cnt, blank_cnt, i, j])
    # 현재 학생이 앉을 수 있는 자리를 like_cnt, blank_cnt는 내림차순, i, j는 오름차순으로 정렬
    able_position.sort(key=lambda x : (-x[0], -x[1], x[2], x[3]))

    # 조건 다 따졌을때 우선순위가 제일 높은 위치로 자리 지정
    board[able_position[0][2]][able_position[0][3]] = student

ans = 0

# 만족도 구하기
for i in range(N):
    for j in range(N):
        cnt = 0
        for k in range(4):
            nr = i + dr[k]
            nc = j + dc[k]
            if 0 <= nr < N and 0 <= nc < N:
                if board[nr][nc] in likes_info[board[i][j]]:
                    cnt += 1
        if cnt == 1:
            ans += 1
        elif cnt == 2:
            ans += 10
        elif cnt == 3:
            ans += 100
        elif cnt == 4:
            ans += 1000
print(ans)


'''
3
4 2 5 1 7
3 1 9 4 5
9 8 1 2 3
8 1 9 3 4
7 2 3 4 8
1 9 2 5 7
6 5 2 3 4
5 1 9 2 8
2 9 3 1 4
'''