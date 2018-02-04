package search;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import Stdlib.StdIn;
import Stdlib.StdOut;


public class ST<Key extends Comparable<Key>, Value>implements Iterable<Key> {

	private TreeMap<Key, Value> st;
	
	/**
	 * Initializes an empty symbol table
	 */
	public ST(){
		st = new TreeMap<Key, Value>();
				
	}
	
	public Value get(Key key){
		if(key == null) throw new IllegalArgumentException("calls get() with null key");
		return st.get(key);
	}
	
	
	/**
	 * Inserts the specified key-value pair into the symbol table, overwriting the old
	 * value with the new value if the symbol table already contains the specified key.
	 * Deletes the specified key (and its associated value) from this symbol table
	 * if the specified value is null, it throws an exception.
	 * 
	 * @param key
	 * @param val
	 */
	public void put(Key key, Value val){
		if(key == null) throw new IllegalArgumentException("calls put() with null key");
		if(val == null) st.remove(key);
		else			st.put(key, val);
	}
	
	/**
	 * Removes the specified key and its associated value from this symbol table
	 * (if the key is in this symbol table).
	 * 
	 * @param key
	 */
	public void delete(Key key){
		if(key == null) throw new IllegalArgumentException("calls delete() with null key");
		st.remove(key);
	}
	
	/**
	 * Return true if this symbol table contain the given key.
	 * 
	 * @param key
	 * @return
	 */
	public boolean contains(Key key){
		if(key == null) throw new IllegalArgumentException("calls contains() with null key");
		return st.containsKey(key);
	}
	
	/**
	 * Return the number of key-value pairs in this symbol table.
	 * 
	 * @return
	 */
	public int size() {
		return st.size();
	}
	
	/**
	 * Return true if this symbol table is empty.
	 * @return
	 */
	public boolean isEmpty(){
		return size() == 0;
	}
	
	/**
	 * Returns all keys in this symbol table.
	 * @return
	 */
	public Iterable<Key> keys(){
		return st.keySet();
	}
	
	/**
	 * Returns all of the keys in this symbol table.
	 * To iterate over all of the keys in a symbol table named,
	 * use the foreach notation.
	 */
	@Override
	public Iterator<Key> iterator() {
		return st.keySet().iterator();
	}
	
	/**
	 * Return the smallest key in this symbol table greater than or equal to key.
	 * @return
	 */
	public Key min(){
		if(isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
		return st.lastKey();
	}
	
	/**
	 * Return the largest key in this symbol table less than or equal to key.
	 * @param key
	 * @return
	 */
	public Key floor(Key key){
		if(key == null) throw new IllegalArgumentException("argument to floor() is null");
		Key k = st.floorKey(key);
		if(k == null) throw new NoSuchElementException("all keys are greater than "+ key);
		return k;
	}
	
	public static void main(String[] args){
		ST<String,Integer> st = new ST<String, Integer>();
		for(int i=0; !StdIn.isEmpty(); i++){
			String key = StdIn.readString();
			st.put(key, i);
		}
		
		for(String s: st.keys())
			StdOut.println(s + " " + st.get(s));
				
	}
	
}
