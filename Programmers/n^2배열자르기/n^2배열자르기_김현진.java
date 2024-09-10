class n^2 배열자르기_김현진
{

	public int[] solution(int n, long left, long right) {
		int[] result = new int[(int) (right - left + 1)];
		int idx = 0;

		for (long i = left; i <= right; i++) {
			int row = (int) (i / n);
			int col = (int) (i % n);
			result[idx++] = Math.max(row, col) + 1;
		}
		return result;
	}
}