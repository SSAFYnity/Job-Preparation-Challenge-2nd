import sys
input = sys.stdin.readline
N = int(input().rstrip())
M = int(input().rstrip())
vip = [int(input().rstrip()) for _ in range(M)]

dp = [0] * 41

dp[0] = 1
dp[1] = 1 # 1
dp[2] = 2 # 1 2, 2 1
# dp[3] = 3 # 1 2 3, 2 1 3, 1 3 2

# dp[i] = dp[i-1](자리를 안 바꾼 경우) + dp[i-2](자리 바꾼 경우)
for i in range(3, N+1):
    dp[i] = dp[i - 1] + dp[i - 2]

answer = 1

if M > 0:
    pre = 0
    # 반복문을 통해 vip 사이에 그룹에 들어가는 경우의 수를 확인
    for j in range(M):
        answer *= dp[vip[j] - 1 - pre]
        pre = vip[j]
    answer *= dp[N - pre]
else:
    answer = dp[N]
print(answer)

