package de.m_marvin.unimat.api;

/**
 * Basic operations that can be done on matrices as data types.<br>
 * These define essentially an 2 dimensional array, nothing more.
 * @param <N> The data type of the numerical values
 */
public interface IMatrix<N extends Number> {

	public Class<? extends Number> getTypeClass();
	
	public int width();
	public int height();
	
	public N m(int x, int y);
	public void set(int x, int y, N m);

	public boolean isSquare();
	
}
