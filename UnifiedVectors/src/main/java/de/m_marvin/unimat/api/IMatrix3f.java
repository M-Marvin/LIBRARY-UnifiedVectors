package de.m_marvin.unimat.api;

public interface IMatrix3f<M extends IMatrix3f<M>> extends IMatrix<M> {

	public float m00();
	public float m01();
	public float m02();
	public float m10();
	public float m11();
	public float m12();
	public float m20();
	public float m21();
	public float m22();
	
	default int getColumns() {
		return 3;
	}
	
	default int getRows() {
		return 3;
	}
	
}
