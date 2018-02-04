package gupiao;
/*
 * ��������һ�����飬���ĵ�i��Ԫ����һ֧�����Ĺ�Ʊ�ڵ�i��ļ۸�
 * ���һ���㷨���ҵ�����������������������ʽ��ס�
 */
public class Test3 {
	//����������Σ��������Ϊ��0-i���������ֵ + i-prices.length-1���������ֵ��
	//���������
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
        //��i������������ֵ
        
        for(int i=0; i<right.length-1; i++){
        	if(max < left[i] + right[i+1]){
        		max = left[i] + right[i+1];
        	}
        }
        
        //���ֻ��һ��
        if(left[left.length-1] > max){
        	max = left[left.length];
        }
        
        return max;
    }
}
