class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] matrix = new int[rows][columns];
		int count = 1;
		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {
				matrix[i][j] = count++;
			}
		}
		int size = queries.length;
        int[] answer = new int[size];
		for(int i=0;i<size;i++) {
			int x1 = queries[i][0]-1; int y1 = queries[i][1]-1;
			int x2 = queries[i][2]-1; int y2 = queries[i][3]-1;
			int currentR = x1; int currentC = y1;
			int start = matrix[x1][y1];
			int min = Integer.MAX_VALUE;
			for(int rotateCount=0;rotateCount<2*(x2-x1+y2-y1)-1;rotateCount++) {
				if(min>matrix[currentR][currentC]) {
					min = matrix[currentR][currentC];
				}
				if(rotateCount<x2-x1) {
					matrix[currentR][currentC] = matrix[++currentR][currentC];
				}
				else if(rotateCount<x2-x1+y2-y1) {
					matrix[currentR][currentC] = matrix[currentR][++currentC];
				}
				else if(rotateCount<2*(x2-x1)+y2-y1) {
					matrix[currentR][currentC] = matrix[--currentR][currentC];
				}
				else {
					matrix[currentR][currentC] = matrix[currentR][--currentC];
				}
			}
			if(min>matrix[x1][y1+1]) {
				min = matrix[x1][y1+1];
			}
			matrix[x1][y1+1] = start;
			answer[i] = min;
		}
        return answer;
    }
}