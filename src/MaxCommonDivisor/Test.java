package MaxCommonDivisor;

public class Test {

	public static void main(String[] args) {
		System.out.println(gcd(10, 5));

	}
	
	public static int gcd(int a, int b){
		if(b == 0) return a;
		int r = a % b;
		return gcd(b, r);
	}

}
