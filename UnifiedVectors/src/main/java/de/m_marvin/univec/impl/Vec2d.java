package de.m_marvin.univec.impl;

import de.m_marvin.univec.VectorParser;
import de.m_marvin.univec.api.IVector2;
import de.m_marvin.univec.api.IVector2Math;

/*
 * Implementation of a 3 dimensional double vector
 */
public class Vec2d implements IVector2Math<Double, Vec2d> {
	
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

	@Override
	public Class<? extends Number> getTypeClass() {
		return Double.class;
	}
	
	public static Vec2d fromVec(Object vectorObject) {
		return new Vec2d(0, 0).readFrom(vectorObject);
	}
	
	@Override
	public <T> Vec2d readFrom(T vectorObject) {
		try {
			VectorParser.parseFromVectorObject(vectorObject, this);
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
	public Vec2d reset() {
		this.x = this.y = 0;
		return this;
	}
	
	@Override
	public Vec2d copy() {
		return new Vec2d(this.x, this.y);
	}

	@Override
	public Vec2d addI(IVector2<? extends Number> vec) {
		this.x += vec.x().doubleValue();
		this.y += vec.y().doubleValue();
		return this;
	}

	@Override
	public Vec2d addI(Double x, Double y) {
		this.x += x;
		this.y += y;
		return this;
	}

	@Override
	public Vec2d subI(IVector2<? extends Number> vec) {
		this.x -= vec.x().doubleValue();
		this.y -= vec.y().doubleValue();
		return this;
	}

	@Override
	public Vec2d subI(Double x, Double y) {
		this.x -= x;
		this.y -= y;
		return this;
	}

	@Override
	public Vec2d mulI(IVector2<? extends Number> vec) {
		this.x *= vec.x().doubleValue();
		this.y *= vec.y().doubleValue();
		return this;
	}

	@Override
	public Vec2d mulI(Double x, Double y) {
		this.x *= x;
		this.y *= y;
		return this;
	}

	@Override
	public Vec2d mulI(Double n) {
		this.x *= n;
		this.y *= n;
		return this;
	}
	
	@Override
	public Vec2d divI(IVector2<? extends Number> vec) {
		this.x /= vec.x().doubleValue();
		this.y /= vec.y().doubleValue();
		return this;
	}

	@Override
	public Vec2d divI(Double x, Double y) {
		this.x /= x;
		this.y /= y;
		return this;
	}
	
	@Override
	public Vec2d divI(Double n) {
		this.x /= n;
		this.y /= n;
		return this;
	}

	@Override
	public Vec2d moduleI(Double m) {
		this.x %= m;
		this.y %= m;
		return this;
	}

	@Override
	public Vec2d clampI(IVector2<? extends Number> min, IVector2<? extends Number> max) {
		this.x = Math.max(min.x().doubleValue(), Math.min(this.x, max.x().doubleValue()));
		this.y = Math.max(min.y().doubleValue(), Math.min(this.y, max.y().doubleValue()));
		return this;
	}

	@Override
	public Vec2d minI(Double value) {
		this.x = Math.min(this.x, value);
		this.y = Math.min(this.y, value);
		return this;
	}
	
	@Override
	public Vec2d minI(IVector2<? extends Number> vec) {
		this.x = Math.min(this.x,  vec.x().doubleValue());
		this.y = Math.min(this.y,  vec.y().doubleValue());
		return this;
	}
	
	@Override
	public Vec2d maxI(Double value) {
		this.x = Math.max(this.x, value);
		this.y = Math.max(this.y, value);
		return this;
	}
	
	@Override
	public Vec2d maxI(IVector2<? extends Number> vec) {
		this.x = Math.max(this.x,  vec.x().doubleValue());
		this.y = Math.max(this.y,  vec.y().doubleValue());
		return this;
	}
	
	@Override
	public Vec2d clampI(Double min, Double max) {
		this.x = Math.max((Double) min, Math.min(this.x, (Double) max));
		this.y = Math.max((Double) min, Math.min(this.y, (Double) max));
		return this;
	}

	@Override
	public Vec2d absI() {
		this.x = Math.abs(this.x);
		this.y = Math.abs(this.y);
		return this;
	}

	@Override
	public Vec2d negateI() {
		this.x = -this.x;
		this.y = -this.y;
		return this;
	}
	
	@Override
	public Double sum() {
		return this.x + this.y;
	}
	
	@Override
	public Vec2d signI() {
		this.x = this.x > 0 ? 1 : this.x < 0 ? -1 : 0;
		this.y = this.y > 0 ? 1 : this.y < 0 ? -1 : 0;
		return this;
	}

	@Override
	public Vec2d floorI() {
		this.x = Math.floor(this.x);
		this.y = Math.floor(this.y);
		return this;
	}

	@Override
	public Vec2d ceilI() {
		this.x = Math.ceil(this.x);
		this.y = Math.ceil(this.y);
		return this;
	}

	@Override
	public Vec2d roundI() {
		this.x = Math.round(this.x);
		this.y = Math.round(this.y);
		return this;
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
		return (Double) Math.sqrt(this.lengthSqr());
	}

	@Override
	public Double lengthSqr() {
		return this.x * this.x + this.y * this.y;
	}

	@Override
	public Vec2d normalizeI() {
		double f = this.length();
		if (f == 0) throw new ArithmeticException("division trough zero, cant normalize vector of length 0");
		return this.divI(f);
	}

	@Override
	public Vec2d tryNormalizeI() {
		double f = this.length();
		if (f == 0) return this.setI(0D, 0D);
		return this.divI(f);
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
	public Double dist(IVector2<? extends Number> vec) {
		 return (double) Math.sqrt(this.distSqr(vec).doubleValue());
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
	public Vec2d anyOrthogonal() {
		return new Vec2d(-y, x);
	}
	
	@Override
	public Vec2d[] orthogonals() {
		return new Vec2d[] {new Vec2d(-y, x), new Vec2d(y, -x)};
	}
	
	@Override
	public String toString() {
		return String.format("[%f  %f]", this.x, this.y);
	}
	
}
