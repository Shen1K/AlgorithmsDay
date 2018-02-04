package Stopwatch;

import java.util.Scanner;

import Stdlib.StdIn;
import Stdlib.StdOut;
import Stdlib.StdRandom;

/**
 * ��ʱ���ĳ�����������
 * @author 314-B03
 *
 */

public class Stopwatch {
	
	private final long start;
	public Stopwatch(){
		start = System.currentTimeMillis();
	}
	public double elapsedTime(){
		long now = System.currentTimeMillis();
		return (now - start) / 1000.0;
	}
	
	public static void main(String[] args){
		int N = Integer.parseInt(StdIn.readString());
		int[] a = new int[N];
		for(int i=0; i<N; i++){
			a[i] = StdRandom.uniform(-1000000, 1000000);
		}
		Stopwatch timer = new Stopwatch();
		int cnt = ThreeSum.count(a);
		double time = timer.elapsedTime();
		StdOut.print(cnt + "tripe " + time + "sec");
		
	}
}
