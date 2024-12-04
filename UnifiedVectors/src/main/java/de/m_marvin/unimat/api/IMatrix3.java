package de.m_marvin.unimat.api;

public interface IMatrix3<N extends Number> extends IMatrix<N> {

	public N m00();
	public N m01();
	public N m02();
	public N m10();
	public N m11();
	public N m12();
	public N m20();
	public N m21();
	public N m22();
	
	default int getColumns() {
		return 3;
	}
	
	default int getRows() {
		return 3;
	}
	
}
