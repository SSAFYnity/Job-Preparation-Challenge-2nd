import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int answer = 1;
            int i = Math.min(n, k);
            while (i > 0 && k > 0) {
                i = Math.min(i, k);
                if (k%i==0) {
                    answer *= i;
                    k /= i;
                }
                i--;
            }

            bw.append('#');
            bw.append(test_case + " " + answer + "\n");
        }

        bw.flush();
        bw.close();
    }
}

