N = int(input())
costs = [list(map(int, input().split())) for _ in range(N)]

for i in range(1, N):
    for j in range(3):
        costs[i][j] += min(costs[i-1][(j-1) % 3], costs[i-1][(j+1) % 3])

print(min(costs[N-1]))
