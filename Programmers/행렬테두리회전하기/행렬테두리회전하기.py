arr = []
def solution(rows, columns, queries):
    global arr
    answer = []
    CreateTable(rows, columns)

    for querie in queries:
        answer.append(Rotation(querie))
    
    print(answer)
    return answer

def CreateTable(rows, columns):
    global arr
    arr = [[0] * columns for _ in range(rows)] 
    for i in range(rows):
        for j in range(columns):
            arr[i][j] = i * columns + j + 1

def Rotation(querie):
    global arr

    y1, x1, y2, x2 = [q - 1 for q in querie]
    
    # 첫 번째 값을 임시로 저장
    temp = arr[x1][y1]
    min_value = temp

    # 좌측 열 회전
    for i in range(x1, x2):
        arr[i][y1] = arr[i + 1][y1]
        min_value = min(min_value, arr[i][y1])

    # 하단 행 회전
    for i in range(y1, y2):
        arr[x2][i] = arr[x2][i + 1]
        min_value = min(min_value, arr[x2][i])

    # 우측 열 회전
    for i in range(x2, x1, -1):
        arr[i][y2] = arr[i - 1][y2]
        min_value = min(min_value, arr[i][y2])

    # 상단 행 회전
    for i in range(y2, y1, -1):
        arr[x1][i] = arr[x1][i - 1]
        min_value = min(min_value, arr[x1][i])

    # 저장한 첫 번째 값을 마지막 위치에 삽입
    arr[x1][y1 + 1] = temp

    print(arr)

    return min_value

solution(6, 6, [[2,2,5,4],[3,3,6,6],[5,1,6,3]])