package de.m_marvin.univec.api;

public interface IVector4Math<N extends Number, VO extends IVector4<N>, VZ extends IVector4<Integer>> extends IVector4<N> {

	public <T> VO readFrom(T vectorObject);
	public <T> T writeTo(T vectorObject);
	
	/* Basic math */
	
	public VO setI(N x, N y, N z, N w);
	public VO setI(IVector4<? extends Number> vec);
	
	public VO copy();
	
	public VO add(IVector4<? extends Number> vec);
	public VO add(N x, N y, N z, N w);
	default public VO addI(IVector4<? extends Number> vec) { return this.setI(this.add(vec)); }
	default public VO addI(N x, N y, N z, N w) { return this.setI(this.add(x, y, z, w)); }
	
	public VO sub(IVector4<? extends Number> vec);
	public VO sub(N x, N y, N z, N w);
	default public VO subI(IVector4<? extends Number> vec) { return this.setI(this.sub(vec)); }
	default public VO subI(N x, N y, N z, N w) { return this.setI(this.sub(x, y, z, w)); }
	
	public VO mul(IVector4<? extends Number> vec);
	public VO mul(N x, N y, N z, N w);
	public VO mul(N n);
	default public VO mulI(IVector4<? extends Number> vec) { return this.setI(this.mul(vec)); }
	default public VO mulI(N x, N y, N z, N w) { return this.setI(this.mul(x, y, z, w)); }
	default public VO mulI(N n) { return this.setI(this.mul(n)); }
	
	public VO div(IVector4<? extends Number> vec);
	public VO div(N x, N y, N z, N w);
	public VO div(N n);
	default public VO divI(IVector4<? extends Number> vec) { return this.setI(this.div(vec)); }
	default public VO divI(N x, N y, N z, N w) { return this.setI(this.div(x, y, z, w)); }
	default public VO divI(N n) { return this.setI(this.div(n)); }

	public VO min(IVector4<? extends Number> vec);
	public VO min(N value);
	default public VO minI(IVector4<? extends Number> vec) { return this.setI(this.min(vec)); }
	default public VO minI(N value) { return this.setI(this.min(value)); }
	
	public VO max(IVector4<? extends Number> vec);
	public VO max(N value);
	default public VO maxI(IVector4<? extends Number> vec) { return this.setI(this.max(vec)); }
	default public VO maxI(N value) { return this.setI(this.max(value)); }
	
	public VO clamp(IVector4<? extends Number> min, IVector4<? extends Number> max);
	public VO clamp(N min, N max);
	default public VO clampI(IVector4<? extends Number> min, IVector4<? extends Number> max) { return this.setI(this.clamp(min, max)); }
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

	public N dot(IVector4<? extends Number> vec);
	public VO lerp(IVector4<? extends Number> vec, N delta);
	public N distSqr(IVector4<? extends Number> vec);
	@SuppressWarnings("unchecked")
	default public N dist(IVector4<? extends Number> vec) { return (N) (Double) Math.sqrt((Double) this.distSqr(vec)); }
	
	public VO module(N m);
	default public VO moduleI(N m) { return this.setI(this.module(m)); }
	
	public VO normalize();
	default public VO normalizeI() { return this.setI(this.normalize()); }

	public VO tryNormalize();
	default public VO tryNormalizeI() { return this.setI(this.tryNormalize()); }
	
}
