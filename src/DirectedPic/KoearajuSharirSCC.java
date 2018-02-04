package DirectedPic;

import ChainedList.Queue;
import Stdlib.In;
import Stdlib.StdOut;

public class KoearajuSharirSCC {
	
	private boolean[] marked;	//marked[v] = has vertex v been visited?
	private int[] id;			//id[v] = id of strong component containing v
	private int count;			//number of strong-connected components
	
	public KoearajuSharirSCC(Digraph G){
		//compute reverse postorder of reverse graph
		DepthFirstOrder dfs = new DepthFirstOrder(G.reverse());
		//run DFS on G, using reverse postorder to guide calculation
		marked = new boolean[G.V()];
		id = new int[G.V()];
		for(int v : dfs.reversePost()){
			if(!marked[v]){
				dfs(G, v);
				count++;
			}
		}
		
		assert check(G);
	}

	//does the id[] array contain the strongly connected components?
	private boolean check(Digraph g) {
		TransitiveClosure tc = new TransitiveClosure(g);
		for(int v=0; v<g.V(); v++){
			for(int w=0; w<g.V(); w++){
				if(stronglyConnected(v, w) != (tc.reachable(v, w) && tc.reachable(w, v)))
					return false;
			}
		}
		return true;
	}

	public boolean stronglyConnected(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		return id[v] == id[w];
	}

	//DFS on graph G
	private void dfs(Digraph g, int v) {
		marked[v] = true;
		id[v] = count;
		for(int w : g.adj(v)){
			if(!marked[w])
				dfs(g, w);
		}	
	}
	
	public int id(int v){
		validateVertex(v);
		return id[v];
	}
	
	//Returns the number of strong components.
	public int count(){
		return count;
	}
	
    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
    
    public static void main(String[] args){
    	In in = new In(args[0]);
    	Digraph G = new Digraph(in);
    	KoearajuSharirSCC scc = new KoearajuSharirSCC(G);
    	
    	//number of connected components
    	int m = scc.count();
    	StdOut.println(m + " strong components");
    	
    	//compute list of vertices in each strong component
    	Queue<Integer>[] components = (Queue<Integer>[])new Queue[m];
    	for(int i=0; i<m; i++){
    		components[i] = new Queue<Integer>();
    	}
    	for(int v=0; v<G.V(); v++){
    		components[scc.id(v)].enqueue(v);
    	}
    	
    	//print result
    	for(int i=0; i<m; i++){
    		for(int v : components[i]){
    			StdOut.print(v + " ");
    		}
    		StdOut.println();
    	}
    }
}
