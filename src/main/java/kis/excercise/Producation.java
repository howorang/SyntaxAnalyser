package kis.excercise;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum  Producation {
    root(Arrays.asList('0','1','2','3','4','5','6','7','8','9','('), Collections.emptyList()),
    expressions(Arrays.asList('0','1','2','3','4','5','6','7','8','9','(','_'), Collections.emptyList()),
    expression(Arrays.asList('0','1','2','3','4','5','6','7','8','9','('), Arrays.asList(')', ';')),
    expressionP(Arrays.asList('*',':','+','-','^','_'), Arrays.asList(')', ';')),
    operand(Arrays.asList('0','1','2','3','4','5','6','7','8','9','('), Arrays.asList(')', ';','*',':','+','-','^','_')),
    number(Arrays.asList('0','1','2','3','4','5','6','7','8','9'), operand.follow),
    numberP(Arrays.asList('_','.'), number.follow),
    digitString(Arrays.asList('0','1','2','3','4','5','6','7','8','9'), numberP.follow),
    digitStringP(Arrays.asList('0','1','2','3','4','5','6','7','8','9','_'), digitString.follow),
    digits(Arrays.asList('0','1','2','3','4','5','6','7','8','9'), digitStringP.first),
    operators(Arrays.asList('*',':','+','-','^'), expression.first);

    public final List<Character> first;
    public final List<Character> follow;

    Producation(List<Character> first, List<Character> follow) {
        this.first = Collections.unmodifiableList(first);
        this.follow = Collections.unmodifiableList(follow);
    }
}
