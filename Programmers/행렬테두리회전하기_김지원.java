import java.util.Arrays;
class Solution {
    static int[][] map;
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int [queries.length];
        map = new int[rows][columns];
        int num = 1;
        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                map[i][j] = num++;

            }
        }
        for(int i=0; i<answer.length; i++) {
            answer[i] = move(queries[i]);
        }
        return answer;
    }

    static int move(int[] pos) {
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        for(int i=0; i<pos.length; i++) {
            pos[i]--;
        }
        int row = pos[0], col = pos[1];
        int len = 2*(pos[2]-pos[0])+2*(pos[3]-pos[1]);
        int[] tmp = new int[len];
        int idx = 0;

        for(int i=pos[1]; i<pos[3]; i++) {
            tmp[idx++] = map[pos[0]][i];
        }
        for(int i=pos[0]; i<pos[2]; i++) {
            tmp[idx++] = map[i][pos[3]];
        }
        for(int i=pos[3]; i>pos[1]; i--) {
            tmp[idx++] = map[pos[2]][i];
        }
        for(int i=pos[2]; i>pos[0]; i--) {
            tmp[idx++] = map[i][pos[1]];
        }

        idx = 0;
        for(int i=0; i<tmp.length; i++) {
            if((idx == 0 && col == pos[3]) ||
                    (idx == 1 && row == pos[2]) ||
                    (idx == 2 && col == pos[1])) idx++;

            row = row + dir[idx][0];
            col = col + dir[idx][1];

            map[row][col] = tmp[i];
        }
        Arrays.sort(tmp);
        return tmp[0];
    }
}