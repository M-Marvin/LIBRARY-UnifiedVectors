package de.m_marvin.univec.impl;

import de.m_marvin.univec.VectorParser;
import de.m_marvin.univec.api.IVector2;
import de.m_marvin.univec.api.IVector2Math;

/*
 * Implementation of a 2 dimensional float vector
 */
public class Vec2f implements IVector2Math<Float, Vec2f> {
	
	public float x;
	public float y;
	
	public Vec2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vec2f() {
		this.x = 0;
		this.y = 0;
	}

	public Vec2f(IVector2<? extends Number> vec) {
		this.x = vec.x().floatValue();
		this.y = vec.y().floatValue();
	}

	@Override
	public Class<? extends Number> getTypeClass() {
		return Float.class;
	}

	public static Vec2f fromVec(Object vectorObject) {
		return new Vec2f(0, 0).readFrom(vectorObject);
	}
	
	@Override
	public <T> Vec2f readFrom(T vectorObject) {
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
	public Vec2f reset() {
		this.x = this.y = 0;
		return this;
	}
	
	@Override
	public Vec2f copy() {
		return new Vec2f(this.x, this.y);
	}

	@Override
	public Vec2f addI(IVector2<? extends Number> vec) {
		this.x += vec.x().doubleValue();
		this.y += vec.y().doubleValue();
		return this;
	}

	@Override
	public Vec2f addI(Float x, Float y) {
		this.x += x;
		this.y += y;
		return this;
	}

	@Override
	public Vec2f subI(IVector2<? extends Number> vec) {
		this.x -= vec.x().doubleValue();
		this.y -= vec.y().doubleValue();
		return this;
	}

	@Override
	public Vec2f subI(Float x, Float y) {
		this.x -= x;
		this.y -= y;
		return this;
	}

	@Override
	public Vec2f mulI(IVector2<? extends Number> vec) {
		this.x *= vec.x().doubleValue();
		this.y *= vec.y().doubleValue();
		return this;
	}

	@Override
	public Vec2f mulI(Float x, Float y) {
		this.x *= x;
		this.y *= y;
		return this;
	}

	@Override
	public Vec2f mulI(Float n) {
		this.x *= n;
		this.y *= n;
		return this;
	}
	
	@Override
	public Vec2f divI(IVector2<? extends Number> vec) {
		this.x /= vec.x().doubleValue();
		this.y /= vec.y().doubleValue();
		return this;
	}

	@Override
	public Vec2f divI(Float x, Float y) {
		this.x /= x;
		this.y /= y;
		return this;
	}
	
	@Override
	public Vec2f divI(Float n) {
		this.x /= n;
		this.y /= n;
		return this;
	}

	@Override
	public Vec2f moduleI(Float m) {
		this.x %= m;
		this.y %= m;
		return this;
	}

	@Override
	public Vec2f clampI(IVector2<? extends Number> min, IVector2<? extends Number> max) {
		this.x = (float) Math.max(min.x().doubleValue(), Math.min(this.x, max.x().doubleValue()));
		this.y = (float) Math.max(min.y().doubleValue(), Math.min(this.y, max.y().doubleValue()));
		return this;
	}

	@Override
	public Vec2f minI(Float value) {
		this.x = (float) Math.min(this.x, value);
		this.y = (float) Math.min(this.y, value);
		return this;
	}
	
	@Override
	public Vec2f minI(IVector2<? extends Number> vec) {
		this.x = (float) Math.min(this.x,  vec.x().doubleValue());
		this.y = (float) Math.min(this.y,  vec.y().doubleValue());
		return this;
	}
	
	@Override
	public Vec2f maxI(Float value) {
		this.x = (float) Math.max(this.x, value);
		this.y = (float) Math.max(this.y, value);
		return this;
	}
	
	@Override
	public Vec2f maxI(IVector2<? extends Number> vec) {
		this.x = (float) Math.max(this.x,  vec.x().doubleValue());
		this.y = (float) Math.max(this.y,  vec.y().doubleValue());
		return this;
	}
	
	@Override
	public Vec2f clampI(Float min, Float max) {
		this.x = (float) Math.max((Float) min, Math.min(this.x, (Float) max));
		this.y = (float) Math.max((Float) min, Math.min(this.y, (Float) max));
		return this;
	}

	@Override
	public Vec2f absI() {
		this.x = Math.abs(this.x);
		this.y = Math.abs(this.y);
		return this;
	}

	@Override
	public Vec2f negateI() {
		this.x = -this.x;
		this.y = -this.y;
		return this;
	}
	
	@Override
	public Float sum() {
		return this.x + this.y;
	}
	
	@Override
	public Vec2f signI() {
		this.x = this.x > 0 ? 1 : this.x < 0 ? -1 : 0;
		this.y = this.y > 0 ? 1 : this.y < 0 ? -1 : 0;
		return this;
	}

	@Override
	public Vec2f floorI() {
		this.x = (float) Math.floor(this.x);
		this.y = (float) Math.floor(this.y);
		return this;
	}

	@Override
	public Vec2f ceilI() {
		this.x = (float) Math.ceil(this.x);
		this.y = (float) Math.ceil(this.y);
		return this;
	}

	@Override
	public Vec2f roundI() {
		this.x = Math.round(this.x);
		this.y = Math.round(this.y);
		return this;
	}
	
	@Override
	public boolean isFinite() {
		return Float.isFinite(x) && Float.isFinite(y);
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
		return (float) Math.sqrt(this.lengthSqr());
	}

	@Override
	public Float lengthSqr() {
		return this.x * this.x + this.y * this.y;
	}

	@Override
	public Vec2f normalizeI() {
		float f = this.length();
		if (f == 0) throw new ArithmeticException("division trough zero, cant normalize vector of length 0");
		return this.divI(f);
	}

	@Override
	public Vec2f tryNormalizeI() {
		float f = this.length();
		if (f == 0) return this.setI(0F, 0F);
		return this.divI(f);
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
	public Float dist(IVector2<? extends Number> vec) {
		 return (float) Math.sqrt(this.distSqr(vec).floatValue());
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
	public Vec2f anyOrthogonal() {
		return new Vec2f(-y, x);
	}
	
	@Override
	public Vec2f[] orthogonals() {
		return new Vec2f[] {new Vec2f(-y, x), new Vec2f(y, -x)};
	}
	
	@Override
	public String toString() {
		return String.format("[%f  %f]", this.x, this.y);
	}

}
