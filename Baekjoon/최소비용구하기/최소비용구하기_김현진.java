import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 최소비용구하기_김현진 {
	public static int N, M, MAX = 987_654_321;
	public static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];
		for (int i = 0; i <= N; i++)
			Arrays.fill(map[i], -1);

		for (int i = 0; i < M; i++) {
			String[] arguments = br.readLine().split(" ");
			int s = Integer.parseInt(arguments[0]);
			int e = Integer.parseInt(arguments[1]);
			int v = Integer.parseInt(arguments[2]);

			if (map[s][e] != -1)
				map[s][e] = Math.min(map[s][e], v);
			else
				map[s][e] = v;
		}

		String[] arguments = br.readLine().split(" ");
		int s = Integer.parseInt(arguments[0]);
		int e = Integer.parseInt(arguments[1]);

		boolean[] visited = new boolean[N + 1];
		int[] dist = new int[N + 1];

		// 초기화
		for (int i = 1; i <= N; i++) {
			dist[i] = map[s][i];
			if (dist[i] == -1)
				dist[i] = MAX;
			if (i == s)
				dist[i] = 0;
		}
		visited[s] = true;

		// 다익스트라
		while (true) {
			// 최소 비용 구하기
			int value = MAX;
			int idx = -1;
			for (int i = 1; i <= N; i++) {
				if (visited[i] || dist[i] > value)
					continue;
				value = dist[i];
				idx = i;
			}

			// 조건에 맞는게 없으면 while 종료
			if (value == MAX)
				break;

			visited[idx] = true;
			for (int i = 1; i <= N; i++) {
				if (visited[i] || map[idx][i] == -1)
					continue;
				dist[i] = Math.min(dist[i], dist[idx] + map[idx][i]);
			}
		}

		System.out.println(dist[e]);

	}
}