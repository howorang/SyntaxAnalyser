package kis.excercise;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MathExpression expression = new MathExpression("(1.2*3)+5-(23.4+3)^3;8:13;");
        try {
            expression.start();
        } catch (IncorrectInputException e) {
            e.printStackTrace();
        }
    }
}
