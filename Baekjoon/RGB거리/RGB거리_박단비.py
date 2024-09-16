import sys
input = sys.stdin.readline

N = int(input())
houses = [list(map(int, input().split())) for _ in range(N)]

# dp[i][j] : i번 집을 j색으로 칠 했을때 1번 ~ i번집을 칠하는 비용의 최소 비용
# j : 0 -> 빨강, j : 1 -> 초록, j : 2 -> 파랑

dp = [[0] * 3 for _ in range(N)]
for i in range(3):
    dp[0][i] = houses[0][i]

for i in range(1, N):
    dp[i][0] = min(dp[i - 1][1], dp[i - 1][2]) + houses[i][0]
    dp[i][1] = min(dp[i - 1][0], dp[i - 1][2]) + houses[i][1]
    dp[i][2] = min(dp[i - 1][0], dp[i - 1][1]) + houses[i][2]
print(min(dp[N-1]))