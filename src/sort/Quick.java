package sort;

import Stdlib.StdRandom;

public class Quick {
	public static void sort(Comparable[] a){
		//������a����������������������
		//StdRandom.shuffle(a);
		sort(a, 0, a.length-1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi){
		if(hi<=lo+5){
			Example.InsertSort(a, lo, hi);
			return;
		} 
			
		int j = partition(a, lo, hi);
		sort(a, lo, j-1);
		sort(a, j+1, hi);
	}
	
	private static int partition(Comparable[] a, int lo, int hi){
		//�������з�Ϊa[lo..i-1]��a[i], a[i+1, hi-1]
		int i = lo, j = hi+1;
		Comparable v = a[lo];
		while(true){
			//ɨ�����ң����ɨ���Ƿ����������λ��
			while(Example.less(a[++i], v))
				if(i == hi)
					break;
			while(Example.less(v, a[--j]))
				if(j == lo)
					break;
			if(i >= j)
				break;
			Example.exch(a, i, j);
		}
		Example.exch(a, lo, j);
		return j;
	}
}
