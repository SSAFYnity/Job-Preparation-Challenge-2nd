import java.util.*;

class Solution {
    public int solution(int n, int[] cores) {
        int core = n;
        int time = 0;
        
        core -= cores.length;
        while (core > 0) {
            time++;
            for (int i = 1; i<cores.length; i++) {
                if (cores[i]%time==0) {
                    core--;
                }
            }
        }
        return time;
    }
}