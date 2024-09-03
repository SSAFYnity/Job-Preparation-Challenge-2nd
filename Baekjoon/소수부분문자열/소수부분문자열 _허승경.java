import java.io.*;

public class Main {
    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        boolean [] arr = new boolean[100001];
        arr[0] = true;
        arr[1] = true;

        for (int i = 2; i < 50001; i++) {
            if(!arr[i]) {
                for (int j = i + i; j < 100001; j += i) {
                    arr[j] = true;
                }
            }
        }

        while(true){
            String str = br.readLine();
            if(str.equals("0")) break;
            long maxNum = Long.MIN_VALUE;

            for(int i = 0; i < str.length(); i++){
                String tmp = "";
                    for(int j = i; j < str.length(); j++){
                    if(j-i >= 5) break;
                    tmp += str.charAt(j);
                    if(!arr[Integer.parseInt(tmp)]){
                        maxNum = Math.max(maxNum, Long.parseLong(tmp));
                    }

                }
            }

            sb.append(maxNum + "\n");
        }

        System.out.println(sb);
    }
}
