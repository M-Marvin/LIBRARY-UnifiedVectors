package de.m_marvin.unimat.api;

public interface IMatrix<N extends Number> {

	public N getField(int x, int y);
	public void setField(int x, int y, N f);

	public int getColumns();
	public int getRows();
	
	public N[] toArr();
	public void loadArr(N[] arr);
	
}


