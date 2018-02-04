package Union_find;

import Stdlib.StdIn;
import Stdlib.StdOut;

public class WeightedQuickUnionUF {
	private int[] id; 	//父链接数组
	private int[] sz;	//各个根节点所对应的分量大小
	private int count;	//连通分量的数量
	public WeightedQuickUnionUF(int N){
		count = N;
		id = new int[N];
		for(int i=0; i<N; i++){
			id[i] = i;
		}
		sz = new int[N];
		for(int i=0; i<N; i++)
			sz[i] = 1;
	}
	
	public int count(){
		return count;
	}
	
	public boolean connected(int p, int q){
		return find(p) == find(q);
	}
	
	public int find(int p){
		//跟随链接找到根节点
		while(p != id[p])
			p = id[p];
		return p;
	}
	
	public void union(int p, int q){
		int i = find(p);
		int j = find(q);
		if(i == j) return;
		
		//将小树根节点连接到大树的根节点
		if(sz[i] < sz[j]){
			id[i] = j;
			sz[j] += sz[i];
		}else{
			id[j] = i;
			sz[i] += sz[j]; 
		}
		count--;
	}
	
	public static void main(String[] args){
		//解决由StdIn得到的动态连接性问题
		int N = StdIn.readInt(); //读取触点数量
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
		while(!StdIn.isEmpty()){
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if(uf.connected(p, q)) continue;
			uf.union(p, q);
			StdOut.print(p + " " + q);
		}
		StdOut.println(uf.count + "components");
	}
}
