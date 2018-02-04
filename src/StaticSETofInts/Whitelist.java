package StaticSETofInts;


public class Whitelist {
	public static void main(String[] args){
		int[] w = {11,14,23,15,26,12,8,19,31};
		StaticSetofInts set = new StaticSetofInts(w);
		int key = 11;
		System.out.println(set.contains(key));
	}
}
