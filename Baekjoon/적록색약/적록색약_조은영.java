import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int N;
	static String colors;
	static char [][]originalColor;
	static char [][]anotherColor;
	static boolean [][]visited;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	static StringBuilder sb=new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		
		originalColor=new char[N][N];
		anotherColor=new char[N][N];

		
		// 입력
		for(int i=0; i<N; i++) {
			colors=br.readLine();
			for(int j=0; j<N; j++) {
				char input=colors.charAt(j);
				originalColor[i][j]=input;
				
				// 적록색약의 경우, 빨강이 들어오면 그린으로 바꿔서 입력
				if(input=='R') input='G';
				anotherColor[i][j]=input;
				
			}
		}
		
		
		
		// 탐색하는 함수
		DFSAll(originalColor);
		
		DFSAll(anotherColor);
		
		System.out.println(sb);
		
	}
	
	static void DFSAll(char[][]arr) {
		
		int cnt=0; // 구역 개수 세기
		visited=new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					DFS(arr, i, j);
					
					cnt++;
				}
			}

		}
		sb.append(cnt).append(" ");
		
	}
	
	static void DFS(char[][] arr, int x, int y) {
		char alphabet=arr[x][y];
		visited[x][y]=true;
		
		// 상 하 좌 우로만 인정이 된다는 조건이 있으므로 4방 탐색
		for(int i=0; i<4; i++) {
			int nx=x+dx[i];
			int ny=y+dy[i];
			
			
			// 범위를 넘어가거나 이미 방문한 곳이거나 이전 알파벳과 다르면 DFS를 실행하지 않음
			if(!isRange(nx, ny, N) || visited[nx][ny] || arr[nx][ny]!=alphabet) continue;
			
			DFS(arr, nx, ny);
		}
	}
	
	static boolean isRange(int x, int y, int n) {
		return (x>=0 && x<n && y>=0 && y<n);
	}

}