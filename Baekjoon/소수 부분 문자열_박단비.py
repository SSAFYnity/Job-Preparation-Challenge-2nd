# 소수를 미리 다 찾아놓음
# 소수 돌아가면서 테스트케이스에 포함되어 있는지 확인
# 제일 마지막꺼로 출력

sosu = []
for i in range(2, 100001):
    # possible = True
    for j in range(2, int(i**0.5) + 1):
        if i % j == 0:
            break
    else:
        sosu.append(i)

while True:
    line = input()
    if line == '0':
        break
    ans = ''
    for s in sosu:
        if str(s) in line:
            ans = s
    print(ans)