import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		boolean arr[] = new boolean[100001];
		arr[0] = true;
		arr[1] = true;
		for (int i = 2; i < 50001; i++) {
			if(!arr[i]) {
				for (int j = i + i; j < 100001; j += i) {
					arr[j] = true;
				}
			}
		}

		String str = "";
		while (!(str = bf.readLine()).equals("0")) {
			int max = 0;
			for (int i = 0; i < str.length(); i++) {
				for (int j = i; j < i + 6; j++) {
					if (j >= str.length())
						break;
					int num = Integer.parseInt(str.substring(i, j + 1));
					if (num > 100000) {
						continue;
					}
					if (!arr[num]) {
						max = Math.max(num, max);
					}
				}
			}
			bw.write(max + "\n");
		}
		bw.flush();
	}
}
