import java.util.*;
import java.io.*;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int test_case = 1; test_case <= T; test_case++)
        {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
 
            int result;
 
            if(n >= k) result = k;
            else result = gcd(n, k);
 
            sb.append("#"+test_case+" "+result+"\n");
        }
        System.out.print(sb);
    }
 
    static int gcd(int n, int k){
        int result = 1;
 
        for(int idx = n; idx > 1; idx--){
            if(k % idx == 0){   
                result *= idx;      // 나눠지는 값을 계속 곱해나가기
                k /= idx;
            }
        }
        return result;
    }
}