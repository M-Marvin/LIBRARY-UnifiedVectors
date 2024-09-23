package de.m_marvin.unimat.api;

public interface IMatrixMath<M extends IMatrix<M>> {

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
	
	public float adjugateAndDet();
	public float determinant();
	public boolean invertI();
	public M tryInvertI();
	
}
