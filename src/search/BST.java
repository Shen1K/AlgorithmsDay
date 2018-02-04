package search;

import ChainedList.Queue;

public class BST<Key extends Comparable<Key>, Value> {
	private Node root;
	private class Node{
		private Key key;	//键
		private Value val;	//值
		private Node left, right;	//指向子树的链接
		private int N;	//以该节点为根的子树中的节点数
		
		//节点构造器
		public Node(Key key, Value val, int N){
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}
	
	public int size(){
		return size(root);
	}
	
	private int size(Node x){
		if(x == null) return 0;
		else return x.N;
	}
	
	//用于查找key的value值
	public Value get(Key key){
		return get(root, key);
	}
	
	private Value get(Node x, Key key){
		//以x为根节点的子树中查找并返回key所对应的值
		//如果找不到，就返回null
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0)	return get(x.left, key);
		else if(cmp > 0) return get(x.right, key);
		else return x.val;	
	}
	
	//查找并修改key的value值
	public void put(Key key, Value val){
		//查找key,找到则更新它的值，否则为它创建一个新节点
		root = put(root, key, val);
	}
	
	private Node put(Node x, Key key, Value val){
		if(x == null) return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if(cmp < 0) x.left = put(x.left, key, val);
		if(cmp > 0) x.right = put(x.right, key, val);
		else x.val = val;
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	//返回最小值
	public Key min(){
		return min(root).key;
	}
	private Node min(Node x){
		if(x.left == null) return x;
		return min(x.left);
	}
	
	//返回最大值
	public Key max(){
		return max(root).key;
	}
	private Node max(Node x){
		if(x.right == null) return x;
		return max(x.right);
	}
	
	//小于等于key的最大值
	public Key floor(Key key){
		Node x = floor(root, key);
		if(x == null) return null;
		return x.key;
	}
	private Node floor(Node x, Key key){
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp == 0)	return x;
		if(cmp < 0)	return floor(x.left, key);
		Node t = floor(x.right, key);
		if(t != null)	return t;
		else 	return x;
	}
	
	//大于等于key的最小值
	public Key ceiling(Key key){
		Node x = ceiling(root, key);
		if(x == null) return null;
		return x.key;
	}
	private Node ceiling(Node x, Key key){
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp == 0)	return x;
		if(cmp > 0 ) return ceiling(x.right, key);
		Node t = ceiling(x.left, key);
		if(t != null) return t;
		else return x;
	}
	
	//查找排位为k的值
	public Key select(int k){
		return select(root, k).key;
	}
	private Node select(Node x, int k){
		//返回排名为k的节点
		if(x == null) return null;
		int t = size(x.left);
		if(t > k) return select(x.left, k);
		else if(t < k) return select(x.right, k-t-1);
		else return x;
	}
	
	//返回Key值为key的排名
	public int rank(Key key){
		return rank(key, root);
	}
	private int rank(Key key, Node x){
		//返回以x为根节点的子树中小于x.key的键的数量
		if(x == null)	return 0;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)	return rank(key, x.left);
		else if(cmp > 0)	return 1 + size(x.left) + rank(key, x.right);
		else return size(x.left);
	}
	
	//删除最小元素
	public void deleteMin(){
		root = deleteMin(root);
	}
	private Node deleteMin(Node x){
		if(x.left == null)	return x.right;
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	//删除值为key的元素
	public void delete(Key key){
		root  = delete(root, key);
	}
	private Node delete(Node x, Key k){
		if(x == null) return null;
		int cmp = k.compareTo(x.key);
		if(cmp < 0)	x.left = delete(x.left, k);
		else if(cmp > 0) x.right = delete(x.right, k);
		//当找到key时，进行删除操作
		else{
			if(x.right == null)	return x.left;
			if(x.left == null)	return x.right;
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public Iterable<Key> keys(){
		return keys(min(), max());
	}
	public Iterable<Key> keys(Key lo, Key hi){
		Queue<Key> queue = new Queue<Key>();
		keys(root, queue, lo, hi);
		return  (Iterable<Key>) queue;
	}
	private void keys(Node x, Queue<Key> queue, Key lo, Key hi){
		if(x == null)	return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if(cmplo < 0)	keys(x.left, queue, lo, hi);
		if(cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
		if(cmphi > 0)	keys(x.right,  queue, lo, hi);
	}
	
}
