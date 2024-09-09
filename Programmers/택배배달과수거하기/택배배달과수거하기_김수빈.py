def solution(cap, n, deliveries, pickups):
    # 끝부터 배달하고 수거해야 최소 이동수 도출
    deliveries.reverse()
    pickups.reverse()
    answer = 0
    
    # 수거 및 배달 개수
    deli = 0
    pick = 0
    
    for i in range(n):
        # 해당 지역에 배달 혹은 수거해야할 것 할당
        deli += deliveries[i]
        pick += pickups[i]
        
        # 해당 지역에 배달 혹은 수거해야할 것이 하나라도 있을 때까지 반복
        while deli > 0 or pick > 0:
            deli -= cap
            pick -= cap
            answer += (n - i) * 2
                
    return answer