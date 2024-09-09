def solution(rows, columns, queries):
    # 1. 행렬 초기화
    matrix = [[(i * columns) + j + 1 for j in range(columns)] for i in range(rows)]
    result = []

    # 2. 회전 처리
    for x1, y1, x2, y2 in queries:
        # 좌표를 0부터 시작하는 인덱스로 맞추기 위해 1씩 빼줍니다.
        x1, y1, x2, y2 = x1 - 1, y1 - 1, x2 - 1, y2 - 1

        # 회전할 테두리의 숫자들 중 가장 작은 값 추적
        min_value = matrix[x1][y1]

        # 테두리의 첫 값 저장
        temp = matrix[x1][y1]

        # 왼쪽 세로줄 위로 이동
        for i in range(x1, x2):
            matrix[i][y1] = matrix[i + 1][y1]
            min_value = min(min_value, matrix[i][y1])

        # 아래 가로줄 왼쪽으로 이동
        for i in range(y1, y2):
            matrix[x2][i] = matrix[x2][i + 1]
            min_value = min(min_value, matrix[x2][i])

        # 오른쪽 세로줄 아래로 이동
        for i in range(x2, x1, -1):
            matrix[i][y2] = matrix[i - 1][y2]
            min_value = min(min_value, matrix[i][y2])

        # 위쪽 가로줄 오른쪽으로 이동
        for i in range(y2, y1, -1):
            matrix[x1][i] = matrix[x1][i - 1]
            min_value = min(min_value, matrix[x1][i])

        # 마지막으로 temp값을 적절한 위치에 넣어줍니다.
        matrix[x1][y1 + 1] = temp

        # 이번 회전에서의 최소값을 결과 리스트에 추가
        result.append(min_value)

    return result
