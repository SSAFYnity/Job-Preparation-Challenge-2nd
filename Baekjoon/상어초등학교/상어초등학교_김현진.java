import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class 상어초등학교_김현진 {
    
    static int N, answer=0;
    static int [][] seat, like, empty;
    static List<Integer> list[];
    static List<int []> condition1, condition2;
    
    static int [] dx = {-1,0,1,0};
    static int [] dy = {0,1,0,-1};
    static int [] score = {0,1,10,100,1000};
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        
        N = Integer.parseInt(br.readLine());
        
        seat = new int[N][N];
        
        list = new ArrayList[N*N+1];
        
        for(int i=1;i<N*N+1;i++) list[i] = new ArrayList<>();
        
        for(int i=0;i<N*N;i++) {
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            for(int j=0;j<4;j++) {
                list[student].add(Integer.parseInt(st.nextToken()));
            }
            findSeat(student);
        }
        
        cal();
        System.out.println(answer);
 
    }
    
    
    public static void findSeat(int student) {
        like = new int[N][N];
        empty = new int[N][N];
        condition1 = new ArrayList<>();
        condition2 = new ArrayList<>();
        
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(seat[i][j]==0) {
                    for(int d=0;d<4;d++) {
                        int nx = i+dx[d];
                        int ny = j+dy[d];
                        if(range(nx,ny)) {
                            if(list[student].contains(seat[nx][ny])) { //인접한 좋아하는 학생
                                like[i][j]++;
                            }
                            else if(seat[nx][ny]==0) { //인접한 빈 곳
                                empty[i][j]++;
                            }
                        }
                    }
                }
            }
        }
        
        int max=0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(seat[i][j]==0) {
                    if(max<like[i][j]) {
                        max = like[i][j];
                        condition1.clear();
                        condition1.add(new int[] {i,j});
                    }else if(max==like[i][j]) {
                        condition1.add(new int[] {i,j});
                    }
                }
            }
        }
       //condition1 리스트 사이즈가 1인경우 조건1충족
        if(condition1.size()==1) seat[condition1.get(0)[0]][condition1.get(0)[1]] = student;
        else { //아닌 경우 조건 2,3고려
            max =0;
            for(int i=0;i<condition1.size();i++) {
                int [] temp = condition1.get(i);
                
                if(max<empty[temp[0]][temp[1]]) {
                    max = empty[temp[0]][temp[1]];
                    condition2.clear();
                    condition2.add(new int[] {temp[0],temp[1]});
                }else if(max==empty[temp[0]][temp[1]]) {
                    condition2.add(new int[] {temp[0],temp[1]});
                }
            }
            seat[condition2.get(0)[0]][condition2.get(0)[1]]= student;    
        }
        
    }
    
    public static boolean range(int x, int y) {
        return x>=0 && x<N && y>=0 && y<N;
    }
 
    public static void cal() {
        int count=0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                count=0;
                for(int d=0;d<4;d++) {
                    int nx = i+dx[d];
                    int ny = j+dy[d];
                    
                    if(range(nx,ny) && list[seat[i][j]].contains(seat[nx][ny])) count++;
                }
                answer+=score[count];
            }
        }
    }
}
Colored by Color Scripter