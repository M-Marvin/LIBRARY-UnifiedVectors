package de.m_marvin.unimat.api;

import de.m_marvin.univec.api.IVector3;

/*
 * Declares all mathematical methods for the quaternions
 */
@SuppressWarnings("unchecked")
public interface IQuaternionMath<N extends Number, QO extends IQuaternion<N>, QI extends IQuaternion<?>, V extends IVector3<N>> extends IQuaternion<N> {
	
	/* Basic math */
	
	public QO mul(QI quat);
	default public QO mulI(QI quat) { return this.setI((QI) this.mul(quat)); }

	public QO mul(N f);
	default public QO mulI(N f) { return this.setI((QI) this.mul(f)); }
	
	public QO div(N f);
	default public QO divI(N f) { return this.setI((QI) this.div(f)); }
	
	public QO add(QI quat);
	default public QO addI(QI quat) { return this.setI((QI) this.add(quat)); }

	public QO sub(QI quat);
	default public QO subI(QI quat) { return this.setI((QI) this.sub(quat)); }

	public boolean isFinite();

	public QO setI(N i, N j, N k, N r);
	public QO setI(QI quat);

	public QO copy();
	
	/* Quaternion math */
	
	public QO conj();
	default public QO conjI() { return this.setI((QI) this.conj()); }
	
	public V euler(EulerOrder order, boolean degree);
	
	public static enum EulerOrder {
		XYZ, ZYX, ZXY, YXZ;
	}
	
}
