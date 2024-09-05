import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<1000; i++) {
            String a = br.readLine();
            if (a.equals("0")) {
                break;
            }
            int max = 0;
            for (int j = 0; j<a.length(); j++) {
                for (int k = j; k<Math.min(j+5, a.length()); k++) {
                    int number = Integer.parseInt(a.substring(j, k+1));
                    if (isPrime(number)) {
                        max = Math.max(max, number);
                    }
                }
            }
            sb.append(max + "\n");
        }
        System.out.println(sb);
    }

    public static boolean isPrime(int num) {
        boolean answer = true;
        if (num<=1 && num>100000) {
            return false;
        } else {
            for (int i = 2; i<=Math.sqrt(num); i++) {
                if (num%i==0) {
                    answer = false;
                    break;
                }
            }
        }
        return answer;
    }
}