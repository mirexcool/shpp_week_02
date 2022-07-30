package com.shpp.p2p.cs.yyefimov.assignment3;

import com.shpp.cs.a.console.TextProgram;

// Class Assignment3Part2 will make Exponentiation.
public class Assignment3Part3 extends TextProgram {
    //  Tests for our method.
    @Override
    public void run() {
        //  Tests.
        println(raiseToPower(0.5, -2) + " Correct answer is 4.0");
        println(raiseToPower(2, 3) + " Correct answer is 8.0");
        println(raiseToPower(0, 0) + " Correct answer is 1.0");
        println(raiseToPower(0.5, 2) + " Correct answer is 0.25");
        println(raiseToPower(2, 10) + " Correct answer is 1024.0");
        println(raiseToPower(9999, 0) + " Correct answer is 1.0");
        println(raiseToPower(777, 1) + " Correct answer is 777.0");

    }

    //  Raising double base to power int exponent.
    private double raiseToPower(double base, int exponent) {
        //  Define edge case.
        double result = 1.0;

        //  If exponent > 0 we will make multiplications.
        //  If exponent < 0 we will make divisions.
        while (exponent != 0) {
            if (exponent > 0) {
                result *= base;
                exponent--;
            } else {
                result /= base;
                exponent++;
            }
        }
        return result;
    }

}
