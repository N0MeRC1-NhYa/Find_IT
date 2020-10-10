
import java.math.BigInteger;

import java.util.Scanner;

public class Convertor {

    private int convertFrom;
    private int convertTo;
    private String num;
    private BigInteger decimalNum = new BigInteger("0");
    private int powstart;
    private String convertedNum = "-1";

    //конструктор класса
    public Convertor(int a, int b, String x) {
        convertFrom = a;
        convertTo = b;
        num = x;
    }

    // публичный метод для запуска вычислений
    public String convert() {
        getDecimal();
        if (!decimalNum.equals(-1)) {
            convertDecimal();
        }
        return convertedNum;
    }

    //ищет максимальную степень в нужной системе счисления
    private void getPow() {
        int i = 0;
        BigInteger powNum = new BigInteger(String.valueOf(convertTo)).pow(i);
        while (powNum.compareTo(decimalNum) < 0) {
            i++;
            powNum = new BigInteger(String.valueOf(convertTo)).pow(i);
        }
        powstart = i - 1;
    }

    //конвертирует наше десятичное число в число снужным основанием
    private void convertDecimal() {
        getPow();
        System.out.println("pow - " + powstart);
        int i = powstart;
        String ans = "";
        while (i >= 0) {
            BigInteger del = decimalNum.divide(new BigInteger(String.valueOf(convertTo)).pow(i));
            System.out.println(del.intValue());
            if (del.compareTo(new BigInteger(String.valueOf(10))) >= 0) {
                ans += (String.valueOf((char) ('a' + del.intValue() - 10)));
            } else {
                ans += (String.valueOf((char) ('0' + del.intValue())));
            }
            decimalNum = decimalNum.subtract(del.multiply(new BigInteger(String.valueOf(convertTo)).pow(i)));
            i--;
            System.out.println("The answer is: " + ans);
        }
        convertedNum = ans;
    }

    // функция делает из числа с основанием параметра ф число десятичное
    private void getDecimal() {
        for (int i = 0; i < num.length(); i++) {
            BigInteger mp = new BigInteger(String.valueOf(convertFrom)).pow(i);
            BigInteger nm;
            if (Character.isDigit(num.charAt(num.length() - 1 - i))) {
                nm = new BigInteger(String.valueOf((int) num.charAt(num.length() - i - 1) - '0'));
            } else {
                nm = new BigInteger(String.valueOf((int) num.charAt(num.length() - i - 1) - 'a' + 10));
            }
            if (nm.compareTo(new BigInteger("36")) > 0) {
                System.out.println("Input Error: Unknown character");
                decimalNum = new BigInteger("-1");
                break;
            }
            decimalNum = decimalNum.add(nm.multiply(mp));
        }
        System.out.println("Decimal number: " + decimalNum.intValue());
    }

}

class Main {
    // запуск программы и бесконечный цикл для ввода данных пользователем
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Приветствую вас в конвертере. Для начала работы нажмите Enter");
        in.nextLine();
        while (true) {
            System.out.println("Вам необходимо ввести числа в следующем формате: Основание исходного числа (press Enter), Основание числа, в которую необходимо перевести (press Enter), Само число (press Enter)");
            System.out.println("Для того чтобы выйти из конвертера впишите '50' (press Enter)");
            int a = in.nextInt();
            if (a == 50) {
                break;
            }
            int b = in.nextInt();
            String num = in.nextLine();
            Convertor ct;
            ct = new Convertor(a, b, num);
            System.out.println(ct.convert());
        }
    }
}
