import java.io.*;
import java.util.*;

public class 택배배달과수거하기_여창영 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int deliver,pickup,check;
        
        deliver = 0;
        pickup = 0;
        
        for(int i=n-1;i>=0;i--){
            if(deliveries[i]!=0 || pickups[i]!=0){
                check=0;
                
                while(deliver<deliveries[i] || pickup<pickups[i]){
                    deliver+=cap;
                    pickup+=cap;
                    check++;
                }
                
                deliver-=deliveries[i];
                pickup-=pickups[i];
                answer+=(i+1)*2*check;
            }
        }
        
        return answer;
    }
}
