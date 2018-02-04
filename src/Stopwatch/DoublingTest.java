package Stopwatch;

import Stdlib.StdOut;
import Stdlib.StdRandom;

public class DoublingTest {
	public static double timeTrial(int N){
		//为处理N个随机的六位数的ThreeSum.count()计时
		int MAX = 1000000;
		int[] a = new int[N];
		for(int i=0; i<N; i++){
			a[i] = StdRandom.uniform(-MAX, MAX);
		}
		Stopwatch timer = new Stopwatch();
		int cnt = ThreeSum.count(a);
		return timer.elapsedTime();
	}
	
	public static void main(String[] args){
		for(int N=250; true; N +=N){
			double time = timeTrial(N);
			StdOut.printf("%7d %5.1f\n", N, time);
		}
	}
}
