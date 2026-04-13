package de.m_marvin.univec.api;

/*
 * Declares all mathematical methods for the 4d vectors
 */
public interface IVector4Math<N extends Number, VO extends IVector4<N> & IVector4Math<N, VO>> extends IVector4<N> {

	public <T> VO readFrom(T vectorObject);
	public <T> T writeTo(T vectorObject);
	
	/* Basic math */
	
	public VO setI(N x, N y, N z, N w);
	public VO setI(IVector4<? extends Number> vec);

	public VO reset();
	public VO copy();
	
	default public VO add(IVector4<? extends Number> vec) { return this.copy().addI(vec); }
	default public VO add(N x, N y, N z, N w) { return this.copy().addI(x, y, z, w); }
	public VO addI(IVector4<? extends Number> vec);
	public VO addI(N x, N y, N z, N w);

	default public VO sub(IVector4<? extends Number> vec) { return this.copy().subI(vec); }
	default public VO sub(N x, N y, N z, N w) { return this.copy().subI(x, y, z, w); }
	public VO subI(IVector4<? extends Number> vec);
	public VO subI(N x, N y, N z, N w);

	default public VO mul(IVector4<? extends Number> vec) { return this.copy().mulI(vec); }
	default public VO mul(N x, N y, N z, N w) { return this.copy().mulI(x, y, z, w); }
	default public VO mul(N n) { return this.copy().mulI(n); }
	public VO mulI(IVector4<? extends Number> vec);
	public VO mulI(N x, N y, N z, N w);
	public VO mulI(N n);

	default public VO div(IVector4<? extends Number> vec) { return this.copy().divI(vec); }
	default public VO div(N x, N y, N z, N w) { return this.copy().divI(x, y, z, w); }
	default public VO div(N n) { return this.copy().divI(n); }
	public VO divI(IVector4<? extends Number> vec);
	public VO divI(N x, N y, N z, N w);
	public VO divI(N n);

	default public VO min(IVector4<? extends Number> vec) { return this.copy().minI(vec); }
	default public VO min(N value) { return this.copy().minI(value); }
	public VO minI(IVector4<? extends Number> vec);
	public VO minI(N value);

	default public VO max(IVector4<? extends Number> vec) { return this.copy().maxI(vec); }
	default public VO max(N value) { return this.copy().maxI(value); }
	public VO maxI(IVector4<? extends Number> vec);
	public VO maxI(N value);
	
	default public VO clamp(IVector4<? extends Number> min, IVector4<? extends Number> max) { return this.copy().clampI(min, max); }
	default public VO clamp(N min, N max) { return this.copy().clampI(min, max); }
	public VO clampI(IVector4<? extends Number> min, IVector4<? extends Number> max);
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

	public N dot(IVector4<? extends Number> vec);
	public VO lerp(IVector4<? extends Number> vec, N delta);
	public N distSqr(IVector4<? extends Number> vec);
	@SuppressWarnings("unchecked")
	default public N dist(IVector4<? extends Number> vec) { return (N) (Double) Math.sqrt((Double) this.distSqr(vec)); }

	default public VO cross(IVector3<? extends Number> vec) { return this.copy().crossI(vec); }
	public VO crossI(IVector3<? extends Number> vec);
	
	default public VO module(N m) { return this.copy().moduleI(m); }
	public VO moduleI(N m);

	default public VO normalize() { return this.copy().normalizeI(); }
	public VO normalizeI();

	default public VO tryNormalize() { return this.copy().tryNormalizeI(); }
	public VO tryNormalizeI();
	
}
