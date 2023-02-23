
/**
 *
 *  @author Nahornyi Andrii S24918
 *
 */
package zad1;
/**
 *
 *  @author Nahornyi Andrii S24918
 *
 */
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Calc {
    //A map of all the operations
    private static final Map<String, BiFunction<BigDecimal, BigDecimal, BigDecimal>> OPERATORS = new HashMap<String, BiFunction<BigDecimal, BigDecimal, BigDecimal>>() {{
        put("+", (a, b) -> a.add(b));                                       //Addition
        put("-", (a, b) -> a.subtract(b));                                  //Subtraction
        put("*", (a, b) -> a.multiply(b));                                  //Multiplication
        put("/", (a, b) -> a.divide(b, 7, BigDecimal.ROUND_HALF_UP));  //Division

    }};

    //A method to calculate the result of the expression
    public String doCalc(String cmd) {
        String[] args = cmd.split("\\s+");         //Split the expression into an array of strings
        try {
            BigDecimal a = new BigDecimal(args[0]);                                             //The first number
            BigDecimal b = new BigDecimal(args[2]);                                             //The second number
            BiFunction<BigDecimal, BigDecimal, BigDecimal> operator = OPERATORS.get(args[1]);   //The operator between the numbers

            return operator.apply(a, b).stripTrailingZeros().toString();                        //Return the result of the expression
        } catch (Exception e) {
            return "Invalid command to calc";
        }
    }
}