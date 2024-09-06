package com.jonghyun.challenge;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 문제 : RGB 거리
 * 설명 : 전보다 더 빨리 품!
 * 메모지에이션으로 푸는데 재귀말고 함수식으로 풀었음, 예를 들어 그 다음에 오는 집의 비용은 그 전 집(같은 색 제외)중에 최소값을 넣어주면 됨!
 * 3가지로 나누어서 해결했음!
 * @author 나종현
 *
 */
public class RGB거리_나종현 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = parseInt(in.readLine()); 
		int memo[][] = new int[N][3];
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<3; j++) { // j=0이면 R, j=1이면 G, j=2이면 B
				memo[i][j] = parseInt(st.nextToken());
			}
		}
		for(int i=1; i<N; i++) {
			for(int j=0; j<3; j++) { 
				if(j == 0) { // R일 때
					memo[i][j] = memo[i][j] + Math.min(memo[i-1][1], memo[i-1][2]);																
				}else if(j == 1) { // G일 때
					memo[i][j] = memo[i][j] + Math.min(memo[i-1][0], memo[i-1][2]);																
				}else if(j == 2) { // B일 때
					memo[i][j] = memo[i][j] + Math.min(memo[i-1][0], memo[i-1][1]);																
				}
			}
		}
		// 가장 마지막에 오는 비용 3개 중 최소값
		int min = Integer.MAX_VALUE;
		for(int i=0; i<3; i++) {
			min = Math.min(min, memo[N-1][i]);
		}
		System.out.println(min);
		
	}

}
