for tc in range(1, int(input())+1):
    n, k = map(int, input().split())

    result = []

    for i in range(n, 0, -1):
        if k % i == 0:
            result.append(i)
            k //= i
    
    anser = 1
    for num in result:
        anser *= num

    print(f"#{tc} {anser}")
