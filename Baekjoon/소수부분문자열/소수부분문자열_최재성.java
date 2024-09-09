import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //소수 set 만들기
        boolean[] notPrimes = new boolean[100001];
        notPrimes[0] = true;
        notPrimes[1] = true;
        for(int i=2; i<=100000; i++) {
            if(notPrimes[i]) continue;
            for(int j=i+i; j<=100000; j+=i) {
                notPrimes[j] = true;
            }
        }

        //부분 문자열 찾기
        while(true) {
            int result = 0;
            String s = br.readLine();
            if(s.equals("0")) return;
            for(int i=5; i<=s.length() && i>0; i--) {
                for(int j=0; j<s.length() - i; j++) {
                    int sub = Integer.parseInt(s.substring(j, j + i));
                    if(!notPrimes[sub])
                        result = Math.max(result, sub);
                }
                if(result != 0) {
                    System.out.println(result);
                    break;
                }
            }
        }
    }
}

