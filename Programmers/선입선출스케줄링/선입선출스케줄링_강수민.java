public class Solution {
    public int solution(int n, int[] cores) {

        if (n <= cores.length) {
            return n;
        }

        n -= cores.length; // 초기 작업 수 감소
        long left = 1;
        long right = 1000000000;
        long time = Long.MAX_VALUE;

        // 이진 탐색
        while (left <= right) {
            long mid = (left + right) / 2;
            long ct = cntJobs(cores, mid);

            if (ct >= n) {
                time = mid; // 최소 시간을 찾기
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // 남아있는 작업 처리
        for (int i = 0; i < cores.length; i++) {
            n -= (time - 1) / cores[i];
        }

        // 마지막 작업을 처리한 코어 찾기
        for (int i = 0; i < cores.length; i++) {
            if (time % cores[i] == 0) {
                n--;
                if (n == 0) {
                    return i + 1;
                }
            }
        }

        return -1;
    }

    // 시간 동안 처리된 작업 수를 계산
    private long cntJobs(int[] cores, long time) {
        if (time <= 0) return 0;

        long totalJobs = 0;

        for (int coreTime : cores) {
            totalJobs += (time / coreTime); // 각 코어가 처리한 작업 수
        }
        return totalJobs;
    }
}
