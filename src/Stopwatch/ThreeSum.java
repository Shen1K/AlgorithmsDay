package Stopwatch;

import com.sun.corba.se.impl.naming.cosnaming.InternalBindingKey;

public class ThreeSum {

	public static int count(int[] a){
		//统计和为0的元组数量
		int N = a.length;
		int cnt = 0;
		for(int i=0; i<N; i++){
			for(int j=i+1; j<N; j++){
				for(int k=j+1; k<N; k++)
					if(a[i]+a[j]+a[k] == 0)
						cnt ++;
			}
		}
		return cnt;
	}
}
