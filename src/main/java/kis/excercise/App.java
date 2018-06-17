package kis.excercise;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        System.out.println("By wyjść podaj 'q'");
        while (!exit) {
            System.out.println("Podaj wyrażenie: ");
            String input = scanner.nextLine();
            if (input.equals("q")) {
                break;
            }
            MathExpression parser = new MathExpression(input);
            try {
                parser.start();
                System.out.println("Poprawna");
            } catch (IncorrectInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
