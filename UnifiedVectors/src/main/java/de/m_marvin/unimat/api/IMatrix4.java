package de.m_marvin.unimat.api;

public interface IMatrix4<N extends Number> extends IMatrix<N> {

	public N m00();
	public N m01();
	public N m02();
	public N m03();
	public N m10();
	public N m11();
	public N m12();
	public N m13();
	public N m20();
	public N m21();
	public N m22();
	public N m23();
	public N m30();
	public N m31();
	public N m32();
	public N m33();

	default int getColumns() {
		return 4;
	}
	
	default int getRows() {
		return 4;
	}
	
}
