package MinimumTree;

import Stdlib.StdOut;

public class Edge implements Comparable<Edge>{
	private final int v;
	private final int w;
	private final double weight;
	
	//Initializes an edge between vertices {v} and {w} of the given {weight}.
	public Edge(int v, int w, double weight){
		if(v < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");
		if(w < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");
		if(Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	//Returns the weight of this edge
	public double weight(){
		return weight;
	}
	
	//Returns either endpoint of this edge.
	public int either(){
		return v;
	}
	
	//Returns the endpoint of this edge that is different from the given vertex.
	public int Other(int vertex){
		if(vertex == v) return w;
		else if(vertex == w) return v;
		else throw new IllegalArgumentException("Illegal endpoint");
	}
	
	/**
	 * Compares two edges by weight.
	 * Note that {compareTo()} is not consistent with equals(),
	 * which uses the reference equality implementation inherited from Object.
 	 */
	public int compareTo(Edge that) {
		return Double.compare(this.weight, that.weight);
	}
	
	public String toString(){
		return String.format("%d-%d %.5f", v, w, weight);
	}
	
	public static void main(String[] args){
		Edge edge = new Edge(12, 34, 5.67);
		StdOut.println(edge);
	}
	
}
