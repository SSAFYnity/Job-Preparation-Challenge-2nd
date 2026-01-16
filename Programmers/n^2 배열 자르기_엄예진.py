def solution(n, left, right):
    answer = []
    for i in range(left, right + 1):
        quo = i // n
        re = i % n
        if quo < re:
            quo, re = re, quo
        answer.append(quo + 1)
    
    return answer