package StaticSETofInts;
/**
 * Search whether the number exits in the Array;
 */
import java.util.Arrays;
import java.util.Stack;

public class StaticSetofInts {
	private int[] a;
	public StaticSetofInts(int[] keys){
		a = new int[keys.length];
		for(int i=0; i<keys.length; i++){
			a[i] = keys[i]; // �����Ը���
		}
		
		Arrays.sort(a);
		
		Stack stack = new Stack();
	}
	


	//�Ƿ����ĳ��ֵ
	public boolean contains(int key){
//		int i = rank(key);
//		if(i == -1)return false;
//		else return true;
		
		return rank(key) != -1;
	}
	
	//���ֲ���
	public int rank(int key){
		int lo = 0;
		int hi = a.length-1;
		
		while (lo <= hi){
			//keyֵҪô��a[lo...hi]�У�Ҫôû��
			int mid = (hi + lo)/2;

			if(a[mid] > key) hi = mid-1;
			else if(a[mid] < key) lo = mid+1;
			else return mid;
		}
		return -1;
	}
	
}
