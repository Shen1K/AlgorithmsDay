package sort;

import Stdlib.StdOut;

public class Shell {
	public static void sort(Comparable[] a){
		//将a[]按升序排列
		int N = a.length;
		int h = 1;
		while(h < N/5) 
			h = 5*h + 1;

		while(h >= 1){
			//将数组变为h有序
			for(int i=h; i<N; i++){
				//将a[i]插入到a[i-h], a[i-2*h], a[i-3*h]...之中
				for(int j=i; j>=h && Example.less(a[j], a[j-h]); j -= h){
					Example.exch(a, j, j-h);
				}
			}
			h = h/5;
		}
	}
	
}
