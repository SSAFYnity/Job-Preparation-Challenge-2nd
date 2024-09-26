# 문제를 바로 이해하지 못함.
# M이 5, N이 4일 경우
# (day )  x : y
# (day1) 1 : 1
# (day2) 2 : 2
# (day3) 3 : 3
# (day4) 4 : 4
# (day5) 5 : 1
# (day6) 1 : 2
# (day7) 2 : 3
# (day8) 3 : 4
# (day9) 4 : 1


import sys
input = sys.stdin.readline

def calculate(M, N, X, Y):
    k = X # k를 X로 초기화
    while k <= M * N: # K의 범위는 M * N을 넘을 수 없음
        if (k - X) % M == 0 and (k - Y) % N == 0:
            return k
        k += M
    return -1

T = int(input())
for _ in range(T):
    M, N, x, y = map(int, input().split())
    print(calculate(M, N, x, y))