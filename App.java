// Importing libraries
import java.util.Scanner;

public class App {
    // Main function
    public static void main(String[] args) {
        // Declaring variables
        boolean valid = false;
        String equation = "";
        Scanner input = new Scanner(System.in);

        // Prompting user to enter equation until they enter valid one
        while(!valid) {
            // Prompting user to enter equation
            System.out.print("Enter an equation in «X + Y» format: ");
            equation = input.nextLine();

            // Validating equation
            if(validate(equation) == true) {
                // Solving equation after validation is successful
                if(solve(equation) == true) {
                    valid = true;
                }  
            }
        }

        // Optimization before end of application
        input.close();
    }

    // Function to validate equation (removed warnings of unused variables to make IDE look cleaner)
    @SuppressWarnings("unused")
    public static boolean validate(String equation) {
        // Declaring variables
        String[] equationElements = new String[3];

        // Step 1: Equation is not containing extra elements and starts with a number
        try {
            String[] testExtras = equation.split(" ");
            System.arraycopy(testExtras, 0, equationElements, 0, testExtras.length);
        } catch(ArrayIndexOutOfBoundsException e) {
            try {
                int testSpace = Integer.parseInt(equation.substring(0, 1));
            }
            catch(NumberFormatException f) {
                System.out.println("\nYour equation starts with space, not with first number. Please try again\n");
                return false;
            }

            System.out.println("\nEquation can contain only 3 elements - first number, operator, and second number. Please try again\n");
            return false;
        }

        // If equation is not containing extra elements, then we are good to split equation elements and move them to array
        equationElements = equation.split(" ");

        // Step 2: Equation is not lacking elements
        try {
            int testSecondNumber = Integer.parseInt(equationElements[2]);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("\nEquation does not have enough elements. Please try again\n");
            return false;
        }
        // Step 3: Second number is integer
        catch(NumberFormatException e) {
            System.out.println("\n«" + equationElements[2] + "» is not integer. Please try again\n");
            return false;
        }

        // Step 4: First number is integer
        try {
            int testFirstNumber = Integer.parseInt(equationElements[0]);
        }
        catch(NumberFormatException e) {
            System.out.println("\n«" + equationElements[0] + "» is not integer. Please try again\n");
            return false;
        }

        // Step 5: Equation ends with a number
        try {
            int testSpace = Integer.parseInt(equation.substring(equation.length() - 1));
        }
        catch(NumberFormatException f) {
            System.out.println("\nYour equation ends with space, not with second number. Please try again\n");
            return false;
        }

        // If none of the exceptions were thrown, then validation is successful
        return true;
    }

    // Function to solve equation and validate operator
    public static boolean solve(String equation) {
        // Declaring variables
        String[] equationElements = equation.split(" ");
        int x = Integer.parseInt(equationElements[0]), y = Integer.parseInt(equationElements[2]);

        // Validating operator, then solving equation and outputting result
        switch(equationElements[1]) {
            case "+":
                System.out.println("Result: " + (x + y));
                return true;
            case "-":
                System.out.println("Result: " + (x - y));
                return true;
            case "*":
                System.out.println("Result: " + (x * y));
                return true;
            case "/":
                System.out.println("Result: " + (x / y));
                return true;
            default:
                System.out.println("\n«" + equationElements[1] + "» is not operator. Please try again\n");
                return false;
        }
    }
}
