from heapq import heappush, heappop

import sys
input = sys.stdin.readline

N = int(input()) # 도시 개수
M = int(input()) # 버스 개수
graph = [[] for _ in range(N+1)]
for _ in range(M):
    v1, v2, cost = map(int, input().split())
    graph[v1].append((v2, cost))

start, end = map(int, input().split())

# 다익스트라
costs = [1e9] * (N+1)
heap = []

costs[start] = 0
heappush(heap, [0, start])

while heap:
    cur_cost, cur_v = heappop(heap)
    if costs[cur_v] >= cur_cost:
        for next_v, next_cost in graph[cur_v]:
            sum_cost = cur_cost + next_cost
            if sum_cost < costs[next_v]:
                costs[next_v] = sum_cost
                heappush(heap, [costs[next_v], next_v])
print(costs[end])