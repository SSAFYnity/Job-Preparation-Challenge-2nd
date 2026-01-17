import java.util.*;

class Solution {
    static int n, m, r, c, k;
    static int[][] dr = {{1,0,0,-1}, {0,-1,1,0}};
    static String answer = "";
    static Map<Integer, Character> map = new HashMap<>(); // d l r u
    
    public void back(int y, int x, String str) {
        if (str.length() >= k) {
            if (y == r && x == c && (answer.equals("") || answer.compareTo(str) > 0)) {answer = str;}
            return;
        }
        
        for (int d = 0; d < 4; d++) {
            int w = y + dr[0][d];
            int v = x + dr[1][d];
            if (isOut(w,v)) {continue;}
            if ((abs(r-w)+abs(c-v)) > (k-str.length()-1)) {continue;}
            
            if (!answer.equals("") && answer.compareTo(str + map.get(d)) < 0) { continue; }
            back(w, v, str + map.get(d));
        }
    }
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        toStatic(n, m, r, c, k);
        setMap();
        if ((k-(abs(r-x)+abs(c-y)))%2!=0) return "impossible";
        
        back(x-1, y-1, "");
        if (answer.equals("")) return "impossible";
        return answer;
    }
    
    public boolean isOut(int y, int x) {
        return y < 0 || y >= n || x < 0 || x >= m;
    }
    
    public void toStatic(int n, int m, int r, int c, int k) {
        Solution.n = n;
        Solution.m = m;
        Solution.r = r-1;
        Solution.k = k;
        Solution.c = c-1;
    }
    
    public void setMap() {
        map.put(2, 'r');
        map.put(0, 'd');
        map.put(1, 'l');
        map.put(3, 'u');
    }
    
    public int abs(int num) {
        if (num < 0) {return (-1) * num;}
        return num;
    }
}