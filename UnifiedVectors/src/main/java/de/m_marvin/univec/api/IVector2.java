package de.m_marvin.univec.api;

/*
 * Base interface of all vectors (2d and 2d ones)
 * Contains basic methods that share all vectors
 */
public interface IVector2<N extends Number> extends IVector {
	
	public N x();
	default N getX() {return x();}
	
	public N y();
	default N geY() {return y();}
	
	public void setX(N x);
	public void setY(N y);
	
	public N length();
	public N lengthSqrt();
	
}
