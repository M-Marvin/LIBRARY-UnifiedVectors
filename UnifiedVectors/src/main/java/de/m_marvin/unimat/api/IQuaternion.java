package de.m_marvin.unimat.api;

public interface IQuaternion<Q extends IQuaternion<?>> {

	public float i();
	public float j();
	public float k();
	public float r();

	public Q setI(float i, float j, float k, float r);
	default public Q setI(Q quat) { return this.setI(quat.i(), quat.j(), quat.k(), quat.r()); }

	public Q mul(Q quat);
	default public Q mulI(Q quat) { return this.setI(this.mul(quat)); }

	public Q mul(float f);
	default public Q mulI(float f) { return this.setI(this.mul(f)); }
	
	public Q conj();
	default public Q conjI() { return this.setI(this.conj()); }
	
	public Q copy();

}
