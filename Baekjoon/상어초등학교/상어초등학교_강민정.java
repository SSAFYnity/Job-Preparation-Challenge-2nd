import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

class 상어초등학교_강민정 {
    static final int[] score = new int[]{0, 1, 10, 100, 1000};
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static int n;

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int emptyCnt;
        int likeCnt;

        public Node(int x, int y, int emptyCnt, int likeCnt) {
            this.x = x;
            this.y = y;
            this.emptyCnt = emptyCnt;
            this.likeCnt = likeCnt;
        }

        @Override
        public int compareTo(Node o) {
            if(this.likeCnt != o.likeCnt) {
                return -(this.likeCnt - o.likeCnt);     // 인접한 곳에 좋아하는 학생이 많은 순
            }

            if(this.emptyCnt != o.emptyCnt) {
                return -(this.emptyCnt - o.emptyCnt);   // 인접한 칸 중에서 비어있는 칸이 많은 순
            }

            if(this.x != o.x) {      // 행 번호가 작은 순
                return this.x - o.x;
            }

            return this.y - o.y;        // 열 번호가 작은 순
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());        // 3 <= 격자 크기 <= 20
        int[][] arr = new int[n][n];
        int answer = 0;     // 만족도 총 합
        List<List<Integer>> likesLst = new ArrayList();

        // 학생 별로 좋아하는 학생 리스트를 담기 위한 2차원 리스트 초기화
        for(int i=0; i<=n*n; i++) {
            likesLst.add(new ArrayList());
        }

        for(int i=0; i<n*n; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());     // 학생 번호
            List<Integer> likes = new ArrayList();      // 학생별로 좋아하는 학생의 번호를 담는 리스트

            // 학생 번호 idx가 좋아하는 학생 4명의 번호 입력 받기
            while(st.hasMoreTokens()) {
                likes.add(Integer.parseInt(st.nextToken()));
            }
            likesLst.set(idx, likes);

            PriorityQueue<Node> pq = new PriorityQueue();
            for(int x=0; x<n; x++) {        // 행
                for(int y=0; y<n; y++) {    // 열
                    if(arr[x][y] != 0) {    // 빈 자리가 아니라면
                        continue;
                    }

                    // 사방탐색
                    int likeCnt = 0;    // 인접한 자리에 좋아하는 학생 수
                    int emptyCnt = 0;   // 인접한 자리에 빈 자리 수
                    for(int d=0; d<4; d++) {
                        int nx = dx[d] + x;
                        int ny = dy[d] + y;

                        if(isNotValid(nx, ny)) {      // 인덱스 범위 초과
                            continue;
                        }

                        if(arr[nx][ny] == 0) {      // 빈 자리라면
                            emptyCnt++;
                            continue;
                        }

                        if(likes.contains(arr[nx][ny])) {       // 친한 친구라면
                            likeCnt++;
                        }
                    }
                    pq.add(new Node(x, y, emptyCnt, likeCnt));
                }
            }

            Node node = pq.poll();
            arr[node.x][node.y] = idx;      // idx번 학생을 자리에 앉히기
        }

        // 만족도 총합 구하기
        for(int i=0; i<n; i++) {        // 행
            for(int j=0; j<n; j++) {    // 열
                int cnt = 0;        // 만족도

                for(int d=0; d<4; d++) {    // 4방 탐색
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if(isNotValid(nx, ny)) {    // 인덱스가 유효하지 않다면
                        continue;
                    }

                    if(likesLst.get(arr[i][j]).contains(arr[nx][ny])) {     // 좋아하는 학생 번호라면
                        cnt++;      // 만족도 증가
                    }
                }
                answer += score[cnt];
            }
        }
        System.out.println(answer);     // 만족도의 총합 출력
    }

    public static boolean isNotValid(int nx, int ny) {
        return 0 > nx || 0 > ny || nx >= n || ny >=n;
    }
}