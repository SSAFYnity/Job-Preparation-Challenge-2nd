import java.util.*;

class Solution {    
    public int solution(int n, int[] cores) {
        if (n <= cores.length) { return n+1; }
        
        int s = 0;
        int e = n * Arrays.stream(cores).min().getAsInt();
        int p = (s+e)/2;
        int time = 0;
        int work = 0;
        int answer = 1;
        
        while (s <= e) {
            p = (s+e)/2;
            
            int temp = 0;
            for (int c : cores) { temp += p/c + 1; }
            
            if (temp >= n) {
                e = p-1;
                time = p;
                work = temp;
            }
            else s = p+1;
        }
        
        work -= n;
        
        for (int i = cores.length - 1; i >= 0; i--) {
            if (time%cores[i] == 0) {
                if (work == 0) {
                    answer = i + 1;
                    break;
                }
                work--;
            }
        }
        
        return answer;
    }
}