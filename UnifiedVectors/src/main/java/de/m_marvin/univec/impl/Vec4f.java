package de.m_marvin.univec.impl;

import de.m_marvin.univec.VectorParser;
import de.m_marvin.univec.api.IVector2;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.api.IVector4;
import de.m_marvin.univec.api.IVector4Math;

public class Vec4f implements IVector4Math<Float, Vec4f> {
	
	public float x;
	public float y;
	public float z;
	public float w;

	public Vec4f(IVector2<? extends Number> vec2, float z, float w) {
		this.x = vec2.x().floatValue();
		this.y = vec2.y().floatValue();
		this.z = z;
		this.w = w;
	}

	public Vec4f(IVector3<? extends Number> vec3, float w) {
		this.x = vec3.x().floatValue();
		this.y = vec3.y().floatValue();
		this.z = vec3.z().floatValue();
		this.w = w;
	}
	
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

	@Override
	public Class<? extends Number> getTypeClass() {
		return Float.class;
	}
	
	public static Vec4f fromVec(Object vectorObject) {
		return new Vec4f(0, 0, 0, 0).readFrom(vectorObject);
	}
	
	@Override
	public <T> Vec4f readFrom(T vectorObject) {
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
	public Vec4f reset() {
		this.x = this.y = this.z = this.w = 0;
		return this;
	}
	
	@Override
	public Vec4f copy() {
		return new Vec4f(this.x, this.y, this.z, this.w);
	}

	@Override
	public Vec4f addI(IVector4<? extends Number> vec) {
		this.x += vec.x().doubleValue();
		this.y += vec.y().doubleValue();
		this.z += vec.z().doubleValue();
		this.w += vec.w().doubleValue();
		return this;
	}

	@Override
	public Vec4f addI(Float x, Float y, Float z, Float w) {
		this.x += x;
		this.y += y;
		this.z += z;
		this.w += w;
		return this;
	}

	@Override
	public Vec4f subI(IVector4<? extends Number> vec) {
		this.x -= vec.x().doubleValue();
		this.y -= vec.y().doubleValue();
		this.z -= vec.z().doubleValue();
		this.w -= vec.w().doubleValue();
		return this;
	}

	@Override
	public Vec4f subI(Float x, Float y, Float z, Float w) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		this.w -= w;
		return this;
	}

	@Override
	public Vec4f mulI(IVector4<? extends Number> vec) {
		this.x *= vec.x().doubleValue();
		this.y *= vec.y().doubleValue();
		this.z *= vec.z().doubleValue();
		this.w *= vec.w().doubleValue();
		return this;
	}

