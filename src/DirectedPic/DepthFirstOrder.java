package DirectedPic;

import ChainedList.Queue;
import ChainedList.Stack;
import Stdlib.In;
import Stdlib.StdOut;

public class DepthFirstOrder {
	private boolean[] marked;		//marked[v] = has v been marked in dfs?
	private int[] pre;				//pre[v] = preorder number of v
	private int[] post;				//post[v] = postorder number of v
	private Queue<Integer> preorder;	//vertices in preorder
	private Queue<Integer> postorder;	//vertices in postorder
	private int preCounter;			//counter or preorder numbering
	private int postCounter;		//counter for postorder numbering
	
	public DepthFirstOrder(Digraph G){
		pre = new int[G.V()];
		post = new int[G.V()];
		postorder = new Queue<Integer>();
		preorder = new Queue<Integer>();
		marked = new boolean[G.V()];
		for(int v=0; v<G.V(); v++)
			if(!marked[v]) dfs(G, v);
	}

	//run DFS in digraph G from vertex v and compute preorder/postorder
	private void dfs(Digraph g, int v) {
		marked[v] = true;
		pre[v] = preCounter++;
		preorder.enqueue(v);
		for(int w : g.adj(v)){
			if(!marked[w])
				dfs(g, w);
		}
		postorder.enqueue(v);
		post[v] = postCounter++;
	}
	
	//Returns the preorder number of vertex {v}.
	public int pre(int v){
		validateVertex(v);
		return pre[v];
	}
	
	//Returns the postorder number of vertex {v}.
	public int post(int v){
		validateVertex(v);
		return post[v];
	}
	
	//Returns the vertices in postorder.
	public Iterable<Integer> post(){
		return postorder;
	}
	
	//Returns the vertices in preorder.
	public Iterable<Integer> pre(){
		return preorder;
	}
	
	//Returns the vertices in reverse postorder.
	public Iterable<Integer> reversePost(){
		Stack<Integer> reverse = new Stack<Integer>();
		for(int v : postorder)
			reverse.push(v);
		return reverse;
	}
	
	private boolean check(){
		
		//chech that post(v) is consistent with post()
		int r = 0;
		for(int v : post()){
			if(post(v) != r){
				StdOut.println("post(v) and post() inconsistent");
			}
			r++;
		}
		
		//check that pre(v) is consistent with pre()
		r = 0;
		for(int v : pre()){
			if(pre(v) != r){
				StdOut.println("pre(v) and pre() inconsistent");
				return false;
			}
			r++;
		}
		return true;
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
    	
    	DepthFirstOrder dfs = new DepthFirstOrder(G);
    	StdOut.println("  v pre post");
    	StdOut.println("------------");
    	for(int v=0; v<G.V(); v++){
    		StdOut.printf("%4d %4d %4d\n", v, dfs.pre(v), dfs.post(v));
    	}
    	
    	StdOut.print("Preorder: ");
    	for(int v : dfs.pre()){
    		StdOut.print(v + " ");
    	}
    	StdOut.println();
    	
    	StdOut.print("Postorder: ");
    	for(int v : dfs.post()){
    		StdOut.print(v + " ");
    	}
    	StdOut.println();
    	
    	StdOut.print("Reverse postorder: ");
    	for(int v : dfs.reversePost()){
    		StdOut.print(v + " ");
    	}
    	StdOut.println();
    }
}
