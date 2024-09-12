def solution(n, left, right):
    answer = []
    start = left // n + 1
    cnt = 0
    for i in range(start, n+1):
        j_start = left % n + 1 if i == start else 1
        for j in range(j_start, n+1):
            if i >= j:
                answer.append(i)
            else:
                answer.append(j)
            cnt += 1
            if cnt == right - left + 1:
                break
        else:
            continue
        break
                
    return answer
