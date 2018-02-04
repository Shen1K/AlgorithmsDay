package SanTongDengFen;

public class Bucket {
	//每个桶状态
	public int[] bucket_state = new int[Test2.Bucket_MAX];
	//每个状况的父亲状况
	public Bucket pre;
	
	
    public void setBucketState(int bucket_s[]) {  
        for (int i = 0; i < Test2.Bucket_MAX; i++) {  
            this.bucket_state[i] = bucket_s[i];  
        }  
    }
    
    //桶状态初始化
    public Bucket() {  
        setBucketState(Test2.BUCKET_INIT_STATE);       
    }

    //设置桶的状态
    public Bucket(int[] bucket_s){
    	setBucketState(bucket_s);
    }
    
    public Bucket(Bucket bucket){
    	setBucketState(bucket.bucket_state);
    }

    //判断两个桶是否相等
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
