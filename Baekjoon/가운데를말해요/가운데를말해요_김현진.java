import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 가운데를말해요_김현진 {
	public static void main(String[] args) throws IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
		PriorityQueue<Integer> min = new PriorityQueue<>();

		int N = Integer.parseInt(sc.readLine());

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(sc.readLine());

			if (min.size() == max.size()) {
				max.offer(num);

				if (!min.isEmpty() && max.peek() > min.peek()) {
					max.offer(min.poll());
					min.offer(max.poll());
				}
			} else {
				min.offer(num);
				if (max.peek() > min.peek()) {
					max.offer(min.poll());
					min.offer(max.poll());
				}
			}
			sb.append(max.peek()).append('\n');
		}
		System.out.println(sb);
	}
}