	@Override
	public Vec4f mulI(Float x, Float y, Float z, Float w) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		this.w *= w;
		return this;
	}

	@Override
	public Vec4f mulI(Float n) {
		this.x *= n;
		this.y *= n;
		this.z *= n;
		this.w *= n;
		return this;
	}
	
	@Override
	public Vec4f divI(IVector4<? extends Number> vec) {
		this.x /= vec.x().doubleValue();
		this.y /= vec.y().doubleValue();
		this.z /= vec.z().doubleValue();
		this.w /= vec.w().doubleValue();
		return this;
	}

	@Override
	public Vec4f divI(Float x, Float y, Float z, Float w) {
		this.x /= x;
		this.y /= y;
		this.z /= z;
		this.w /= w;
		return this;
	}
	
	@Override
	public Vec4f divI(Float n) {
		this.x /= n;
		this.y /= n;
		this.z /= n;
		this.w /= n;
		return this;
	}

	@Override
	public Vec4f moduleI(Float m) {
		this.x %= m;
		this.y %= m;
		this.z %= m;
		this.w %= m;
		return this;
	}

	@Override
	public Vec4f clampI(IVector4<? extends Number> min, IVector4<? extends Number> max) {
		this.x = (float) Math.max(min.x().doubleValue(), Math.min(this.x, max.x().doubleValue()));
		this.y = (float) Math.max(min.y().doubleValue(), Math.min(this.y, max.y().doubleValue()));
		this.z = (float) Math.max(min.z().doubleValue(), Math.min(this.z, max.z().doubleValue()));
		this.w = (float) Math.max(min.w().doubleValue(), Math.min(this.w, max.w().doubleValue()));
		return this;
	}

	@Override
	public Vec4f minI(Float value) {
		this.x = Math.min(this.x, value);
		this.y = Math.min(this.y, value);
		this.z = Math.min(this.z, value);
		this.w = Math.min(this.w, value);
		return this;
	}
	
	@Override
	public Vec4f minI(IVector4<? extends Number> vec) {
		this.x = (float) Math.min(this.x,  vec.x().doubleValue());
		this.y = (float) Math.min(this.y,  vec.y().doubleValue());
		this.z = (float) Math.min(this.z,  vec.z().doubleValue());
		this.w = (float) Math.min(this.w,  vec.w().doubleValue());
		return this;
	}
	
	@Override
	public Vec4f maxI(Float value) {
		this.x = Math.max(this.x, value);
		this.y = Math.max(this.y, value);
		this.z = Math.max(this.z, value);
		this.w = Math.max(this.w, value);
		return this;
	}
	
	@Override
	public Vec4f maxI(IVector4<? extends Number> vec) {
		this.x = (float) Math.max(this.x,  vec.x().doubleValue());
		this.y = (float) Math.max(this.y,  vec.y().doubleValue());
		this.z = (float) Math.max(this.z,  vec.z().doubleValue());
		this.w = (float) Math.max(this.w,  vec.w().doubleValue());
		return this;
	}
	
	@Override
	public Vec4f clampI(Float min, Float max) {
		this.x = Math.max((Float) min, Math.min(this.x, (Float) max));
		this.y = Math.max((Float) min, Math.min(this.y, (Float) max));
		this.z = Math.max((Float) min, Math.min(this.z, (Float) max));
		this.w = Math.max((Float) min, Math.min(this.w, (Float) max));
		return this;
	}

	@Override
	public Vec4f absI() {
		this.x = Math.abs(this.x);
		this.y = Math.abs(this.y);
		this.z = Math.abs(this.z);
		this.w = Math.abs(this.w);
		return this;
	}

	@Override
	public Vec4f negateI() {
		this.x = -this.x;
		this.y = -this.y;
		this.z = -this.z;
		this.w = -this.w;
		return this;
	}
	
	@Override
	public Float sum() {
		return this.x + this.y + this.z + this.w;
	}
	
	@Override
	public Vec4f signI() {
		this.x = this.x > 0 ? 1 : this.x < 0 ? -1 : 0;
		this.y = this.y > 0 ? 1 : this.y < 0 ? -1 : 0;
		this.z = this.z > 0 ? 1 : this.z < 0 ? -1 : 0;
		this.w = this.w > 0 ? 1 : this.w < 0 ? -1 : 0;
		return this;
	}

	@Override
	public Vec4f floorI() {
		this.x = (float) Math.floor(this.x);
		this.y = (float) Math.floor(this.y);
		this.z = (float) Math.floor(this.z);
		this.w = (float) Math.floor(this.w);
		return this;
	}

	@Override
	public Vec4f ceilI() {
		this.x = (float) Math.ceil(this.x);
		this.y = (float) Math.ceil(this.y);
		this.z = (float) Math.ceil(this.z);
		this.w = (float) Math.ceil(this.w);
		return this;
	}

	@Override
	public Vec4f roundI() {
		this.x = Math.round(this.x);
		this.y = Math.round(this.y);
		this.z = Math.round(this.z);
		this.w = Math.round(this.w);
		return this;
	}
	
	@Override
	public boolean isFinite() {
		return Float.isFinite(x) && Float.isFinite(y) && Float.isFinite(z) && Float.isFinite(w);
	}

	@Override
	public Vec4f crossI(IVector3<? extends Number> vec) {
		float x = this.y * vec.z().floatValue() - this.z * vec.y().floatValue();
		float y = this.z * vec.x().floatValue() - this.x * vec.z().floatValue();
		this.z = this.x * vec.y().floatValue() - this.y * vec.x().floatValue();
		this.y = y;
		this.x = x;
		this.w = 0;
		return this;
	}
	
	@Override
	public Float dot(IVector4<? extends Number> vec) {
		return this.x * vec.x().floatValue() + this.y * vec.y().floatValue() + this.z * vec.z().floatValue() + this.w * vec.w().floatValue();
	}
	
	@Override
	public Float length() {
		return (float) Math.sqrt(this.lengthSqr());
	}

	@Override
	public Float lengthSqr() {
		return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
	}

	@Override
	public Vec4f normalizeI() {
		float f = this.length();
		if (f == 0) throw new ArithmeticException("division trough zero, cant normalize vector of length 0");
		return this.divI(f);
	}

	@Override
	public Vec4f tryNormalizeI() {
		float f = this.length();
		if (f == 0) return this.setI(0F, 0F, 0F, 0F);
		return this.divI(f);
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
		return String.format("[%f  %f  %f  %f]", this.x, this.y, this.z, this.w);
	}
	
}
