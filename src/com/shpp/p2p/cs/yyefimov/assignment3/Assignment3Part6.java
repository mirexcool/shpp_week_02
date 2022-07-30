package com.shpp.p2p.cs.yyefimov.assignment3;


import acm.graphics.*;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.util.ArrayList;

// Class Assignment3Part6 will show some animation.
public class Assignment3Part6 extends WindowProgram {
    //  Screen size setup.
    public static final int APPLICATION_WIDTH = 1200;
    public static final int APPLICATION_HEIGHT = 800;

    /*  The amount of time between frames (60 FPS).  */
    private static final double TIME_FOR_FRAME = 1000.0 / 60;
    /*  Animation time. We need 5sec(5000ms) animation  */
    private static final double ANIMATION_TIME = 5000.0;
    /*  Animation settings.
     * GRAVITY will pull asteroids to bottom of screen.
     * ASTEROID_GENERATION_COUNT will produce more asteroids (randomly from 0 to count) each timeframe
     * of ASTEROID_GENERATION_SPEED (less amount will do faster generation).
     * ASTEROID_SPEED set the speed of asteroid (randomly from 0 to count).
     * STAR_SPEED will set the star speed at the beginning of animation (randomly from -count to count).
     * STAR_ELASTICITY when star hits the edge of the screen it will reflect, you can set
     * the coefficient of reflection force.
     *  */
    private static final double GRAVITY = 0.15;
    private static final int ASTEROID_GENERATION_COUNT = 15;
    private static final double ASTEROID_GENERATION_SPEED = 5;
    private static final double ASTEROID_SPEED = 5;
    private static final double STAR_SIZE = 250;
    private static final double STAR_SPEED = 20;
    private static final double STAR_ELASTICITY = 1;

    //  Method will create animation for 5 seconds.
    @Override
    public void run() {
        double timeStart, timeEnd, deltaTime;
        RandomGenerator rg = RandomGenerator.getInstance();
        ArrayList<Asteroid> asteroids = new ArrayList<>();
        //  Save the time of started program.
        timeStart = System.currentTimeMillis();
        timeEnd = timeStart;
        //  deltaTime will show how long our program in process.
        deltaTime = timeEnd - timeStart;
        //  Preparing for our animation.
        createScene(rg, asteroids);
        while (deltaTime <= ANIMATION_TIME) {
            doAnimation(rg, asteroids);
            if (deltaTime % ASTEROID_GENERATION_SPEED == 0)
                createAsteroids(rg, rg.nextInt(ASTEROID_GENERATION_COUNT), asteroids);
            pause(TIME_FOR_FRAME);
            timeEnd = System.currentTimeMillis();
            deltaTime = timeEnd - timeStart;
        }
    }

    //  Preparing for animation.
    private void createScene(RandomGenerator rg, ArrayList<Asteroid> asteroids) {
        GOval star = new GOval(STAR_SIZE, STAR_SIZE);
        double dx, dy;
        double xPos = rg.nextDouble(STAR_SIZE, getWidth() - STAR_SIZE);
        double yPos = rg.nextDouble(STAR_SIZE, getHeight() - STAR_SIZE);
        //  dx, dy is speed on each way.
        dx = rg.nextDouble(-STAR_SPEED, STAR_SPEED);
        dy = rg.nextDouble(-STAR_SPEED, STAR_SPEED);
        star.setLocation(xPos, yPos);
        star.setFilled(true);
        star.setColor(Color.BLACK);
        star.sendToFront();
        add(star);
        //  Asteroid class is additional class to store GObject with some parameters.
        Asteroid starAsteroid = new Asteroid(star, dx, dy, 0);
        //  Only zero object - star will have false boolean.
        starAsteroid.isAsteroid = false;
        asteroids.add(starAsteroid);
    }

