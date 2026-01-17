class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int dlen = n, plen = n;

        while(dlen > 0 || plen > 0) {
            dlen = findFinalHouse(dlen, deliveries);
            plen = findFinalHouse(plen, pickups);

            answer += 2 * Math.max(dlen, plen);

            box(dlen, deliveries, cap);
            box(plen, pickups, cap);

        }
        return answer;
    }
    int findFinalHouse(int len, int[] arr) {
        for(int i=len-1; i>= 0; i--) {
            if(arr[i] > 0) {
                len = i+1;
                break;
            }else if(i == 0) {
                len = 0;
            }
        }
        return len;
    }

    void box(int len, int[] arr, int boxes) {
        for(int i=len-1; i>= 0; i--) {
            if(arr[i] - boxes >= 0) {
                arr[i] -= boxes;
                break;
            }else {
                boxes -= arr[i];
                arr[i] = 0;
            }
        }
    }
}