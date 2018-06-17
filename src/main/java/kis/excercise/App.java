package kis.excercise;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MathExpression expression = new MathExpression("3-;");
        try {
            expression.start();
        } catch (IncorrectInputException e) {
            e.printStackTrace();
        }
    }
}
