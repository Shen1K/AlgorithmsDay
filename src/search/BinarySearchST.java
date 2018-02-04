package search;

public class BinarySearchST <Key extends Comparable<Key>, Value>{
	private Key[] keys;
	private Value[] vals;
	private int n = 0;
	public BinarySearchST(int capacity){
		keys = (Key[])new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
	}
	
	//resize the underlying arrays
    private void resize(int capacity) {
        assert capacity >= n;
        Key[]   tempk = (Key[])   new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;
        keys = tempk;
    }
    
    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return
     */
    public int size(){
    	return n;
    }
    
    /**
     * Returns true if this symbol table is empty.
     *
     * @return {@code true} if this symbol table is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    
    public boolean contains(Key key){
    	if(key == null) throw new IllegalArgumentException("argument to contains() is null");
    	return get(key) != null;
    }
    
    /**
     * Return the value associated with the given key in this symbol table
     * 
     * @param key
     * @return
     */
    public Value get(Key key){
    	if(key == null) throw new IllegalArgumentException("argument to get() is null");
    	if(isEmpty()) return null;
    	int i = rank(key);
    	if(i < n && keys[i].compareTo(key) == 0) return vals[i];
    	return null;
    }
    
    /**
     * Returns the number of keys in this symbol table strictly less than key.
     * @param key
     * @return
     */
    public int rank(Key key){
    	if(key == null) throw new IllegalArgumentException("argument to rank() is null");
    	
    	int lo = 0, hi = n-1;
    	while(lo <= hi){
    		int mid = lo + (hi - lo) / 2;
    		int cmp = key.compareTo(keys[mid]);
    		if(cmp < 0) hi = mid - 1;
    		else if(cmp > 0) lo = mid + 1;
    		else return mid;
    	}
    	return lo;
    }
}
