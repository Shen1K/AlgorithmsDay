package search;

import java.beans.DefaultPersistenceDelegate;

import com.sun.corba.se.spi.orb.StringPair;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

import ChainedList.Queue;
import Stdlib.StdIn;
import Stdlib.StdOut;

public class SeparateChainingHashST<Key, Value> {
	private static final int INIT_CAPACITY = 4;
	
	private int n;	//number of key-value pairs
	private int m;	//hash table size
	private SequentialSearchST<Key, Value>[] st;	//array of linked-list symbol tables
	
	//Initializes an empty symbol table with chains.
	public SeparateChainingHashST() {
		// TODO Auto-generated constructor stub
		this(INIT_CAPACITY);
	}
	
	/**
	 * Initializes an empty symbol table with chains
	 * @param m
	 */
	public  SeparateChainingHashST(int m){
		this.m = m;
		st = (SequentialSearchST<Key, Value>[])new SequentialSearchST[m];
		for(int i = 0; i < m; i++)
			st[i] = new SequentialSearchST<Key, Value>();
	}
	
	//resize the hash table to have the given number of chains,
	//rehashing all of the keys.
	private void resize(int chains){
		SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(chains);
		for(int i = 0; i < m; i++){
			for(Key key : st[i].keys()){
				temp.put(key, st[i].get(key));
			}
		}
		this.m = temp.m;
		this.n = temp.n;
		this.st = temp.st;
	}
	
	//hash value between 0 and m-1
	private int hash(Key key){
		return (key.hashCode() & 0x7fffffff) % m;
	}
	
	/**
	 * Return the number of key-value pairs in this symbol table.
	 * 
	 * @return the number of key-value pairs in this symbol table.
	 */
	public int size(){
		return n;
	}
	
	//Return true if this symbol table is empty.
	public boolean isEmpty(){
		return size() == 0;
	}
	
	//Returns true if this symbol table contain the specified key.
	public boolean contains(Key key){
		if(key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key)	!= null;
	}
	
	/**
	 * Returns the value associated with the specified key in this symbol table.
	 * 
	 * @param key
	 * @return
	 */
	private Value get(Key key) {
		if(key == null) throw new IllegalArgumentException("argument to get() is null");
		int i = hash(key);
		return st[i].get(key);
	}

	/**
	 *Insert the specified key-value pair into the symbol table, overwriting the old
	 *value with the new value if the symbol table already contains the specified key.
	 *Deletes the specified key(and its associated value) from this symbol table if
	 *the specified value is null.
	 * @param key
	 * @param val
	 */
	private void put(Key key, Value val) {
		if(key == null) throw new IllegalArgumentException("first argument to put() is null");
		if(val == null){
			delete(key);
			return;
		}
		
		if(n >= 10*m) resize(2*m);
		
		int i = hash(key);
		if(!st[i].contains(key)) n++;
		st[i].put(key, val);
		
	}

	public void delete(Key key) {
		if(key == null) throw new IllegalArgumentException("argument to delete() is null");
		
		int i = hash(key);
		if(st[i].contains(key)) n--;
		st[i].delete(key);
		
		//halve table size if average length of list <= 2
		if(m > INIT_CAPACITY && n <= 2*m) resize(m/2);
	}
	
	//return keys in symbol table as an Iterable
	public Iterable<Key> keys(){
		Queue<Key> queue = new Queue<Key>();
		for(int i = 0; i < m; i++){
			for(Key key : st[i].keys())
				queue.enqueue(key);
		}
		return queue;
	}
	
	public static void main(String[] args){
		SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<String, Integer>();
		for(int i = 0; !StdIn.isEmpty(); i++){
			String key = StdIn.readString();
			st.put(key, i);
		}
		
		for(String s : st.keys())
			StdOut.println(s + " " + st.get(s));
	}
	
}
