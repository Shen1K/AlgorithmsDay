import java.awt.event.ItemEvent;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

public class FixedCapacityStack<Item> {
	
	private Item[] a;
	private int N;
	public FixedCapacityStack(int cap){
		a = (Item[])new Object[cap];
	}
	
	public boolean isEmpty(){ return N == 0;}
	public int size() { return N; }
	public void push(Item item){
		if(N == a.length) resize(2 * a.length);
		a[N++] = item;
	}
	public Item pop(){
		Item item = a[--N];
		a[N] = null; //±ÜÃâ¶ÔÏóÓÎÀë
		if(N>0 && N==a.length/4 )resize(a.length/2);
		return item;
	}
	
	
	private void resize(int max){
		ItemEvent[] temp = (ItemEvent[]) new Object[max];
		for(int i=0; i<N; i++){
			temp[i] = (ItemEvent) a[i];
		}
		a = (Item[]) temp;
	}
	

	public static void main(String[] args) {
		FixedCapacityStack s;
		s = new FixedCapacityStack<String>(100);
		while(!StdIn.isEmpty()){
			String item = StdIn.readString();
			if(!item.equals("-")){
				s.push(item);
			}else if(!s.isEmpty())
				StdOut.print(s.pop() + " ");
		}	
		StdOut.print("(" + s.size() + " left on stack)");

	}

}
