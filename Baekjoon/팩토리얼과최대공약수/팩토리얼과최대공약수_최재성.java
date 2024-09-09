import java.io.*;
import java.util.StringTokenizer;
   
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
   
        int T = Integer.parseInt(br.readLine());
   
        for(int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int result = getGCD(a, b);
            bw.append('#');
            bw.append(t + " " + result + "\n");
        }
        bw.flush();
        bw.close();
    }
   
    private static int getGCD(int a, int b) {
        int result = 1;
        for(int i=Math.min(a, b); i>=2 && b > 1; i--) {
            if(i > b) continue;
            if(b % i == 0) {
                result *= i;
                b /= i;
            }
        }
        return result;
    }
}
