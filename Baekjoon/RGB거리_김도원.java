package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		
		// 소요비용을 업데이트해가며 저장해둘 배열
		int[] last = new int[3];
		StringTokenizer st;
		for(int i = 0; i < n; ++i) {
			
			// 한줄 입력받기
			int[] input = new int[3];
			st = new StringTokenizer(br.readLine());
			input[0] = Integer.valueOf(st.nextToken());
			input[1] = Integer.valueOf(st.nextToken());
			input[2] = Integer.valueOf(st.nextToken());
			
			// 이전값들과 더해서 가장 큰값을 저장해두기 (input배열을 재사용해도 무방했다.)
			for(int j = 0; j < 3; ++j) {
				int min = 2147483647;
				for(int k = 0; k < 3; ++k) {
					if(j == k)
						continue;
					if(input[j] + last[k] < min) {
						min = input[j] + last[k];
					}
				}
				input[j] = min;
			}
			
			// last를 새로운 배열로 변경
			last = input;
		}
		
		// 가장 작은 값 출력
		System.out.println(Math.min(Math.min(last[0], last[1]),last[2]));
	}
}
