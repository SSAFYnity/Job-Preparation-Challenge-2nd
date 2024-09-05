import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String string = br.readLine();
            if (string.equals("0"))
                return;
            int maxLength = 0;
            int answer = 0;
            int length = string.length();
            for (int s = 0; s < length; s++) {
                if (string.charAt(s) == '0') continue;
                for (int e = s + 1; e <= s + 6 && e <= length; e++) {
                    String substring = string.substring(s, e);
                    if (isPrime(substring)) {
                        answer = Math.max(answer, Integer.parseInt(substring));
                    }
                }
            }
            System.out.println(answer);
        }
    }

    public static boolean isPrime(String str) {
        int n = Integer.parseInt(str);
        if (n > 100000)
            return false;
        if (n < 2)
            return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}