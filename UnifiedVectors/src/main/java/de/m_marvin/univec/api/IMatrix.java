package de.m_marvin.univec.api;

public interface IMatrix<N extends Number> {
	
	public N get(int x, int y);
	public void set(int x, int y, N value);
	
}
