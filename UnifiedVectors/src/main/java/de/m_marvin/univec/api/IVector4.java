package de.m_marvin.univec.api;

/*
 * Base interface of all 3d vectors, extends the 3d base interface with w axis
 * Contains basic methods that share all 3d vectors
 */
public interface IVector4<N extends Number> extends IVector3<N> {

	public N w();
	default N getW() {return w();}
	
	public void setW(N w);
	
}
