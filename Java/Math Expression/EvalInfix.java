

public class EvalInfix {
    /**
     * First ensure the arithmetic operations plus parentheses
     * are surrounded by spaces. Then go ahead and split up the
     * whole expression using spaces (i.e, the operands will be
     * nicely separated from everything else by at least a single
     * space). This will not work for negative numbers.
     */
    public static String[] tokenize(String s) {
        // Note the missing minus character...
        String symbols[] = {"\\(", "\\)", "\\+", "\\-", "\\*", "\\/"};

        // First eliminate any quotation marks
        s = s.replaceAll("'", " ");
        s = s.replaceAll("\"", " ");

        // Now all operators or parentheses, surround the character
        // with a single space on either side.
        for (int i = 0; i < symbols.length; i++) {
            String regex = symbols[i];
            String replace = " " + regex + " ";
            s = s.replaceAll(regex, replace);
        }   

        // Special case: If there is a unary minus, then then
        // what appears to the right of the symbol is whitespace
        // and a digit; what appears to the left whitespace
        // and a non-digit symbol.
        s = s.replaceAll("(^|([\\+\\-\\*\\/]))\\s+\\-\\s+(\\d+)", "$1 -$3");

        // Eliminate extra whitespaces at start and end of the
        // transformed string. 
        s = s.trim();

        // Finally, take advantage of the whitespace to create an
        // array of strings where each item in the array is one
        // of the elements in the original string passed in to this
        // method.
        return s.split("\\s+");
    }
    
    public static boolean isOperator(String token) {
		return token.equals("+") || token.equals("-") || token.equals("*")
				|| token.equals("/") || token.equals("(") || token.equals(")");
    }
    
	public static StringList toPostfix(String[] tokens) throws StringStackException {
    	
    	StringStack stack = new StringStackRefBased();
    	StringList list = new StringListRefBased();
    	
		for (String token : tokens) {
			if (!isOperator(token)) {
				// Test for integer
				try {
					Integer.parseInt(token);
				} catch (NumberFormatException e) {
					System.out.println("<non-integer>");
					return null;
				}
				// if it is a number, always push it
				list.insertTail(token);
			} else if (token.equals("(")) {
				stack.push(token); // always push the (
			} else if (token.equals(")")) {
				while (!stack.peek().equals("(")) {
					list.insertTail(stack.pop());
				}
				stack.pop(); // remove the open (
			} else {
				while (!stack.isEmpty() && !stack.peek().equals("(")) {
					String op = stack.peek();
					if ((op.equals("+") || op.equals("-")) && (token.equals("*") || token.equals("/"))) {
						break;
					} else {
						list.insertTail(stack.pop());
					}
				}

				if (!token.equals(")")) {
					stack.push(token);
				}
			}
		}
    	while (!stack.isEmpty()) {
    		list.insertTail(stack.pop());
    	}
    	
    	return list;
    }

    public static String evaluateExpression(String expr) {
        String result = "";
        
        if (!isBalanced(expr)) {
        	System.out.println("unbalanced ()");
        	return null;
        }
        String[] tokens = tokenize(expr);
        StringList list;
		try {
			list = toPostfix(tokens);
			if (list == null) {
				return null;
			}
	        result = evaluatePostfix(list);
		} catch (StringStackException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("<noninteger>");
		}
        
        return result;
    }
    
    private static String evaluatePostfix(StringList list) throws NumberFormatException, StringStackException {
		StringStack stack = new StringStackRefBased();
		if (list.isEmpty()) {
			return "0";
		}
		while (!list.isEmpty()) {
			String item = list.removeTail();
			if (isOperator(item)) {
				stack.push(item);
			} else {
				if (stack.isEmpty()) {
					return item; // no operators, only a number
				}
				Integer op1 = Integer.parseInt(item);
				while (!stack.isEmpty() && !isOperator(stack.peek())) {
					Integer op2 = Integer.parseInt(stack.pop());
					op1 = compute(op1, op2, stack.pop());
					if (op1 == null) {
						return null;
					}
				}
 				stack.push(op1.toString());
			}
		}
		String value = "0";
		while(!stack.isEmpty()) {
			Integer op1 = Integer.parseInt(stack.pop());
			if (stack.isEmpty()) {
				value = op1.toString();
			} else {
				Integer op2 = Integer.parseInt(stack.pop());
				stack.push(compute(op1, op2, stack.pop()).toString());
			}
		}
		return value;
	}

	private static Integer compute(Integer op1, Integer op2, String item) {
		if (item.equals("+")) {
			return op1 + op2;
		} else if (item.equals("-")) {
			return op1 - op2;
		} else if (item.equals("*")) {
			return op1 * op2;
		} else if (item.equals("/")) {
			if (op2 == 0) {
				System.out.println("<divide by zero>");
				return null;
			} else {
				return op1 / op2;
			}
		}
		return null;
	}

	public static boolean isBalanced(String expr) {
    	int curOpenBrackets = 0;
    	for (int i=0; i<expr.length();i++) {
    		char ch = expr.charAt(i);
    		if (ch == '(') {
    			curOpenBrackets++;
    		} else if (ch == ')') {
    			curOpenBrackets--;
    			if (curOpenBrackets < 0) {
    				// there is a closing bracket without an operning bracket
    				return false;
    			}
    		}
    	}
		return (curOpenBrackets == 0);
    }
 

    public static void main(String args[]) {
        if (args.length < 1) {
            System.err.println("usage: java EvalInfix '<expression>'");
            System.exit(1);
        }
        String value = evaluateExpression(args[0]);
        if (value != null) {
        	System.out.println(value);
        }

    }
}
