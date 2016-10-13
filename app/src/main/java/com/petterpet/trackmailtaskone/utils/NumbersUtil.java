package com.petterpet.trackmailtaskone.utils;

import android.content.Context;

import com.petterpet.trackmailtaskone.R;

/**
 * Created by obabichev on 12/10/16.
 * <p>
 * I copied it from http://www.cyberforum.ru/java-j2se/thread607557.html
 * thanks for exiqa
 */

public class NumbersUtil {

    private Context context;

    private String[] FROM_ONE_TO_NINETEEN;
    private String[] TENS;
    private String[] HUNDREDS;


    public NumbersUtil(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        FROM_ONE_TO_NINETEEN = new String[]{
                context.getString(R.string.one), context.getString(R.string.two), context.getString(R.string.three),
                context.getString(R.string.four), context.getString(R.string.five), context.getString(R.string.six),
                context.getString(R.string.seven), context.getString(R.string.eight), context.getString(R.string.nine),
                context.getString(R.string.ten), context.getString(R.string.eleven), context.getString(R.string.tvelve),
                context.getString(R.string.thirteen), context.getString(R.string.fourteen), context.getString(R.string.fifteen),
                context.getString(R.string.sixteen), context.getString(R.string.seventeen), context.getString(R.string.eighteen),
                context.getString(R.string.nineteen)
        };

        TENS = new String[]{
                context.getString(R.string.twenty), context.getString(R.string.thirty), context.getString(R.string.fourty),
                context.getString(R.string.fifty), context.getString(R.string.sixty), context.getString(R.string.seventy),
                context.getString(R.string.eighty), context.getString(R.string.ninety)
        };

        HUNDREDS = new String[]{
                context.getString(R.string.one_hundred), context.getString(R.string.two_hundred), context.getString(R.string.three_hundred),
                context.getString(R.string.four_hundred), context.getString(R.string.five_hundred), context.getString(R.string.six_hundred),
                context.getString(R.string.seven_hundred), context.getString(R.string.eight_hundred), context.getString(R.string.nine_hundred)
        };
    }


    public String convertNumberToWords(int number) {
        if (number == 0) {
            return "";
        }

        if (number == 1000) {
            return context.getString(R.string.thousand);
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
