package sort;

public class MergeBU {
	private static Comparable[] aux;	//�鲢����ĸ�������
	public static void sort(Comparable[] a){
		//����lgN�������鲢
		int N = a.length;
		aux = new Comparable[N];
		for(int sz=1; sz<N; sz--){
			for(int lo=0; lo<N-sz; lo +=sz+sz){
				Merge.merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
			}
		}
	}
}
