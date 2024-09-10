def solution(cap, n, deliveries, pickups):
    answer = 0
    deli = 0
    pick = 0

    for i in range(n-1, -1, -1):
        
        cnt = 0
        deli += deliveries[i]
        pick += pickups[i]

        while(deli > 0 or pick > 0 ):
            deli -= cap
            pick -= cap
            cnt += 1
        
        answer += (i+1) * 2 * cnt

    return answer

solution(2, 7, [1, 0, 2, 0, 1, 0, 2], [0, 2, 0, 1, 0, 2, 0])

