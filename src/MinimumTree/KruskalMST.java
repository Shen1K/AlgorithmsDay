package MinimumTree;

import ChainedList.Queue;
import Stdlib.In;
import Stdlib.StdOut;
import Union_find.UF;

public class KruskalMST {
	private static final double FLOATING_POINT_EPSILON = 1E-12;
	
	private double weight;			//weight of MST
	private Queue<Edge> mst = new Queue<Edge>();	//edges in MST
	
	public KruskalMST(EdgeWeightedGraph G){
		MinPQ<Edge> pq = new MinPQ<Edge>();
		for(Edge e : G.edges()){
			pq.insert(e);
		}
		
		//run greedy algorithm
		UF uf = new UF(G.V());
		while(!pq.isEmpty() && mst.size() < G.V()-1){
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.Other(v);
			if(!uf.connected(v, w)){	//v-w does not create a cycle
				uf.union(v, w);			//merge v and w components
				mst.enqueue(e);			//add edge e to mst
				weight += e.weight();
			}
		}
		
		//check optimality conditions
		assert check(G);
	}
	
	/**
	 * Returns the edges in a minimum spanning tree
	 * @return
	 */
	public Iterable<Edge> edges(){
		return mst;
	}

	//Returns the sum of the edge weights in a minimum spanning tree(or forest).
	public double weight(){
		return weight;
	}
	
	private boolean check(EdgeWeightedGraph g) {
		double total = 0.0;
		for(Edge e : edges()){
			total += e.weight();
		}
		if(Math.abs(total - weight()) > FLOATING_POINT_EPSILON){
			System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", total, weight());
			return false;
		}
		
		//check that it is acyclic
		UF uf = new UF(g.V());
		for(Edge e : edges()){
			int v = e.either(), w = e.Other(v);
			if(uf.connected(v, w)){
				System.err.println("Not a forest");
				return false;
			}
			uf.union(v, w);
		}
		
		//check that it is a spanning forest
		for(Edge e : g.edges()){
			int v = e.either(), w = e.Other(v);
			if(!uf.connected(v, w)){
				System.err.println("Not a spanning forest");
				return false;
			}
		}
		
		for(Edge e : edges()){
			//all edges in MST except e
			uf = new UF(g.V());
			for(Edge f : mst){
				int x = f.either(), y = f.Other(x);
				if(f != e) uf.union(x, y);
			}
			
			for(Edge f : g.edges()){
				int x = f.either(), y = f.Other(x);
				if(!uf.connected(x, y)){
					if(f.weight() < e.weight()){
						System.err.println("Edge " + f + " violates cut optimality conditions");
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public static void main(String[] args){
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        KruskalMST mst = new KruskalMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
	}
}
