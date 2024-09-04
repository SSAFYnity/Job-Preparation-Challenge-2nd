class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        int[][] map = new int[rows][columns];
        
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                map[i][j] = i * columns + j + 1;
            }
        }
        
        for(int i = 0; i < queries.length; i++) { // 회전
            int min = map[queries[i][0] - 1][queries[i][1] - 1];
            int temp = map[queries[i][0] - 1][queries[i][1] - 1];
            
            for(int j = queries[i][0] - 1; j < queries[i][2] - 1; j++) { // 좌측라인
                map[j][queries[i][1] - 1] = map[j + 1][queries[i][1] - 1];
                min = Math.min(map[j + 1][queries[i][1] - 1], min);
            }
            for(int j = queries[i][1] - 1; j < queries[i][3] - 1; j++) { // 하측라인
                map[queries[i][2] - 1][j] = map[queries[i][2] - 1][j + 1];
                min = Math.min(map[queries[i][2] - 1][j + 1], min);
            }
            for(int j = queries[i][2] - 1; j > queries[i][0] - 1; j--) { // 우측라인
                map[j][queries[i][3] - 1] = map[j - 1][queries[i][3] - 1];
                min = Math.min(map[j - 1][queries[i][3] - 1], min);
            }
            for(int j = queries[i][3] - 1; j > queries[i][1] - 1; j--) { // 상측라인
                map[queries[i][0] - 1][j] = map[queries[i][0] - 1][j - 1];
                min = Math.min(map[queries[i][0] - 1][j - 1], min);
            }
            map[queries[i][0] - 1][queries[i][1]] = temp;
            
            answer[i] = min;
        }
        
        return answer;
    }
}