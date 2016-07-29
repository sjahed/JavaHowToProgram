package ch21;

public class InfixToPostfixConverter {

	private StringBuffer infix;
	private StringBuffer postfix;
	private Stack<Character> workStack;
	private static final Character[] OPERATOR_LIST = {'+','-','*','/','^','%'};
	
	public InfixToPostfixConverter(String expression){
		
		infix = new StringBuffer(expression);
		postfix = new StringBuffer(infix.length());
		workStack = new Stack<Character>();
		convertToPostfix();
	}
	
	private void convertToPostfix(){
		
		workStack.push('(');
		infix.append(")");

		int counter = 0;
		while(!workStack.isEmpty()){
			
			if(counter > infix.length()-1)
				counter = infix.length()-1;
			
			Character currentChar = infix.charAt(counter++);
			
			
			if(isSpace(currentChar))
				continue;
			
			if(isDigit(currentChar))
				postfix.append(currentChar);
			else if(currentChar.equals('('))
				workStack.push(currentChar);
			else if(isOperator(currentChar)){

				if(precedence(currentChar,workStack.peek())
						&& isOperator(workStack.peek())){
					
					postfix.append(workStack.pop().toString());
				}
		
				workStack.push(currentChar);
			}else if(currentChar.equals(')')){
				
				do{
					postfix.append(workStack.pop());
				}while(!workStack.peek().equals('('));
				
				workStack.pop();
			}
			
		}//end of while workStack is not empty
		
	}
	
	
	private boolean isSpace(Character c) {
		return c.equals(' ');
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
	
	private boolean isOperator(Character c){
		for(Character op : OPERATOR_LIST)
			if(c.equals(op))
				return true;
			
		return false;
	}
	
	private boolean precedence(Character op1, Character op2){
		
		switch(op1){
		case '-':
		case '+':
			return !(op2.equals('+') || op2.equals('-') );
		case '*':
		case '/':
		case '%':
			return op2.equals('^');
		case '^':
			return !op2.equals('^');
		default:
			return false;
		}

	}//end of precedence method
	
	public StringBuffer getPostfix(){
		return postfix;
	}
	
}
