import java.util.*;

class Solution {
    static int answer = 8, inf = Integer.MAX_VALUE;
    
    public int solution(int N, int number) {
        TreeSet<Integer>[] arr = new TreeSet[9];
        
        int c = 0;
        for (int i = 0; i < 8; i++) {
            c = c * 10 + N;
            arr[i+1] = new TreeSet<>();
            arr[i+1].add(c);
        }
        if (arr[1].contains(number)) {return 1;}
        
        for (int i = 2; i <= 8; i++) {
            for (int o = i/2 + (i%2 == 0 ? 0 : 1); o < i; o++) {
                for (int one : arr[o]) {
                    for (int two : arr[i-o]) {
                        
                        if (one != 0) {
                            arr[i].add(one + two);
                            arr[i].add(two-one);
                            arr[i].add(two/one);
                        }
                        if (two != 0) {
                            arr[i].add(one * two);
                            arr[i].add(one-two);
                            arr[i].add(one/two);
                        }
                    }
                }
            }
            if (arr[i].contains(number)) return i;
        }
        
        return -1;
    }
}