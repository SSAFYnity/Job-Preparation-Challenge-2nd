import java.util.*;
import java.io.*;

public class Main {

    public static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String s = br.readLine();
            if (s.equals("0")) break;
            int answer = 0;

            for (int l = Math.min(s.length(), 5); l > 0; l--) {
                for (int i = 0; i <= s.length() - l; i++) {
                    int temp = Integer.parseInt(s.substring(i, i+l));
                    if (isPrime(temp)) {
                        answer = Math.max(answer, temp);
                    }
                }
                if (answer != 0) {
                    break;
                }
            }

            bw.append(answer+"\n");
        }

        bw.flush();
        bw.close();
    }
}

