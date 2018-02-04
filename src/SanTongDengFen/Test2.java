package SanTongDengFen;


import java.util.LinkedList;

import java.util.ListIterator;




public class Test2 {
	//用于保存路上已有的状态
	public static LinkedList<Bucket> states = new LinkedList<Bucket>();
	
	static int Bucket_MAX = 3;
	
	//记录可以实现的路径数目
	static int count = 0;
	
	//定义三个水桶所能容纳的最大的水的含量
    static int BUCKETS_CAPACITY[] = new int[]{8, 5, 3};        
    
    //定义起始时，三个水桶初始的状态  
    static int BUCKET_INIT_STATE[] = new int[]{8, 0, 0};    
    
    //定义三个水桶最终的状态  
    static int BUCKET_FINAL_STATE[] = new int[]{4, 4, 0};      
	
	//用于判断情况是否已经存在，避免环路
	public static Boolean isExist(LinkedList<Bucket> states, Bucket state){
        ListIterator<Bucket> itr= states.listIterator();  
        while (itr.hasNext()){  
            if(itr.next().isSameState(state)) {  
                return true;  
            }  
        }  
        return false; 
	}
	
	/**
	 * 
	 * @param from 	倒水的桶
	 * @param to	接受水的桶
	 * @param bucket	桶状态	
	 */
	public static void dumpWater(int from, int to, Bucket next){
		
		//接受水的桶可以接受的水量
		int dump = BUCKETS_CAPACITY[to] - next.bucket_state[to];
		//接受水量大于from的水量，to水量变多，from水量清0
		if(dump >= next.bucket_state[from]){
			next.bucket_state[to] += next.bucket_state[from];
			next.bucket_state[from] = 0;
		}else{
			//接受水量小于from水量，to变满，from减少dump
			next.bucket_state[from] = next.bucket_state[from] - dump;
			next.bucket_state[to] = BUCKETS_CAPACITY[to];
		}
	}
	
	//搜索一条可以到达最终状态的路径
	public static void searchState(LinkedList<Bucket> states){
		Bucket curr = states.getLast();
		if(curr.isFinally()){

			System.out.println("---------find result "+(++count)+"------------");
			printResult(states);
			System.out.println();
			return;
		}
		
		//注水的方式有6种
		for(int i=0; i<Bucket_MAX; i++){
			for(int j=0; j<Bucket_MAX; j++){
				if(i != j){
					searchStateOnAction(states, curr, i, j);
				}
			}
		}
	}

	//注水的具体步骤
	private static void searchStateOnAction(LinkedList<Bucket> states2, Bucket curr, int i, int j) {
		if(canDumpWater(i,j, curr)){
			Bucket next = new Bucket(curr);
			dumpWater(i, j, next);
			if(!isExist(states, next)){
				/*
				 * 这三步实现了一条可能路径的查找
				 */
				states.add(next);
				searchState(states);
				states.removeLast();
			}
		}
		
	}

	//注水的剪枝函数
	private static boolean canDumpWater(int i, int j, Bucket curr) {
		if(curr.bucket_state[i] == 0)
			return false;
		else if(curr.bucket_state[j] == BUCKETS_CAPACITY[j])
			return false;
		else
			return true;
	}

	//用于打印出集合states中的元素
	private static void printResult(LinkedList<Bucket> states) {
		ListIterator<Bucket> itr= states.listIterator();
		while(itr.hasNext()){
			System.out.print(itr.next().printArray()+" ");
		}
	}
	
    public static void main(String[] args) {  
    	//创造(8,0,0)的 状态   
        Bucket init=new Bucket();      
  
        states.addLast(init);     //将初始的状态插入到LinkedList states的最后端  
        searchState(states);        //调用状态树搜索算法  

        System.out.println("---------------finish--------------");    
 
          
    }

	
}
