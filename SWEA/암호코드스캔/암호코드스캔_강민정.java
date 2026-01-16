import java.io.*;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class 암호코드스캔_강민정 {
    static final int PASSWORD_LEN = 8;
    static final int SUM_ROW = 10000;
    static final int SUM_COL = 9;
    static final String[] codes = {"3211", "2221", "2122", "1411", "1132", "1231", "1114", "1312", "1213", "3112"};

    static Map<Character, String> map;
    static String[] passwordArr;
    static int[][] sum;
    static List<String> sumResult;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());        // 테스트케이스 수
        StringTokenizer st;
        map = new HashMap();

        map.put('0', "0000");
        map.put('1', "0001");
        map.put('2', "0010");
        map.put('3', "0011");
        map.put('4', "0100");
        map.put('5', "0101");
        map.put('6', "0110");
        map.put('7', "0111");
        map.put('8', "1000");
        map.put('9', "1001");
        map.put('A', "1010");
        map.put('B', "1011");
        map.put('C', "1100");
        map.put('D', "1101");
        map.put('E', "1110");
        map.put('F', "1111");

        for(int tc=1; tc<=t; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());       // 1 <= 배열의 세로 크기 < 2,000
            int m = Integer.parseInt(st.nextToken());       // 1 <= 배열의 가로 크기 < 500
            List<String> lst = new ArrayList();
            sumResult = new ArrayList();
            result = 0;

            for(int i=0; i<n; i++) {
                String str = br.readLine();    // m개의 배열 값이 주어짐

                if(lst.contains(st)) {
                    continue;
                }

                for(int j=0; j<m; j++) {
                    if(str.charAt(j) != '0') {
                        lst.add(str);
                        break;
                    }
                }
            }
            sum = new int[SUM_ROW][SUM_COL];
            bw.write("#" + tc + " " + encode());
        }
        bw.flush();
    }

    /*
        16진수 -> 2진수 변환
     */
    public static void toBinaryNumber(List<String> lst) {
        passwordArr = new String[lst.size()];
        for(int i=0; i<lst.size(); i++) {
            for(int j=0; j<lst.get(i).length(); j++) {
                for(Character ch : map.keySet()) {
                    if(lst.get(i).charAt(j) == ch) {
                        passwordArr[i] += map.get(ch);
                    }
                }
            }
        }
    }

    public static int encode() {
        int cur = 0;

        for(int i=0; i<passwordArr.length; i++) {
            for(int j=passwordArr[i].length()-1; i>0; i--) {
                if(passwordArr[i].charAt(j) != '0') {
                    passwordArr[i] = passwordArr[i].substring(0, j + 1);
                    break;
                }
            }
        }

        for(int i=0; i<passwordArr.length; i++) {       // 행
            int r1 = 0, r2 = 0, r3 = 0, r4 = 0;
            int idx = PASSWORD_LEN;

            for(int j=passwordArr[i].length()-1; j>0; j--) {        // 열
                char tmp = passwordArr[i].charAt(j);

                if(tmp == '1' && r3 == 0) {
                    r4++;
                } else if(tmp == '0' && r2 == 0) {
                    if(r4 != 0) {
                        r3++;
                    } else {
                        passwordArr[i] = passwordArr[i].substring(0, i);
                    }
                } else if(tmp == '1' && r1 == 0) {
                    r2++;
                } else {
                    int mul = Math.min(Math.min(r2, r3), r4);

                    r1 = (mul * 7) - (r2 + r3 + r4);
                    int rateSum = r1 + r2 + r3 + r4;

                    r1 /= mul;
                    r2 /= mul;
                    r3 /= mul;
                    r4 /= mul;

                    String code = "";
                    code += r1;
                    code += r2;
                    code += r3;
                    code += r4;

                    sum[cur][idx--] = Arrays.asList(codes).indexOf(code);

                    if(idx == 0) {
                        cur++;
                        idx = PASSWORD_LEN;
                    }

                    r1 = 0; r2 = 0; r3 = 0; r4 = 0;

                    if(passwordArr[i].length() - rateSum > 0) {
                        passwordArr[i] = passwordArr[i].substring(0, passwordArr[i].length() - rateSum);
                    } else {
                        break;
                    }

                    i = passwordArr[i].length();
                }
            }
        }

        for(int i = 0; i<sum.length; i++) {
            int n1 = 0, n2 = 0;

            for(int j = 1; j<sum.length; j++) {
                if(j % 2 == 1) {
                    n1 += sum[i][j];
                } else if(j % 2 == 0) {
                    n2 += sum[i][j];
                }
            }

            if(((n1 * 3) + n2) % 10 == 0) {
                String tmpStr = Arrays.toString(sum).replaceAll("[^0-9]", "");
                if(!sumResult.contains(tmpStr)) {
                    sumResult.add(tmpStr);
                }
            }
        }

        for(String sumStr : sumResult) {
            for(int i=0; i<9; i++) {
                result += sumStr.charAt(i) - '0';
            }
        }
        return result;
    }
}