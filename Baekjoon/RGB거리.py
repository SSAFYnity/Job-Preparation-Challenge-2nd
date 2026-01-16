N = int(input())
price = []

for _ in range(N):
    price.append(list(map(int, input().split())))

dp = [[0] * 3 for _ in range(N)]

dp[0][0] = price[0][0]
dp[0][1] = price[0][1]
dp[0][2] = price[0][2]

for i in range(1, N):
    dp[i][0] = min(dp[i-1][1], dp[i-1][2]) + price[i][0]
    dp[i][1] = min(dp[i-1][0], dp[i-1][2]) + price[i][1]
    dp[i][2] = min(dp[i-1][0], dp[i-1][1]) + price[i][2]

min_money = min(dp[N-1][0], dp[N-1][1], dp[N-1][2])

print(min_money)