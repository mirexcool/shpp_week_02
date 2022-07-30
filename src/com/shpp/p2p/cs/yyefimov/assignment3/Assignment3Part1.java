package com.shpp.p2p.cs.yyefimov.assignment3;

import com.shpp.cs.a.console.TextProgram;

// Class Assignment3Part1 will check your physical activity.
public class Assignment3Part1 extends TextProgram {

    //  Defining exercise program
    public static final int EXERCISE_PLAN_ONE = 5;
    public static double EXERCISE_PLAN_ONE_TIME = 30;
    public static final int EXERCISE_PLAN_TWO = 3;
    public static double EXERCISE_PLAN_ONE_TWO = 40;
    public static final int EXERCISE_PERIOD = 7;
    //  Defining static String fields.
    public static final String NUMBER_REQUEST =
            "How many minutes did you do on day";
    public static final String WRONG_INPUT = "Please enter only numbers";
    public static final String[] GOOD_RESULT_MASSAGE = {
            "Great job! You've done enough exercise for cardiovascular health.",
            "Great job! You've done enough exercise to keep a low blood pressure."};
    public static final String[] HEALTH_PARAMETERS = {
            "Cardiovascular health:", "Blood pressure:"};

    public static final String[] BAD_RESULT_MASSAGE = {"You needed to train hard for at least",
            "more day(s) a week!"};

    //  Array will use to calculate exercise activity.
    private final int[] exerciseProgram = {EXERCISE_PLAN_ONE, EXERCISE_PLAN_TWO};


    //  Method will read data from console and check user health.
    @Override
    public void run() {
        readExerciseTimeEachDay();
        checkHealth();
    }

    /*  Method will check how many exercise need to be in good shape, or it will print
     *   good result massage if you do enough exercise.*/
    private void checkHealth() {
        /*  Cycle will use Array exerciseProgram[] that already contains information about
         *   exercise activity*/
        for (int i = 0; i < HEALTH_PARAMETERS.length; i++) {
            println(HEALTH_PARAMETERS[i]);
            if (exerciseProgram[i] <= 0) {
                println(" " + GOOD_RESULT_MASSAGE[i]);
            } else {
                println(" " + BAD_RESULT_MASSAGE[0] +
                        " " + exerciseProgram[i] +
                        " " + BAD_RESULT_MASSAGE[1]);
            }
        }
    }

    /*  Reading data from console. Each number readied from console will show time spent
        for exercise for period */
    private void readExerciseTimeEachDay() {
        double exerciseTime;
        String input;
        //  Checking how long was each exercise.
        for (int i = 1; i <= EXERCISE_PERIOD; i++) {
            input = readLine(NUMBER_REQUEST + " " + i + " ? ");
            //  Input validation. We will catch wrong input and ask to write correct input.
            try {
                exerciseTime = Double.parseDouble(input);
            } catch (NumberFormatException ignored) {
                exerciseTime = -1;
            }
            if (exerciseTime == -1) {
                i--;
                println(WRONG_INPUT);
            } else checkExerciseTime(exerciseTime);
        }
    }

    //  Check and save result for training period.
    private void checkExerciseTime(double exerciseTime) {
        if (exerciseTime >= EXERCISE_PLAN_ONE_TWO) {
            exerciseProgram[1]--;
        }
        if (exerciseTime >= EXERCISE_PLAN_ONE_TIME) {
            exerciseProgram[0]--;
        }
    }

}
