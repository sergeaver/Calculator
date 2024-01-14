import java.util.Arrays;

enum NUMBER {
    I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10);

    final int value;
    NUMBER(int number) {
        value = number;
    }
    public static Boolean contains(String number) {
        for (NUMBER c : NUMBER.values()) {
            if (c.name().equals(number)) {
                return true;
            }
        }
        return false;
    }
    public static String representForRegex() {
        String values = Arrays.toString(NUMBER.values());
        return String.join("|",
                values.replaceAll(" ", "")
                        .replace("[", "").replace("]", "").split(","));
    }
}

class ArabicAndRomeNumberTogetherException extends Exception {}
class NegativeRomeNumberException extends Exception {}
class PositiveArabicNumberException extends Exception {}
class Calculator{
    private int a;
    private int b;
    private Boolean isRome = false;

    private String operator;
    Calculator(String expression) throws ArabicAndRomeNumberTogetherException{
        this.parseExpression(expression.replaceAll(" ", ""));
    }

    public String calc() throws NegativeRomeNumberException, PositiveArabicNumberException {
        int result;
        switch (this.operator) {
            case "+":
                result = this.a + this.b;
                break;
            case "-":
                result = this.a - this.b;
                break;
            case "*":
                result = this.a * this.b;
                break;
            case "/":
                result = this.a / this.b;
                break;
            default:
                return "Wrong operator";
        }
        if (this.isRome & result < 0) {
            throw new NegativeRomeNumberException();
        } else if (!this.isRome & result >= 0) {
            throw new PositiveArabicNumberException();
        }
        return Integer.toString(result);
    }

    private void parseExpression(String expression) throws ArabicAndRomeNumberTogetherException{
        String[] expressionList = expression.split("[+\\-*/]");
        String regex = "([1-9]|10|" + NUMBER.representForRegex() + ")";
        String operator = expression.replaceAll(regex, "");
        String a = expressionList[0];
        String b = expressionList[1];
        if ((NUMBER.contains(a) & !NUMBER.contains(b)) |
            (!NUMBER.contains(a) & NUMBER.contains(b))){
            throw new ArabicAndRomeNumberTogetherException();
        }
        if (NUMBER.contains(a)) {
            this.a = NUMBER.valueOf(a).value;
            this.isRome = true;
        } else {
            this.a = Integer.parseInt(a);
        }
        if (NUMBER.contains(b)) {
            this.b = NUMBER.valueOf(b).value;
        } else {
            this.b = Integer.parseInt(b);
        }
        this.operator = operator;
    }
}
