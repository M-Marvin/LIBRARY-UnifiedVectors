package de.m_marvin.univec;

import de.m_marvin.univec.impl.Vec3d;

/**
 * Contains different utility functions that might be useful
 */
public class VecUtil {

	private VecUtil() {}
	
	/**
	 * Calculate shortest line between two other arbitrary, infinite lines in 3D space<br>
	 * represented by the equations L1(s) = P1 + s * V1 and L2(s) = P2 + s * V2<br>
	 * Returns two points at the position of the two lines intersecting with the shortest, perpendicular line.
	 **/	
	public static double[] findShortestBetweenLines(Vec3d P1, Vec3d P2, Vec3d V1, Vec3d V2) {
	    double S1 = P2.dot(V1) - P1.dot(V1);
	    double S2 = P2.dot(V2) - P1.dot(V2);
	    double S3 = V2.lengthSqr();
	    double S4 = V1.lengthSqr();
	    double S5 = V2.dot(V1);
	    double a = (S1*S3-S2*S5)/(S3*S4-S5*S5);
	    double b = -(S2*S4-S1*S5)/(S3*S4-S5*S5);
		return new double[] {a, b};
	}
	
	/**
	 * Calculate shortest line between two other arbitrary, infinite lines in 3D space<br>
	 * represented by the equations L1(s) = P1 + s * (P2 - P1) and L2(s) = P3 + s * (P4 - P3)<br>
	 * Returns two points at the position of the two lines intersecting with the shortest, perpendicular line.
	 **/
	public static double[] findShortestBetweenLinePoints(Vec3d P1, Vec3d P2, Vec3d P3, Vec3d P4) {
		Vec3d V1 = P2.sub(P1);
		Vec3d V2 = P4.sub(P3);
		return findShortestBetweenLines(P1, P3, V1, V2);
	}

	/**
	 * Calculate shortest line between two other arbitrary, finite lines in 3D space <br>
	 * and uses it to find the shortest distance between the two lines<br>
	 * represented by the equations L1(s) = P1 + s * V1 and L2(s) = P3 + s * V1<br>
	 * Returns the shortest distance between the two lines.<br>
	 * Optionally two additional vector can be passed, which will be set to the two endpoints (intersections) of the shortest possible line.
	 **/
	public static double findShortestDistanceBetweenLines(Vec3d P1, Vec3d P2, Vec3d V1, Vec3d V2, Vec3d PI1, Vec3d PI2) {

		double[] x = VecUtil.findShortestBetweenLines(P1, P2, V1, V2);

		boolean onLine1 = x[0] >= 0 && x[0] <= 1;
		boolean onLine2 = x[1] >= 0 && x[1] <= 1;
		
		Vec3d ip1 = onLine1 ? P1.add(V1.mul(x[0])) : null;
		Vec3d ip2 = onLine2 ? P2.add(V2.mul(x[1])) : null;
		double d;
		
		if (ip1 == null && ip2 == null) {
			double d1 = P1.dist(P2);
			double d2 = P1.add(V1).dist(P2);
			double d3 = P1.dist(P2.add(V2));
			double d4 = P1.add(V1).dist(P2.add(V2));
			d = Math.min(Math.min(d1, d2), Math.min(d3, d4));
			if (d == d1) {
				ip1 = P1;
				ip2 = P2;
			} else if (d == d2) {
				ip1 = P1.add(P2);
				ip2 = P2;
			} else if (d == d3) {
				ip1 = P1;
				ip2 = P2.add(V2);
			} else if (d == d4) {
				ip1 = P1.add(V1);
				ip2 = P2.add(V2);
			}
		} else if (ip1 == null) {
			double d1 = P1.dist(ip2);
			double d2 = P1.add(V1).dist(ip2);
			d = Math.min(d1, d2);
			if (d == d1) {
				ip1 = P1;
			} else if (d == d2) {
				ip1 = P1.add(P2);
			}
		} else if (ip2 == null) {
			double d1 = ip1.dist(P2);
			double d2 = ip1.dist(P2.add(V2));
			d = Math.min(d1, d2);
			if (d == d1) {
				ip2 = P2;
			} else if (d == d2) {
				ip2 = P2.add(V2);
			}
		} else {
			d = ip1.dist(ip2);
		}
		
		if (PI1 != null) PI1.setI(ip1);
		if (PI2 != null) PI2.setI(ip2);
		return d;
	}

	/**
	 * Calculate shortest line between two other arbitrary, finite lines in 3D space <br>
	 * and uses it to find the shortest distance between the two lines<br>
	 * represented by the equations L1(s) = P1 + s * (P2 - P1) and L2(s) = P3 + s * (P4 - P3)<br>
	 * Returns the shortest distance between the two lines.<br>
	 * Optionally two additional vector can be passed, which will be set to the two endpoints (intersections) of the shortest possible line.
	 **/
	public static double findShortestDistanceBetweenLinePoints(Vec3d P1, Vec3d P2, Vec3d P3, Vec3d P4, Vec3d PI1, Vec3d PI2) {
		return findShortestDistanceBetweenLines(P1, P2.sub(P1), P3, P4.sub(P3), PI1, PI2);
	}
	
}
