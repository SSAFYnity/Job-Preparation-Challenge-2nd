import java.io.*;
import java.util.*;

class 팩토리얼과최대공약수_안창호
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{	
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
			int answer = 1;
			
            for (int i = N; i > 1; i--) 
            {
             	if (K % i == 0)
                {
                    K /= i;
                    answer *= i;
                }
                
                if (K == 1) break;
            }
			
            System.out.println("#"+ test_case + " " + answer);
		}
	}
}