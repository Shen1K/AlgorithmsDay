package sort;

import Stdlib.In;
import Stdlib.StdOut;
import sun.net.www.content.audio.wav;

public class Example {
	public static void sort(Comparable[] a){
		Shell.sort(a);
	}
	
	public static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) < 0;
	}
	
	public static void exch(Comparable[] a, int i, int j){
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	private static void show(Comparable[] a){
		//在单行中打印数组
		for(int i=0; i<a.length; i++){
			StdOut.print(a[i]+" ");
		}
		StdOut.println();
	}
	
	public static boolean isSorted(Comparable[] a){
		//测试数组元素是否有序
		for(int i=1; i<a.length; i++){
			if(less(a[i], a[i-1])) return false;
		}
		return true;
	}
	
	public static void main(String[] args){
		//从标准输入读取字符串，将他们排序输出
		String[] a = In.readStrings();
		sort(a);
		assert isSorted(a);
		show(a);
	}
	
	//选择排序
	public static void selectionSort(Comparable[] a){
		//将a[]按升序排列
		int n = a.length;
		for(int i=0; i<n; i++){
			//将a[i]和a[i+1..N]中最小元素交换
			int min = i;
			for(int j=i+1; j<n; j++){
				if(less(a[j], a[min])) min = j;
			}
			exch(a, i, min);
		}
	}
	
	//插入排序
	public static void InsertSort(Comparable[] a){
		//将a[]按升序排列
		int N = a.length;
		for(int i=1; i<N; i++){
			for(int j=i; j>0 && less(a[j], a[j-1]); j--)
				exch(a, j, j-1);
		}
	}
	
	public static void InsertSort(Comparable[] a, int lo, int hi){
		int N = hi - lo + 1;
		for(int i=1; i<N; i++){
			for(int j=i; j>0 && less(a[j], a[j-1]); j--)
				exch(a, j, j-1);
		}
	}
}
