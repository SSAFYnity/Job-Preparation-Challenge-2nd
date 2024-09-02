class 행렬테두리회전하기_강민정 {
    static int[][] arr;     // rows * columns 크기의 행렬
    static final int MAX_NUM = 10000;       // 배열의 최댓값

    /*
        2 <= rows <= 100
        2 <= columns <= 100
        1 <= queries의 행의 개수 <= 10,000
        queries의 각 행은 [x1행, y1열, x2행, y2열]
        1 <= x1 < x2 <= rows, 1 <= y1 < y2 <= columns
    */
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length]; // 각 회전들을 배열에 적용한 뒤 위치가 바뀐 숫자들 중 가장 작은 숫자를 담은 배열
        arr = new int[rows][columns];
        int num = 1;            // 배열 값
        int queriesCnt = 0;     // 명령문 실행 횟수

        // 행렬 초기화
        for(int i=0; i<rows; i++) {     // 행
            for(int j=0; j<columns; j++) {      // 열
                arr[i][j] = num++;
            }
        }

        for(int[] query : queries) {
            int startRow = query[0] - 1;        // 시작 행
            int startCol = query[1] - 1;        // 시작 열
            int endRow = query[2] - 1;          // 마지막 행
            int endCol = query[3] - 1;          // 마지막 열
            int leftTop = arr[startRow][startCol];  // 왼쪽 상단 값
            int minValue = leftTop;       // 최소값 초기화

            minValue = Math.min(minValue, leftRotation(startRow, endRow, startCol));    // 왼쪽 테두리
            minValue = Math.min(minValue, bottomRotation(startCol, endCol, endRow));    // 아래 테두리
            minValue = Math.min(minValue, rightRotation(startRow, endRow, endCol));     // 오른쪽 테두리
            minValue = Math.min(minValue, topRotation(startCol, endCol, startRow));     // 위쪽 테두리

            arr[startRow][startCol + 1] = leftTop;   // 저장해둔 왼쪽 상단 값을 오른쪽 옆에 할당
            answer[queriesCnt++] = minValue;        // 최솟값 할당
        }

        return answer;
    }

    /*
        상단 테두리 회전
    */
    public int topRotation(int start, int end, int row) {
        int value = MAX_NUM;
        for(int col=end; col>start; col--) {        // 열
            arr[row][col] = arr[row][col - 1];
            value = Math.min(arr[row][col], value);
        }
        return value;
    }

    /*
        우측 테두리 회전
    */
    public int rightRotation(int start, int end, int col) {
        int value = MAX_NUM;
        for(int row=end; row>start; row--) {      // 행
            arr[row][col] = arr[row - 1][col];
            value = Math.min(arr[row][col], value);
        }
        return value;
    }

    /*
        하단 테두리 회전
    */
    public int bottomRotation(int start, int end, int row) {
        int value = MAX_NUM;
        for(int col=start; col<end; col++) {     // 열
            arr[row][col] = arr[row][col + 1];
            value = Math.min(value, arr[row][col]);
        }
        return value;
    }

    /*
        좌측 테두리 회전
    */
    public int leftRotation(int start, int end, int col) {
        int value = MAX_NUM;
        for(int row=start; row<end; row++) {     // 행
            arr[row][col] = arr[row + 1][col];
            value = Math.min(value, arr[row][col]);
        }
        return value;
    }
}