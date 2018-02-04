package Union_find;

import Stdlib.StdIn;
import Stdlib.StdOut;

public class UF {
	private int[] id;	//分量id(以触点作为索引)
	private int count;	//分量数量
	
	public UF(int N){
		//初始化分量id数组
		count = N;
		id = new int[N];
		for(int i=0; i<N; i++){
			id[i] = i;
		}
	}
	
	public int count(){
		return count;
	}
	
	public boolean connected(int p, int q){
		return find(p) == find(q);
	}
	
//	public int find(int p){
//		return id[p];
//	}
	
//	public void union(int p, int q){
//		//将p和q归并到相同的分量中
//		int pID = find(p);
//		int qID = find(q);
//		
//		//如果p和q已经在相同的分量中则不需要采取任何行为
//		if(pID == qID) return;
//		
//		//将p的分量重命名为q的名称
//		for(int i=0; i<id.length; i++)
//			if(id[i] == pID) 
//				id[i] = qID;
//		count--;
//		
//	}
	
	//quick-union方法
	private int find(int p){
		while(p != id[p]) 
			p = id[p];
		return p;
		
	}
	
	public void union(int p, int q){
		//将p和q根节点统一
		int pRoot = find(p);
		int qRoot = find(q);
		if(pRoot == qRoot) return;
		id[pRoot] = qRoot;
		
		count--; 
	}
	
	
	public static void main(String[] args){
		//解决由StdIn得到的动态连接性问题
		int N = StdIn.readInt(); //读取触点数量
		UF uf = new UF(N);
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
