class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] arr = new int[rows][columns];
        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                arr[i][j] = i * columns + j + 1;
            }
        }
        int[] answer = new int[queries.length];
        for(int i=0; i<answer.length; i++){
            answer[i] = rotate(arr, queries[i]);
        }
        return answer;
    }
    private static int rotate(int[][] arr, int[] q) {
        int nx = q[0] - 1;
        int ny = q[1] - 1;
        int sx = q[0] - 1;
        int sy = q[1] - 1;
        int ex = q[2] - 1;
        int ey = q[3] - 1;
        
        //오른쪽
        int a = arr[nx][ny];
        int b = 0;
        int result = a;
        while(ny != ey) {
            b = arr[nx][ny + 1];
            arr[nx][ny + 1] = a;
            a = b;
            ny++;
            result = Math.min(result, a);
        }
        //아래
        while(nx != ex) {
            b = arr[nx + 1][ny];
            arr[nx + 1][ny] = a;
            a = b;
            nx++;
            result = Math.min(result, a);
        }
        //왼쪽
        while(ny != sy) {
            b = arr[nx][ny - 1];
            arr[nx][ny - 1] = a;
            a = b;
            ny--;
            result = Math.min(result, a);
        }
        //위
        while(nx != sx) {
            b = arr[nx - 1][ny];
            arr[nx - 1][ny] = a;
            a = b;
            nx--;
            result = Math.min(result, a);
        }
        return result;
    }
}
