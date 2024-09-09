class Solution { // 행렬 테두리 회전하기
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int[][] board;
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int ans = 0;
        int num = 1;
        board = new int[rows][columns];
        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                board[i][j] = num++;
            }
        }

        // queries for
        for (int[] query : queries){
            int m = rotation(query);
            answer[ans++] = m;
        }

        return answer;
    }

    public int rotation(int[] query){ // 회전하기
        int sr = query[0] - 1; // 시작 row
        int sc = query[1] - 1; // 시작 column
        int er = query[2] - 1; // 끝 row
        int ec = query[3] - 1; // 끝 column

        int cr = query[0] - 1;
        int cc = query[1] - 1;
        int min = Integer.MAX_VALUE;
        int tmp = board[cr][cc];
        for (int d=0; d<4; d++){

            while (true){
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                if (nr < sr || nr > er || nc < sc || nc > ec) break;

                min = Math.min(min, tmp);
                int tmp2 = board[nr][nc];
                board[nr][nc] = tmp;
                tmp = tmp2;
                cr = nr;
                cc = nc;
            }
        }
        return min;
    }
}