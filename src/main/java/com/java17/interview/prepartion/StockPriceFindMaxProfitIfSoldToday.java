package com.java17.interview.prepartion;

public class StockPriceFindMaxProfitIfSoldToday {
    
    public static int findMaxProfit(int[] arr){
        int profit = 0;
        int max = arr[arr.length-1];
        for(int i=arr.length-2;i>=0;i--){
            if(arr[i]>max){
                max = arr[i];
            }else{
                profit = Math.max(profit,max-arr[i]);
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        
        System.out.println(findMaxProfit(new int[]{2,3,1,4,5}));
    }
}
