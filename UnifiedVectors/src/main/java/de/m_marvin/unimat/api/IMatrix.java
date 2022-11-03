package de.m_marvin.unimat.api;

public interface IMatrix<M extends IMatrix<?>> {

	public M copy();
	
	public float getField(int x, int y);
	public void setField(int x, int y, int f);

	public float[] toFloatArr();
	public void loadFloatArr(float[] arr);
	
}
