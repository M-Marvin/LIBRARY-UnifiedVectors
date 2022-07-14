package de.m_marvin.univec.impl;

import de.m_marvin.univec.api.IVector2;
import de.m_marvin.univec.api.IVector2Math;

/*
 * Implementation of a 2 dimensional integer vector
 */
public class Vec2i implements IVector2Math<Integer, Vec2i, IVector2<? extends Number>> {
	
	public Integer x;
	public Integer y;
	
	public Vec2i(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}

	public Vec2i(IVector2<? extends Number> vec) {
		this.x = (int) vec.x();
		this.y = (int) vec.y();
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
		this.x = (Integer) vec.x();
		this.y = (Integer) vec.y();
		return this;
	}

	@Override
	public Vec2i copy() {
		return new Vec2i(this.x, this.y);
	}

	@Override
	public Vec2i add(IVector2<? extends Number> vec) {
		return new Vec2i(this.x + (Integer) vec.x(), this.y + (Integer) vec.y());
	}

	@Override
	public Vec2i add(Integer x, Integer y) {
		return new Vec2i(this.x + x, this.y + y);
	}

	@Override
	public Vec2i sub(IVector2<? extends Number> vec) {
		return new Vec2i(this.x - (Integer) vec.x(), this.y - (Integer) vec.y());
	}

	@Override
	public Vec2i sub(Integer x, Integer y) {
		return new Vec2i(this.x - x, this.y - y);
	}

	@Override
	public Vec2i mul(IVector2<? extends Number> vec) {
		return new Vec2i(this.x * (Integer) vec.x(), this.y * (Integer) vec.y());
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
		return new Vec2i(this.x / (Integer) vec.x(), this.y / (Integer) vec.y());
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
	public Vec2i clamp(IVector2<? extends Number> min, IVector2<? extends Number> max) {
		return new Vec2i(
				Math.max((Integer) min.x(), Math.min(this.x, (Integer) max.x())),
				Math.max((Integer) min.y(), Math.min(this.y, (Integer) max.y()))
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
	public double angle(IVector2<? extends Number> vec) {
		return Math.atan2(this.y, this.x)-Math.atan2((Integer) vec.y(), (Integer) vec.x());
	}
	
	@Override
	public Integer cross(IVector2<? extends Number> vec) {
		return this.x * (Integer) vec.y() - this.y * (Integer) vec.x();	
	}
	
	@Override
	public Integer dot(IVector2<? extends Number> vec) {
		return this.x * (Integer) vec.x() + this.y * (Integer) vec.y();
	}
	
	@Override
	public Integer length() {
		return (int) Math.sqrt(this.lengthSqrt());
	}

	@Override
	public Integer lengthSqrt() {
		return this.x * this.x + this.y * this.y;
	}
	
	@Override
	public Vec2i normalize() {
		Integer f = this.length();
		return this.div(f);
	}
	
	@Override
	public Vec2i lerp(IVector2<? extends Number> vec, Integer delta) {
		float f = 1.0F - delta;
		return new Vec2i(
				(int) (this.x * f + (Integer) vec.x() * delta),
				(int) (this.y * f + (Integer) vec.y() * delta)
			);
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
		result = result * 31 + Integer.hashCode(this.x); // Could be just 31 + x/y
		result = result * 31 + Integer.hashCode(this.y);
		return result;
	}
	
	@Override
	public String toString() {
		return "Vec2i[" + this.x + "," + this.y + "]";
	}
	
}
