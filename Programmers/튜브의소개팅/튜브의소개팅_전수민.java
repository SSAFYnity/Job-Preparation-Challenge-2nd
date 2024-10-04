import java.util.*;
import java.io.*;
public class 튜브의소개팅_전수민 {

    static int [][] dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    static int M,N;

    public int[] solution(int m, int n, int s, int[][] time_map) {
        int [] answer = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE};
        M = m; N = n;
        int [][] visited = new int  [m][n];
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.w,b.w)); // 우선순위 큐
        for(int i = 0; i < visited.length; i++){
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        pq.add(new Node(0,0,0,0));
        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(now.r == m-1 && now.c == n-1){
                if(answer[0] > now.acc || (answer[0] == now.acc && answer[1] > now.w)) {
                    answer[0] = now.acc;
                    answer[1] = now.w;
                }
            }

            for(int i = 0; i < 4; i++){
                int ny = now.r + dir[i][0];
                int nx = now.c + dir[i][1];
                if(OOB(ny,nx) || time_map[ny][nx] == -1) continue;
                long newS = (long)now.w + (long)time_map[ny][nx];
                if(newS > s) continue;
                if(now.acc + 1 >= visited[ny][nx]) continue; // 지나쳐온 친구의 수가 같다면, 이미 가중치 적은 버전으로 지나갔음으로, 더 해볼 필요가 없다.
                visited[ny][nx] = now.acc + 1;
                pq.add(new Node(ny,nx,now.w + time_map[ny][nx], now.acc + 1));

            }
        }
        return answer;
    }

    public boolean OOB (int y, int x) {
        return y >= M || x >= N || y < 0 || x < 0 ;
    }
}

class Node {
    int r; // y값
    int c; // x값
    int w; // 가중치
    int acc; // 지나간 경로의 길이

    public Node(int r, int c, int w, int acc){
        this.r = r;
        this.c = c;
        this.w = w;
        this.acc = acc;
    }
}
