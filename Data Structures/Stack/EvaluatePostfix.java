package il.ac.telhai.ds.stack;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class EvaluatePostfix {

	private static StreamTokenizer tokenizer = new StreamTokenizer(new InputStreamReader(System.in));
	private static Stack<Double> myStack = new DLinkedListStack<Double>();
	
	public static void main(String[] args) throws IOException {
		tokenizer.slashSlashComments(false);
		tokenizer.ordinaryChar('/');
		int stackCount = 0;
		while(true){
			tokenizer.nextToken();
			if(tokenizer.ttype == StreamTokenizer.TT_EOF){
				break;
			}
			if(tokenizer.ttype == StreamTokenizer.TT_WORD){
				if(tokenizer.sval.equals("quit")) {
					break;
				}
				else {
					System.err.println(tokenizer);
					System.err.println(myStack);
					System.exit(1);
				}
			}
			if (tokenizer.ttype == StreamTokenizer.TT_NUMBER){
				myStack.push(tokenizer.nval);
				stackCount++;
				continue;
			}
			char c = (char)tokenizer.ttype;
			Double num1 = myStack.pop();
			Double num2 = myStack.pop();
			if(num1 == null || num2 == null){
				System.err.println(tokenizer);
				System.err.println(myStack);
				System.exit(1);
			}
			stackCount--;
			switch (c){
				case '+':
					myStack.push(num1+num2);
					break;
				case '-':
					myStack.push(num2-num1);
					break;
				case '*':
					myStack.push(num1*num2);
					break;
				case '/':
					myStack.push(num2/num1);
					break;
			}


		}
		if(stackCount == 0){
			System.err.println(tokenizer);
			System.err.println(myStack);
			System.exit(1);
		}
		Double res = myStack.pop();
		stackCount--;
		if(stackCount != 0){
			System.err.println(tokenizer);
			System.err.println(myStack);
			System.exit(1);
		}
		System.out.println(res);

	}

}
