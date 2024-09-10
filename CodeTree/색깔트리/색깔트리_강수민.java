import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class Main {
    private static class ColorTree {
        int mId, pId, color, maxDepth;
        ArrayList<Integer> childList;

        ColorTree(int mId, int pId, int color, int maxDepth) {
            this.mId = mId;
            this.pId = pId;
            this.color = color;
            this.maxDepth = maxDepth;
            childList = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "[ mId:" + mId + " / pId:" + pId + " / color:" + color + " / maxDepth:" + maxDepth + " / childList:" + childList.toString() + " ]";
        }
    }

    private static class ColorPoint {
        boolean[] used;
        int point;

        ColorPoint() {
            this.used = new boolean[6];
            this.point = 0;
        }

        public void add(boolean[] used) {
            for(int i = 1; i < 6; i++) {
                if(this.used[i]) used[i] = true;
            }
        }

        public void calPoint() {
            int temp = 0;
            for(int i = 1; i < 6; i++) {
                if(this.used[i]) {
                    temp++;
                }
            }
            point = temp * temp;
        }
    }

    private static Map<Integer, ColorTree> treeList;
    private static ArrayList<Integer> rootList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        treeList = new HashMap<>();
        rootList = new ArrayList<>();

        int Q = Integer.parseInt(br.readLine());

        while(Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());

            if (order == 100) { // 1) 노드 추가

                int mId = Integer.parseInt(st.nextToken());
                int pId = Integer.parseInt(st.nextToken());
                int color = Integer.parseInt(st.nextToken());
                int maxDepth = Integer.parseInt(st.nextToken());

                if (pId == -1) { // 루트 노드
                    treeList.put(mId, new ColorTree(mId, pId, color, maxDepth));
                    rootList.add(mId);
                } else if (checkDepth(pId)) { // 일반 노드 최대 깊이 안넘어서 삽입 가능
                    treeList.put(mId, new ColorTree(mId, pId, color, maxDepth));
                    treeList.get(pId).childList.add(mId);
                }

            } else if (order == 200) { // 2) 색깔 변경

                int mId = Integer.parseInt(st.nextToken());
                int color = Integer.parseInt(st.nextToken());
                chgColor(mId, color);

            } else if (order == 300) { // 3) 색깔 조회

                int mId = Integer.parseInt(st.nextToken());
                // sb.append(treeList.get(mId).color).append('\n');
                System.out.println(treeList.get(mId).color);

            } else { // 4) 점수 조회

                int sum = 0;

                for(int i : rootList) {
                    sum += findSubColor(i).point;
                }

                System.out.println(sum);
            }

            // for(int i : rootList) {
            //     print(i, 0);
            // }
            // System.out.println("================");
        }

        bw.write(sb.toString());
        bw.flush();
    }

    public static boolean checkDepth(int idx) { // 루트 노드 까지 Depth 괜찮은지 확인

        ColorTree tree = treeList.get(idx);
        int depth = 1;
        if (tree.maxDepth == depth) return false;

        while(tree.pId != -1) {
            tree = treeList.get(tree.pId);
            if (tree.maxDepth == ++depth) return false;
        }

        return true;
    }

    public static void chgColor(int idx, int color) { // 서브트리의 컬러 변경

        ColorTree tree = treeList.get(idx);
        tree.color = color;

        for(int i : tree.childList) {
            chgColor(i, color);
        }

    }

    public static ColorPoint findSubColor(int idx) { // 서브 트리의 컬러 종류(boolean[6])와 점수 합계 반환, 재귀로 구성

        ColorTree tree = treeList.get(idx);
        // System.out.println("findSubColor 현재트리 : " + tree);

        ColorPoint now = new ColorPoint();
        now.used[tree.color] = true;

        int sum = 0;

        for(int i : tree.childList) {
            ColorPoint child = findSubColor(i);
            child.add(now.used);
            sum += child.point;
        }

        now.calPoint();
        now.point += sum;
        // System.out.println("point : " + now.point);
        // System.out.println(Arrays.toString(now.used));

        return now;
    }

    public static void print(int idx, int depth) {
        for(int i = 0; i < depth; i++) {
            System.out.print("\t\t");
        }

        System.out.println(treeList.get(idx));

        for(int i : treeList.get(idx).childList) {
            print(i, depth + 1);
        }
    }
}


/*

< 문제 분석 >
 - 색깔 트리를 만든다.
    - 트리의 속성 : 고유번호 mId, 부모노드번호 pId, 색깔 color, 최대깊이 maxDepth
        - 자식 노드를 저장할 ArrayList<Integer> childList 필요할듯
    - 4가지 기능이 가능해야 한다.
        1) 노드 추가
        2) 색깔 변경
        3) 색깔 조회
        4) 점수 조회

< 풀이 계획 >
    - 색깔 트리 클래스를 구현하고, 필요 메서드를 만들어야 할듯
        1) 노드 추가
            - checkDepth() : 루트 노드 까지 Depth 괜찮은지 확인
        2) 색깔 변경
            - chgColor() : 서브트리의 컬러 변경
        3) 색깔 조회
            - 메서드 필요 없음
        4) 점수 조회
            - findSubColor() : 서브 트리의 컬러 종류(boolean[6])와 점수 합계 반환
                                , 재귀로 구성
    - 전체 트리를 담는 리스트 필요 : treeList
    - 시간 복잡도 계산
        - Q는 최대 10만
        - id는 최대 10만
            - ColorTree[100_001] : 버려지는 공간이 많음 -> 공간 복잡도 이슈
            - Map<Integer, ColorTree> : 단점이 크게 없음. 루트만 넣는게 아니라 모든 노드 다 넣기
        - '4) 점수 조회'에서 중복 안 일어나게 root들에서 시작
        - 정확한 시간 복잡도를 계산하기엔 어려움. 하나하나 비효율 개선하기.
    - 공간 복잡도 계산
        - ColorTree : 4Byte * 4 + a
        - ColorTree[100_001] 구현 시, 16만 바이트(0.16MB?) 이상
        - Map으로 최적화하자
< 테케 모음 >


*/