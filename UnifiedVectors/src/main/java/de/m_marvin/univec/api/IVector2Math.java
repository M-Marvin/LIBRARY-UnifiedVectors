package de.m_marvin.univec.api;

/*
 * Declares all mathematical methods for the 2d vectors
 */
public interface IVector2Math<N extends Number, VO extends IVector2<N> & IVector2Math<N, VO>> extends IVector2<N> {
	
	public <T> VO readFrom(T vectorObject);
	public <T> T writeTo(T vectorObject);
	
	/* Basic math */

	public VO setI(N x, N y);
	public VO setI(IVector2<? extends Number> vec);
	
	public VO reset();
	public VO copy();

	default public VO add(IVector2<? extends Number> vec) { return this.copy().addI(vec); }
	default public VO add(N x, N y) { return this.copy().addI(x, y); }
	public VO addI(IVector2<? extends Number> vec);
	public VO addI(N x, N y);

	default public VO sub(IVector2<? extends Number> vec) { return this.copy().subI(vec); }
	default public VO sub(N x, N y) { return this.copy().subI(x, y); }
	public VO subI(IVector2<? extends Number> vec);
	public VO subI(N x, N y);

	default public VO mul(IVector2<? extends Number> vec) { return this.copy().mulI(vec); }
	default public VO mul(N x, N y) { return this.copy().mulI(x, y); }
	default public VO mul(N n) { return this.copy().mulI(n); }
	public VO mulI(IVector2<? extends Number> vec);
	public VO mulI(N x, N y);
	public VO mulI(N n);

	default public VO div(IVector2<? extends Number> vec) { return this.copy().divI(vec); }
	default public VO div(N x, N y) { return this.copy().divI(x, y); }
	default public VO div(N n) { return this.copy().divI(n); }
	public VO divI(IVector2<? extends Number> vec);
	public VO divI(N x, N y);
	public VO divI(N n);

	default public VO min(IVector2<? extends Number> vec) { return this.copy().minI(vec); }
	default public VO min(N value) { return this.copy().minI(value); }
	public VO minI(IVector2<? extends Number> vec);
	public VO minI(N value);

	default public VO max(IVector2<? extends Number> vec) { return this.copy().maxI(vec); }
	default public VO max(N value) { return this.copy().maxI(value); }
	public VO maxI(IVector2<? extends Number> vec);
	public VO maxI(N value);
	
	default public VO clamp(IVector2<? extends Number> min, IVector2<? extends Number> max) { return this.copy().clampI(min, max); }
	default public VO clamp(N min, N max) { return this.copy().clampI(min, max); }
	public VO clampI(IVector2<? extends Number> min, IVector2<? extends Number> max);
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

	public double angle(IVector2<? extends Number> vec);
	
	public N cross(IVector2<? extends Number> vec);
	public N dot(IVector2<? extends Number> vec);
	public VO lerp(IVector2<? extends Number> vec, N delta);
	public N distSqr(IVector2<? extends Number> vec);
	public N dist(IVector2<? extends Number> vec);

	default public VO module(N m) { return this.copy().moduleI(m); }
	public VO moduleI(N m);

	default public VO normalize() { return this.copy().normalizeI(); }
	public VO normalizeI();

	default public VO tryNormalize() { return this.copy().tryNormalizeI(); }
	public VO tryNormalizeI();
	
	public VO anyOrthogonal();
	public VO[] orthogonals();
	
}
