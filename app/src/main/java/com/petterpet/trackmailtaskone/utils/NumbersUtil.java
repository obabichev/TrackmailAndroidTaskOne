package com.petterpet.trackmailtaskone.utils;

/**
 * Created by obabichev on 12/10/16.
 * <p>
 * I copied it from http://www.cyberforum.ru/java-j2se/thread607557.html
 * thanks for exiqa
 */

public class NumbersUtil {

    private static final String[] FROM_ONE_TO_NINETEEN = {
            "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь",
            "девять", "десять", "одиннадцать", "двенадцать", "тринадцать",
            "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать",
            "восемнадцать", "девятнадцать"
    };

    private static final String[] TENS = {
            "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят",
            "семьдесят", "восемьдесят", "девяносто"
    };

    private static final String[] HUNDREDS = {
            "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот",
            "семьсот", "восемьсот", "девятьсот"
    };

    public static String convertNumberToWords(int number) {
        if (number == 0) {
            return "";
        }

        if (number == 1000){
            return "тысяча";
        }

        StringBuilder sb = new StringBuilder();

        if (number > 99) {
            int index = (number % 1000) / 100 - 1;
            sb.append(HUNDREDS[index]);
            number %= 100;
            sb.append(" ");
        }

        if (number > 19) {
            int index = number / 10 - 2;
            sb.append(TENS[index]);
            number %= 10;
            sb.append(" ");
        }

        if (number > 0) {
            sb.append(FROM_ONE_TO_NINETEEN[number - 1]);
        }

        return sb.toString();
    }
}
