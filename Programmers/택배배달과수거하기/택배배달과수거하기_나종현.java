package com.jonghyun.algorithm.challenge;

import java.util.Stack;

public class 택배배달과수거하기_나종현 {
    public void upToDate(int cap, Stack<Integer> s, int[] arrays){
        int currentCap = cap;
        while(currentCap > 0 && !s.isEmpty()){
            int top = s.peek();
            if(currentCap >= arrays[top]){
                currentCap -= arrays[top];
                arrays[top] = 0;
                s.pop();
            }else{
                arrays[top] -= currentCap;
                break;
            }
        }
    }
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        
        Stack<Integer> deliverS = new Stack<>();
        Stack<Integer> pickupS = new Stack<>();
        
        for(int i=0; i<n; i++){
            if(deliveries[i] != 0){
                deliverS.push(i);   
            }
            if(pickups[i] != 0){
                pickupS.push(i);
            }
        }
        
        long answer = 0;
        while(!deliverS.isEmpty() && !pickupS.isEmpty()){
            int topD = deliverS.peek();
            int topP = pickupS.peek();
            answer += ((topD > topP ? topD : topP)+1)*2;
            upToDate(cap, deliverS, deliveries);
            upToDate(cap, pickupS, pickups);
        }
        while(!deliverS.isEmpty()){
            answer += (deliverS.peek()+1)*2;
            upToDate(cap, deliverS, deliveries);
        }
        while(!pickupS.isEmpty()){
            answer += (pickupS.peek()+1)*2;
            upToDate(cap, pickupS, pickups);
        }
        
        return answer;
    }
	

}
