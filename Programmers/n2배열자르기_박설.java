import java.util.*;
class Solution {
	public int[] solution(int n, long left, long right) {

		int[] answer = new int[(int)(right-left+1)];
		long m = 0; // 몫
		long k = 0; // 나머지
		int tmp = 0; // answer의 인덱스

		for(long i=left; i<=right; i++){
			m = i/n;
			k = i%n;
			answer[tmp] = (int)Math.max(m+1,k+1);
			tmp++;
		}

		return answer;
	}
}
