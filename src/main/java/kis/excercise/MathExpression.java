package kis.excercise;

import java.util.Arrays;
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
        expression();
        checkFor(';');
        expressions();
        checkFollow(Producation.root.follow);
    }

    private void expressions() throws IncorrectInputException {
        if (Producation.expression.first.contains(getCurrentChar())) {
            expression();
            checkFor(';');
            expressions();
        }
        checkFollow(Producation.expressions.follow);
    }

    private void expression() throws IncorrectInputException {
        operand();
        experssionP();
        checkFollow(Producation.expression.follow);
    }

    private void experssionP() throws IncorrectInputException {
        if (Producation.operators.first.contains(getCurrentChar())) {
            operators();
            expression();
        }
        checkFollow(Producation.expressionP.follow);
    }

    private void operators() throws IncorrectInputException {
        checkForOneOf(Producation.operators.first);
        checkFollow(Producation.operators.follow);
    }

    private void operand() throws IncorrectInputException {
        if (Producation.number.first.contains(getCurrentChar())) {
            number();
        } else if(getCurrentChar() == '(') {
            checkFor('(');
            expression();
            checkFor(')');
        }
        checkFollow(Producation.operand.follow);
    }

    private void number() throws IncorrectInputException {
        digitString();
        numberP();
        checkFollow(Producation.number.follow);
    }

    private void numberP() throws IncorrectInputException {
        if (getCurrentChar() == '.') {
            checkFor('.');
            digitString();
        }
        checkFollow(Producation.numberP.follow);
    }

    private void digitString() throws IncorrectInputException {
        digit();
        digitStringP();
        checkFollow(Producation.digitString.follow);
    }

    private void digit() throws IncorrectInputException {
        checkForOneOf(Producation.digits.first);
        checkFollow(Producation.digits.follow);
    }

    private void digitStringP() throws IncorrectInputException {
        if (Producation.digitString.first.contains(getCurrentChar())) {
            digitString();
        }
        checkFollow(Producation.digitStringP.follow);
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

    private void checkFollow(List<Character> follow) throws IncorrectInputException {
        if (follow.contains(EMPTY_SIGN) || follow.isEmpty()) return;
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
