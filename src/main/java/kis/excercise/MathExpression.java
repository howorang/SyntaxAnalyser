package kis.excercise;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MathExpression {

    private static final char EMPTY_SIGN = '_';

    private int currentPosition = 0;
    String input;
    private int maxPosition;

    public MathExpression(String input) {
        this.input = input;
        this.maxPosition = input.length() - 1;
    }

    public void start() throws IncorrectInputException {
        root();
    }

    private void root() throws IncorrectInputException {
        checkInPlace(Producation.root.first);
        expression();
        checkFor(';');
        expressions();
        checkInPlace(Producation.root.follow);
    }

    private void expressions() throws IncorrectInputException {
        checkInPlace(Producation.expressions.first);
        if (Producation.expression.first.contains(getCurrentChar())) {
            expression();
            checkFor(';');
            expressions();
        }
        checkInPlace(Producation.expressions.follow);
    }

    private void expression() throws IncorrectInputException {
        checkInPlace(Producation.expression.first);
        operand();
        experssionP();
        checkInPlace(Producation.expression.follow);
    }

    private void experssionP() throws IncorrectInputException {
        checkInPlace(Producation.expressionP.first);
        if (Producation.operators.first.contains(getCurrentChar())) {
            operators();
            expression();
        }
        checkInPlace(Producation.expressionP.follow);
    }

    private void operators() throws IncorrectInputException {
        checkInPlace(Producation.operators.first);
        checkForOneOf(Producation.operators.first);
        checkInPlace(Producation.operators.follow);
    }

    private void operand() throws IncorrectInputException {
        checkInPlace(Producation.operand.first);
        if (Producation.number.first.contains(getCurrentChar())) {
            number();
        } else if(getCurrentChar() == '(') {
            checkFor('(');
            expression();
            checkFor(')');
        }
        checkInPlace(Producation.operand.follow);
    }

    private void number() throws IncorrectInputException {
        checkInPlace(Producation.number.first);
        digitString();
        numberP();
        checkInPlace(Producation.number.follow);
    }

    private void numberP() throws IncorrectInputException {
        checkInPlace(Producation.numberP.first);
        if (getCurrentChar() == '.') {
            checkFor('.');
            digitString();
        }
        checkInPlace(Producation.numberP.follow);
    }

    private void digitString() throws IncorrectInputException {
        checkInPlace(Producation.digitString.first);
        digit();
        digitStringP();
        checkInPlace(Producation.digitString.follow);
    }

    private void digit() throws IncorrectInputException {
        checkInPlace(Producation.digits.first);
        checkForOneOf(Producation.digits.first);
        checkInPlace(Producation.digits.follow);
    }

    private void digitStringP() throws IncorrectInputException {
        checkInPlace(Producation.digitStringP.first);
        if (Producation.digitString.first.contains(getCurrentChar())) {
            digitString();
        }
        checkInPlace(Producation.digitStringP.follow);
    }

    private void checkFor(char c) throws IncorrectInputException {
        if (getCurrentChar() != c) {
            throw new IncorrectInputException("Expected: " + c + " at " + currentPosition);
        }
        currentPosition++;
    }

    private void checkForOneOf(Collection<Character> characters) throws IncorrectInputException {
        if (!characters.contains(getCurrentChar())) {
            throw new IncorrectInputException("Expected: " + printList(characters) + " at " + currentPosition);
        }
        currentPosition++;
    }

    private char getCurrentChar() {
        return currentPosition <= maxPosition ? input.charAt(currentPosition) : EMPTY_SIGN;
    }

    private void checkInPlace(List<Character> follow) throws IncorrectInputException {
        if (follow.contains(EMPTY_SIGN) || (follow.isEmpty() && getCurrentChar() == EMPTY_SIGN)) return;
        if (!follow.contains(getCurrentChar())) {
            throw new IncorrectInputException("Expected: " + printList(follow) + " at " + currentPosition);
        }
    }

    private String printList(Collection<Character> list) {
        StringBuilder result = new StringBuilder();
        result.append("{ ");
        result.append(list.stream().map(o -> ((Object) o).toString()).collect(Collectors.joining()));
        result.append(" }");
        return result.toString();
    }
}
