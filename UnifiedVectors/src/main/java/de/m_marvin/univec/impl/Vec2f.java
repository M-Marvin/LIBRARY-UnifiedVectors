package de.m_marvin.univec.impl;

import de.m_marvin.univec.MathHelper;
import de.m_marvin.univec.VectorParser;
import de.m_marvin.univec.api.IVector2;
import de.m_marvin.univec.api.IVector2Math;
import org.joml.Vector2f;

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

	public Vec2f(Vector2f vec) {
		this.x = vec.x;
		this.y = vec.y;
	}

	public Vector2f conv() {
		return new Vector2f(x,y);
	}

	public Vec2f() {
		this.x = 0;
		this.y = 0;
	}

	public Vec2f(IVector2<? extends Number> vec) {
		this.x = vec.x().floatValue();
		this.y = vec.y().floatValue();
	}

	public static Vec2f fromVec(Object vectorObject) {
		return new Vec2f(0, 0).readFrom(vectorObject);
	}

	public <T> Vec2f(T vectorObject) {
		readFrom(vectorObject);
	}

	public Vec2f add(Vector2f vec) {
		return add(new Vec2f(vec));
	}

	public Vec2f sub(Vector2f vec) {
		return sub(new Vec2f(vec));
	}

	public Vec2f mul(Vector2f vec) {
		return mul(new Vec2f(vec));
	}

	@Override
	public <T> Vec2f readFrom(T vectorObject) {
		try {
			Vec2f v = (Vec2f) VectorParser.parseVectorObject(vectorObject, new Vec2f(0, 0));
			this.x = v.x;
			this.y = v.y;
		} catch (IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
			this.x = 0;
			this.y = 0;
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

	public boolean isFinite() {
		return MathHelper.isFinite(x) && MathHelper.isFinite(y);
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
		this.x = vec.x().floatValue();
		this.y = vec.y().floatValue();
		return this;
	}

	@Override
	public Vec2f copy() {
		return new Vec2f(this.x, this.y);
	}

	@Override
	public Vec2f add(IVector2<? extends Number> vec) {
		return new Vec2f(this.x + vec.x().floatValue(), this.y + vec.y().floatValue());
	}

	@Override
	public Vec2f add(Float x, Float y) {
		return new Vec2f(this.x + x, this.y + y);
	}

	@Override
	public Vec2f sub(IVector2<? extends Number> vec) {
		return new Vec2f(this.x - vec.x().floatValue(), this.y - vec.y().floatValue());
	}

	@Override
	public Vec2f sub(Float x, Float y) {
		return new Vec2f(this.x - x, this.y - y);
	}

	@Override
	public Vec2f mul(IVector2<? extends Number> vec) {
		return new Vec2f(this.x * vec.x().floatValue(), this.y * vec.y().floatValue());
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
		return new Vec2f(this.x / vec.x().floatValue(), this.y / vec.y().floatValue());
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
				Math.max(min.x().floatValue(), Math.min(this.x, max.x().floatValue())),
				Math.max(min.y().floatValue(), Math.min(this.y, max.y().floatValue()))
			);
	}
	
	@Override
	public Vec2f clamp(Float min, Float max) {
		return new Vec2f(
				Math.max((Float) min, Math.min(this.x, (Float) max)),
				Math.max((Float) min, Math.min(this.y, (Float) max))
			);
	}

	@Override
	public double angle(IVector2<? extends Number> vec) {
		return Math.atan2(this.y, this.x)-Math.atan2(vec.y().floatValue(), vec.x().floatValue());
	}
	
	@Override
	public Float cross(IVector2<? extends Number> vec) {
		return this.x * vec.y().floatValue() - this.y * vec.x().floatValue();	
	}
	
	@Override
	public Float dot(IVector2<? extends Number> vec) {
		return this.x * vec.x().floatValue() + this.y * vec.y().floatValue();
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
				this.x * f + vec.x().floatValue() * delta,
				this.y * f + vec.y().floatValue() * delta
			);
	}

	@Override
	public Float distSqr(IVector2<? extends Number> vec) {
		Float d1 = this.getX().floatValue() - vec.getX().floatValue();
		Float d2 = this.getY().floatValue() - vec.getY().floatValue();
		return d1 * d1 + d2 * d2;
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

	@Override
	public Class<? extends Number> getTypeClass() {
		return Float.class;
	}
	
}
