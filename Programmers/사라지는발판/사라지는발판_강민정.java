class 사라지는발판_강민정 {
    class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static int rowSize;		// 행 크기
    static int colSize;		// 열 크기
    static int[][] board;

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        rowSize = board.length;		// 행 크기 할당
        colSize = board[0].length;	// 열 크기 할당
        this.board = board;
        return dfs(new Position(aloc[0], aloc[1]), new Position(bloc[0], bloc[1]));		// 두 캐릭터가 움직인 횟수의 합
    }

    public int dfs(Position curTurn, Position nextTurn) {
        if(board[curTurn.x][curTurn.y] == 0)	{		// 현재 플레이어가 있는 곳에 발판이 없음
            return 0;
        }

        int result = 0;

        for(int d=0; d<4; d++) {		// 사방 탐색
            int nx = curTurn.x + dx[d];
            int ny = curTurn.y + dy[d];

            if(!isValid(nx, ny) || board[nx][ny] == 0) {	// 좌표가 격자 바깥으로 나가거나 이 위치에 발판이 없어서 이동 불가
                continue;
            }

            board[curTurn.x][curTurn.y] = 0;		// 밟은 곳의 발판이 사라짐
            int moveCnt = dfs(nextTurn, new Position(nx, ny)) + 1;		// 최근에 움직인 횟수
            board[curTurn.x][curTurn.y] = 1;		// 발판 원상복구

            if(result % 2 == 0 && moveCnt % 2 == 0) {		// 계속 진 경우: 최대한 게임을 오래 끌어야 함
                result = Math.max(result, moveCnt);
            } else if(result % 2 == 1 && moveCnt % 2 == 1) {		// 계속 이긴 경우: 최대한 게임을 빨리 끝내기
                result = Math.min(result, moveCnt);
            } else if(result % 2 == 0 && moveCnt % 2 == 1) {		// 계속 지다가 이기거나
                result = moveCnt;
            }
        }

        return result;
    }

    /*
    	격자 바깥으로 나가면 false를 반환
    */
    public boolean isValid(int nx, int ny) {
        return 0 <= nx && nx < rowSize && 0 <= ny && ny < colSize;
    }

}