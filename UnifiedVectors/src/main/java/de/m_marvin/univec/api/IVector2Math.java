package de.m_marvin.univec.api;

/*
 * Declares all mathematical methods for the 2d vectors
 */
public interface IVector2Math<N extends Number, VO extends IVector2<N>, VZ extends IVector2<Integer>> extends IVector2<N> {
	
	public <T> VO readFrom(T vectorObject);
	public <T> T writeTo(T vectorObject);
	
	/* Basic math */

	public VO setI(N x, N y);
	public VO setI(IVector2<? extends Number> vec);
	
	public VO copy();
	
	public VO add(IVector2<? extends Number> vec);
	public VO add(N x, N y);
	default public VO addI(IVector2<? extends Number> vec) { return this.setI(this.add(vec)); }
	default public VO addI(N x, N y) { return this.setI(this.add(x, y)); }
	
	public VO sub(IVector2<? extends Number> vec);
	public VO sub(N x, N y);
	default public VO subI(IVector2<? extends Number> vec) { return this.setI(this.sub(vec)); }
	default public VO subI(N x, N y) { return this.setI(this.sub(x, y)); }
	
	public VO mul(IVector2<? extends Number> vec);
	public VO mul(N x, N y);
	public VO mul(N n);
	default public VO mulI(IVector2<? extends Number> vec) { return this.setI(this.mul(vec)); }
	default public VO mulI(N x, N y) { return this.setI(this.mul(x, y)); }
	default public VO mulI(N n) { return this.setI(this.mul(n)); }
	
	public VO div(IVector2<? extends Number> vec);
	public VO div(N x, N y);
	public VO div(N n);
	default public VO divI(IVector2<? extends Number> vec) { return this.setI(this.div(vec)); }
	default public VO divI(N x, N y) { return this.setI(this.div(x, y)); }
	default public VO divI(N n) { return this.setI(this.div(n)); }
	
	public VO min(IVector2<? extends Number> vec);
	public VO min(N value);
	default public VO minI(IVector2<? extends Number> vec) { return this.setI(this.min(vec)); }
	default public VO minI(N value) { return this.setI(this.min(value)); }
	
	public VO max(IVector2<? extends Number> vec);
	public VO max(N value);
	default public VO maxI(IVector2<? extends Number> vec) { return this.setI(this.max(vec)); }
	default public VO maxI(N value) { return this.setI(this.max(value)); }
	
	public VO clamp(IVector2<? extends Number> min, IVector2<? extends Number> max);
	public VO clamp(N min, N max);
	default public VO clampI(IVector2<? extends Number> min, IVector2<? extends Number> max) { return this.setI(this.clamp(min, max)); }
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

	public double angle(IVector2<? extends Number> vec);
	public N cross(IVector2<? extends Number> vec);
	public N dot(IVector2<? extends Number> vec);
	public VO lerp(IVector2<? extends Number> vec, N delta);
	public N distSqr(IVector2<? extends Number> vec);
	public N dist(IVector2<? extends Number> vec);
	
	public VO module(N m);
	default public VO moduleI(N m) { return this.setI(this.module(m)); }
	
	public VO normalize();
	default public VO normalizeI() { return this.setI(this.normalize()); }
	
	public VO tryNormalize();
	default public VO tryNormalizeI() { return this.setI(this.tryNormalize()); }
	
	public VO anyOrthogonal();
	public VO[] orthogonals();
	
}
