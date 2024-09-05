import java.io.*;

class 소수부분문자열_강민정 {
    private static final int SIZE = 100001;
    private static final int MAX_LEN = 6;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        boolean[] isPrimeNumber = new boolean[SIZE];

        // 인덱스가 소수라면 true 할당
        for(int i=2; i<SIZE; i++) {
            isPrimeNumber[i] = findPrimeNumber(i);
        }

        while(true) {
            String number = br.readLine();      // 길이가 255를 넘지 않는 숫자 문자열
            int len = number.length();          // 숫자 문자열의 길이
            int answer = 0;                     // 가장 큰 소수

            if(number.equals("0")) {        // 테스트케이스가 끝이라면
                break;
            }

            for(int i=1; i<Math.min(MAX_LEN, len); i++) {     // 길이
                for(int j=0; j<=len-i; j++) {    // 시작 인덱스
                    long subStr = Long.parseLong(number.substring(j, j+i));     // j에서 i만큼 길이를 가진 문자열을 슬라이싱해서 long으로 변환
                    if(subStr >= SIZE) {     // 문제에서 제시한 소수의 범위를 벗어나면
                        continue;
                    }
                    if(isPrimeNumber[(int)subStr]) {        // 소수가 맞으면
                        answer = Math.max(answer, (int)subStr);     // 가장 큰 소수 갱신
                    }
                }
            }
            bw.write(String.valueOf(answer));
            bw.write("\n");
        }
        bw.flush();
    }

    /*
        에라토스테네스의 체 이용
     */
    public static boolean findPrimeNumber(int number) {
        for(int i=2; i<=Math.sqrt(number); i++) {
            if(number % i == 0) {       // 나누어 떨어지면
                return false;       // 소수가 아니다
            }
        }
        return true;        // 소수가 맞음
    }
}