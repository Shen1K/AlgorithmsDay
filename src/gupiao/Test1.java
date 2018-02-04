package gupiao;

/*
 * ������һ�����飬���ĵ�i��Ԫ����һ֧�����Ĺ�Ʊ�ڵ�i��ļ۸�
 * ��������ֻ�������һ�ν���(����,һ��������Ʊ),���һ���㷨���ҳ��������
 */
public class Test1 {

//    public int maxProfit(int[] prices) {
//    	if(prices.length <= 1)
//    		return 0;
//    	
//    	
//    	int max = prices[0] - prices[1];
//    	
//        for(int i=0; i<prices.length-1; i++){
//        	for(int j=i+1; j<prices.length; j++){
//        		int sum = prices[i] - prices[j];
//        		if(sum > max){
//        			max = sum;
//        		}
//        	}
//        }
//        if(max < 0)
//        	max = 0;
//        
//        return max;
//    }
	
    public int maxProfit(int[] prices){
        if(prices==null || prices.length==0){
            return 0;
        }
        
        int min = prices[0];
        int profit = 0;
        for(int i=0; i<prices.length; i++){
          profit = Math.max(profit, prices[i]-min);
          if(min > prices[i]) min = prices[i];
        }
        
        return profit;
    }
}
