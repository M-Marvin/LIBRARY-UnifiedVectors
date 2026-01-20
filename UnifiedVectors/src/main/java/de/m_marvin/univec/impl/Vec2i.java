package de.m_marvin.univec.impl;

import de.m_marvin.univec.VectorParser;
import de.m_marvin.univec.api.IVector2;
import de.m_marvin.univec.api.IVector2Math;

/*
 * Implementation of a 2 dimensional integer vector
 */
public class Vec2i implements IVector2Math<Integer, Vec2i, Vec2i> {
	
	public int x;
	public int y;
	
	public Vec2i(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Vec2i() {
		this.x = 0;
		this.y = 0;
	}

	public Vec2i(IVector2<? extends Number> vec) {
		this.x = vec.x().intValue();
		this.y = vec.y().intValue();
	}

	@Override
	public Class<? extends Number> getTypeClass() {
		return Integer.class;
	}

	public static Vec2i fromVec(Object vectorObject) {
		return new Vec2i(0, 0).readFrom(vectorObject);
	}
	
	@Override
	public <T> Vec2i readFrom(T vectorObject) {
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
	public Integer x() {
		return x;
	}

	@Override
	public Integer y() {
		return y;
	}

	@Override
	public void setX(Integer x) {
		this.x = x;
	}

	@Override
	public void setY(Integer y) {
		this.y = y;
	}

	@Override
	public Vec2i setI(Integer x, Integer y) {
		this.x = x;
		this.y = y;
		return this;
	}

	@Override
	public Vec2i setI(IVector2<? extends Number> vec) {
		this.x = vec.x().intValue();
		this.y = vec.y().intValue();
		return this;
	}

	@Override
	public Vec2i copy() {
		return new Vec2i(this.x, this.y);
	}

	@Override
	public Vec2i add(IVector2<? extends Number> vec) {
		return new Vec2i(this.x + vec.x().intValue(), this.y + vec.y().intValue());
	}

	@Override
	public Vec2i add(Integer x, Integer y) {
		return new Vec2i(this.x + x, this.y + y);
	}

	@Override
	public Vec2i sub(IVector2<? extends Number> vec) {
		return new Vec2i(this.x - vec.x().intValue(), this.y - vec.y().intValue());
	}

	@Override
	public Vec2i sub(Integer x, Integer y) {
		return new Vec2i(this.x - x, this.y - y);
	}

	@Override
	public Vec2i mul(IVector2<? extends Number> vec) {
		return new Vec2i(this.x * vec.x().intValue(), this.y * vec.y().intValue());
	}

	@Override
	public Vec2i mul(Integer x, Integer y) {
		return new Vec2i(this.x * x, this.y * y);
	}

	@Override
	public Vec2i mul(Integer n) {
		return new Vec2i(this.x * n, this.y * n);
	}
	
	@Override
	public Vec2i div(IVector2<? extends Number> vec) {
		return new Vec2i(this.x / vec.x().intValue(), this.y / vec.y().intValue());
	}

	@Override
	public Vec2i div(Integer x, Integer y) {
		return new Vec2i(this.x / x, this.y / y);
	}
	
	@Override
	public Vec2i div(Integer n) {
		return new Vec2i(this.x / n, this.y / n);
	}

	@Override
	public Vec2i module(Integer m) {
		return new Vec2i(this.x % m, this.y % m);
	}

	@Override
	public Vec2i min(Integer value) {
		return new Vec2i(Math.min(this.x, value), Math.min(this.y, value));
	}
	
	@Override
	public Vec2i min(IVector2<? extends Number> vec) {
		return new Vec2i(Math.min(this.x,  vec.x().intValue()), Math.min(this.y,  vec.y().intValue()));
	}
	
	@Override
	public Vec2i max(Integer value) {
		return new Vec2i(Math.max(this.x, value), Math.max(this.y, value));
	}
	
	@Override
	public Vec2i max(IVector2<? extends Number> vec) {
		return new Vec2i(Math.max(this.x,  vec.x().intValue()), Math.max(this.y,  vec.y().intValue()));
	}
	
	@Override
	public Vec2i clamp(IVector2<? extends Number> min, IVector2<? extends Number> max) {
		return new Vec2i(
				Math.max(min.x().intValue(), Math.min(this.x,  max.x().intValue())),
				Math.max(min.y().intValue(), Math.min(this.y,  max.y().intValue()))
			);
	}
	
	@Override
	public Vec2i clamp(Integer min, Integer max) {
		return new Vec2i(
				Math.max((Integer) min, Math.min(this.x, (Integer) max)),
				Math.max((Integer) min, Math.min(this.y, (Integer) max))
			);
	}

	@Override
	public Vec2i abs() {
		return new Vec2i(Math.abs(this.x), Math.abs(this.y));
	}

	@Override
	public Integer sum() {
		return this.x + this.y;
	}
	
	@Override
	public Vec2i sign() {
		return new Vec2i(
				this.x > 0 ? 1 : this.x < 0 ? -1 : 0,
				this.y > 0 ? 1 : this.y < 0 ? -1 : 0
		);
	}

	@Override
	public Vec2i floor() {
		return copy();
	}

	@Override
	public Vec2i ceil() {
		return copy();
	}

	@Override
	public Vec2i round() {
		return copy();
	}
	
	@Override
	public boolean isFinite() {
		return true;
	}
	
	@Override
	public double angle(IVector2<? extends Number> vec) {
		return Math.atan2(this.y, this.x)-Math.atan2(vec.y().intValue(), vec.x().intValue());
	}
	
	@Override
	public Integer cross(IVector2<? extends Number> vec) {
		return this.x * vec.y().intValue() - this.y * vec.x().intValue();	
	}
	
	@Override
	public Integer dot(IVector2<? extends Number> vec) {
		return this.x * vec.x().intValue() + this.y * vec.y().intValue();
	}
	
	@Override
	public Integer length() {
		return (int) Math.sqrt(this.lengthSqr());
	}

	@Override
	public Integer lengthSqr() {
		return this.x * this.x + this.y * this.y;
	}
	
	@Override
	public Vec2i normalize() {
		int f = this.length();
		if (f == 0) throw new ArithmeticException("division trough zero, cant normalize vector of length 0");
		return this.div(f);
	}

	@Override
	public Vec2i tryNormalize() {
		int f = this.length();
		if (f == 0) return new Vec2i(0, 0);
		return this.div(f);
	}
	
	@Override
	public Vec2i lerp(IVector2<? extends Number> vec, Integer delta) {
		float f = 1.0F - delta;
		return new Vec2i(
				(int) (this.x * f + vec.x().intValue() * delta),
				(int) (this.y * f + vec.y().intValue() * delta)
			);
	}

	@Override
	public Integer distSqr(IVector2<? extends Number> vec) {
		Integer d1 = this.getX().intValue() - vec.getX().intValue();
		Integer d2 = this.getY().intValue() - vec.getY().intValue();
		return d1 * d1 + d2 * d2;
	}
	
	@Override
	public Integer dist(IVector2<? extends Number> vec) {
		 return (int) Math.sqrt(this.distSqr(vec).intValue());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vec2i) {
			return	((Vec2i) obj).x == x && 
					((Vec2i) obj).y == y;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		result = result * 31 + Integer.hashCode(this.x);
		result = result * 31 + Integer.hashCode(this.y);
		return result;
	}

	@Override
	public Vec2i anyOrthogonal() {
		return new Vec2i(-y, x);
	}
	
	@Override
	public Vec2i[] orthogonals() {
		return new Vec2i[] {new Vec2i(-y, x), new Vec2i(y, -x)};
	}
	
	@Override
	public String toString() {
		return String.format("[%d  %d]", this.x, this.y);
	}

}
