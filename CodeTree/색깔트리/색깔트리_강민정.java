import java.io.*;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class 색깔트리_강민정 {
    static Map<Integer, Node> trees;
    static List<Integer> rootLst;
    static int total;
    static final int COLOR_CNT = 6;

    static class Node {
        int mId;        // 고유 번호
        int pId;        // 부모 노드 번호
        int color;      // 빨간색:1, 주황색:2, 노랑색:3, 초록색:4, 파란색:5
        int maxDepth;   // 서브트리의 최대 깊이
        List<Integer> childs;

        Node(int mId, int pId, int color, int maxDepth) {
            this.mId = mId;
            this.pId = pId;
            this.color = color;
            this.maxDepth = maxDepth;
            this.childs = new ArrayList();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int q = Integer.parseInt(br.readLine());      // 1 <= 명령의 수 <= 100,000
        trees = new HashMap();
        rootLst = new ArrayList();

        // 명령의 정보가 주어짐
        for(int i=0; i<q; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            if(cmd == 100) {  // 노드 추가
                int mId = Integer.parseInt(st.nextToken());     // 1 <= 고유한 번호 <= 100,000
                int pId = Integer.parseInt(st.nextToken());     // 1 <= 부모 노드 번호 <= 100,000
                int color = Integer.parseInt(st.nextToken());   // 1 <= 색깔 <= 5
                int maxDepth = Integer.parseInt(st.nextToken());    // 1 <= 최대 깊이 <= 100

                if(pId == -1) {     // 루트 노드일 때
                    trees.put(mId, new Node(mId, pId, color, maxDepth));
                    rootLst.add(mId);
                } else if(isAbleToAdd(pId)) {       // 부모 노드 pId에 자식을 추가할 수 있으면
                    trees.put(mId, new Node(mId, pId, color, maxDepth));
                    trees.get(pId).childs.add(mId);
                }
            } else if(cmd == 200) {     // 색깔 변경
                int mId = Integer.parseInt(st.nextToken());     // 루트
                int color = Integer.parseInt(st.nextToken());   // 색깔

                changeColor(mId, color);
            } else if(cmd == 300) {     // 색깔 조회
                int mId = Integer.parseInt(st.nextToken());     // 노드 번호

                bw.write(trees.get(mId).color + "\n");
            }
            else {  // 점수 조회
                // 모든 노드의 가치를 계산하여, 가치 제곱의 합을 출력
                total = 0;

                for(int id : rootLst) {
                    calDifferentColor(id);
                }

                bw.write(total + "\n");
            }
        }

        bw.flush();
    }

    private static boolean isAbleToAdd(int pId) {
        Node parent = trees.get(pId);
        if(parent.childs.size() + 1 == parent.maxDepth) {      // 현재 자식을 최대로 가졌다면(자기 자신을 포함하니까 +1)
            return false;
        }
        return true;
    }

    private static void changeColor(int mId, int color) {
        List<Integer> childs = trees.get(mId).childs;

        for(Integer child : childs) {
            trees.get(child).color = color;
            changeColor(child, color);
        }
    }

    private static void calDifferentColor(int mId) {
        Node cur = trees.get(mId);
        boolean[] visit = new boolean[COLOR_CNT];
        visit[cur.color] = true;
        int sum = 1;

        for(Integer child : cur.childs) {
            if(!visit[trees.get(child).color]) {
                visit[trees.get(child).color] = true;
                sum++;
            }
            calDifferentColor(child);
        }
        total += sum * sum;
    }
}