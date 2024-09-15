import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> list;
    static boolean [][] visited;
    static int [][] classroom;
    static int n;
    static int tn;
    static int [] dx = {-1, 0, 1, 0};
    static int [] dy = {0, 1, 0, -1};

    static class Point implements Comparable<Point>{
        int cnt;
        int empty;
        int x;
        int y;

        public Point(int cnt, int empty, int x, int y){
            this.cnt = cnt;
            this.empty = empty;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point p){
            // 우선 순위 : cnt -> empty -> x ->y
            if(this.cnt < p.cnt) return 1;
            else if(this.cnt == p.cnt){
                if(this.empty < p.empty) return 1;
                else if(this.empty == p.empty){
                    if(this.x > p.x) return 1;
                    else if(this.x == p.x){
                        if(this.y > p.y) return 1;
                    }
                }
            }
            return -1;
        }
    }

    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        tn = n*n;

        visited = new boolean[n][n];
        classroom = new int[n][n];
        list = new ArrayList<>();
        for(int i = 0; i <= tn; i++){
            list.add(new ArrayList<>());
        }
        int order [] = new int[tn];
        for(int i = 0; i < tn; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            list.get(start).add(Integer.parseInt(st.nextToken()));
            list.get(start).add(Integer.parseInt(st.nextToken()));
            list.get(start).add(Integer.parseInt(st.nextToken()));
            list.get(start).add(Integer.parseInt(st.nextToken()));
            order[i] = start;       // 진행 순서
        }

        for(int i = 0; i < tn; i++){
            findFriends(order[i]);
        }

        int result = 0;
        int count = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                count = 0;
                int num = classroom[i][j];
                for(int t = 0; t < 4; t++){
                    int tx = i + dx[t];
                    int ty = j + dy[t];

                    if(!isRange(tx, ty)) continue;
                    if(list.get(num).contains(classroom[tx][ty])) count++;
                }
                if(count == 1) result += 1;
                else if(count == 2) result += 10;
                else if(count == 3) result += 100;
                else if(count == 4) result += 1000;
            }
        }
        System.out.println(result);
    }

    static void findFriends(int start){
        PriorityQueue<Point> pq = new PriorityQueue<>();

        int fcount = 0;      // 친구 수
        int tcount = 0;      // 주변 좌석

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                // 1. 현재 위치 빈자리 확인
                if(visited[i][j]) continue;
                fcount = 0;
                tcount = 0;
                // 2. 사방 탐색
                for(int t = 0; t < 4; t++){
                    int tx = i + dx[t];
                    int ty = j + dy[t];

                    if(!isRange(tx, ty)) continue;
                    if(!visited[tx][ty]) tcount++;
                    if(list.get(start).contains(classroom[tx][ty]))
                        fcount++;
                }
                pq.add(new Point(fcount, tcount, i, j));

            }
        }
        Point p = pq.poll();
        visited[p.x][p.y] = true;
        classroom[p.x][p.y] = start;

    }

    static boolean isRange(int i, int j){
        boolean result = false;
        if(i >= 0 && i < n && j >= 0 && j < n) result = true;

        return result;
    }
}
