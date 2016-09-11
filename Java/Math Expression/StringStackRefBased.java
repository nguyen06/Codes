public class StringStackRefBased implements StringStack{

	private StringNode top;

	public boolean isEmpty() {
		return top == null;
	}

	public String pop() throws StringStackException {
		if (isEmpty()) {
			throw new StringStackException("Unable to pop, StringStack is empty");
		}
		StringNode oldTop = top;
		top = top.next;
		return oldTop.item;
	}

	public void popAll() {
		top = null; // Let the garbage collector clean up the memory
	}

	public void push(String item) throws StringStackException {
		StringNode newNode = new StringNode(item);
		newNode.next = top;
		top = newNode;
	}

	public String peek() throws StringStackException {
		if (isEmpty()) {
			throw new StringStackException("Stack is empty cannot peek");
		}
		return top.item;
	}
	
	public static void main(String[] args){
		EvalInfix value = new EvalInfix();
		String a = "xpp{UTw{u}z{{mmy}tY}GL}s" ;
		System.out.println(value.isBalanced(a));
		String b = "xpp{UTw{{{uz}}mmytY{{GLs" ;
		System.out.println(value.isBalanced(b));
		String c = "(314 -5)*(10+20/3)";
		String token[] = value.tokenize(c);
		try{
		StringList d = value.toPostfix(token);
		System.out.println(d.toString());
		} catch(Exception e){
			System.err.println(e);
		}
		System.out.println(value.evaluateExpression(c));
		
		// test with 10
		 String f = "10";
		 System.out.println(value.evaluateExpression(f));
		 
		 //test with 10 + 2
		  f = "10 + 2";
		 System.out.println(value.evaluateExpression(f));
		 
		 // test with 10 - 2
		 f = "10  - 2";
		 System.out.println(value.evaluateExpression(f));
		 // test with 2-10
		 f = "2-10";
		 System.out.println(value.evaluateExpression(f));
		 // test with 5 * 8 * 9
		 f = "5*8*9";
		 System.out.println(value.evaluateExpression(f));
		 // test with 5*8 9
		 f = "20 + 30 + a";
		 System.out.println(value.evaluateExpression(f));
		 f = " 10 * 2  + 5 9";
		 System.out.println(value.evaluateExpression(f));
		 f = " ((10 * 2)  + 5 ";
		 System.out.println(value.isBalanced(f));
		 System.out.println(value.evaluateExpression(f));
	}


}