package sort;

import Stdlib.StdOut;

public class Shell {
	public static void sort(Comparable[] a){
		//��a[]����������
		int N = a.length;
		int h = 1;
		while(h < N/5) 
			h = 5*h + 1;

		while(h >= 1){
			//�������Ϊh����
			for(int i=h; i<N; i++){
				//��a[i]���뵽a[i-h], a[i-2*h], a[i-3*h]...֮��
				for(int j=i; j>=h && Example.less(a[j], a[j-h]); j -= h){
					Example.exch(a, j, j-h);
				}
			}
			h = h/5;
		}
	}
	
}
