package gupiao;

/*
 * 假设有一个数组，它的第i个元素是一个给定的股票在第i天的价格。
 * 设计一个算法来找到最大的利润。你可以完成尽可能多的交易(多次买卖股票)。
 * 然而,你不能同时参与多个交易(你必须在再次购买前出售股票)。
 */

public class Test2 {
//类似贪心
//    public int maxProfit(int[] prices) {
//    	
//    	if(prices.length <= 1)
//    		return 0;
//    	
//    	int begin = prices[0];
//    	int end = prices[0];
//    	int sum = 0;
//    	
//        for(int i=0; i<prices.length; i++){
//        	if(prices[i]>=end){
//        		end = prices[i];
//        	}else{
//        		sum = sum + end - begin;
//        		begin = prices[i];
//        		end = prices[i];
//        	}
//        }
//        sum = sum + end - begin;
//        return sum;
//    }
	
    public int maxProfit(int[] prices){
        if(prices.length <= 1)
            return 0;
        
        int[] buy = new int[prices.length];
        int[] sell = new int[prices.length];
        
        buy[0] = prices[0];
        sell[0] = 0;
        
        for(int i=1; i<prices.length; i++){
            buy[i] = Math.min(buy[i-1], prices[i]-sell[i-1]);
            sell[i] = Math.max(sell[i-1], prices[i]-buy[i-1]);
        }
        
        return sell[prices.length-1];
    }
    
}
