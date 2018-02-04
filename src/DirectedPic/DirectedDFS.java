package DirectedPic;

import ChainedList.Bag;
import Stdlib.In;
import Stdlib.StdOut;

public class DirectedDFS {
	private boolean[] marked;	//marked[v] = true if v is reachable from source
	private int count;			//number of vertices reachable from s
	
	//Computes the vertices in digraph {G} that are reachable from the source vertex {s}.
	public DirectedDFS(Digraph G, int s){
		marked = new boolean[G.V()];
		validateVertex(s);
		dfs(G, s);
	}
	
	//Computes the vertices in digraph {G} that are connected to any of the source vertices {sources}.
	public DirectedDFS(Digraph G, Iterable<Integer> sources){
		marked = new boolean[G.V()];
		validateVertices(sources);
		for(int v : sources){
			if(!marked[v]) dfs(G, v); 
		}
	}
	
	/**
	 * use the method of dfs to search the point.
	 * @param g
	 * @param v
	 */
    private void dfs(Digraph g, int v) {
		count ++;
		marked[v] = true;
		for(int w : g.adj(v))
			if(!marked[w]) dfs(g, w);
	}


	// throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
    
    //Is there a directed path from the source vertex (or any of the source vertices) and vertex?
    public boolean marked(int v){
    	validateVertex(v);
    	return marked[v];
    }
   
    //Returns the number of vertices reachable from the source vertex (or source vertices).
    public int count(){
    	return count;
    }
    
    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int V = marked.length;
        for (int v : vertices) {
            if (v < 0 || v >= V) {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
            }
        }
    }
    
    public static void main(String[] args){
    	In in = new In(args[0]);
    	Digraph G = new Digraph(in);
    	
    	Bag<Integer> sources = new Bag<Integer>();
    	for(int i=1; i<args.length; i++){
    		int s = Integer.parseInt(args[i]);
    		sources.add(s);
    	}
    	
    	DirectedDFS dfs = new DirectedDFS(G, sources);
    	
    	for(int v=0; v<G.V(); v++){
    		if(dfs.marked(v)) StdOut.print(v + " ");
    	}
    	StdOut.println();
    }
}