    //  Method will create some amount of asteroids what will depend on amount.
    private void createAsteroids(RandomGenerator rg, int amount, ArrayList<Asteroid> asteroids) {
        //  We can imagine that when our star is produce asteroid this will reduce it size.
        double size = asteroids.get(0).asteroid.getSize().getHeight() - amount / 3.0;
        //  Get positioning for asteroids. They will come from center of star.
        double xPos = asteroids.get(0).asteroid.getX() + size / 2.0;
        double yPos = asteroids.get(0).asteroid.getY() + size / 2.0;
        GObject object = createNewAsteroid(xPos, yPos, rg, size);

        for (int i = 0; i < amount; i++) {
            //  Set starting speed for each asteroid. "2" in dy we put for more beautiful animation.
            double dx = rg.nextDouble(-ASTEROID_SPEED, ASTEROID_SPEED);
            double dy = rg.nextDouble(-2 * ASTEROID_SPEED, ASTEROID_SPEED);
            double dr = rg.nextDouble(-ASTEROID_SPEED, ASTEROID_SPEED);
            //  Creating new asteroid using constructor in Asteroid class.
            Asteroid asteroid = new Asteroid(object, dx, dy, dr);
            add(object);
            object.sendToBack();
            asteroids.add(asteroid);
        }
        ((GOval) asteroids.get(0).asteroid).setSize(size, size);
    }

    private GObject createNewAsteroid(double xPos, double yPos, RandomGenerator rg, double size) {
        GPolygon asteroid = new GPolygon(xPos, yPos);
        /*  Points for GPolygon. You need to read how to create polygon for understanding
         * why here was used a lot of "magic" numbers =) */
        asteroid.addVertex(0, 0);
        asteroid.addVertex(size / 10.0, size / 3.33);
        asteroid.addVertex(size / 5.0, 0);
        asteroid.addVertex(size / 2.0, -size / 10.0);
        asteroid.addVertex(size / 5.0, -size / 5.0);
        asteroid.addVertex(size / 10, -size / 2.0);
        asteroid.addVertex(0, -size / 5.0);
        asteroid.addVertex(-size / 3.33, -size / 10);
        asteroid.addVertex(0, 0);
        asteroid.setFilled(true);
        asteroid.setColor(rg.nextColor());
        asteroid.sendToBack();
        return asteroid;
    }

    //  Method will move each object on the screen.
    private void doAnimation(RandomGenerator rg, ArrayList<Asteroid> asteroids) {
        //  Star is always the first element.
        moveStar(asteroids.get(0), rg);
        moveAsteroids(asteroids, rg);
    }

    //  Method will move each asteroid.
    private void moveAsteroids(ArrayList<Asteroid> asteroids, RandomGenerator rg) {
        for (Asteroid asteroid : asteroids) {
            if (asteroid.isAsteroid) {
                //  GRAVITY pulls object down on the screen.
                asteroid.asteroidDy += GRAVITY;
                asteroid.asteroid.move(asteroid.asteroidDx, asteroid.asteroidDy);
                asteroid.asteroid.setColor(rg.nextColor());
                //  Rotate our object.
                ((GPolygon) asteroid.asteroid).rotate(asteroid.asteroidDr);
            }
        }
    }

    //  Method will move star.
    private void moveStar(Asteroid star, RandomGenerator rg) {
        star.asteroid.move(star.asteroidDx, star.asteroidDy);
        star.asteroid.setColor(rg.nextColor());
        //  Reflecting from the edge.
        if (star.asteroid.getX() > getWidth() - STAR_SIZE)
            star.asteroidDx *= -STAR_ELASTICITY;
        if (star.asteroid.getY() > getHeight() - STAR_SIZE)
            star.asteroidDy *= -STAR_ELASTICITY;
        if (star.asteroid.getX() <= 0)
            star.asteroidDx *= -STAR_ELASTICITY;
        if (star.asteroid.getY() <= 0)
            star.asteroidDy *= -STAR_ELASTICITY;
    }
}