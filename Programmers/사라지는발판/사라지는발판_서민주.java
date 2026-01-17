import java.util.*;

class Solution {
    
    // dy, dx는 y와 x의 이동방향
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static int ay, ax, by, bx; // a, b의 시작점
    static int n, m; // board의 가로, 세로
    static int[][] array; // board를 전역으로 변경
    static int answer; // 정답
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        n = board.length;
        m = board[0].length;
        ay = aloc[0];
        ax = aloc[1];
        by = bloc[0];
        bx = bloc[1];
        answer = 0;
        array = board;
        dfs(true, 0);
        return answer;
    }
    
    public void dfs(boolean isTurn, int cnt) {
        int ny = 0;
        int nx = 0;
        for (int i = 0; i<4; i++) {
            if (isTurn) { // A의 차례
                ny = ay+dy[i];
                nx = ax+dx[i];
                if (ny>=0 && ny<n && nx>=0 && nx<m && array[ny][nx]==1) {
                    array[ny][nx] = 0; // 갈 수 없게 변경
                    answer++;
                    if (isTurn) {
                        dfs(!isTurn, cnt+1);
                    } else {
                        dfs(isTurn, cnt+1);
                    }
        }
            } else if (!isTurn) { // B의 차례
                ny = by+dy[i];
                nx = bx+dx[i];
                if (ny>=0 && ny<n && nx>=0 && nx<m && array[ny][nx]==1) {
                    array[ny][nx] = 0; // 갈 수 없게 변경
                    answer++;
                    if (isTurn) {
                        dfs(!isTurn, cnt+1);
                    } else {
                        dfs(isTurn, cnt+1);
            }
        }
            }
        }
    }
}