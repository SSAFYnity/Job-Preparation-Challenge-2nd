import java.util.*;

class Solution {
    
    public int solution(int n, int[] cores) {
        if (n <= cores.length) {
            return n;
        }
        int left = 0;   // n번째 작업이 시행되는 시간
        int right = n * 10000;
        int processed = 0;  // n번째 작업이 시행되는 시간 바로 직전 시간까지 처리된 작업 개수
        while(left <= right) {
            int mid = (left + right) / 2;
            int count = calculate(mid, cores);  // 처리할 수 있는 작업 개수
            if (count >= n) {
                right = mid - 1;
            } else {
                left = mid + 1;
                processed = count;
            }
        }
        
        int remain = n - processed; // n번째 작업이 시행되는 시간에 처리되는 작업 개수
        for (int i = 0; i < cores.length; i++) {
            // 해당 시간에 작업하는 코어라면
            if (left % cores[i] == 0) {
                remain--;
                if (remain == 0) {
                    return i + 1;
                }
            }
        }
        return 0;
    }

    private int calculate(int totalTime, int[] cores) {
        int count = cores.length;
        for (int processTime : cores) {
            // 해당 코어는 totalTime 내에 (totalTime / processTime)개의 작업 처리 가능
            count += totalTime / processTime;
        }
        return count;
    }

}