package sort;

import Stdlib.StdOut;
import Stdlib.StdRandom;
import Stopwatch.Stopwatch;

public class SortCompare {
	//ѡ�������㷨����������ʱ��
	public static double time(String alg, Comparable[] a){
		Stopwatch timer = new Stopwatch();
		if(alg.equals("Insertion")) Example.InsertSort(a);
		if(alg.equals("Selection")) Example.selectionSort(a);
		if(alg.equals("Shell")) Shell.sort(a);
		if(alg.equals("Merge")) Merge.sort(a);
		if(alg.equals("Quick")) Quick.sort(a);
		if(alg.equals("Quick3way")) Quick3way.sort(a);
		return timer.elapsedTime();
	}
	
	public static double timeRandomInput(String alg, int N, int T){
		//ʹ���㷨alg��T������ΪN����������
		double total = 0.0;
		Double[] a = new Double[N];
		for(int t=0; t<T; t++){
			//����һ�β��ԣ�����һ�����鲢����
			for(int i=0; i<N; i++)
				a[i] = StdRandom.uniform();
			total += time(alg, a);
		}
		return total;
	}
	
	public static void main(String[] args){
		String alg1 = "Insertion";
		String alg2 = "Selection";
		String alg3 = "Shell";
		String alg4 = "Merge";
		String alg5 = "Quick";
		String alg6 = "Quick3way";
		int N = 1000000;
		int T = 100;
		//double t1 = timeRandomInput(alg1, N, T);//�㷨1����ʱ��
		//double t2 = timeRandomInput(alg2, N, T);//�㷨2����ʱ��
		//double t3 = timeRandomInput(alg3, N, T);//�㷨3����ʱ��
		double t4 = timeRandomInput(alg4, N, T);
		double t5 = timeRandomInput(alg5, N, T);//�㷨5����ʱ��
		double t6 = timeRandomInput(alg6, N, T);
//		StdOut.printf("For %d random Doubles\n  %s is", N, alg3);
//		StdOut.printf(" %.2f times faster than %s\n", t1/t3, alg1);
		//StdOut.print(t1 + "  " + t2 + " " + t3);
		StdOut.print(t4 +" " + t5 + " " + t6);
	}
}
