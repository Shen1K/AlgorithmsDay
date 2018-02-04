package SanTongDengFen;


import java.util.LinkedList;

import java.util.ListIterator;




public class Test2 {
	//���ڱ���·�����е�״̬
	public static LinkedList<Bucket> states = new LinkedList<Bucket>();
	
	static int Bucket_MAX = 3;
	
	//��¼����ʵ�ֵ�·����Ŀ
	static int count = 0;
	
	//��������ˮͰ�������ɵ�����ˮ�ĺ���
    static int BUCKETS_CAPACITY[] = new int[]{8, 5, 3};        
    
    //������ʼʱ������ˮͰ��ʼ��״̬  
    static int BUCKET_INIT_STATE[] = new int[]{8, 0, 0};    
    
    //��������ˮͰ���յ�״̬  
    static int BUCKET_FINAL_STATE[] = new int[]{4, 4, 0};      
	
	//�����ж�����Ƿ��Ѿ����ڣ����⻷·
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
	 * @param from 	��ˮ��Ͱ
	 * @param to	����ˮ��Ͱ
	 * @param bucket	Ͱ״̬	
	 */
	public static void dumpWater(int from, int to, Bucket next){
		
		//����ˮ��Ͱ���Խ��ܵ�ˮ��
		int dump = BUCKETS_CAPACITY[to] - next.bucket_state[to];
		//����ˮ������from��ˮ����toˮ����࣬fromˮ����0
		if(dump >= next.bucket_state[from]){
			next.bucket_state[to] += next.bucket_state[from];
			next.bucket_state[from] = 0;
		}else{
			//����ˮ��С��fromˮ����to������from����dump
			next.bucket_state[from] = next.bucket_state[from] - dump;
			next.bucket_state[to] = BUCKETS_CAPACITY[to];
		}
	}
	
	//����һ�����Ե�������״̬��·��
	public static void searchState(LinkedList<Bucket> states){
		Bucket curr = states.getLast();
		if(curr.isFinally()){

			System.out.println("---------find result "+(++count)+"------------");
			printResult(states);
			System.out.println();
			return;
		}
		
		//עˮ�ķ�ʽ��6��
		for(int i=0; i<Bucket_MAX; i++){
			for(int j=0; j<Bucket_MAX; j++){
				if(i != j){
					searchStateOnAction(states, curr, i, j);
				}
			}
		}
	}

	//עˮ�ľ��岽��
	private static void searchStateOnAction(LinkedList<Bucket> states2, Bucket curr, int i, int j) {
		if(canDumpWater(i,j, curr)){
			Bucket next = new Bucket(curr);
			dumpWater(i, j, next);
			if(!isExist(states, next)){
				/*
				 * ������ʵ����һ������·���Ĳ���
				 */
				states.add(next);
				searchState(states);
				states.removeLast();
			}
		}
		
	}

	//עˮ�ļ�֦����
	private static boolean canDumpWater(int i, int j, Bucket curr) {
		if(curr.bucket_state[i] == 0)
			return false;
		else if(curr.bucket_state[j] == BUCKETS_CAPACITY[j])
			return false;
		else
			return true;
	}

	//���ڴ�ӡ������states�е�Ԫ��
	private static void printResult(LinkedList<Bucket> states) {
		ListIterator<Bucket> itr= states.listIterator();
		while(itr.hasNext()){
			System.out.print(itr.next().printArray()+" ");
		}
	}
	
    public static void main(String[] args) {  
    	//����(8,0,0)�� ״̬   
        Bucket init=new Bucket();      
  
        states.addLast(init);     //����ʼ��״̬���뵽LinkedList states������  
        searchState(states);        //����״̬�������㷨  

        System.out.println("---------------finish--------------");    
 
          
    }

	
}
