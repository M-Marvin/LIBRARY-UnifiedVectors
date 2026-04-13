package de.m_marvin.univec.api;

import de.m_marvin.unimat.api.IQuaternion;

/*
 * Declares all mathematical methods for the 3d vectors
 */
public interface IVector3Math<N extends Number, VO extends IVector3<N> & IVector3Math<N, VO, Q>, Q extends IQuaternion<? extends Number>> extends IVector3<N> {

	public <T> VO readFrom(T vectorObject);
	public <T> T writeTo(T vectorObject);
	
	/* Basic math */

	public VO setI(N x, N y, N z);
	public VO setI(IVector3<? extends Number> vec);

	public VO reset();
	public VO copy();
	
	default public VO add(IVector3<? extends Number> vec) { return this.copy().addI(vec); }
	default public VO add(N x, N y, N z) { return this.copy().addI(x, y, z); }
	public VO addI(IVector3<? extends Number> vec);
	public VO addI(N x, N y, N z);

	default public VO sub(IVector3<? extends Number> vec) { return this.copy().subI(vec); }
	default public VO sub(N x, N y, N z) { return this.copy().subI(x, y, z); }
	public VO subI(IVector3<? extends Number> vec);
	public VO subI(N x, N y, N z);

	default public VO mul(IVector3<? extends Number> vec) { return this.copy().mulI(vec); }
	default public VO mul(N x, N y, N z) { return this.copy().mulI(x, y, z); }
	default public VO mul(N n) { return this.copy().mulI(n); }
	public VO mulI(IVector3<? extends Number> vec);
	public VO mulI(N x, N y, N z);
	public VO mulI(N n);

	default public VO div(IVector3<? extends Number> vec) { return this.copy().divI(vec); }
	default public VO div(N x, N y, N z) { return this.copy().divI(x, y, z); }
	default public VO div(N n) { return this.copy().divI(n); }
	public VO divI(IVector3<? extends Number> vec);
	public VO divI(N x, N y, N z);
	public VO divI(N n);

	default public VO min(IVector3<? extends Number> vec) { return this.copy().minI(vec); }
	default public VO min(N value) { return this.copy().minI(value); }
	public VO minI(IVector3<? extends Number> vec);
	public VO minI(N value);

	default public VO max(IVector3<? extends Number> vec) { return this.copy().maxI(vec); }
	default public VO max(N value) { return this.copy().maxI(value); }
	public VO maxI(IVector3<? extends Number> vec);
	public VO maxI(N value);
	
	default public VO clamp(IVector3<? extends Number> min, IVector3<? extends Number> max) { return this.copy().clampI(min, max); }
	default public VO clamp(N min, N max) { return this.copy().clampI(min, max); }
	public VO clampI(IVector3<? extends Number> min, IVector3<? extends Number> max);
	public VO clampI(N min, N max);
	
	public VO negateI();
	default public VO negate() { return this.copy().negateI(); }
	
	public N sum();
	
	public VO absI();
	default public VO abs() { return this.copy().absI(); }
	
	public VO signI();
	default public VO sign() { return this.copy().signI(); }
	
	public VO floorI();
	default public VO floor() { return this.copy().floorI(); }
	
	public VO ceilI();
	default public VO ceil() { return this.copy().ceilI(); }
	
	public VO roundI();
	default public VO round() { return this.copy().roundI(); }
	
	public boolean isFinite();
	
	/* Vector math */

	public double angle(IVector3<? extends Number> vec);
	public N dot(IVector3<? extends Number> vec);
	public VO lerp(IVector3<? extends Number> vec, N delta);
	public N distSqr(IVector3<? extends Number> vec);
	public N dist(IVector3<? extends Number> vec);
	
	public Q relativeRotationQuat(IVector3<? extends Number> reference);

	default public VO cross(IVector3<? extends Number> vec) { return this.copy().crossI(vec); }
	public VO crossI(IVector3<? extends Number> vec);
	
	default public VO module(N m) { return this.copy().moduleI(m); }
	public VO moduleI(N m);

	default public VO normalize() { return this.copy().normalizeI(); }
	public VO normalizeI();

	default public VO tryNormalize() { return this.copy().tryNormalizeI(); }
	public VO tryNormalizeI();
	
	public VO transform(Q quaternion);
	default public VO transformI(Q quaternion) { return setI(transform(quaternion)); }

	public VO anyOrthogonal();
	public VO[] orthogonals(IVector3<? extends Number> vec2);
	
}
