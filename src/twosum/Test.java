package twosum;

import java.util.HashMap;

public class Test {
	public static void main(String[] args){
        Test t = new Test();
        int[] a = {1,0,-1};
        int target = -1;

        int[] b = t.twoSum(a, target);
        System.out.println(b[0]+" ,"+b[1]);
	}
	
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] res = new int[2];

     

        //将数组中的值放入map中
        for(int i=0; i<numbers.length; i++){
            map.put(numbers[i], i);
        }

        for(int i=0; i<numbers.length; i++){
            int overplus = target - numbers[i];
            if( map.containsKey(overplus)&& map.get(overplus) != i  ){
                res[0] = i;
                res[1] = map.get(overplus);
                break;
            }
        }

        return res;
    }
}
