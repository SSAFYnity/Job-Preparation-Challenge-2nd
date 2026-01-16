import java.io.*;
import java.util.*;

public class 암호코드스캔_여창영 {
    static int x, T, N, M, multiple, result;
    static long sum;
    static int password[];
    static char code[][];
    static int digit[][] = { { 0, 0, 0, 0 }, { 0, 0, 0, 1 }, { 0, 0, 1, 0 }, { 0, 0, 1, 1 }, { 0, 1, 0, 0 },
            { 0, 1, 0, 1 }, { 0, 1, 1, 0 }, { 0, 1, 1, 1 }, { 1, 0, 0, 0 }, { 1, 0, 0, 1 }, { 1, 0, 1, 0 },
            { 1, 0, 1, 1 }, { 1, 1, 0, 0 }, { 1, 1, 0, 1 }, { 1, 1, 1, 0 }, { 1, 1, 1, 1 } };

    public static boolean validate() {
        sum = 0;

        sum += (password[0] + password[2] + password[4] + password[6]) * 3 + password[1] + password[3] + password[5]
                + password[7];

        if (sum % 10 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static int checkValue(int x, int y, int z) {

        int a, b, c;

        multiple = Math.min(x, Math.min(y, z));
        a = x / multiple;
        b = y / multiple;
        c = z / multiple;

        if (a == 2 && b == 1 && c == 1) {
            return 0;
        } else if (a == 2 && b == 2 && c == 1) {
            return 1;
        } else if (a == 1 && b == 2 && c == 2) {
            return 2;
        } else if (a == 4 && b == 1 && c == 1) {
            return 3;
        } else if (a == 1 && b == 3 && c == 2) {
            return 4;
        } else if (a == 2 && b == 3 && c == 1) {
            return 5;
        } else if (a == 1 && b == 1 && c == 4) {
            return 6;
        } else if (a == 3 && b == 1 && c == 2) {
            return 7;
        } else if (a == 2 && b == 1 && c == 3) {
            return 8;
        } else if (a == 1 && b == 1 && c == 2) {
            return 9;
        }

        return -50000;
    }

    public static void pre() throws IOException {
        int i, j, k, len, a, b, c;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (x = 1; x <= T; x++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            len = 7;
            result = 0;

            String prev = br.readLine();

            password = new int[8];
            int prevRow[] = new int[M * 4];
            int currRow[] = new int[M * 4];

            for (i = 1; i < N; i++) {
                String curr = br.readLine();
                if (prev.equals(curr)) {
                    continue;
                }
                prev = curr;

                for (j = 0; j < M; j++) {
                    for (k = 0; k < 4; k++) {
                        currRow[j * 4 + k] = digit[Character.digit(curr.charAt(j), 16)][k];
                    }
                }

                for (j = M * 4 - 1; j >= 0; j--) {
                    if (currRow[j] == 1 && prevRow[j] == 0) {
                        a = 0;
                        b = 0;
                        c = 0;
                        while (currRow[j] == 1) {
                            c++;
                            j--;
                        }
                        while (currRow[j] == 0) {
                            b++;
                            j--;
                        }
                        while (currRow[j] == 1) {
                            a++;
                            j--;
                        }
                        if (len != 0) {
                            while (currRow[j] == 0) {
                                j--;
                            }
                        }
                        j++;

                        password[len] = checkValue(a, b, c);
                        len--;

                        if (len < 0) {
                            if (validate()) {
                                for (int idx = 0; idx < 8; idx++) {
                                    result += password[idx];
                                }
                            }
                            len = 7;
                        }

                    }
                }

                prevRow = currRow.clone();
            }

            sb.append("#").append(x).append(" ").append(result).append('\n');
        }

        System.out.print(sb);
    }

    public static void main(String[] args) throws IOException {
        pre();
    }
}
