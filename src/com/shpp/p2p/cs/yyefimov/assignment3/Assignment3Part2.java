package com.shpp.p2p.cs.yyefimov.assignment3;

import com.shpp.cs.a.console.TextProgram;

import java.math.BigInteger;

/*  Class Assignment3Part2 will check Collatz conjecture.
    https://en.wikipedia.org/wiki/Collatz_conjecture   */
public class Assignment3Part2 extends TextProgram {
    //  Defining static String fields.
    public static final String EVEN_MASSAGE = "is even so I take half:";
    public static final String ODD_MASSAGE = "is odd so I make 3n + 1:";
    public static final String THE_END = "The end =)";
    public static final String NUMBER_REQUEST = "Enter a number:";
    public static final String WRONG_INPUT = "Please enter only positive integer numbers.";


    //  Method will print solution for our problem.
    @Override
    public void run() {
        /*  Method will check the number which comes from console input.
            Class BigInteger provide you be able to store any number.
            https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html   */
        BigInteger number;

        number = readIntFromConsole();
        if (number.compareTo(new BigInteger("0")) > 0) {
            printSolution(number);
        } else println(WRONG_INPUT);
    }

    private BigInteger readIntFromConsole() {
        String input;
        BigInteger number = new BigInteger("-1");

        input = readLine(NUMBER_REQUEST);
        try {
            number = new BigInteger(input);
        } catch (NumberFormatException ignored) {
        }
        return number;
    }

    //  Method will do calculation while not get value 1 from input number.
    private void printSolution(BigInteger number) {
        //  Check to stop while. We need to stop when number = 1.
        while (number.compareTo(new BigInteger("1")) > 0) {
            //  Mod with BigInteger it's the same as int % 2.
            BigInteger mod = number.mod(new BigInteger("2"));
            //  Method compare will give as -1, 0, 1 which depend on comparing values.
            if (mod.compareTo(new BigInteger("0")) == 0) {
                //  Using printf() eases output. Divide method mean "/".
                BigInteger newNumber = number.divide(new BigInteger("2"));
                System.out.printf("%d %s %d\n", number, EVEN_MASSAGE, newNumber);
                number = newNumber;
            }
            if (mod.compareTo(new BigInteger("0")) != 0) {
                //  Multiply method mean "*".
                BigInteger newNumber = number.multiply(new BigInteger("3")).add(new BigInteger("1"));
                System.out.printf("%d %s %d\n", number, ODD_MASSAGE, newNumber);
                number = newNumber;
            }
        }
        println(THE_END);
    }
}
