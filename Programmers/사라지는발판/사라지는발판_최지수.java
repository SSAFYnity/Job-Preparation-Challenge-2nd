import java.util.*;

class Solution {
    
    static int col, row;
    static int[][] dr = {{0,1,0,-1}, {1,0,-1,0}};
    static int[][] board;
    
    static boolean isOut(int y, int x) {
        return y < 0 || y >= row || x < 0 || x >= col;
    }
    
    static class WV {
        int y, x;
        public WV(int y, int x) {
            this.y = y;
            this.x = x;
        }
        @Override
        public boolean equals(Object ob) {
            WV o = (WV) ob;
            return o.y == this.y && o.x == this.x;
        }
    }
    
    static int back(WV a, WV b) {
        if (board[a.y][a.x] == 0) {return 0;}
        
        int my = 0;
        for (int d = 0; d < 4; d++) {
            int y = a.y + dr[0][d];
            int x = a.x + dr[1][d];
            if (isOut(y, x) || board[y][x] == 0) continue;
            
            board[a.y][a.x] = 0;
            int enemy = back(b, new WV(y,x)) + 1;
            board[a.y][a.x] = 1;
            
            if (my % 2 == 0) { // 나는 모르고
                if (enemy % 2 == 0) { // 상대방이 진행 가능
                    my = Math.max(my, enemy); // 최대한 시간 끌어
                } else { // 상대방이 더 이상 진행할 수 없음
                    my = enemy;
                }
            } else if (enemy % 2 == 1) { // 나는 진행할 수 있고 상대방이 진행할 수 없음
                my = Math.min(my, enemy); // 최대한 빨리
            }
        }
        
        return my;
    }
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        row = board.length;
        col = board[0].length;
        Solution.board = board;
        
        
        return back(new WV(aloc[0], aloc[1]), new WV(bloc[0], bloc[1]));
    }
}