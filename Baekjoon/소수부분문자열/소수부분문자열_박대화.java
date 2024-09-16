import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            int max = 0;
            String num = br.readLine();
            if(num.charAt(0) == '0' && num.length() == 1) break;

            for(int i = 1; i <= 5; i++) {
                for(int j = 0; j < num.length(); j++) {
                    if(j + i > num.length()) break;

                    int n = Integer.parseInt(num.substring(j, j + i));
                    if(isPrime(n)) {
                        max = Math.max(max, n);
                    }
                }
            }

            System.out.println(max);
        }
    }

    public static boolean isPrime(int num) {
        if(num == 1 || num == 0) {
            return false;
        }
        for(int i = 2; i < Math.sqrt(num) + 1; i++) {
            if(num % i == 0 && num != i) {
                return false;
            }
        }
        return true;
    }
}
