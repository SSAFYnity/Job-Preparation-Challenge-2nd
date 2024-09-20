# 16진수 -> 2진수
hex_to_bin = {
    '0': '0000', '1': '0001', '2': '0010', '3': '0011',
    '4': '0100', '5': '0101', '6': '0110', '7': '0111',
    '8': '1000', '9': '1001', 'A': '1010', 'B': '1011',
    'C': '1100', 'D': '1101', 'E': '1110', 'F': '1111'
}

#  암호 -> 십진수
code_num = {
    (3, 2, 1, 1): 0,
    (2, 2, 2, 1): 1,
    (2, 1, 2, 2): 2,
    (1, 4, 1, 1): 3,
    (1, 1, 3, 2): 4,
    (1, 2, 3, 1): 5,
    (1, 1, 1, 4): 6,
    (1, 3, 1, 2): 7,
    (1, 2, 1, 3): 8,
    (3, 1, 1, 2): 9
}


def check():
    possible_code = 0
    for i in range(N):
        idx = 4 * M - 1  # input에서 글자 하나가 2진수로 변환시 4자리로 늘어나기 때문에 * 4
        while idx > 54:  # 암호코드 하나의 최소 길이가 56이니깐 idx로 변환하면 55까지니깐
            if new_list[i - 1][idx] == '0' and new_list[i][idx] == '1':  # 새로운 암호의 시작. # 암호코드들이 붙어있는 경우는 존재하지 않기 때문에 윗 줄이 '0'
                password = []
                for k in range(8):  # 암호가 총 8자리 수니깐
                    w1 = w2 = w3 = w4 = 0
                    while new_list[i][idx] == '1':
                        w4 += 1
                        idx -= 1
                    while new_list[i][idx] == '0':
                        w3 += 1
                        idx -= 1
                    while new_list[i][idx] == '1':
                        w2 += 1
                        idx -= 1
                    min_value = min(w4, w3, w2)
                    w1 = (7 - (w4 // min_value + w3 // min_value + w2 // min_value)) * min_value
                    idx -= w1

                    password.append(code_num[(w1 // min_value, w2 // min_value, w3 // min_value, w4 // min_value)])

                # 코드의 뒷자리수부터 넣고 있기 때문에 홀수 자리가 실제로는 짝수자리에 들어가있음.
                even_nums = password[0] + password[2] + password[4] + password[6]
                odd_nums = password[1] + password[3] + password[5] + password[7]
                # 홀수 자리 * 3 + 짝수자리 -> 10의 배수면 정상
                if (odd_nums * 3 + even_nums) % 10 == 0:
                    possible_code += odd_nums + even_nums

            idx -= 1
    return possible_code


T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    N, M = map(int, input().split())
    board = [input() for _ in range(N)]

    # input을 2진수로 변환하기
    new_list = []
    for i in range(N):
        num_line = ''
        for j in range(M):
            num_line += hex_to_bin[board[i][j]]
        new_list.append(num_line)

    # 암호 코드가 정상인지 확인
    password = check()
    print(f'#{test_case}', password)