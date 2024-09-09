import sys
input = sys.stdin.readline
from heapq import heappush, heappop

N = int(input())  # 1 <= N <= 100,000
minheap = []
maxheap = []
for i in range(N):
    num = int(input())  # -10,000 <= num <= 10,000
    if i % 2:
        heappush(minheap, num)
        if num < -maxheap[0]:
            heappush(minheap, -heappop(maxheap))
            heappush(maxheap, -heappop(minheap))
    else:
        heappush(maxheap, -num)
        if minheap and num > minheap[0]:
            heappush(minheap, -heappop(maxheap))
            heappush(maxheap, -heappop(minheap))

    print(-maxheap[0])
