package sort;

import com.sun.corba.se.impl.naming.cosnaming.InternalBindingKey;

public class Merge {
	private static Comparable[] aux;
	
	public static void sort(Comparable[] a){
		aux = new Comparable[a.length];
		sort(a, 0, a.length-1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi){
		//������a[1o...hi]����
		if(hi <= lo) return;
		int mid = lo + (hi - lo)/2;
		sort(a, lo, mid);	//����������
		sort(a, mid+1, hi);	//���Ұ������
		merge(a, lo, mid, hi);	//�鲢
	}
	
	public static void merge(Comparable[] a, int lo, int mid, int hi){
		//��a[lo..mid]��a[mid+1..hi]�ϲ�
		int i = lo, j = mid+1;
		
		for(int k=lo; k<=hi; k++){
			aux[k] = a[k];
		}
		for(int k=lo; k<hi; k++){
			if(i>mid) a[k] = aux[j++];
			else if(j>hi) a[k] = aux[i++];
			else if(Example.less(aux[j], aux[i])) a[k] = aux[j++];
			else a[k] = aux[i++];
		}
	}
}
