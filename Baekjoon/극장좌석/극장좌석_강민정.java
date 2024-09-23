import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();       // 1 <= 좌석 개수 <= 40
        int m = sc.nextInt();       // 0 <= 고정석 개수 <= N
        int[] dp = new int[41];
        int answer = 1;     // 좌석에 앉을 수 있는 방법의 가짓수
        int start = 0;

        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for(int i=4; i<=n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];      // i 길이만큼의 좌석은 i-1과 i-2 길이만큼의 좌석의 경우의 수의 합
        }

        // 고정석의 번호가 작은 수부터 큰 수의 순서로 입력됨
        for(int i=0; i<m; i++) {
            int vip = sc.nextInt();     // 고정석
            answer *= dp[vip - start - 1];      // 고정석을 제외한 일반 좌석의 경우의 수를 모두 곱하기
            start = vip;        // 그 다음 좌석 시작점
        }

        answer *= dp[n - start];     // 마지막 VIP 이후로 좌석이 남아있을 때

        System.out.println(answer);       // 좌석에 앉을 수 있는 방법의 가짓수를 출력
    }
}