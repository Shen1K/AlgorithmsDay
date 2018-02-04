package SanTongDengFen;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Test {

	//用于保存已经出现过的状态
	public static Set<int[]> cache =  new HashSet<int[]>();

	public static Queue<int[]> waitline = new LinkedList<int[]>();

	public static Map<int[], int[]> map = new HashMap<int[], int[]>();

	//把每次情况保存到cache和waitline中
	public static void save(int[] c, int[] c2) {
		if(cache.contains(c))
			return;
		else if(c[0]==0 && c[1] == 4 && c[2] == 4) {
			map.put(c,c2);
			return;
		}
		else
			waitline.add(c);
			cache.add(c);
			map.put(c, c2);
	}

	//每次倒一次产生的种类
	public static void pull(int[] b) {
		int[] c = new int[b.length];
		for(int i=0; i<b.length; i++){
			c[i] = b[i];
		}
		
		if(c[0] > 0) {
			if(c[1]<5) {
//				if(5-c[1] >= c[0]) {
//					c[0] = 0;
//					c[1] = c[1] + c[0];
//				}else {
//					c[0] = c[0] - (5 - c[1]);
//					c[1] = 5;
//				} 
				dost(c, 0, 1,5);
				save(c, b);
			}
				for(int i=0; i<b.length; i++){
					c[i] = b[i];
				}
			if(c[2] < 8) {
				dost(c,0,2,8);
				save(c,b);
			}
		}
				for(int i=0; i<b.length; i++){
					c[i] = b[i];
				}
		if(c[1] > 0) {
			if(c[0]<3) {
				dost(c, 1, 0,3);
				save(c,b);
			}
				for(int i=0; i<b.length; i++){
					c[i] = b[i];
				}
			if(c[2] < 8) {
				dost(c,1,2,8);
				save(c,b);
			}
		}
				for(int i=0; i<b.length; i++){
					c[i] = b[i];
				}
		if(c[2] > 0) {
			if(c[0]<3) {
				dost(c, 2, 0,3);
				save(c,b);
			}
				for(int i=0; i<b.length; i++){
					c[i] = b[i];
				}
			if(c[1] < 5) {
				dost(c,2,1,5);
				save(c,b);
			}
		}
	}

	//从c[v1]倒水向c[v2]的情况
	public static void dost(int[] c, int v1, int v2, int up) {
		if(c[v2] < up) {
			if(up-c[v2] >= c[v1]) {
				
				c[v2] = c[v2] + c[v1];
				c[v1] = 0;
			}else {
				c[v1] = c[v1] - (up - c[v2]);
				c[v2] = up;
			} 
		}else
			return;
	}

	public static void printint(int[] s) {
		System.out.print("(");
		for(int i=0; i<s.length; i++) {
			System.out.print(s[i] + ",");
		}
		System.out.print(")");
	}
	public static void main(String[] args) {
		int[] a = {0, 0, 8};
		save(a,a);
		while(!waitline.isEmpty()) {
			pull(waitline.poll());
		}
    Set<int[]> ks = map.keySet();
    Iterator<int[]> it = ks.iterator();
    while (it.hasNext()) {
        int[] key = it.next();
        int[] value = map.get(key);
        System.out.println("key=" + key + " value=" + value);
	        
		}
	}
}

