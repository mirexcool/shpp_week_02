package com.shpp.p2p.cs.yyefimov.assignment3;

import com.shpp.cs.a.console.TextProgram;

import acm.util.RandomGenerator;

// Class Assignment3Part5 will simulate St. Petersburg paradox.
public class Assignment3Part5 extends TextProgram {

    //  Defining static string fields.
    public static final String GAME_MASSAGE = "This game, you earned $";
    public static final String TOTAL_MONEY_MASSAGE = "Your total is $";

    //  Defining how much money do we need to earn to win the game.
    public static final int MONEY_TO_WIN = 20;

    //  Variable to count money.
    private int money = 0;

    //  Method will create RandomGenerator and after that the game will start.
    @Override
    public void run() {
        RandomGenerator rg = RandomGenerator.getInstance();

        int i = 0;

        while (money < MONEY_TO_WIN) {
            //  We will play game while not earned sum for end game.
            money += headAndTailStreak(rg);
            println(TOTAL_MONEY_MASSAGE + money);
            i++;
        }
        System.out.printf("It took %d games to earn 20$\n", i);
    }

    //  Method will make imitation of "Head and Tail" coin game.
    private int headAndTailStreak(RandomGenerator rg) {
        int streakMoney = 1;

        //  While "Head", we will double money.
        while (rg.nextBoolean()) {
            streakMoney *= 2;
        }
        //  Print the result of game streak.
        println(GAME_MASSAGE + streakMoney);
        return streakMoney;
    }
}
