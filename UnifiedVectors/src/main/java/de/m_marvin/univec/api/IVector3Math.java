package de.m_marvin.univec.api;

import de.m_marvin.unimat.api.IQuaternion;

/*
 * Declares all mathematical methods for the 3d vectors
 */
public interface IVector3Math<N extends Number, VO extends IVector3<N>, VZ extends IVector3<Integer>, Q extends IQuaternion<? extends Number>> extends IVector3<N> {

	public <T> VO readFrom(T vectorObject);
	public <T> T writeTo(T vectorObject);
	
	/* Basic math */

	public VO setI(N x, N y, N z);
	public VO setI(IVector3<? extends Number> vec);
	
	public VO copy();
	
	public VO add(IVector3<? extends Number> vec);
	public VO add(N x, N y, N z);
	default public VO addI(IVector3<? extends Number> vec) { return this.setI(this.add(vec)); }
	default public VO addI(N x, N y, N z) { return this.setI(this.add(x, y, z)); }
	
	public VO sub(IVector3<? extends Number> vec);
	public VO sub(N x, N y, N z);
	default public VO subI(IVector3<? extends Number> vec) { return this.setI(this.sub(vec)); }
	default public VO subI(N x, N y, N z) { return this.setI(this.sub(x, y, z)); }
	
	public VO mul(IVector3<? extends Number> vec);
	public VO mul(N x, N y, N z);
	public VO mul(N n);
	default public VO mulI(IVector3<? extends Number> vec) { return this.setI(this.mul(vec)); }
	default public VO mulI(N x, N y, N z) { return this.setI(this.mul(x, y, z)); }
	default public VO mulI(N n) { return this.setI(this.mul(n)); }
	
	public VO div(IVector3<? extends Number> vec);
	public VO div(N x, N y, N z);
	public VO div(N n);
	default public VO divI(IVector3<? extends Number> vec) { return this.setI(this.div(vec)); }
	default public VO divI(N x, N y, N z) { return this.setI(this.div(x, y, z)); }
	default public VO divI(N n) { return this.setI(this.div(n)); }

	public VO min(IVector3<? extends Number> vec);
	public VO min(N value);
	default public VO minI(IVector3<? extends Number> vec) { return this.setI(this.min(vec)); }
	default public VO minI(N value) { return this.setI(this.min(value)); }
	
	public VO max(IVector3<? extends Number> vec);
	public VO max(N value);
	default public VO maxI(IVector3<? extends Number> vec) { return this.setI(this.max(vec)); }
	default public VO maxI(N value) { return this.setI(this.max(value)); }
	
	public VO clamp(IVector3<? extends Number> min, IVector3<? extends Number> max);
	public VO clamp(N min, N max);
	default public VO clampI(IVector3<? extends Number> min, IVector3<? extends Number> max) { return this.setI(this.clamp(min, max)); }
	default public VO clampI(N min, N max) { return this.setI(this.clamp(min, max)); }

	public VO abs();
	default public VO absI() { return this.setI(this.abs()); }

	public N sum();
	public VZ sign();
	public VZ floor();
	public VZ ceil();
	public VZ round();
	
	public boolean isFinite();
	
	/* Vector math */

	public double angle(IVector3<? extends Number> vec);
	public N dot(IVector3<? extends Number> vec);
	public VO cross(IVector3<? extends Number> vec);
	public VO lerp(IVector3<? extends Number> vec, N delta);
	public N distSqr(IVector3<? extends Number> vec);
	public N dist(IVector3<? extends Number> vec);
	
	public Q relativeRotationQuat(IVector3<? extends Number> reference);
	
	public VO module(N m);
	default public VO moduleI(N m) { return this.setI(this.module(m)); }
	
	public VO normalize();
	default public VO normalizeI() { return this.setI(this.normalize()); }

	public VO tryNormalize();
	default public VO tryNormalizeI() { return this.setI(this.tryNormalize()); }
	
	public VO transform(Q quaternion);
	default public VO transformI(Q quaternion) { return setI(transform(quaternion)); }

	public VO anyOrthogonal();
	public VO[] orthogonals(IVector3<? extends Number> vec2);
	
}
