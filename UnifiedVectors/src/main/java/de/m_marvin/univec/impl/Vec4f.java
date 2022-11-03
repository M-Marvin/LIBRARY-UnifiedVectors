package de.m_marvin.univec.impl;

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

	public Vec4f(IVector4<? extends Number> vec) {
		this.x = (float) vec.x();
		this.y = (float) vec.y();
		this.z = (float) vec.z();
		this.w = (float) vec.w();
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
		this.x = (float) vec.x();
		this.y = (float) vec.y();
		this.z = (float) vec.z();
		this.w = (float) vec.w();
		return this;
	}

	@Override
	public Vec4f copy() {
		return new Vec4f(this.x, this.y, this.z, this.w);
	}

	@Override
	public Vec4f add(IVector4<? extends Number> vec) {
		return new Vec4f(this.x + (float) vec.x(), this.y + (float) vec.y(), this.z + (float) vec.z(), this.w + (float) vec.w());
	}

	@Override
	public Vec4f add(Float x, Float y, Float z, Float w) {
		return new Vec4f(this.x + x, this.y + y, this.z + z, this.w + w);
	}

	@Override
	public Vec4f sub(IVector4<? extends Number> vec) {
		return new Vec4f(this.x - (float) vec.x(), this.y - (float) vec.y(), this.z - (float) vec.z(), this.w - (float) vec.w());
	}

	@Override
	public Vec4f sub(Float x, Float y, Float z, Float w) {
		return new Vec4f(this.x - x, this.y - y, this.z - z, this.w - w);
	}

	@Override
	public Vec4f mul(IVector4<? extends Number> vec) {
		return new Vec4f(this.x * (float) vec.x(), this.y * (float) vec.y(), this.z * (float) vec.z(), this.w * (float) vec.w());
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
		return new Vec4f(this.x / (float) vec.x(), this.y / (float) vec.y(), this.z / (float) vec.z(), this.w / (float) vec.w());
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
				Math.max((float) min.x(), Math.min(this.x, (float) max.x())),
				Math.max((float) min.y(), Math.min(this.y, (float) max.y())),
				Math.max((float) min.z(), Math.min(this.z, (float) max.z())),
				Math.max((float) min.w(), Math.min(this.w, (float) max.w()))
			);
	}
	
	@Override
	public Vec4f clamp(Float min, Float max) {
		return new Vec4f(
				Math.max((float) min, Math.min(this.x, (float) max)),
				Math.max((float) min, Math.min(this.y, (float) max)),
				Math.max((float) min, Math.min(this.z, (float) max)),
				Math.max((float) min, Math.min(this.w, (float) max))
			);
	}

	@Override
	public Float dot(IVector4<? extends Number> vec) {
		return this.x * (float) vec.x() + this.y * (float) vec.y() + this.z * (float) vec.z() + this.w * (float) vec.w();
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
				this.x * f + (float) vec.x() * delta,
				this.y * f + (float) vec.y() * delta,
				this.z * f + (float) vec.z() * delta,
				this.w * f + (float) vec.w() * delta
			);
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

}
