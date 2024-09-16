import java.io.*;
import java.util.*;

// [BOJ] 소수 부분 문자열
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] isPrime = new boolean[100001];
        isPrime[0] = isPrime[1] = true; // default값이 false라서 반대로
        for (int i=2; i<100001; i++){
            if(isPrime[i])continue; //소수가 아니면 넘어가기
            for (int j=2*i; j<100001; j+=i){
                isPrime[j] = true;
            }
        }

        while(true){
            String nums = br.readLine();
            if (nums.equals("0")) break;

            int ans = 0;
            for (int i=0; i<nums.length(); i++){
                for(int j=5; j>0; j--){
                    if(i+j > nums.length()) continue;

                    int part = Integer.parseInt(nums.substring(i, i+j));
//                    System.out.println(part);
                    if (!isPrime[part]){
                        ans = Math.max(ans, part);
                    }
                }
            }
            System.out.println(ans);
        }
    }

}