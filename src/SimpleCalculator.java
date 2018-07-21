import edu.princeton.cs.algs4.*;



public class SimpleCalculator {
    public static void main(String[] args) {

//We use one stack for operands (+ and *) and one stack for values (integers in equation)
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();

//Sample equation
        String equation = "11+1=*3+4==";

        for(int i = 0; i < equation.length(); i++){

        //String for storing integers representations
            String temp = Character.toString(equation.charAt(i));

//This test is to check if the next character in the equation string represents an integer
//if so, we concatenate it with the current number-string (temp)
            while (i < equation.length()-1 &&
                    (!temp.equals("*") && !temp.equals("+") && !temp.equals("=")) &&
                            !Character.toString(equation.charAt(i+1)).equals("*") &&
                            !Character.toString(equation.charAt(i+1)).equals("+") &&
                            !Character.toString(equation.charAt(i+1)).equals("="))
            {
                temp += Character.toString(equation.charAt(i+1));
                i++;
            }

//print for visual satisfaction
            System.out.print(temp);

//pushing operands to the operand stack
            if (temp.equals("+")) ops.push(temp);
            else if (temp.equals("*")) ops.push(temp);

//If the current character is "=" we pop the last operand from the ops stack
//then we perform the arithmetic operation corresponding to op.pop on the next two vals.pop
//finally we push the result of the aritmethic operation to the vals stack
            else if (temp.equals("=")){
                String op = ops.pop();
                double v = vals.pop();
                if (op.equals("+")) v=vals.pop() + v;
                else if (op.equals("*")) v=vals.pop() * v;
                vals.push(v);
            }
//If we didn't find = * or + we push the integer-string (temp) to the values stack
            else vals.push(Double.parseDouble(temp));
        }
//Result
        StdOut.println(vals.pop());
        }
}
