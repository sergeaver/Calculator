import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main {
    public static String calc(String input) {
        try {
            return new Calculator(input).calc();
        }
        catch (ArabicAndRomeNumberTogetherException e) {
            return "You can't to use arabic and rome numbers together.";
        }
    }
    public static void main(String[] args) {
        String romes = NUMBER.representForRegex();
        String regex = "^([1-9]|10|" + romes + ")[+\\-*\\/]([1-9]|10|" + romes + ")$";
        System.out.println(regex);
        Pattern pattern = Pattern.compile(regex);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, input expression:");
        try {
            String input = scanner.next(pattern);
            System.out.println(Main.calc(input));
        }
        catch (InputMismatchException e) {
            System.out.println("Wrong input.");
        }
    }
}