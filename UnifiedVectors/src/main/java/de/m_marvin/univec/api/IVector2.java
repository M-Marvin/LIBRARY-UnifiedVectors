package de.m_marvin.univec.api;

/*
 * Base interface of all vectors
 * Contains basic methods that share all vectors
 */
public interface IVector2<N extends Number> extends IVector {
	
	public N x();
	default N getX() {return x();}
	
	public N y();
	default N getY() {return y();}
	
	public void setX(N x);
	public void setY(N y);
	
	public N length();
	public N lengthSqr();
	
}
