import sys
input = sys.stdin.readline

N = int(input())
M = int(input())
dp = [1, 1]
vips = [0]

for _ in range(M):
    vips.append(int(input()))
vips.append(N+1)

dpIdx = [vips[idx] - vips[idx-1] - 1 for idx in range(1, len(vips))]

for idx in range(1, max(dpIdx)):
    dp.append(dp[idx]+dp[idx-1])

answer = 1
for idx in dpIdx:
    answer *= dp[idx]

print(answer)
