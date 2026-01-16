import java.util.*;

class Solution {
    public int solution(int n, int[] cores) {
        int answer = 0;
        int size = cores.length;

        int[] copyCores = Arrays.copyOf(cores, size);

        if (n <= size) return size;

        n -= size;

        while(n != 0) {

            for(int i = 0; i < size; i++) {
                copyCores[i] -= 1;
                if (copyCores[i] == 0) {
                    copyCores[i] = cores[i];
                    --n;

                    if (n == 0) answer = i + 1;
                }
            }

        }

        return answer;
    }
}