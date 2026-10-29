import java.util.Scanner;

class DivideByZeroException extends Exception{
    DivideByZeroException(String msg){
        super(msg);
    }
}

public class CalculatorDemo{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean done = false;
        int n = 0;

        while (!done){
            n++;
            System.out.print("Enter first number: ");
            String a = sc.next();

            System.out.print("Enter second number: ");
            String b = sc.next();

            System.out.print("Enter operator (+,-,*,/): ");
            String op = sc.next();
            
            try{
                double n1 = Double.parseDouble(a);
                double n2 = Double.parseDouble(b);
                double res = calc(n1, op, n2);

                System.out.println("Result: " + res);
                done = true;
            } catch (NumberFormatException e){
                System.out.println("Invalid number entered. Please try again.");
            } catch (DivideByZeroException e){
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e){
                System.out.println("Invalid operator. Please use +, -, * or /.");
            } finally{
                System.out.println("Attempt " + n + " logged");
            }
        }
    }

    static double calc(double n1, String op, double n2) throws DivideByZeroException{
        switch (op){
            case "+": return n1 + n2;
            case "-": return n1 - n2;
            case "*": return n1 * n2;
            case "/":
                if (n2 == 0) {
                    throw new DivideByZeroException("Cannot divide by zero");
                }
                return n1 / n2;
            default:
                throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }
}