package com.shpp.p2p.cs.yyefimov.assignment3;


import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

// Class Assignment3Part4 will build the brick pyramid.
public class Assignment3Part4 extends WindowProgram {
    //  Brick parameters settings here.
    public static final double BRICK_HEIGHT = 20;
    public static final double BRICK_WIDTH = 40;
    public static final int BRICKS_IN_BASE = 15;
    public static final Color BRICK_COLOR = new Color(255, 140, 0);

    //  Screen size setup.
    public static final int APPLICATION_WIDTH = 800;
    public static final int APPLICATION_HEIGHT = 600;
    public static final double SCREEN_OFFSET = 1.0;

    //  Variable for our method.
    private int brickNumber = 0;

    //  Method will create bricks in correct amount. And then will put every brick in right place.
    @Override
    public void run() {
        /*  Method createBricks() will return link on array of
            GRect[] which will consist bricks for building.   */
        GRect[] bricks = createBricks();
        //  Method will put every brick in right place in our pyramid.
        buildPyramid(bricks);
    }

    /*  Method createBricks() will return link on array of
        GRect[] which will consist bricks for building.     */
    private GRect[] createBricks() {
        int amount;

        /*  We can get correct amount using Arithmetic progression.
            https://en.wikipedia.org/wiki/Arithmetic_progression    */
        amount = ((1 + BRICKS_IN_BASE) * BRICKS_IN_BASE) / 2;
        //  Creating link on array of GRect of amount elements in it.
        GRect[] bricks = new GRect[amount];
        //  Creating each brick ant set it colors.
        for (int i = 0; i < amount; i++) {
            bricks[i] = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
            bricks[i].setColor(Color.BLACK);
            bricks[i].setFilled(true);
            bricks[i].setFillColor(BRICK_COLOR);
        }
        return (bricks);
    }

    private void buildPyramid(GRect[] bricks) {
        double rowStartX;
        double rowStartY;

        /*  Building each row. We can find the start position of each first brick in each row,
          so send these variables to method, and it will build the row.                         */
        for (int i = 0; i < BRICKS_IN_BASE; i++) {
            rowStartX = (getWidth() - BRICK_WIDTH * BRICKS_IN_BASE) / 2.0 - SCREEN_OFFSET;
            rowStartY = getHeight() - BRICK_HEIGHT - SCREEN_OFFSET;
            buildBrickRow(bricks, rowStartX, rowStartY, i);
        }
    }

    //  Method puts every brick on window using each brick, row number, end row starting location.
    private void buildBrickRow(GRect[] bricks, double rowStartX, double rowStartY, int i) {
        //  Every new row will consist 1 brick less (BRICKS_IN_BASE - i).
        for (int j = 0; j < BRICKS_IN_BASE - i; j++) {
            //  Every brick need to get it owd position.
            double xLocation = rowStartX + j * BRICK_WIDTH + i * (BRICK_WIDTH / 2.0);
            double yLocation = rowStartY - i * BRICK_HEIGHT;
            //  Setting position.
            bricks[brickNumber].setLocation(xLocation, yLocation);
            //  Put brick on screen.
            add(bricks[brickNumber]);
            /*  brickNumber is Class private variable, using it
                we can count every brick what was used for building. */
            brickNumber++;
        }
    }
}
