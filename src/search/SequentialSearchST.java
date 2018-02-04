package search;

import ChainedList.Queue;
import Stdlib.StdIn;
import Stdlib.StdOut;

public class SequentialSearchST<Key, Value> {
	private int n;		//number of key-value pairs
	private Node first;	//the linked list of key-value pairs
	
	//a helper linked list data type
	private class Node{
		//链表节点的定义
		Key key;
		Value val;
		Node next;
		
		public Node(Key key, Value val, Node next){
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}
	
	//Initializes an empty symbol table.
	public SequentialSearchST(){
	}
	
	//Returns the number of key-value pairs in this symbol table.
	public int size(){
		return n;
	}
	
	//Returns true if this symbol table is empty.
	public boolean isEmpty(){
		return size() == 0;
	}
	
	/**
	 * Returns true if this symbol table contains the specified key.
	 * @param key
	 * @return
	 */
	public boolean contains(Key key){
		if(key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}
	
	/**
	 * Returns the value associated with the given key in this symbol table.
	 * @param key
	 * @return
	 */
	public Value get(Key key){
		//查找给定的键，返回相关的值
		for(Node x = first; x != null; x = x.next){
			if(key.equals(x.key))
				return x.val; 	//命中
		}
		return null;
	}
	
	/**
	 * Insert the specified key-value pair into the symbol table,overwriting the
	 * old value with the new value if the symbol table already contains the specified key.
	 * 
	 * @param key
	 * @param val
	 */
	public void put(Key key, Value val){
		//查找给定的键，找到则更新其值，否则在表中新建节点
		for(Node x = first; x != null; x = x.next){
			if(key.equals(x.key)){
				x.val = val;
				return;
			}
		}
		first = new Node(key, val, first); //未命中，新建节点
		n++;
	}
	
	/**
	 * Removes the specified key and its associated value from this symbol table
	 * (if the key is in this symbol table).
	 * @param key
	 */
	public void delete(Key key){
		if(key == null) throw new IllegalArgumentException("argument to delete() is null");
		first = delete(first, key);
	}
	
	//delete key in linked list beginning at Node x
	//warning: function call stack too large if table is large
	public Node delete(Node x, Key key){
		if(x == null) return null;
		if(key.equals(x.key)){
			n--;
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
	}
	
	
	public Iterable<Key> keys(){
		Queue<Key> queue = new Queue<Key>();
		for(Node x = first; x != null; x = x.next){
			queue.enqueue(x.key);
		}
		return (Iterable<Key>) queue;	
	}
	
	public static void main(String[] args){
		SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
		for(int i = 0; !StdIn.isEmpty(); i++){
			String key = StdIn.readString();
			st.put(key, i);
		}
		for(String s : st.keys())
			StdOut.println(s + " " + st.get(s));
	}
}
