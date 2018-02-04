package gupiao;
/*
 * 假设你有一个数组，它的第i个元素是一支给定的股票在第i天的价格。
 * 设计一个算法来找到最大的利润。你最多可以完成两笔交易。
 */
public class Test3 {
	//最大买卖两次，可以理解为（0-i天买卖最大值 + i-prices.length-1天买卖最大值）
	//中最大的情况
    public int maxProfit(int[] prices) {
        
        if(prices.length==0){
            return 0;
        }
        
        int[] left=new int[prices.length];
        int[] right=new int[prices.length];
        
        int buy, sell, profit = 0;
        
        buy = prices[0];
        for(int i=0; i<left.length; i++){
        	sell = prices[i];
        	
        	if(sell < buy){
        		buy = sell;
        		left[i] = profit;
        	}else if(sell-buy < profit){
        		left[i] = profit;
        	}else{
        		profit = sell - buy;
        		left[i] = profit;
        	}
        }
        
        sell = prices[prices.length-1];
        profit = 0;
        for(int i=prices.length-1; i>=0; i--){
        	buy = prices[i];
        	
        	if(sell < buy){
        		right[i] = profit;
        		sell = buy;
        	}else if(sell-buy<profit){
        		right[i] = profit; 
        	}else{
        		profit = sell - buy;
        		right[i] = profit;
        	}
        }
        
        int max = 0;
        //求i天左右相加最大值
        
        for(int i=0; i<right.length-1; i++){
        	if(max < left[i] + right[i+1]){
        		max = left[i] + right[i+1];
        	}
        }
        
        //如果只买一次
        if(left[left.length-1] > max){
        	max = left[left.length];
        }
        
        return max;
    }
}
