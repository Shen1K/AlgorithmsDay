package sort;

import Stdlib.StdRandom;

public class Quick {
	public static void sort(Comparable[] a){
		//对数组a随机化，消除对输入的依赖
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
		//将数组切分为a[lo..i-1]，a[i], a[i+1, hi-1]
		int i = lo, j = hi+1;
		Comparable v = a[lo];
		while(true){
			//扫描左右，检查扫描是否结束并交换位置
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
