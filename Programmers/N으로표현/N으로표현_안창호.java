import java.util.*;
class Solution {
    public int solution(int N, int number) {
        if (N == number) return 1;

        List<HashSet<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) { // 1~8 번의 N 사용 횟수를 저장하기 위한 반복
            list.add(new HashSet<>());
        }
        list.get(1).add(N);

        for (int i = 2; i < 9; i++) {
            for (int j = 1; j < i; j++) {
                HashSet<Integer> a = list.get(j);
                HashSet<Integer> b = list.get(i - j);

                for (int one : a) {
                    for (int two : b) {
                        list.get(i).add(one + two);
                        list.get(i).add(one - two);
                        list.get(i).add(one * two);
                        if (two != 0) list.get(i).add(one / two); // divide by zero
                    }
                }

                list.get(i).add(Integer.parseInt(String.valueOf(N).repeat(i))); // i가 3이라면 NNN의 형태
            }
            // N을 i번 사용한 연산의 조합에서 number가 존재한다면
            if (list.get(i).contains(number)) {
                return i;
            }
        }
        // 최솟값이 8 이하인 경우가 없다면
        return -1;
    }
}