class Solution {
    /*
        1 <= 배열 크기 n <= 10^7
        0 <= left <= right < n^2
        right - left < 10^5
    */
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right - left + 1)];
        int idx = 0;        // 1차원 배열 인덱스

        // left ~ right만 남기기
        for(long i=left; i<=right; i++) {
            answer[idx++] = (int)Math.max(i/n+1, i%n+1);
        }
        return answer;
    }
}