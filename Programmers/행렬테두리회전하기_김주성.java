import java.util.*;


class 행렬테두리회전하기_김주성 {
        public static int[] solution(int rows, int columns, int[][] queries) {
        int[][] arr = new int[rows+1][columns+1];
        int[] answer = new int[queries.length];

        int n = 1;
        for (int i = 1; i < rows+1; i++) {
            for (int j = 1; j < columns+1; j++){
                arr[i][j] = n++;
            }
        }

        for (int t = 0; t < queries.length; t++) {
            int y1 = queries[t][0], x1 = queries[t][1], y2 = queries[t][2], x2 = queries[t][3];

            int s = arr[y1][x1];
            int ans = s;
            // 1. 오른쪽(상->하)
            for (int y = y1; y < y2; y++) {
                arr[y][x1] = arr[y+1][x1];
                ans = Math.min(ans, arr[y+1][x1]);
            }

            // 2. 아래(우->좌)
            for (int x = x1; x < x2; x++) {
                arr[y2][x] = arr[y2][x+1];
                ans = Math.min(ans, arr[y2][x+1]);
            }

//        // 3. 왼쪽(하->상)
            for (int y = y2; y > y1; y--) {
                arr[y][x2] = arr[y-1][x2];
                ans = Math.min(ans, arr[y-1][x2]);
            }

//        // 4. 맨위(좌->우)
            for (int x = x2; x > x1; x--) {
                arr[y1][x] = arr[y1][x-1];
                ans = Math.min(ans, arr[y1][x-1]);
            }
            arr[y1][x1+1] = s;


            answer[t] = ans;
        }

        return answer;
    }
}
