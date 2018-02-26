package Strings;

import Stdlib.StdIn;
import Stdlib.StdOut;

public class Count {
	public Count(){}
/**
 * Reads in text from standard input; calculates the frequency of 
 * occurrence of each character over the alphabet specified as a
 * command-line argument; and prints the frequencies to standard output.
 */
	public static void main(String[] args){
		Alphabet alphabet = new Alphabet(args[0]);
		final int R = alphabet.radix();
		int[] count = new int[R];
		while(StdIn.hasNextChar()){
			char c = StdIn.readChar();
			if(alphabet.contains(c))
				count[alphabet.toIndex(c)]++;
		}
		for(int c=0; c<R; c++)
			StdOut.println(alphabet.toChar(c)+" "+count[c]);
	}
}
