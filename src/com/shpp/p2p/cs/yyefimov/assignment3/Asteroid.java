package com.shpp.p2p.cs.yyefimov.assignment3;

import acm.graphics.GObject;

//  Asteroid class will store some parameters of GObject for animation.
public class Asteroid {
    public GObject asteroid;
    //  Speed.
    public double asteroidDx;
    public double asteroidDy;
    //  Rotating.
    public double asteroidDr;
    //  Boolean to mark objects.
    public boolean isAsteroid = true;

    //  Constructor. https://www.w3schools.com/java/java_constructors.asp
    public Asteroid(GObject object, double dx, double dy, double dr) {
        this.asteroid = object;
        this.asteroidDx = dx;
        this.asteroidDy = dy;
        this.asteroidDr = dr;
    }
}
