class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int) (right-left+1)];
        long start_row = 0;
        long start_col = left % (long)n;
        long end_row = 0;
        long end_col = right % (long)n;

        if(left / (long) n != 0){
            start_row = left / (long) n;
        }
        if(right / (long) n != 0){
            end_row = right / (long) n;
        }

        int cnt = 0;
        if(start_row == end_row){
            for(long i = start_col; i < end_col+1; i++){
                int tmp = (int) Math.max(start_row, i);
                answer[cnt++] = tmp+1;
            }
        }
        else{
            for(long i = start_col; i < n; i++){     // 시작
                int tmp = (int) Math.max(start_row, i);
                answer[cnt++] = tmp+1;
            }

            for(long i = start_row+1; i < end_row; i++){        // 중간
                for(long j = 0; j < n; j++){
                    int tmp = (int) Math.max(i, j);
                    answer[cnt++] = tmp+1;
                }
            }

            for(long i = 0; i <= end_col; i++){     // 끝
                int tmp = (int) Math.max(end_row, i);
                answer[cnt++] = tmp+1;
            }
        }

        return answer;
    }
}