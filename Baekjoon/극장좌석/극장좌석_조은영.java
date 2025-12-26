import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N, M;
	static int[] VIP;
	static int[] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		// 맨 마지막 수
		N=Integer.parseInt(br.readLine());
		M=Integer.parseInt(br.readLine());
		
		VIP=new int[40];
		dp=new int[41];
		
		// dp : 좌석이 N개 있을 때 앉을 수 있는 경우의 수
		// 아... 항상 dp문제를 풀 땐 입력이 0인 경우가 있다는걸 생각해야함
		
		if(N==M) {
			System.out.println(1);
			return;
		}
		
		dp[0]=1;
		dp[1]=1;
		dp[2]=2;
		
		// VIP 좌석 입력
		for(int i=0; i<M; i++) {
			VIP[i]=Integer.parseInt(br.readLine());
		}
		
		// dp값 채우기
		for(int i=3; i<=N; i++) {
			dp[i]=dp[i-1]+dp[i-2];
		}
		
		int ans=1;
		int start=0;
		
		//  start=0 , end = vip[i], 길이=end-start, start=end+1로 갱신
		for(int i=0; i<M; i++) {
			int end=VIP[i];
			int length=end-start-1;
			start=end;
			ans*=dp[length];
		}
		
		// 마지막은 예외처리
		ans*=dp[N-start];
		
		System.out.println(ans);
	}

}