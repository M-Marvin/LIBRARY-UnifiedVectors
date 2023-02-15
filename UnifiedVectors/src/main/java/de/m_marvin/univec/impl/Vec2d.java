package de.m_marvin.univec.impl;

import de.m_marvin.univec.VectorParser;
import de.m_marvin.univec.api.IVector2;
import de.m_marvin.univec.api.IVector2Math;

/*
 * Implementation of a 3 dimensional double vector
 */
public class Vec2d implements IVector2Math<Double, Vec2d, IVector2<? extends Number>> {
	
	public double x;
	public double y;
	
	public Vec2d(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vec2d() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vec2d(IVector2<? extends Number> vec) {
		this.x = vec.x().doubleValue();
		this.y = vec.y().doubleValue();
	}

	public static Vec2d fromVec(Object vectorObject) {
		return new Vec2d(0, 0).readFrom(vectorObject);
	}
	
	@Override
	public <T> Vec2d readFrom(T vectorObject) {
		try {
			VectorParser.parseVectorObject(vectorObject, this);
		} catch (IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public <T> T writeTo(T vectorObject) {
		try {
			return VectorParser.parseToVectorObject(vectorObject, this);
		} catch (IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
			return vectorObject;
		}
	}
	
	@Override
	public Double x() {
		return x;
	}

	@Override
	public Double y() {
		return y;
	}

	@Override
	public void setX(Double x) {
		this.x = x;
	}

	@Override
	public void setY(Double y) {
		this.y = y;
	}

	@Override
	public Vec2d setI(Double x, Double y) {
		this.x = x;
		this.y = y;
		return this;
	}

	@Override
	public Vec2d setI(IVector2<? extends Number> vec) {
		this.x = vec.x().doubleValue();
		this.y = vec.y().doubleValue();
		return this;
	}

	@Override
	public Vec2d copy() {
		return new Vec2d(this.x, this.y);
	}

	@Override
	public Vec2d add(IVector2<? extends Number> vec) {
		return new Vec2d(this.x + vec.x().doubleValue(), this.y + vec.y().doubleValue());
	}

	@Override
	public Vec2d add(Double x, Double y) {
		return new Vec2d(this.x + x, this.y + y);
	}

	@Override
	public Vec2d sub(IVector2<? extends Number> vec) {
		return new Vec2d(this.x - vec.x().doubleValue(), this.y - vec.y().doubleValue());
	}

	@Override
	public Vec2d sub(Double x, Double y) {
		return new Vec2d(this.x - x, this.y - y);
	}

	@Override
	public Vec2d mul(IVector2<? extends Number> vec) {
		return new Vec2d(this.x * vec.x().doubleValue(), this.y * vec.y().doubleValue());
	}

	@Override
	public Vec2d mul(Double x, Double y) {
		return new Vec2d(this.x * x, this.y * y);
	}

	@Override
	public Vec2d mul(Double n) {
		return new Vec2d(this.x * n, this.y * n);
	}
	
	@Override
	public Vec2d div(IVector2<? extends Number> vec) {
		return new Vec2d(this.x / vec.x().doubleValue(), this.y / vec.y().doubleValue());
	}

	@Override
	public Vec2d div(Double x, Double y) {
		return new Vec2d(this.x / x, this.y / y);
	}
	
	@Override
	public Vec2d div(Double n) {
		return new Vec2d(this.x / n, this.y / n);
	}

	@Override
	public Vec2d module(Double m) {
		return new Vec2d(this.x % m, this.y % m);
	}

	@Override
	public Vec2d clamp(IVector2<? extends Number> min, IVector2<? extends Number> max) {
		return new Vec2d(
				Math.max(min.x().doubleValue(), Math.min(this.x, max.x().doubleValue())),
				Math.max(min.y().doubleValue(), Math.min(this.y, max.y().doubleValue()))
			);
	}
	
	@Override
	public Vec2d clamp(Double min, Double max) {
		return new Vec2d(
				Math.max((Double) min, Math.min(this.x, (Double) max)),
				Math.max((Double) min, Math.min(this.y, (Double) max))
			);
	}
	
	@Override
	public boolean isFinite() {
		return Double.isFinite(x) && Double.isFinite(y);
	}
	
	@Override
	public double angle(IVector2<? extends Number> vec) {
		return Math.atan2(this.y, this.x)-Math.atan2(vec.y().doubleValue(), vec.x().doubleValue());
	}
	
	@Override
	public Double cross(IVector2<? extends Number> vec) {
		return this.x * vec.y().doubleValue() - this.y * vec.x().doubleValue();	
	}
	
	@Override
	public Double dot(IVector2<? extends Number> vec) {
		return this.x * vec.x().doubleValue() + this.y * vec.y().doubleValue();
	}
	
	@Override
	public Double length() {
		return (Double) Math.sqrt(this.lengthSqrt());
	}

	@Override
	public Double lengthSqrt() {
		return this.x * this.x + this.y * this.y;
	}
	
	@Override
	public Vec2d normalize() {
		double f = this.length();
		return this.div(f);
	}
	
	@Override
	public Vec2d lerp(IVector2<? extends Number> vec, Double delta) {
		double f = 1.0F - delta;
		return new Vec2d(
				this.x * f + vec.x().doubleValue() * delta,
				this.y * f + vec.y().doubleValue() * delta
			);
	}
	
	@Override
	public Double distSqr(IVector2<? extends Number> vec) {
		Double d1 = this.getX().doubleValue() - vec.getX().doubleValue();
		Double d2 = this.getY().doubleValue() - vec.getY().doubleValue();
		return d1 * d1 + d2 * d2;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vec2d) {
			return	((Vec2d) obj).x == x && 
					((Vec2d) obj).y == y;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		result = result * 31 + Double.hashCode(this.x);
		result = result * 31 + Double.hashCode(this.y);
		return result;
	}
	
	@Override
	public String toString() {
		return "Vec2d[" + this.x + "," + this.y + "]";
	}

	@Override
	public Class<? extends Number> getTypeClass() {
		return Double.class;
	}
	
	@Override
	public Vec2d anyOrthogonal() {
		return new Vec2d(-y, x);
	}
	
	@Override
	public Vec2d[] orthogonals() {
		return new Vec2d[] {new Vec2d(-y, x), new Vec2d(y, -x)};
	}
	
}
