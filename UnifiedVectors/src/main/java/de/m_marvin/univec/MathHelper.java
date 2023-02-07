package de.m_marvin.univec;

public class MathHelper {

    //code from JOML:
    public static final double PI = java.lang.Math.PI;
    static final double PI2 = PI * 2.0;
    static final float PI_f = (float) java.lang.Math.PI;
    static final float PI2_f = PI_f * 2.0f;
    static final double PIHalf = PI * 0.5;
    static final float PIHalf_f = (float) (PI * 0.5);
    static final double PI_4 = PI * 0.25;
    static final double PI_INV = 1.0 / PI;

    public static float sqrt(float r) {
        return (float) java.lang.Math.sqrt(r);
    }

    public static double sqrt(double r) {
        return java.lang.Math.sqrt(r);
    }

    public static double cosFromSin(double sin, double angle) {
        double cos = sqrt(1.0 - sin * sin);
        double a = angle + PIHalf;
        double b = a - (int)(a / PI2) * PI2;
        if (b < 0.0)
            b = PI2 + b;
        if (b >= PI)
            return -cos;
        return cos;
    }

    public static float abs(float r) {
        return java.lang.Math.abs(r);
    }

    public static double abs(double r) {
        return java.lang.Math.abs(r);
    }

    public static boolean isFinite(double d) {
        return abs(d) <= Double.MAX_VALUE;
    }

    public static boolean isFinite(float f) {
        return abs(f) <= Float.MAX_VALUE;
    }

}
