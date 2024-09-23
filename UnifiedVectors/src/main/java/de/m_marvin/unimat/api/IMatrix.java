package de.m_marvin.unimat.api;

public interface IMatrix<M extends IMatrix<M>> {

	public M copy();
	
	public float getField(int x, int y);
	public void setField(int x, int y, float f);

	public int getColumns();
	public int getRows();
	
	public float[] toFloatArr();
	public void loadFloatArr(float[] arr);
	
}


