import java.util.Arrays;

class 외벽점검_강민정 {
    static int[] permutation;   // dist 순열
    static int people;      // 사람 수(dist 배열 길이)
    static int[] distArr;   // 입력으로 받은 dist 배열
    static int[][] weaks;   // weak 배열을 순환하는 모든 경우
    static int weakLen;     // weak 배열 길이
    static int answer;      // 취약 지점 점검을 위한 최소 인원 수

    public int solution(int n, int[] weak, int[] dist) {
        people = dist.length;
        answer = people + 1;
        permutation = new int[people];
        this.distArr = dist;
        weakLen = weak.length;
        weaks = new int[weakLen][weakLen];
        weaks[0] = weak.clone();

        // 시작점을 바꿔가면서 모든 취약점 케이스 구하기
        for(int start = 1; start<weakLen; start++) {    // 행
            for(int i=1; i<weakLen; i++) {      // 열
                weaks[start][i - 1] = weaks[start - 1][i];
            }
            weaks[start][weakLen - 1] = n + weaks[start - 1][0];
        }

        dfs(new boolean[people], 0);

        if(answer == people + 1) {      // 모든 취약지점을 점검할 수 없다면
            answer = -1;
        }
        return answer;
    }

    /*
        dist 배열의 순열 생성 후 모든 취약점에 대해 탐색
        visit: 인덱스 = dist 배열의 인덱스
        depth: 현재까지 고른 원소 갯수
    */
    public void dfs(boolean[] visit, int depth) {
        if(depth == people) {      // 기저조건
            for(int[] weakCase : weaks) {       // 모든 취약점 케이스 탐색
                int weakStartIdx = 0;       // 취약 시작 지점
                int weakEndIdx = weakStartIdx + 1;      // 취약 도착 지점
                int person = 0;     // 사람 인덱스

                while(weakStartIdx < weakLen && weakEndIdx < weakLen && person < people) {  // 인덱스가 초과하지 않을 동안
                    // 한 사람이 최대한 취약 지점을 점검해야한다.
                    while(weakEndIdx < weakLen && permutation[person] >= weakCase[weakEndIdx] - weakCase[weakStartIdx]) {
                        weakEndIdx++;   // 다음 취약지점으로 가기
                    }
                    weakStartIdx = weakEndIdx;      // 취약 시작 지점 갱신 
                    person++;       // 다음 사람으로 교체하기
                }

                // 모든 외벽을 점검했다면
                if(weakStartIdx == weakLen) {
                    answer = Math.min(answer, person);      // 투입한 사람 수 갱신
                }
            }
            return;
        }

        for(int i=0; i<people; i++) {       // 모든 사람을 순서만 다르게 한 번씩 선택
            if(visit[i]) {  // 이미 선택한 사람이라면
                continue;
            }
            visit[i] = true;
            permutation[depth] = distArr[i];        // depth번째에 i번 사람 선택
            dfs(visit, depth + 1);
            visit[i] = false;
            permutation[depth] = 0;     // depth번째에 아무도 선택하지 않음
        }
    }
}