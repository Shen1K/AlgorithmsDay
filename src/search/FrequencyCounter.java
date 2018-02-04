package search;

import Stdlib.In;
import Stdlib.StdIn;
import Stdlib.StdOut;

/**
 * The Frequency Counter class provides a client for reading
 * in a sequence of words and printing a word (exceeding a given
 * length) that occurs most frequently. It is useful as a test client
 * for various symbol table implementations.
 * 
 * @author 314-B03
 *
 */
public class FrequencyCounter {
	
	private FrequencyCounter(){};
	
	public static void main(String[] args){
		int minlen = Integer.parseInt(args[0]);
		ST<String, Integer> st = new ST<String, Integer>();
		In in = new In("D://Tale.txt");
		while(!in.isEmpty()){
			//������ű�ͳ��Ƶ��
			String word = in.readString();
			if(word.length() < minlen) continue; //���Խ϶̵ĵ���
			if(!st.contains(word)) st.put(word, 1);
			else 				   st.put(word, st.get(word)+1);	
		}
		//�ҳ�����Ƶ����ߵĵ���
		String max = " ";
		st.put(max, 0);
		for(String word : st.keys())
			if(st.get(word) > st.get(max))
				max = word;
		StdOut.println(max + " " + st.get(max));
	}
}
