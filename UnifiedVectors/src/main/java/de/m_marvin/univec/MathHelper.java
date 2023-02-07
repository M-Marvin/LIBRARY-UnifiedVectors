package de.m_marvin.univec;

public class MathHelper {
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
