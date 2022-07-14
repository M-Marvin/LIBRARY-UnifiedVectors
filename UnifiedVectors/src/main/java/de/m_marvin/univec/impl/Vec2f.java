package de.m_marvin.univec.impl;

import de.m_marvin.univec.api.IVector2;
import de.m_marvin.univec.api.IVector2Math;

/*
 * Implementation of a 2 dimensional float vector
 */
public class Vec2f implements IVector2Math<Float, Vec2f, IVector2<? extends Number>> {
	
	public float x;
	public float y;
	
	public Vec2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vec2f(IVector2<? extends Number> vec) {
		this.x = (float) vec.x();
		this.y = (float) vec.y();
	}
	
	@Override
	public Float x() {
		return x;
	}

	@Override
	public Float y() {
		return y;
	}

	@Override
	public void setX(Float x) {
		this.x = x;
	}

	@Override
	public void setY(Float y) {
		this.y = y;
	}

	@Override
	public Vec2f setI(Float x, Float y) {
		this.x = x;
		this.y = y;
		return this;
	}

	@Override
	public Vec2f setI(IVector2<? extends Number> vec) {
		this.x = (float) vec.x();
		this.y = (float) vec.y();
		return this;
	}

	@Override
	public Vec2f copy() {
		return new Vec2f(this.x, this.y);
	}

	@Override
	public Vec2f add(IVector2<? extends Number> vec) {
		return new Vec2f(this.x + (float) vec.x(), this.y + (float) vec.y());
	}

	@Override
	public Vec2f add(Float x, Float y) {
		return new Vec2f(this.x + x, this.y + y);
	}

	@Override
	public Vec2f sub(IVector2<? extends Number> vec) {
		return new Vec2f(this.x - (float) vec.x(), this.y - (float) vec.y());
	}

	@Override
	public Vec2f sub(Float x, Float y) {
		return new Vec2f(this.x - x, this.y - y);
	}

	@Override
	public Vec2f mul(IVector2<? extends Number> vec) {
		return new Vec2f(this.x * (float) vec.x(), this.y * (float) vec.y());
	}

	@Override
	public Vec2f mul(Float x, Float y) {
		return new Vec2f(this.x * x, this.y * y);
	}

	@Override
	public Vec2f mul(Float n) {
		return new Vec2f(this.x * n, this.y * n);
	}
	
	@Override
	public Vec2f div(IVector2<? extends Number> vec) {
		return new Vec2f(this.x / (float) vec.x(), this.y / (float) vec.y());
	}

	@Override
	public Vec2f div(Float x, Float y) {
		return new Vec2f(this.x / x, this.y / y);
	}
	
	@Override
	public Vec2f div(Float n) {
		return new Vec2f(this.x / n, this.y / n);
	}

	@Override
	public Vec2f module(Float m) {
		return new Vec2f(this.x % m, this.y % m);
	}

	@Override
	public Vec2f clamp(IVector2<? extends Number> min, IVector2<? extends Number> max) {
		return new Vec2f(
				Math.max((float) min.x(), Math.min(this.x, (float) max.x())),
				Math.max((float) min.y(), Math.min(this.y, (float) max.y()))
			);
	}
	
	@Override
	public Vec2f clamp(Float min, Float max) {
		return new Vec2f(
				Math.max((float) min, Math.min(this.x, (float) max)),
				Math.max((float) min, Math.min(this.y, (float) max))
			);
	}

	@Override
	public double angle(IVector2<? extends Number> vec) {
		return Math.atan2(this.y, this.x)-Math.atan2((float) vec.y(), (float) vec.x());
	}
	
	@Override
	public Float cross(IVector2<? extends Number> vec) {
		return this.x * (float) vec.y() - this.y * (float) vec.x();	
	}
	
	@Override
	public Float dot(IVector2<? extends Number> vec) {
		return this.x * (float) vec.x() + this.y * (float) vec.y();
	}
	
	@Override
	public Float length() {
		return (float) Math.sqrt(this.lengthSqrt());
	}

	@Override
	public Float lengthSqrt() {
		return this.x * this.x + this.y * this.y;
	}
	
	@Override
	public Vec2f normalize() {
		float f = this.length();
		return this.div(f);
	}
	
	@Override
	public Vec2f lerp(IVector2<? extends Number> vec, Float delta) {
		float f = 1.0F - delta;
		return new Vec2f(
				this.x * f + (float) vec.x() * delta,
				this.y * f + (float) vec.y() * delta
			);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vec2f) {
			return	((Vec2f) obj).x == x && 
					((Vec2f) obj).y == y;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		result = result * 31 + Float.hashCode(this.x);
		result = result * 31 + Float.hashCode(this.y);
		return result;
	}
	
	@Override
	public String toString() {
		return "Vec2f[" + this.x + "," + this.y + "]";
	}
	
}
