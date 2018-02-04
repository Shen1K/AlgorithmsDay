package ResizingArrayStack;

import java.util.Iterator;

public class ResizingArrayStack<Item> {
	
	private Item[] a = (Item[]) new Object[1]; //数据存储数组
	private int N = 0;	//数组长度
	private boolean isEmpty(){
		return N == 0;
	}
	private int size(){
		return N;
	}
	private void resize(int max){
		Item[] temp = (Item[]) new Object[max];
		for(int i=0; i<a.length; i++){
			temp[i] = a[i];
		}
		a = temp;
	} 
	
	private void push(Item item){
		if(N == a.length) resize(2*a.length);
		a[N++] = item;
	}
	
	private Item pop(){
		Item item = a[--N];
		a[N] = null;
		if(N > a.length / 4) resize(a.length / 2);
		return item;	
	}
	
	public Iterator<Item> iterator(){
		return new ReverseArrayIterator();
	}
	
	private class ReverseArrayIterator implements Iterator<Item>{
		private int i = N;
		public boolean hasNext() { return i > 0;}
		public Item next(){ return a[--i];}
		public void remove(){ }
	}
}
