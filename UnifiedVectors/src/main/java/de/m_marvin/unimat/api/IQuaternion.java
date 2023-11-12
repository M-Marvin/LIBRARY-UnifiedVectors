package de.m_marvin.unimat.api;

/*
 * Base interface of all quaternions
 * Contains basic methods that share all quaternions
 */
public interface IQuaternion<N extends Number> {

	public N i();
	public N j();
	public N k();
	public N r();
	
	public default N getI() { return i(); }
	public default N getJ() { return j(); }
	public default N getK() { return k(); }
	public default N getR() { return r(); }
	
	public void setI(N i);
	public void setJ(N j);
	public void setK(N k);
	public void setR(N r);
	
}
