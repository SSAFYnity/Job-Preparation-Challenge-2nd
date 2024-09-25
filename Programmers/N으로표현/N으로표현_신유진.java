import java.util.*;

class Solution {
    public int solution(int N, int number) {
        int answer = 0;

        if (N == number) return 1;

        List<Integer>[] data = new ArrayList[9];
        int tmp = 0;
        for(int i = 0; i < 8; i++) {
            data[i+1] = new ArrayList<Integer>();
            tmp = tmp * 10 + N;
            if (tmp == number) return i+1;
            data[i+1].add(tmp);
        }

        // 2 = (1, 1) 3 = (1, 2) 4 = (1, 3), (2, 2) 5 = (1, 4), (2, 3)
        for(int ans = 2; ans <= 8; ans++) {
            for(int div = 1; div < ans; div++) {
                for (int i = 0; i < data[div].size(); i++) {
                    for(int j = 0; j < data[ans - div].size(); j++) {
                        int v1 = data[div].get(i);
                        int v2 = data[ans - div].get(j);
                        int res = v1 + v2;
                        if (res == number) return ans;
                        data[ans].add(res);

                        res = v1 - v2;
                        if (res == number) return ans;
                        data[ans].add(res);

                        res = v1 * v2;
                        if (res == number) return ans;
                        data[ans].add(res);

                        if (v2 == 0) continue;
                        res = v1 / v2;
                        if (res == number) return ans;
                        data[ans].add(res);
                    }
                }
            }

        }

        return -1;
    }
}