class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int n = queries.length;
        int[] answer = new int[n];
        int [][] arr = new int[rows][columns];
        
        for(int i = 0; i < rows; i++){                  
            for(int j = 0; j < columns; j++){
                arr[i][j] = (((i+1)-1)*columns + (j+1));
            }
        }
        
        for(int i = 0; i < n; i++){
            int x1 = queries[i][0];
            int y1 = queries[i][1];
            int x2 = queries[i][2];
            int y2 = queries[i][3];
            
            int tmp = arr[x1-1][y2-1];
            int minNum = arr[x1-1][y2-1];
            
            for(int idx = y2-2; idx >= y1-1; idx--){   // 우
                arr[x1-1][idx+1] = arr[x1-1][idx];
                minNum = Math.min(minNum, arr[x1-1][idx+1]);
            }
            for(int idx = x1; idx < x2; idx++){        // 상
                arr[idx-1][y1-1] = arr[idx][y1-1];
                minNum = Math.min(minNum, arr[idx-1][y1-1]);
            }
            for(int idx = y1; idx < y2; idx++){        // 좌
                arr[x2-1][idx-1] = arr[x2-1][idx];
                minNum = Math.min(minNum, arr[x2-1][idx-1]);
            }
            for(int idx = x2-2; idx >= x1-1; idx--){    // 하
                arr[idx+1][y2-1] = arr[idx][y2-1];
                minNum = Math.min(minNum, arr[idx+1][y2-1]);
            }
            
            arr[x1][y2-1] = tmp;
            answer[i] = minNum;
            
        }
        
        
        
        return answer;
    }
    
}