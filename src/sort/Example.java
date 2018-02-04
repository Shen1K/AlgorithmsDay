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
		//�ڵ����д�ӡ����
		for(int i=0; i<a.length; i++){
			StdOut.print(a[i]+" ");
		}
		StdOut.println();
	}
	
	public static boolean isSorted(Comparable[] a){
		//��������Ԫ���Ƿ�����
		for(int i=1; i<a.length; i++){
			if(less(a[i], a[i-1])) return false;
		}
		return true;
	}
	
	public static void main(String[] args){
		//�ӱ�׼�����ȡ�ַ������������������
		String[] a = In.readStrings();
		sort(a);
		assert isSorted(a);
		show(a);
	}
	
	//ѡ������
	public static void selectionSort(Comparable[] a){
		//��a[]����������
		int n = a.length;
		for(int i=0; i<n; i++){
			//��a[i]��a[i+1..N]����СԪ�ؽ���
			int min = i;
			for(int j=i+1; j<n; j++){
				if(less(a[j], a[min])) min = j;
			}
			exch(a, i, min);
		}
	}
	
	//��������
	public static void InsertSort(Comparable[] a){
		//��a[]����������
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
