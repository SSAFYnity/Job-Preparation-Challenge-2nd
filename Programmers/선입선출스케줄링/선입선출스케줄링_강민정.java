class Solution {
    /*
        n: 처리해야 될 작업의 개수
        cores: 각 코어의 처리시간이 담긴 배열
    */
    public int solution(int n, int[] cores) {
        return binarySearch(n, cores);      // 마지막 작업을 처리하는 코어의 번호
    }

    public int binarySearch(int n, int[] cores) {
        int min = 0;        // 최소 시간
        int max = 10000 * n;        // 최대 시간
        int time = 0;
        int m = 0;
        int answer = 0;         // 마지막 작업을 처리하는 코어의 번호

        while(true) {
            int mid = (min + max) / 2;
            int count = 0;

            if(mid == 0) {      // 시간이 0이면
                count = cores.length;
            } else {
                count = cores.length;
                for(int i=0; i<cores.length; i++) {
                    count += (mid / cores[i]);      // 시간 mid까지 처리할 수 있는 작업량
                }
            }

            if(min > max) {
                break;
            }

            if(count >= n) {
                max = mid - 1;
                time = mid;
                m = count;
            } else {
                min = mid + 1;
            }
        }

        m -= n;
        for(int i=cores.length - 1; i>=0; i--) {
            if(time % cores[i] == 0) {
                if(m == 0) {
                    answer = i + 1;
                    break;
                }
                m--;
            }
        }

        return answer;
    }
}