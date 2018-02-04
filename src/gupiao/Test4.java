package gupiao;

/*
 * 假设你有一个数组，它的第i个元素是一支给定的股票在第i天的价格。
 *	设计一个算法来找到最大的利润。你最多可以完成 k 笔交易
 */

public class Test4 {
	
	/*
	firstBuy[i] = max(firstBuy[i-1], prices[i])

	firstSell[i] = max(firstSell[i-1], prices[i]-firstBuy[i-1])

	secBuy[i] = max(secBuy[i-1], prices[i]-firstSell[i-1])

	secSell[i] = max(secSell[i-1], prices[i]-secSell[i-1])
	
	根据以上递推式，可以发现，第k次购买和第k次爬出的值，只跟前一天的购买抛出情况有关
	 */
	
	
    public int maxProfit(int K, int[] prices) {
        if(prices.length == 0){
        	return 0;
        }
        
        if(K>prices.length)
        	K = prices.length;
        
        int[] buy = new int[K];
        int[] sell = new int[K];
        
        for (int i = 0; i < K; ++i) {
            buy[i] = prices[0];
            sell[i] = 0;
        }
        
        for(int i=1; i<prices.length; i++){
            buy[0] = Math.min(buy[0], prices[i]);
            sell[0] = Math.max(sell[0], prices[i]-buy[0]);
            
        	for(int j=1; j<K; j++){
        		
        		buy[j] = Math.min(buy[j],prices[i]-sell[j-1]);
        		sell[j] = Math.max(sell[j], prices[i]-buy[j]);
        	}
        }
        
        return sell[K-1];   
    }
    
    // public int maxProfit(int K, int[] prices) {
    //     if(prices.length == 0){
    //     	return 0;
    //     }
        
    //     if(K <= prices.length){
    //         int[] buy = new int[K];
    //         int[] sell = new int[K];
        
    //         for (int i = 0; i < K; ++i) {
    //             buy[i] = prices[0];
    //             sell[i] = 0;
    //         }
        
    //         for(int i=1; i<prices.length; i++){
    //             buy[0] = Math.min(buy[0], prices[i]);
    //             sell[0] = Math.max(sell[0], prices[i]-buy[0]);
            
    //     	    for(int j=1; j<K; j++){
        		
    //     		    buy[j] = Math.min(buy[j],prices[i]-sell[j-1]);
    //     		    sell[j] = Math.max(sell[j], prices[i]-buy[j]);
    //     	    }
    //         }
        
    //         return sell[K-1];
    //     }else{
    //         int[] buy = new int[prices.length];
    //         int[] sell = new int[prices.length];
        
    //         buy[0] = prices[0];
    //         sell[0] = 0;
        
    //         for(int i=1; i<prices.length; i++){
    //             buy[i] = Math.min(buy[i-1], prices[i]-sell[i-1]);
    //             sell[i] = Math.max(sell[i-1], prices[i]-buy[i-1]);
    //         }
        
    //         return sell[prices.length-1];
    //     }   
    // }
}
