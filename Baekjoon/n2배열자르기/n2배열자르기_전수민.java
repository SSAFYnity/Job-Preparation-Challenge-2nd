import java.io.*;
import java.util.*;

class Solution {
    /*
        n의 maximum이 10^7이므로, O(n)안에 처리 해야 한다.
        1. 1차원 배열을 만든다.
        2. 1차원 배열의 인덱스를 2차원 배열로 변환한다. (n으로 나눈 몫, n으로 나눈 나머지)
        3. 2차원일때 인덱스를 통해 값을 집어 넣는다.
        4. 배열을 잘라서 반환한다.

    */
    public int[] solution(int n, long left, long right) {
        int [] ans = new int [(int)(right-left+1)];
        int start = 0;
        for(long i = left; i <= right; i++){
            ans[start++] = Math.max((int)(i/n), (int)(i%n)) + 1;
        }
        return ans;
    }
}