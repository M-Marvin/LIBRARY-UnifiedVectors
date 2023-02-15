package de.m_marvin.univec.impl;

import de.m_marvin.univec.VectorParser;
import de.m_marvin.univec.api.IVector4;
import de.m_marvin.univec.api.IVector4Math;

public class Vec4f implements IVector4Math<Float, Vec4f, IVector4<? extends Number>> {
	
	public float x;
	public float y;
	public float z;
	public float w;
	
	public Vec4f(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public Vec4f() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.w = 0;
	}

	public Vec4f(IVector4<? extends Number> vec) {
		this.x = vec.x().floatValue();
		this.y = vec.y().floatValue();
		this.z = vec.z().floatValue();
		this.w = vec.w().floatValue();
	}

	public static Vec4f fromVec(Object vectorObject) {
		return new Vec4f(0, 0, 0, 0).readFrom(vectorObject);
	}
	
	@Override
	public <T> Vec4f readFrom(T vectorObject) {
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
	public Float x() {
		return x;
	}

	@Override
	public Float y() {
		return y;
	}

	@Override
	public Float z() {
		return z;
	}

	@Override
	public Float w() {
		return w;
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
	public void setZ(Float z) {
		this.z = z;
	}

	@Override
	public void setW(Float w) {
		this.w = w;
	}

	@Override
	public Vec4f setI(Float x, Float y, Float z, Float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		return this;
	}

	@Override
	public Vec4f setI(IVector4<? extends Number> vec) {
		this.x = vec.x().floatValue();
		this.y = vec.y().floatValue();
		this.z = vec.z().floatValue();
		this.w = vec.w().floatValue();
		return this;
	}

	@Override
	public Vec4f copy() {
		return new Vec4f(this.x, this.y, this.z, this.w);
	}

	@Override
	public Vec4f add(IVector4<? extends Number> vec) {
		return new Vec4f(this.x + vec.x().floatValue(), this.y + vec.y().floatValue(), this.z + vec.z().floatValue(), this.w + vec.w().floatValue());
	}

	@Override
	public Vec4f add(Float x, Float y, Float z, Float w) {
		return new Vec4f(this.x + x, this.y + y, this.z + z, this.w + w);
	}

	@Override
	public Vec4f sub(IVector4<? extends Number> vec) {
		return new Vec4f(this.x - vec.x().floatValue(), this.y - vec.y().floatValue(), this.z - vec.z().floatValue(), this.w - vec.w().floatValue());
	}

	@Override
	public Vec4f sub(Float x, Float y, Float z, Float w) {
		return new Vec4f(this.x - x, this.y - y, this.z - z, this.w - w);
	}

	@Override
	public Vec4f mul(IVector4<? extends Number> vec) {
		return new Vec4f(this.x * vec.x().floatValue(), this.y * vec.y().floatValue(), this.z * vec.z().floatValue(), this.w * vec.w().floatValue());
	}

	@Override
	public Vec4f mul(Float x, Float y, Float z, Float w) {
		return new Vec4f(this.x * x, this.y * y, this.z * z, this.w * w);
	}

	@Override
	public Vec4f mul(Float n) {
		return new Vec4f(this.x * n, this.y * n, this.z * n, this.w * n);
	}
	
	@Override
	public Vec4f div(IVector4<? extends Number> vec) {
		return new Vec4f(this.x / vec.x().floatValue(), this.y / vec.y().floatValue(), this.z / vec.z().floatValue(), this.w / vec.w().floatValue());
	}

	@Override
	public Vec4f div(Float x, Float y, Float z, Float w) {
		return new Vec4f(this.x / x, this.y / y, this.z / z, this.w / w);
	}
	
	@Override
	public Vec4f div(Float n) {
		return new Vec4f(this.x / n, this.y / n, this.z / n, this.w / n);
	}

	@Override
	public Vec4f module(Float m) {
		return new Vec4f(this.x % m, this.y % m, this.z % m, this.w % m);
	}

	@Override
	public Vec4f clamp(IVector4<? extends Number> min, IVector4<? extends Number> max) {
		return new Vec4f(
				Math.max(min.x().floatValue(), Math.min(this.x, max.x().floatValue())),
				Math.max(min.y().floatValue(), Math.min(this.y, max.y().floatValue())),
				Math.max(min.z().floatValue(), Math.min(this.z, max.z().floatValue())),
				Math.max(min.w().floatValue(), Math.min(this.w, max.w().floatValue()))
			);
	}
	
	@Override
	public Vec4f clamp(Float min, Float max) {
		return new Vec4f(
				Math.max((Float) min, Math.min(this.x, (Float) max)),
				Math.max((Float) min, Math.min(this.y, (Float) max)),
				Math.max((Float) min, Math.min(this.z, (Float) max)),
				Math.max((Float) min, Math.min(this.w, (Float) max))
			);
	}

	@Override
	public boolean isFinite() {
		return Float.isFinite(x) && Float.isFinite(y) && Float.isFinite(z) && Float.isFinite(w);
	}
	
	@Override
	public Float dot(IVector4<? extends Number> vec) {
		return this.x * vec.x().floatValue() + this.y * vec.y().floatValue() + this.z * vec.z().floatValue() + this.w * vec.w().floatValue();
	}
	
	@Override
	public Float length() {
		return (float) Math.sqrt(this.lengthSqrt());
	}

	@Override
	public Float lengthSqrt() {
		return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
	}
	
	@Override
	public Vec4f normalize() {
		float f = this.length();
		return this.div(f);
	}
	
	@Override
	public Vec4f lerp(IVector4<? extends Number> vec, Float delta) {
		float f = 1.0F - delta;
		return new Vec4f(
				this.x * f + vec.x().floatValue() * delta,
				this.y * f + vec.y().floatValue() * delta,
				this.z * f + vec.z().floatValue() * delta,
				this.w * f + vec.w().floatValue() * delta
			);
	}

	@Override
	public Float distSqr(IVector4<? extends Number> vec) {
		Float d1 = this.getX().floatValue() - vec.getX().floatValue();
		Float d2 = this.getY().floatValue() - vec.getY().floatValue();
		Float d3 = this.getZ().floatValue() - vec.getZ().floatValue();
		Float d4 = this.getW().floatValue() - vec.getW().floatValue();
		return d1 * d1 + d2 * d2 + d3 * d3 + d4 * d4;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vec4f) {
			return	((Vec4f) obj).x == x && 
					((Vec4f) obj).y == y &&
					((Vec4f) obj).z == z &&
					((Vec4f) obj).w == w;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		result = result * 31 + Float.hashCode(this.x);
		result = result * 31 + Float.hashCode(this.y);
		result = result * 31 + Float.hashCode(this.z);
		result = result * 31 + Float.hashCode(this.w);
		return result;
	}
	
	@Override
	public String toString() {
		return "Vec4f[" + this.x + "," + this.y + "," + this.z + "," + this.w + "]";
	}

	@Override
	public Class<? extends Number> getTypeClass() {
		return Float.class;
	}
	
}
