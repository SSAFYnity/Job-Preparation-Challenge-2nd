import math

# 소수 판별 함수
def is_prime(check):
    if check == 0:
        return False
    if check == 1:
        return False
    if check == 2:
        return True
    n = int(math.sqrt(check)) + 1
    for i in range(2, n):
        if (check % i == 0):
            return False
    return True

while True:
    number = input().strip()
    ans = 0
    if number == '0':
        break
    l = len(number)
    for i in range(l):
        cnt = 1
        for j in range(i, l):
            # 소수 범위 넘어가면 잴 필요 없음
            if (cnt > 5):
                break
            cnt += 1
            check = int(number[i:j+1])
            if (is_prime(check)):
                if (ans < check):
                    ans = check
    print(ans)