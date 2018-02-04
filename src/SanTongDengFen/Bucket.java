package SanTongDengFen;

public class Bucket {
	//ÿ��Ͱ״̬
	public int[] bucket_state = new int[Test2.Bucket_MAX];
	//ÿ��״���ĸ���״��
	public Bucket pre;
	
	
    public void setBucketState(int bucket_s[]) {  
        for (int i = 0; i < Test2.Bucket_MAX; i++) {  
            this.bucket_state[i] = bucket_s[i];  
        }  
    }
    
    //Ͱ״̬��ʼ��
    public Bucket() {  
        setBucketState(Test2.BUCKET_INIT_STATE);       
    }

    //����Ͱ��״̬
    public Bucket(int[] bucket_s){
    	setBucketState(bucket_s);
    }
    
    public Bucket(Bucket bucket){
    	setBucketState(bucket.bucket_state);
    }

    //�ж�����Ͱ�Ƿ����
	public boolean isSameState(Bucket state) {
		for(int i=0; i<state.bucket_state.length; i++){
			if(this.bucket_state[i] != state.bucket_state[i])
				return false;
		}
		return true;
		
	}

	public boolean isFinally() {
		for(int i=0; i<this.bucket_state.length; i++){
			if(this.bucket_state[i] != Test2.BUCKET_FINAL_STATE[i])
				return false;
		}
		return true;
	}

	
	public String printArray() {
		String str = "(";
		
		str = str + this.bucket_state[0];
		str = str + "," + this.bucket_state[1];
		str = str + "," + this.bucket_state[2]+")";
		return str;
	}
    
    
}
