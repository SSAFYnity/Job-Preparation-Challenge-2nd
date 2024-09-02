class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right - left) + 1];
        
        int idx = 0;
        for(long i = left; i <= right; i++) {
            long ni = i / n;
            long nj = i % n;
            
            answer[idx] = (int)Math.max(ni, nj) + 1;
            idx++;
        }
        
        return answer;
    }
}