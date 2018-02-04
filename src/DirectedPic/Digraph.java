package DirectedPic;

import java.util.NoSuchElementException;

import ChainedList.Bag;
import ChainedList.Stack;
import Stdlib.In;
import Stdlib.StdOut;

public class Digraph {
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private final int V;		//number of vertices in this digraph
	private int E;				//number of edges in this digraph
	private Bag<Integer>[] adj;	//adj[v] = adjacency list for vertex v
	private int[] indegree;		//indegree[v] = indegree of vertex v
	
	/**
	 * Initializes an empty digraph with {V} vertex
	 * @param V
	 */
	public Digraph(int V){
		if(V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nononegative");
		this.V = V;
		this.E = 0;
		indegree = new int[V];
		adj = (Bag<Integer>[])new Bag[V];
		for(int v=0; v<V; v++){
			adj[v] = new Bag<Integer>();
		}
	}
	
	public Digraph(In in){
		try {
			this.V = in.readInt();
			if(V < 0) throw new IllegalArgumentException("number of "
					+ "vertices in a Digraph must be nonnegative");
			indegree = new int[V];
			adj = (Bag<Integer>[])new Bag[V];
			for(int v=0; v<V; v++){
				adj[v] = new Bag<Integer>();
			}
			int E = in.readInt();
			if(E < 0) throw new IllegalArgumentException("number of edges "
					+ "in a Digraph must be nonnegative");
			for(int i=0; i<E; i++){
				int v = in.readInt();
				int w = in.readInt();
				addEdge(v, w);
			}
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("invalid input format in Digraph constructor", e);
		}
	}
	
	//Initializes a new digraph that is a deep copy of the specified digraph
	public Digraph(Digraph G){
		this(G.V());
		this.E = G.E();
		for(int v=0; v<V; v++)
			this.indegree[v] = G.indegree(v);
		for(int v=0; v<G.V(); v++){
			//reverse so that adjacency list is in same order as original
			Stack<Integer> reverse = new Stack<Integer>();
			for(int w : G.adj[v]){
				reverse.push(w);
			}
			for(int w : reverse){
				adj[v].add(w);
			}
		}
	}
	
	//Returns the number of directed edges incident to vertex {v}.
	//This is known as the indegree of vertex {v}.
	public int indegree(int v) {
		validateVertex(v);
		return indegree[v];
	}
	
	/**
	 * Returns the number of directed edges incident from vertex {v}.
	 * This is known as the outdegree of vertex{v}.
	 * @param v
	 * @return
	 */
	public int outdegree(int v){
		validateVertex(v);
		return adj[v].size();
	}

	//Returns the number of Edges in this digraph.
	public int E() {
		return E;
	}

	//Returns the number of vertices in this digraph.
	public int V() {
		return V;
	}

	//Adds the directed edge v->w to this digraph 
	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		adj[v].add(w);
		indegree[w]++;
		E++;	
	}
	
	//Returns the vertices adjacent from vertex in this digraph.
	public Iterable<Integer> adj(int v){
		validateVertex(v);
		return adj[v];
	}
	
	//Returns the reverse of the digraph. Direction of edge is opposite.
	public Digraph reverse(){
		Digraph reverse = new Digraph(V);
		for(int v=0; v<V; v++){
			for(int w : adj(v)){
				reverse.addEdge(w, v);
			}
		}
		return reverse;
	}
	
	//Returns a string representation of the graph.
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges " + NEWLINE);
		for(int v=0; v<V; v++){
			s.append(String.format("%d: ", v));
			for(int w : adj[v]){
				s.append(String.format("%d ", w));
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}
	

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
    
    public static void main(String[] args){
    	In in = new In(args[0]);
    	Digraph G = new Digraph(in);
    	StdOut.println(G);
    }
}
