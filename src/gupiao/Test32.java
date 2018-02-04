package gupiao;
/*
 * ��������һ�����飬���ĵ�i��Ԫ����һ֧�����Ĺ�Ʊ�ڵ�i��ļ۸�
 * ���һ���㷨���ҵ�����������������������ʽ��ס�
 */
import com.sun.corba.se.impl.orbutil.RepositoryIdUtility;

/*
firstBuy[i] = max(firstBuy[i-1], prices[i])

firstSell[i] = max(firstSell[i-1], prices[i]-firstBuy[i-1])

secBuy[i] = max(secBuy[i-1], prices[i]-firstSell[i-1])

secSell[i] = max(secSell[i-1], prices[i]-secSell[i-1])
 */
public class Test32 {

	public int maxProfit(int[] prices){
		if(prices.length <= 1){
			return 0;
		}
		
		int[] firstBuy = new int[prices.length];
		int[] firstSell = new int[prices.length];
		int[] secBuy = new int[prices.length];
		int[] secSell = new int[prices.length];
		
		firstBuy[0] = prices[0];
		firstSell[0] = 0;
		secBuy[0] = prices[0];
		secSell[0] = 0;
		
		for(int i=1; i<prices.length; i++){
			firstBuy[i] = Math.min(firstBuy[i-1], prices[i]);
			firstSell[i] = Math.max(firstSell[i-1], prices[i]-firstBuy[i-1]);
			secBuy[i] = Math.min(secBuy[i-1], prices[i]-firstSell[i-1]);
			secSell[i] = Math.max(secSell[i-1], prices[i]-secBuy[i-1]);
		}
		
		return secSell[prices.length-1];
	}
	
	public static void main(String[] args){
		int[] a = {2,1,2,0,1};
		Test32 t = new Test32();
		System.out.println(t.maxProfit(a));
	}
}
