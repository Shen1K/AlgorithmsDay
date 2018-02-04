package Graph;

import ChainedList.Stack;
import Stdlib.In;
import Stdlib.StdOut;

public class Cycle {
	private boolean[] marked;	
	private int[] edgeTo;
	private Stack<Integer> cycle;
	
	/*
	 * Determines whether the undirected graph {G} has a cycle and
	 * if so, finds such a cycle.
	 */
	public Cycle(Graph G){
		if(hasSelfLoop(G)) return;
		if(hasParallelEdges(G)) return;
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		for(int v=0; v<G.V(); v++){
			if(!marked[v])
				dfs(G, -1, v);
		}
	}

	private boolean hasParallelEdges(Graph g) {
		marked = new boolean[g.V()];
		
		for(int v=0; v<g.V(); v++){
			//check for parallel edges incident to v
			for(int w : g.adj(v)){
				if(marked[w]){
					cycle = new Stack<Integer>();
					cycle.push(v);
					cycle.push(w);
					cycle.push(v);
					return true;
				}
				marked[w] = false;
			}
		}
		return false;
	}

	//does this graph have a self loop?
	//side effect: initialize cycle to be self loop
	private boolean hasSelfLoop(Graph g) {
		for(int v=0; v<g.V(); v++){
			for(int w : g.adj(v)){
				if(v == w){
					cycle = new Stack<Integer>();
					cycle.push(v);
					cycle.push(v);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Returns true if the graph {G} has a cycle.
	 * @return
	 */
	public boolean hasCycle(){
		return cycle != null;
	}
	
	/**
	 * Returns a cycle in the graph {G}.
	 * @return
	 */
	public Iterable<Integer> cycle(){
		return cycle;
	}
	
	private void dfs(Graph G, int u, int v){
		marked[v] = true;
		for(int w : G.adj(v)){
			//short circuit if cycle already found
			if(cycle != null) return;
			
			if(!marked[w]){
				edgeTo[w] = v;
				dfs(G, u, v);
			}
			
			//check for cycle (but disregard reverse of edge leading to v)
			else if(w != u){
				cycle = new Stack<Integer>();
				for(int x=v; x!=w; x=edgeTo[x]){
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
			

		    /**
		     * Unit tests the {@code Cycle} data type.
		     *
		     * @param args the command-line arguments
		     */

		}
	}
	
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        Cycle finder = new Cycle(G);
        if (finder.hasCycle()) {
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
        else {
            StdOut.println("Graph is acyclic");
        }
    }
}
