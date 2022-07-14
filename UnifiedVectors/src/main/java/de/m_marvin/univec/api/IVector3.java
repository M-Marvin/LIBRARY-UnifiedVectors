package de.m_marvin.univec.api;

/*
 * Base interface of all 3d vectors, extends the 2d base interface with z axis
 * Contains basic methods that share all 3d vectors
 */
public interface IVector3<N extends Number> extends IVector2<N> {

	public N z();
	default N getZ() {return z();}
	
	public void setZ(N z);
	
}
