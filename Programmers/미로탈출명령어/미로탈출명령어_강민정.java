class 미로탈출명령어_강민정 {
    static int MAX_N;       // 미로의 세로 크기
    static int MAX_M;       // 미로의 가로 크기
    static int MAX_K;       // 총 이동 거리
    static Node endNode;    // 도착점 객체
    static boolean isFind = false;      // 경로를 찾았는지 여부
    static String answer = "impossible";    // 사전 순으로 빠른 경로
    // dlru순
    static final int[] dx = new int[]{1, 0, 0, -1};
    static final int[] dy = new int[]{0, -1, 1, 0};
    static final String[] position = new String[]{"d", "l", "r", "u"};

    class Node {
        int x;      // 행
        int y;      // 열
        String pos;     // 이동 경로

        Node(int x, int y, String pos) {
            this.x = x;
            this.y = y;
            this.pos = pos;
        }
    }

    /*
        2 <= 미로의 세로 길이 n <= 50
        2 <= 미로의 가로 길이 m <= 50
        1 <= 시작 지점 행 x <= n
        1 <= 시작 지점 열 y <= m
        1 <= 도착 지점 행 r <= n
        1 <= 도착 지점 열 c <= m
        시작점(x, y)와 도착점(r, c)는 같지 않음
        1 <= 이동 횟수 k <= 2,500
    */
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        MAX_N = n;
        MAX_M = m;
        MAX_K = k;
        endNode = new Node(r, c, "");
        int distance = Math.abs(x - r) + Math.abs(y - c);

        if(distance % 2 == k % 2) {    // 주어진 k로 갈 수 있다면
            dfs(0, new Node(x, y, ""));
        }

        return answer;
    }

    public void dfs(int depth, Node node) {
        if(isFind) {    // 사전 순으로 가장 빠른 경로를 찾았다면, 더이상 탐색할 필요 없음
            return;
        }
        if(depth == MAX_K) {     // MAX_K만큼 이동이 끝났다면
            if(node.x == endNode.x && node.y == endNode.y) {    // 도착
                answer = node.pos;  // 경로 저장
                isFind = true;      // 재귀호출을 끝내기 위해 
            }
            return;
        } else if(Math.abs(node.x - endNode.x) + Math.abs(node.y - endNode.y) > (MAX_K - depth)) {   // 남은 이동횟수로 갈 수 없다면
            return;
        }

        for(int d=0; d<4; d++) {    // 사방 탐색
            int nx = node.x + dx[d];
            int ny = node.y + dy[d];

            if(isValid(nx, ny)) {       // 좌표가 유효하면
                dfs(depth + 1, new Node(nx, ny, node.pos + position[d]));
            }
        }
    }

    /*
        좌표가 유효하면 true 반환
    */
    public boolean isValid(int nx, int ny) {
        return 1 <= nx && 1 <= ny && nx <= MAX_N && ny <= MAX_M;
    }
}