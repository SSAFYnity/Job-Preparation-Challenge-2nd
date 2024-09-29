class Solution {
    int [][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
    boolean [][] vis = new boolean  [5][5];
    int     [][] block = new int    [5][5];
    int r,c;


    public int solution(int[][] board, int[] aloc, int[] bloc) {
        r = board.length; c = board[0].length;

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                block[i][j] = board[i][j];
            }
        }

        return minMax(aloc[0], aloc[1], bloc[0], bloc[1]);

    }

    public int minMax (int curY, int curX, int opY, int opX) {
        int ret = 0;
        if(vis[curY][curX]) return 0;

        for(int i = 0; i < 4; i++){
            int ny = curY + dir[i][0];
            int nx = curX + dir[i][1];
            // 범위를 넘어가거나, 이미 방문했거나 벽인 경우 continue
            if(OOB(ny,nx) || vis[ny][nx] || block[ny][nx] == 0) continue;
            vis[curY][curX] = true;
            int pos = minMax(opY,opX,ny,nx) + 1;
            vis[curY][curX] = false;

            if(ret%2 == 0 && pos%2 == 1) ret = pos;
            else if(ret%2 == 0 && pos%2 == 0) ret = Math.max(ret,pos);
            else if(ret%2 == 1 && pos%2 == 1) ret = Math.min(ret,pos);
        }
        return ret;
    }

    public boolean OOB (int nowY, int nowX){
        return (nowY < 0 || nowX < 0 || nowY >=r || nowX >= c);
    }
}