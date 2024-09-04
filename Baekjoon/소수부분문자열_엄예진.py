import sys

input = sys.stdin.readline

# 방법 1.
# 31120KB, 36ms

# def prime(temp):
#     for i in range(2, temp):
#         if temp % i == 0:
#             return False
#     return True


# 방법 2.
# 32684KB, 36ms

prime_lst = [1, 1] + ([0] * (100000))


def prime(temp):
    for i in range(2, int(temp**0.5) + 1):
        if prime_lst[i] == 0:
            prime_lst[i] = 1
            for j in range(i * 2, 100000, i):
                prime_lst[j] = 2
    if prime_lst[temp] != 2:
        return True
    else:
        return False


while True:
    origin = input().rstrip()

    if origin == "0":
        break
    else:
        answer = 0
        for i in range(len(origin)):
            for j in range(i + 1, len(origin) + 1):
                temp = origin[i:j]

                if len(temp) < 6 and prime(int(temp)):
                    answer = max(answer, int(temp))
    print(answer)
