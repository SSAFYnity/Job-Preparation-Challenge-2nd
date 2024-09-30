import java.util.*;

public class N으로표현_여창영{    
    public int solution(int N, int number) {
        List<Set<Integer>> setList = new ArrayList<>();
        for(int i = 0; i <= 8; i++) {
            setList.add(new HashSet<>());
        }
        
        setList.get(1).add(N);

        StringBuilder sb = new StringBuilder();
        sb.append(N);

        for(int i = 2; i < setList.size(); i++) {
            sb.append(N);
            Set<Integer> newSet = setList.get(i);
            newSet.add(Integer.parseInt(sb.toString()));

            for(int j = 1; j <= i; j++) {
                Set<Integer> setA = setList.get(j);
                Set<Integer> setB = setList.get(i - j);

                for (Integer numA : setA) {
                    for (Integer numB : setB) {
                        newSet.add(numA + numB);
                        newSet.add(numA - numB);
                        newSet.add(numA * numB);
                        if(numA != 0 && numB != 0) {
                            newSet.add(numA / numB);
                        }
                    }
                }
            }
        }
        for (Set<Integer> set : setList) {
            if(set.contains(number)) return setList.indexOf(set);
        }
        return -1;
    }
}