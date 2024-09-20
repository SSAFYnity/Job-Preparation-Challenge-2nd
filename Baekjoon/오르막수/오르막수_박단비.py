# DP인것 같음
# dp[i][j] : i자리 수 중에 j로 끝나는 수 개수
N = int(input())
dp = [[0] * 10 for _ in range(N+1)]

for i in range(10):
    dp[1][i] = 1
if N >= 2:
    for i in range(2, N+1):
        for j in range(10):
            dp[i][j] = sum(dp[i-1][:j+1]) % 10007
print(sum(dp[N]) % 10007)