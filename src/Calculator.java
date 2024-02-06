import java.util.*;

public class Calculator {
    public static void main(String[] args) {
        Scanner mathematicalTask = new Scanner(System.in);
        System.out.println("Input:");
        String input = mathematicalTask.nextLine();

        try {
            String[] parts = input.split(" ");
            if (parts.length != 3)
                throw new IllegalArgumentException("Неверный формат ввода");

            String operand1 = parts[0];
            String operator = parts[1];
            String operand2 = parts[2];

            int num1, num2;

            if ((isRomanNumber(operand1) && !isRomanNumber(operand2)) || (!isRomanNumber(operand1) && isRomanNumber(operand2))) {
                throw new IllegalArgumentException("Разные системы счисления");
            }

            if (isRomanNumber(operand1) && isRomanNumber(operand2)) {
                num1 = romanToArabic(operand1);
                num2 = romanToArabic(operand2);
            } else {
                num1 = Integer.parseInt(operand1);
                num2 = Integer.parseInt(operand2);

                if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
                    throw new IllegalArgumentException("Число должно быть в диапазоне от 1 до 10 включительно");
                }
            }

            String result;
            switch (operator) {
                case "+":
                    result = String.valueOf(num1 + num2);
                    break;
                case "-":
                    if (isRomanNumber(operand1) && isRomanNumber(operand2)) {
                        if (num1 <= num2) {
                            throw new IllegalArgumentException("Результат вычитания двух римских чисел не может быть <= 0");
                        }
                    }
                    result = String.valueOf(num1 - num2);
                    break;
                case "*":
                    result = String.valueOf(num1 * num2);
                    break;
                case "/":
                    result = String.valueOf(num1 / num2);
                    break;
                default:
                    throw new IllegalArgumentException("Недопустимый оператор");
            }

            if (isRomanNumber(operand1) && isRomanNumber(operand2))
                result = arabicToRoman(Integer.parseInt(result));

            System.out.println("Output:");
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Output:");
            System.out.println("throws Exception // " + e.getMessage());
        }
    }


    private static boolean isRomanNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            char num = str.charAt(i);
            if (num != 'I' && num != 'V' && num != 'X') {
                return false;
            }
        }
        return true;
    }


    private static int romanToArabic(String roman) {
        switch (roman) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
            default:
                throw new IllegalArgumentException("Неверное римское число. Должно быть от I до X включительно");
        }
    }

    private static String arabicToRoman(int number) {

        String[] romanOnes = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] romanTens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] romanHundreds = {"", "C"};

        int ones = number % 10;
        int tens = (number / 10) % 10;
        int hundreds = number / 100;

        return romanHundreds[hundreds] + romanTens[tens] + romanOnes[ones];
    }
}