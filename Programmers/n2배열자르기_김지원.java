class Solution {
    public int[] solution(int n, long left, long right) {
        int size = (int)(right - left + 1);
        int[] answer = new int[size];

        long row = left / n;
        long col = left % n;

        for(int i = 0; i < size; i++) {
            long num = 0;
            if(col <= row) {
                num = row;
            } else {
                num = col;
            }
            answer[i] = (int)(num+1);
            if(col + 1 == n) {
                row++;
                col = 0;
            } else col++;
        }
        return answer;
    }
}