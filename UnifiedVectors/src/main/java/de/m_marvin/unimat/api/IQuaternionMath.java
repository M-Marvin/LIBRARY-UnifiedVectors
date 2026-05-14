package de.m_marvin.unimat.api;

import de.m_marvin.univec.api.IVector3;

/*
 * Declares all mathematical methods for the quaternions.
 * This implemention is only really suited for rotation representation and operations on rotational data.
 */
public interface IQuaternionMath<N extends Number, QO extends IQuaternion<N> & IQuaternionMath<N, QO, QI, V>, QI extends IQuaternion<?>, V extends IVector3<N>> extends IQuaternion<N> {
	
	/* Basic math */
	
	default public QO mul(QI quat) { return this.copy().mulI(quat); };
	public QO mulI(QI quat);

	default public QO mul(N f) { return this.copy().mulI(f); }
	public QO mulI(N f);
	
	default public QO div(N f) { return this.copy().div(f); };
	public QO divI(N f);
	
	default public QO add(QI quat) { return this.copy().addI(quat); }
	public QO addI(QI quat);

	default public QO sub(QI quat) { return this.copy().sub(quat); }
	public QO subI(QI quat);

	public QO normalizeI();
	default public QO normalize() { return this.copy().normalizeI(); }

	public QO tryNormalizeI();
	default public QO tryNormalize() { return this.copy().tryNormalizeI(); }
	
	public N length();
	public N lengthSqr();
	
	public boolean isFinite();

	public QO setI(N i, N j, N k, N r);
	public QO setI(QI quat);
	
	public QO copy();
	
	/* Quaternion math */

	default public QO conj() { return this.copy().conjI(); }
	public QO conjI();
	
	public QO setVectorI(IVector3<? extends Number> axis, boolean degree);
	public QO setVectorAngleI(IVector3<? extends Number> axis, N angle, boolean degree);
	public QO setEulerI(IVector3<? extends Number> euler, EulerOrder order, boolean degree);
	
	public V vector(boolean degree);
	public V euler(EulerOrder order, boolean degree);
	
	public static enum EulerOrder {
		XYZ, ZYX, ZXY, YXZ;
	}
	
	public V transform(IVector3<? extends Number> vector);
	
}
