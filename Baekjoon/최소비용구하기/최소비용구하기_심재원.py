import sys
input = sys.stdin.readline
import heapq

N = int(input())
M = int(input())

graph = [[] for _ in range(N+1)]
for _ in range(M):
    depart, arrive, cost = map(int, input().split())
    graph[depart].append((arrive, cost))

A, B = map(int, input().split())

INF = 1e10
costs = [INF] * (N+1)
costs[A] = 0
heap = []
heapq.heappush(heap, (costs[A], A))
while heap:
    cur_cost, depart = heapq.heappop(heap)
    if costs[depart] < cur_cost:
        continue
    for arrive, cost in graph[depart]:
        new_cost = cost + cur_cost
        if costs[arrive] > new_cost:
            costs[arrive] = new_cost
            heapq.heappush(heap, (new_cost, arrive))

print(costs[B])
