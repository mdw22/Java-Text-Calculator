/* Code by Michael White
 * Copyright 2023
 */
import java.io.*;
import java.util.Scanner;

public class App {

    private static boolean operatorValidator(String operator) {
        if (operator.equalsIgnoreCase("*") || operator.equalsIgnoreCase("/")) {
            return true;
        }
        else if (operator.equalsIgnoreCase("+") || operator.equalsIgnoreCase("-")) {
            return true;
        }
        return false;
    }

    private static float calculate(float x_value, float y_value, String operator) {
        if (operator.equalsIgnoreCase("*")) {
            return x_value * y_value;
        }
        else if (operator.equalsIgnoreCase("/")) {
            return x_value / y_value;
        }
        else if (operator.equalsIgnoreCase("+")) {
            return x_value + y_value;
        }
        else if (operator.equalsIgnoreCase("-")) {
            return x_value - y_value;
        }
        return 0;
    }
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Input format : <Number of equations> ',' <Equation 1> ',' <Equation 2> ... \n" 
        + "Acceptable Symbols : '+' '-' '*' '/'");    
        String user_input;
        try {
            user_input = scanner.nextLine();
            char[] char_array = user_input.toCharArray();
            // Get initial number of eq's 
            int num_equations = Character.getNumericValue(char_array[0]);
            // Check for valid input
            if (num_equations <= 0) {
                throw new Exception();
            }

            int j = 1;
            for (int i = 0; i < num_equations; ++i) {
                String operator = "";
                float x_value = 0, y_value = 0, z_value = 0;
                while (char_array[j] == ' ' || char_array[j] == ',') {
                    ++j;
                }
                String xtempInt = "";
                char xchar0 = char_array[j];
                xtempInt += xchar0;
                ++j;
                // At least a two decimal int
                if (char_array[j] != ' ' && char_array[j] != ',') {
                    while (char_array[j] != ' ' && char_array[j] != ',') {
                        xtempInt += char_array[j];
                        ++j;
                    }
                }  
                x_value = Float.parseFloat(xtempInt);
                // Move on to operator
                while (char_array[j] == ' ' || char_array[j] == ',') {
                    ++j;
                }
                // Operator validation
                operator = Character.toString(char_array[j]);
                ++j;
                if (!operatorValidator(operator)) {
                    throw new Exception();
                }
              
                // Move on to y value
                while (char_array[j] == ' ' || char_array[j] == ',') {
                    ++j;
                }
                // Y value initialized here
                String ytempInt = "";
                char ychar0 = char_array[j];
                ytempInt += ychar0;
                // Check if character is last char of input
                if (char_array.length - 1 > j) {
                    ++j;
                    // Add characters to string
                    while (char_array.length > j && char_array[j] != ' ' && char_array[j] != ',') {
                        ytempInt += char_array[j];
                        ++j;
                    }
                }
                y_value = Float.parseFloat(ytempInt);
                //System.out.println("Y value passed");
                // Z value calculated here
                z_value = calculate(x_value, y_value, operator);
                //Print answer to console
                System.out.println("Equation " + (i + 1) + ": " + z_value);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Invalid format. Please follow the format guidelines.");
            e.printStackTrace();
            System.exit(0);
        }   
    }
}