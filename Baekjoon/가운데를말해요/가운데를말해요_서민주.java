import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i<n; i++) {
            int a = Integer.parseInt(br.readLine());
            if (maxQ.size() == minQ.size()) {
                maxQ.add(a);
                if (!minQ.isEmpty() && maxQ.peek() > minQ.peek()) {
                    minQ.add(maxQ.poll());
                    maxQ.add(minQ.poll());
                }
            } else {
                minQ.add(a);
                if (maxQ.peek() > minQ.peek()) {
                    maxQ.add(minQ.poll());
                    minQ.add(maxQ.poll());
                }
            }
            System.out.println(maxQ.peek());
        }
    }
}