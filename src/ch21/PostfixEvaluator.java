package ch21;

public class PostfixEvaluator {


	
	private StringBuffer postfix;
	private Integer result;
	private static final Character[] OPERATOR_LIST = {'+','-','*','/','^','%'};
	
	public PostfixEvaluator(StringBuffer postfix){
		
		this.postfix = postfix;
		result = evaluatePostfixExpression();
	}
	public Integer getResult(){
		return result;
	}
	private Integer evaluatePostfixExpression(){
		
		Stack<Integer> stack = new Stack<Integer>();
		postfix.append(')');
		
		int counter = 0;
		Character currentChar = postfix.charAt(counter);
		
		while(!currentChar.equals(')')){
			
			if(counter > postfix.length() - 1){
				stack.pop();
				continue;
			}
			
			if(isDigit(currentChar))
				stack.push(intValue(currentChar));
			else if(isOperator(currentChar)){
				stack.push(calculate(stack.pop(),stack.pop(),currentChar));
			}
			
			
			currentChar = postfix.charAt(++counter);
		}
		return stack.pop();
	}
	
	private Integer calculate(Integer x, Integer y, Character op){
		switch(op){
		case '+':
			return y + x;
		case '-':
			return Math.abs(y-x);
		case '*':
			return y * x;
		case '/':
			return !x.equals(0) ? y /x : -1;
		case '%':
			return y % x;
		case '^':
			return new Double(Math.pow(y, x)).intValue();
		default:
			return -1;
		}
	}
	
	private boolean isDigit(Character c){
		switch(c){
		case '0':
		case '1':
		case '2':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			return true;
		default:
			return false;
		}
	}
	private Integer intValue(Character c){
		return Character.getNumericValue(c) - Character.getNumericValue('0');
	}
	private boolean isOperator(Character c){
		for(Character op : OPERATOR_LIST)
			if(c.equals(op))
				return true;
			
		return false;
	}
}
