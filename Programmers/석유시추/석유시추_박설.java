import java.util.*;
import java.util.stream.*;

class Solution {

	int dx[] = {0,0,1,-1};
	int dy[] = {1,-1,0,0};

	public int solution(int[][] land) {

		int answer = 0;
		List<int[]> list = new ArrayList<>();
		HashMap<Integer, Integer> myMap = new HashMap<>();

		int idx = 2;
		int cnt = 0;

		for(int i =0 ;i < land.length; i++){
			for(int j = 0 ;j < land[0].length;j ++){
				if(land[i][j] == 1){
					cnt = dfs(land,i,j,idx);
					myMap.put(idx++,cnt);
				}
			}
		}

		for(int j =0 ;j< land[0].length; j++){
			Set<Integer> s = new HashSet<>();
			for(int i = 0;i < land.length ;i ++){
				if(land[i][j] > 1 && !s.contains(land[i][j] )){
					s.add(land[i][j]);
				}
			}

			int addAll = s
				.stream()
				.mapToInt(myMap::get)
				.sum();

			answer = Math.max(answer, addAll);
		}
		return answer;
	}
	public int dfs(int[][] l , int x, int y,int m){
		if(x < 0 || y < 0 || x > l.length -1 || y > l[0].length-1 || l[x][y] !=1 )return 0;
		int ret = 1;
		l[x][y] = m;
		for(int k =0; k < 4; k++){
			ret += dfs(l,x + dx[k], y+dy[k], m);
		}
		return ret;
	}
}
