import sys
import heapq

input = sys.stdin.readline
INF = float('inf')

n = int(input()) 
m = int(input()) 

graph = [[] for _ in range(n+1)]

for _ in range(m):
    u, v, w = map(int, input().split())
    graph[u].append((v, w))

start, end = map(int, input().split())

dist = [INF] * (n + 1)

def dijkstra(start):
    q = []
    heapq.heappush(q, (0, start))
    dist[start] = 0

    while q:
        d, now = heapq.heappop(q)

        if dist[now] < d:
            continue

        for next_node, weight in graph[now]:
            cost = d + weight

            if cost < dist[next_node]:
                dist[next_node] = cost
                heapq.heappush(q, (cost, next_node))

dijkstra(start)

print(dist[end])