package de.m_marvin.unimat.api;

import de.m_marvin.univec.api.IVector;

public interface IMatrixVecMath<N extends Number, M extends IMatrix<N>, V extends IVector, VO extends IVector> extends IMatrixMath<N, M> {
	
	public M mul(IQuaternion<N> quat);
	default public M mulI(IQuaternion<N> quat) { return this.setI((M) this.mul(quat)); }

	public M scalar(N f);
	default public M scalarI(N mat) { return this.setI((M) this.scalar(mat)); }
	
	public M transpose();
	default public M transposeI() { return this.setI((M) this.transpose()); }
	
	public VO translate(V vec);
	
}
