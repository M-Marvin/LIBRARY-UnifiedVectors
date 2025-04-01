package de.m_marvin.unimat.api;

public interface IMatrixMath<N extends Number, M extends IMatrix<N>> {

	/* Basic math */
	
	public M setI(M mat);
	
	public M copy();
	
	public M add(M mat);
	default public M addI(M mat) { return this.setI((M) this.add(mat)); }

	public M sub(M mat);
	default public M subI(M mat) { return this.setI((M) this.sub(mat)); }

	public M mul(M mat);
	default public M mulI(M mat) { return this.setI((M) this.mul(mat)); }
	
	public M scalarMul(N n);
	default public M scalarMulI(N n) { return this.setI((M) this.scalarMul(n)); }

	public M scalarDiv(N n);
	default public M scalarDivI(N n) { return this.setI((M) this.scalarDiv(n)); }
	
	/* Matrix math */
	
	public M identity();
	
	public N adjugateAndDet();
	public N determinant();
	public boolean invertI();
	public M tryInvertI();
	
}
