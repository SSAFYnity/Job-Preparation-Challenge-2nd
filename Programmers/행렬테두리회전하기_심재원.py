def rotate_matrix(matrix, query):
    x1, y1, x2, y2 = map(lambda x: x - 1, query)
    
    cur_x, cur_y = x1, y1
    tmp_value = matrix[x1][y1]
    min_value = tmp_value
    for new_y in range(y1+1, y2+1):
        cur_y = new_y
        tmp_value, matrix[cur_x][cur_y] = matrix[cur_x][cur_y], tmp_value
        min_value = min(min_value, tmp_value)
        
    for new_x in range(x1+1, x2+1):
        cur_x = new_x
        tmp_value, matrix[cur_x][cur_y] = matrix[cur_x][cur_y], tmp_value
        min_value = min(min_value, tmp_value)
        
    for new_y in range(y2-1, y1-1, -1):
        cur_y = new_y
        tmp_value, matrix[cur_x][cur_y] = matrix[cur_x][cur_y], tmp_value  
        min_value = min(min_value, tmp_value)
        
    for new_x in range(x2-1, x1-1, -1):
        cur_x = new_x
        tmp_value, matrix[cur_x][cur_y] = matrix[cur_x][cur_y], tmp_value    
        min_value = min(min_value, tmp_value)
    
    return [matrix, min_value]
        

def solution(rows, columns, queries):
    answer = []
    matrix = [[i for i in range((j-1)*columns+1, j*columns+1)] for j in range(1, rows+1)]
    for query in queries:
        matrix, min_value = rotate_matrix(matrix, query)
        answer.append(min_value)

    return answer