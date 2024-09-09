def solution(n, left, right):
    arr = []
    # 시작과 끝 열과 행 설정
    sr = left // n
    er = right // n
    sc = left % n
    ec = right % n
    # 규칙에 맞는 행과 열을 삽입하기
    for r in range(sr, er + 1):
        c = 0
        if r == sr:
            c = sc
        if r == er:
            n = ec + 1
        while c < n:
            arr.append(max(r, c) + 1)
            c += 1
    return arr
