class Solution {
    private int[][] map;
    public int[] solution(int r, int c, int[][] q) {
        map = new int[r][c];
        for(int i = 0; i < r * c; i++) {
            map[i/c][i%c] = i + 1;
        }

        int[] answer = new int[q.length];

        for(int i = 0; i < q.length; i++) {
            answer[i] = rotate(q[i][0] - 1, q[i][1] - 1, q[i][2] - 1, q[i][3] - 1);
        }

        return answer;
    }

    private int rotate(int x1, int y1, int x2, int y2) {
        int min = map[x1][y1];
        int temp = min;

        for(int i = x1; i < x2; i++) {
            map[i][y1] = map[i + 1][y1];
            min = Math.min(min, map[i][y1]);
        }
        for(int i = y1; i < y2; i++) {
            map[x2][i] = map[x2][i + 1];
            min = Math.min(min, map[x2][i]);
        }
        for(int i = x2; i > x1; i--) {
            map[i][y2] = map[i - 1][y2];
            min = Math.min(min, map[i][y2]);
        }
        for(int i = y2; i > y1 + 1; i--) {
            map[x1][i] = map[x1][i - 1];
            min = Math.min(min, map[x1][i]);
        }

        map[x1][y1+1] = temp;

        return min;
    }
}