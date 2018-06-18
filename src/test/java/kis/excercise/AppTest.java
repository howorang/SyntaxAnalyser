package kis.excercise;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    static Stream<Arguments> correctParamProvider() {
        return Stream.of(
                Arguments.of("2+2;"),
                Arguments.of("(1.2*3)+5-(23.4+3)^3;8:13;"),
                Arguments.of("2*(54-66.8^2-(2-2))*7;"),
                Arguments.of("0.128^0.2;"),
                Arguments.of("2;"),
                Arguments.of("0.56;")
        );
    }

    @ParameterizedTest
    @MethodSource("correctParamProvider")
    public void checkCorrect(String input) throws IncorrectInputException {
        new MathExpression(input).start();
    }

    static Stream<Arguments> incorrectParamProvider() {
        return Stream.of(
//                Arguments.of("2+;"),
//                Arguments.of("((((((;"),
//                Arguments.of("+;"),
//                Arguments.of("(2*2;"),
//                Arguments.of("0.01.1*2;"),
//                Arguments.of("2*2);"),
//                Arguments.of("2--5.5;"),
                Arguments.of("+3.3;")
        );
    }

    @ParameterizedTest
    @MethodSource("incorrectParamProvider")
    public void checkIncorrect(String input)
    {
        assertThrows(IncorrectInputException.class, () -> new MathExpression(input).start());
    }
}
