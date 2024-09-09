import sys

def is_prime(num):
    for i in range(2, int(num**0.5)):
        if num % i == 0:
            return False
    return True

prime_numbers = [True] * 100_001

for i in range(2, 100_001):
    if prime_numbers[i] != False and is_prime(i):
        for j in range(2, 100_001 // i):
            prime_numbers[i*j] = False

input = sys.stdin.readline

while True:
    num = input().strip()
    max_prime = 0
    if num == '0':
        break

    for i in range(1, 7):
        for j in range(len(num)+1-i):
            cur_num = int(num[j:i+j])
            if cur_num <= 100_000 and prime_numbers[cur_num]:
                max_prime = max(max_prime, cur_num)

    print(max_prime)
