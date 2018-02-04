import java.util.Stack;

public class Eval {
	public static void main(String[] args) {
		
		Stack<String> ops = new Stack<String>();
		Stack<Double> vals = new Stack<Double>();
		
		
		while( !StdIn.isEmpty()){
			String s = StdIn.readString();
			
			//��ȡ�ַ���������������ѹ��ջ��
			if(s.equals("(")) ;
			else if(s.equals("+")) ops.push(s);
			else if(s.equals("-")) ops.push(s);
			else if(s.equals("*")) ops.push(s);
			else if(s.equals("/")) ops.push(s);
			else if(s.equals("sqrt")) ops.push(s);
			else if(s.equals(")")) {
				String op = ops.pop();
				double val = vals.pop();
				if     (op.equals("+")) val = vals.pop() + val;
				else if(op.equals("-")) val = vals.pop() - val;
				else if(op.equals("*")) val = vals.pop() * val;
				else if(op.equals("/")) val = vals.pop() / val;
				else if(op.equals("sqrt")) Math.sqrt(val);
				vals.push(val);
			}else {
				vals.push(Double.parseDouble(s));
			}

		}
		StdOut.println(vals.pop());
	}
}
