package frc.robot;

import java.util.HashMap;

public class Color {
    public int h;
    public int s;
    public int v;
    public double precision;
    public String name;
    /**
     * 
     * @param h
     * @param s
     * @param v
     * @param name
     */
    public Color(int h, int s, int v, String name) {
        this.h = h;
        this.s = s;
        this.v = v;
        this.name = name;
        this.precision = 1;
    
    }
    /**
     * 
     * @param h
     * @param s
     * @param v
     * @param precision
     * @param name
     */
    public Color(int h, int s, int v, double precision, String name) {
        this.h = h;
        this.s = s;
        this.v = v;
        this.precision = precision;
        this.name = name;
    }
    /**
     * default constructor
     */
    public Color() {

    }
    public String getName() {
        return this.name;
    }
    public int getH() {
        return this.h;
    }
    public int getS() {
        return this.s;
    }
    public int getV() {
        return this.v;
    }
    public static float[] RGBtoHSB(int r, int g, int b) {
        return java.awt.Color.RGBtoHSB(r, g, b, null); 
    }
}