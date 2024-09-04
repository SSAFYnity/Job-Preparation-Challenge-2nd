def solution(n, left, right):
    answer = []
    for i in range(left, right+1):
        x, y = i %n, i // n

        value = max(x, y) +1

        answer.append(value)

    return answer

