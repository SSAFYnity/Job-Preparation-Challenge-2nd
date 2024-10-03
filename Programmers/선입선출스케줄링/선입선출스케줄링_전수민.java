import java.io.*;
import java.util.*;
// 0. core별로 작업 처리 시간이 적혀 있어서, N초에 얼마나 일을 끝냈는지 확인 가능
// 1. 일의 개수를 기준으로 이분 탐색 + upper bound로 초과해서 일을 한 것 중 target에 가까운 최소값 찾기
// 2. 최소값에서 코어 하나씩 지워가며, 마지막으로 일한 코어 찾기

class Solution {
    public int solution(int n, int[] cores) {
        int ceilingTime = bs_time(n, cores);
        int work = cores.length;
        for(int i = 0; i < cores.length; i++){
            work += ceilingTime/cores[i];
        }
        int t = cores.length-1;
        while (work >= n){
            if(ceilingTime%cores[t] == 0) {
                t--; work--;
            }else{
                t--;
            }
        }
        return t+1+1;
    }
    // tgt에 제일 가까우면서도 그것을 초과하는 일의 개수를 처리하는 `시간`을 구한다.
    public int bs_time(int tgt, int [] cores){
        int top = 10000 * tgt; // 천장: 제일 오래 걸리는 코어로만 일처리 했을 때 걸리는 시간
        int btm = 1;

        while(btm <= top) {
            int mid = (top + btm)/2;
            int amt = cores.length; // mid 시간에 처리할 수 있는 일의 량
            for(int i = 0; i < cores.length; i++){
                amt += mid/cores[i];
            }
            if(amt < tgt) btm = mid+1;
            else if(amt >= tgt) top = mid-1;
        }
        return btm;
    }
}