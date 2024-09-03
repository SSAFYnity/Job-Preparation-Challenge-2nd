import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] data = new int[100001];
        data[0] = 1;
        data[1] = 1;
        for (int i = 2; i < 100001; i++) {
            if (data[i] == 1)
                continue;

            for (int j = 2 * i; j < 100001; j += i) {
                data[j] = 1;
            }
        }

        while (true) {
            String N = br.readLine();

            if (N.equals("0"))
                break;

            int answer = 0;
            for (int i = 0; i < N.length(); i++) {
                for (int j = 5; j > 0; j--) {
                    if (i + j > N.length())
                        continue;

                    int num = Integer.parseInt(N.substring(i, i + j));
                    if (data[num] == 0) {
                        answer = Math.max(answer, num);
                    }
                }
            }

            System.out.println(answer);
        }
    }
}
