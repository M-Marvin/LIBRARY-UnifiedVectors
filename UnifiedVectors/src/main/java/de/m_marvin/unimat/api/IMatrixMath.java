package de.m_marvin.unimat.api;

import de.m_marvin.univec.api.IVector;

public interface IMatrixMath<M extends IMatrix<?>, V extends IVector> {

	/* Basic math */
	
	public M setI(M mat);
	
	public M copy();
	
	public M add(M mat);
	default public M addI(M mat) { return this.setI((M) this.add(mat)); }

	public M sub(M mat);
	default public M subI(M mat) { return this.setI((M) this.sub(mat)); }

	public M mul(M mat);
	default public M mulI(M mat) { return this.setI((M) this.mul(mat)); }

	/* Matrix math */
	
	public M identity();

	public M mul(IQuaternion<?> quat);
	default public M mulI(IQuaternion<?> quat) { return this.setI((M) this.mul(quat)); }

	public M scalar(float f);
	default public M scalarI(float mat) { return this.setI((M) this.scalar(mat)); }
	
	public M transpose();
	default public M transposeI() { return this.setI((M) this.transpose()); }
	
	public V translate(V vec);
	
	public float adjugateAndDet();
	public float determinant();
	public boolean invert();
	
}
