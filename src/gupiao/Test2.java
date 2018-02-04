package gupiao;

/*
 * ������һ�����飬���ĵ�i��Ԫ����һ�������Ĺ�Ʊ�ڵ�i��ļ۸�
 * ���һ���㷨���ҵ����������������ɾ����ܶ�Ľ���(���������Ʊ)��
 * Ȼ��,�㲻��ͬʱ����������(��������ٴι���ǰ���۹�Ʊ)��
 */

public class Test2 {
//����̰��
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
