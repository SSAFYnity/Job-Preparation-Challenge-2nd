import java.io.*;
import java.util.*;

public class Main {
    // 1. 에라토스테네스의 체 만들기
    // 2. 처음부터 끝까지 범위 키워가며 넣어보기
    // 3. 그 중 제일 큰 값을 출력
    public static void main(String[] args) throws IOException {
        BitSet isPrime = new BitSet(100000); isPrime.set(1);
        StringBuilder sb = new StringBuilder();
        // 1. 에라토스테네스의 체 완성
        for (int i = 2; i < Math.sqrt(100000); i++) {
            for (int j = i*i; j < 100000; j+=i) {
                isPrime.set(j);
            }
        }
        // 2. 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String now;
        while (!(now = br.readLine()).equals("0")) {
            int maxPrime = getMaxPrime(now, isPrime);
            sb.append(maxPrime).append("\n");
        }

        System.out.println(sb);
    }
    // 3. 소수 중 최대값 구하기
    private static int getMaxPrime(String now, BitSet isPrime) {
        int maxPrime= 0;

        for (int i = 0; i < now.length(); i++) {
            int left = i;
            int right = i+1;
            int sub   = 0;

            while (right <= now.length()){
                sub = Integer.parseInt(now.substring(left,right));
                if(sub > 100000) break;

                if(!isPrime.get(sub)) {
                    maxPrime = Math.max(maxPrime, sub);
                }
                right++;
            }
        }
        return maxPrime;
    }
}