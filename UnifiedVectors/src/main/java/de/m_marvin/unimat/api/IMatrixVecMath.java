package de.m_marvin.unimat.api;

import de.m_marvin.univec.api.IVector;

public interface IMatrixVecMath<M extends IMatrix<M>, V extends IVector, VO extends IVector> extends IMatrixMath<M> {
	
	public M mul(IQuaternion<?> quat);
	default public M mulI(IQuaternion<?> quat) { return this.setI((M) this.mul(quat)); }

	public M scalar(float f);
	default public M scalarI(float mat) { return this.setI((M) this.scalar(mat)); }
	
	public M transpose();
	default public M transposeI() { return this.setI((M) this.transpose()); }
	
	public VO translate(V vec);
	
}
