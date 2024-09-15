class n2배열자르기_김주성 {
        public static int[] solution(int n, long left, long right) {
        int[] answer = new int[(int) (right-left) + 1];

        for (long i = left; i <= right; i++) {
            answer[(int) (i-left)] = (int) (1 + i / n + (i/n > i % n ? 0 : i % n - i/n));
        }
        
        return answer;
    }
}
