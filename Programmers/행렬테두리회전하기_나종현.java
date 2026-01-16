package com.jonghyun.challenge;

public class 행렬테두리회전하기_나종현 {
	public int[] solution(int rows, int columns, int[][] queries) {
	    int[] answer = new int[queries.length];
	    int[][] matrix = new int[rows][columns];
	    int[] dirY = {0, 1, 0, -1};
	    int[] dirX = {1, 0, -1, 0};
	    
	    int num = 0;
	    for(int i=0; i<rows; i++ ){
	        for(int j=0; j<columns; j++){
	            matrix[i][j] = ++num;
	        }
	    }
	    
	    for(int k=0; k<queries.length; k++){
	        int y= queries[k][0]-1;
	        int x = queries[k][1]-1;
	        int value = matrix[y][x];
	        int min = value;
	        int direction = 0;
	        while(direction < 4){
	            if(y+dirY[direction] < queries[k][0]-1 || y+dirY[direction] > queries[k][2]-1 || x + dirX[direction] < queries[k][1]-1 || x + dirX[direction] > queries[k][3]-1){
	                direction++;
	                continue;
	            }
	            y += dirY[direction];
	            x += dirX[direction];
	            int temp = matrix[y][x];
	            matrix[y][x] = value;
	            if(matrix[y][x] < min) min = matrix[y][x];
	            value = temp;
	        }
	        answer[k] = min; 
	    }
	    
	    return answer;
	}
}
