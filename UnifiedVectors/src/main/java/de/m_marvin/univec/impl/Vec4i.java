package de.m_marvin.univec.impl;

import de.m_marvin.univec.VectorParser;
import de.m_marvin.univec.api.IVector2;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.api.IVector4;
import de.m_marvin.univec.api.IVector4Math;

public class Vec4i implements IVector4Math<Integer, Vec4i> {
	
	public int x;
	public int y;
	public int z;
	public int w;

	public Vec4i(IVector2<? extends Number> vec2, int z, int w) {
		this.x = vec2.x().intValue();
		this.y = vec2.y().intValue();
		this.z = z;
		this.w = w;
	}

	public Vec4i(IVector3<? extends Number> vec3, int w) {
		this.x = vec3.x().intValue();
		this.y = vec3.y().intValue();
		this.z = vec3.z().intValue();
		this.w = w;
	}
	
	public Vec4i(int x, int y, int z, int w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public Vec4i() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.w = 0;
	}

	public Vec4i(IVector4<? extends Number> vec) {
		this.x = vec.x().intValue();
		this.y = vec.y().intValue();
		this.z = vec.z().intValue();
		this.w = vec.w().intValue();
	}

	@Override
	public Class<? extends Number> getTypeClass() {
		return Integer.class;
	}
	
	public static Vec4i fromVec(Object vectorObject) {
		return new Vec4i(0, 0, 0, 0).readFrom(vectorObject);
	}
	
	@Override
	public <T> Vec4i readFrom(T vectorObject) {
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
	public Integer z() {
		return z;
	}

	@Override
	public Integer w() {
		return w;
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
	public void setZ(Integer z) {
		this.z = z;
	}

	@Override
	public void setW(Integer w) {
		this.w = w;
	}

	@Override
	public Vec4i setI(Integer x, Integer y, Integer z, Integer w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		return this;
	}

	@Override
	public Vec4i setI(IVector4<? extends Number> vec) {
		this.x = vec.x().intValue();
		this.y = vec.y().intValue();
		this.z = vec.z().intValue();
		this.w = vec.w().intValue();
		return this;
	}

	@Override
	public Vec4i reset() {
		this.x = this.y = this.z = this.w = 0;
		return this;
	}
	
	@Override
	public Vec4i copy() {
		return new Vec4i(this.x, this.y, this.z, this.w);
	}

	@Override
	public Vec4i addI(IVector4<? extends Number> vec) {
		this.x += vec.x().doubleValue();
		this.y += vec.y().doubleValue();
		this.z += vec.z().doubleValue();
		this.w += vec.w().doubleValue();
		return this;
	}

	@Override
	public Vec4i addI(Integer x, Integer y, Integer z, Integer w) {
		this.x += x;
		this.y += y;
		this.z += z;
		this.w += w;
		return this;
	}

	@Override
	public Vec4i subI(IVector4<? extends Number> vec) {
		this.x -= vec.x().doubleValue();
		this.y -= vec.y().doubleValue();
		this.z -= vec.z().doubleValue();
		this.w -= vec.w().doubleValue();
		return this;
	}

	@Override
	public Vec4i subI(Integer x, Integer y, Integer z, Integer w) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		this.w -= w;
		return this;
	}

	@Override
	public Vec4i mulI(IVector4<? extends Number> vec) {
		this.x *= vec.x().doubleValue();
		this.y *= vec.y().doubleValue();
		this.z *= vec.z().doubleValue();
		this.w *= vec.w().doubleValue();
		return this;
	}

	@Override
	public Vec4i mulI(Integer x, Integer y, Integer z, Integer w) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		this.w *= w;
		return this;
	}

	@Override
	public Vec4i mulI(Integer n) {
		this.x *= n;
		this.y *= n;
		this.z *= n;
		this.w *= n;
		return this;
	}
	
	@Override
	public Vec4i divI(IVector4<? extends Number> vec) {
		this.x /= vec.x().doubleValue();
		this.y /= vec.y().doubleValue();
		this.z /= vec.z().doubleValue();
		this.w /= vec.w().doubleValue();
		return this;
	}

	@Override
	public Vec4i divI(Integer x, Integer y, Integer z, Integer w) {
		this.x /= x;
		this.y /= y;
		this.z /= z;
		this.w /= w;
		return this;
	}
	
	@Override
	public Vec4i divI(Integer n) {
		this.x /= n;
		this.y /= n;
		this.z /= n;
		this.w /= n;
		return this;
	}

	@Override
	public Vec4i moduleI(Integer m) {
		this.x %= m;
		this.y %= m;
		this.z %= m;
		this.w %= m;
		return this;
	}

	@Override
	public Vec4i clampI(IVector4<? extends Number> min, IVector4<? extends Number> max) {
		this.x = (int) Math.max(min.x().doubleValue(), Math.min(this.x, max.x().doubleValue()));
		this.y = (int) Math.max(min.y().doubleValue(), Math.min(this.y, max.y().doubleValue()));
		this.z = (int) Math.max(min.z().doubleValue(), Math.min(this.z, max.z().doubleValue()));
		this.w = (int) Math.max(min.w().doubleValue(), Math.min(this.w, max.w().doubleValue()));
		return this;
	}

	@Override
	public Vec4i minI(Integer value) {
		this.x = Math.min(this.x, value);
		this.y = Math.min(this.y, value);
		this.z = Math.min(this.z, value);
		this.w = Math.min(this.w, value);
		return this;
	}
	
	@Override
	public Vec4i minI(IVector4<? extends Number> vec) {
		this.x = (int) Math.min(this.x,  vec.x().doubleValue());
		this.y = (int) Math.min(this.y,  vec.y().doubleValue());
		this.z = (int) Math.min(this.z,  vec.z().doubleValue());
		this.w = (int) Math.min(this.w,  vec.w().doubleValue());
		return this;
	}
	
	@Override
	public Vec4i maxI(Integer value) {
		this.x = Math.max(this.x, value);
		this.y = Math.max(this.y, value);
		this.z = Math.max(this.z, value);
		this.w = Math.max(this.w, value);
		return this;
	}
	
	@Override
	public Vec4i maxI(IVector4<? extends Number> vec) {
		this.x = (int) Math.max(this.x,  vec.x().doubleValue());
		this.y = (int) Math.max(this.y,  vec.y().doubleValue());
		this.z = (int) Math.max(this.z,  vec.z().doubleValue());
		this.w = (int) Math.max(this.w,  vec.w().doubleValue());
		return this;
	}
	
	@Override
	public Vec4i clampI(Integer min, Integer max) {
		this.x = Math.max((Integer) min, Math.min(this.x, (Integer) max));
		this.y = Math.max((Integer) min, Math.min(this.y, (Integer) max));
		this.z = Math.max((Integer) min, Math.min(this.z, (Integer) max));
		this.w = Math.max((Integer) min, Math.min(this.w, (Integer) max));
		return this;
	}

	@Override
	public Vec4i absI() {
		this.x = Math.abs(this.x);
		this.y = Math.abs(this.y);
		this.z = Math.abs(this.z);
		this.w = Math.abs(this.w);
		return this;
	}

	@Override
	public Vec4i negateI() {
		this.x = -this.x;
		this.y = -this.y;
		this.z = -this.z;
		this.w = -this.w;
		return this;
	}
	
	@Override
	public Integer sum() {
		return this.x + this.y + this.z + this.w;
	}
	
	@Override
	public Vec4i signI() {
		this.x = this.x > 0 ? 1 : this.x < 0 ? -1 : 0;
		this.y = this.y > 0 ? 1 : this.y < 0 ? -1 : 0;
		this.z = this.z > 0 ? 1 : this.z < 0 ? -1 : 0;
		this.w = this.w > 0 ? 1 : this.w < 0 ? -1 : 0;
		return this;
	}

	@Override
	public Vec4i floorI() {
		this.x = (int) Math.floor(this.x);
		this.y = (int) Math.floor(this.y);
		this.z = (int) Math.floor(this.z);
		this.w = (int) Math.floor(this.w);
		return this;
	}

	@Override
	public Vec4i ceilI() {
		this.x = (int) Math.ceil(this.x);
		this.y = (int) Math.ceil(this.y);
		this.z = (int) Math.ceil(this.z);
		this.w = (int) Math.ceil(this.w);
		return this;
	}

	@Override
	public Vec4i roundI() {
		this.x = Math.round(this.x);
		this.y = Math.round(this.y);
		this.z = Math.round(this.z);
		this.w = Math.round(this.w);
		return this;
	}
	
	@Override
	public boolean isFinite() {
		return true;
	}

	@Override
	public Vec4i crossI(IVector3<? extends Number> vec) {
		int x = this.y * vec.z().intValue() - this.z * vec.y().intValue();
		int y = this.z * vec.x().intValue() - this.x * vec.z().intValue();
		this.z = this.x * vec.y().intValue() - this.y * vec.x().intValue();
		this.y = y;
		this.x = x;
		this.w = 0;
		return this;
	}
	
	@Override
	public Integer dot(IVector4<? extends Number> vec) {
		return this.x * vec.x().intValue() + this.y * vec.y().intValue() + this.z * vec.z().intValue() + this.w * vec.w().intValue();
	}
	
	@Override
	public Integer length() {
		return (int) Math.sqrt(this.lengthSqr());
	}

	@Override
	public Integer lengthSqr() {
		return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
	}

	@Override
	public Vec4i normalizeI() {
		int f = this.length();
		if (f == 0) throw new ArithmeticException("division trough zero, cant normalize vector of length 0");
		return this.divI(f);
	}

	@Override
	public Vec4i tryNormalizeI() {
		int f = this.length();
		if (f == 0) return this.setI(0, 0, 0, 0);
		return this.divI(f);
	}
	
	@Override
	public Vec4i lerp(IVector4<? extends Number> vec, Integer delta) {
		float f = 1.0F - delta;
		return new Vec4i(
				(int) (this.x * f + vec.x().intValue() * delta),
				(int) (this.y * f + vec.y().intValue() * delta),
				(int) (this.z * f + vec.z().intValue() * delta),
				(int) (this.w * f + vec.w().intValue() * delta)
			);
	}

	@Override
	public Integer distSqr(IVector4<? extends Number> vec) {
		Integer d1 = this.getX().intValue() - vec.getX().intValue();
		Integer d2 = this.getY().intValue() - vec.getY().intValue();
		Integer d3 = this.getZ().intValue() - vec.getZ().intValue();
		Integer d4 = this.getW().intValue() - vec.getW().intValue();
		return d1 * d1 + d2 * d2 + d3 * d3 + d4 * d4;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vec4i) {
			return	((Vec4i) obj).x == x && 
					((Vec4i) obj).y == y &&
					((Vec4i) obj).z == z &&
					((Vec4i) obj).w == w;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		result = result * 31 + Integer.hashCode(this.x);
		result = result * 31 + Integer.hashCode(this.y);
		result = result * 31 + Integer.hashCode(this.z);
		result = result * 31 + Integer.hashCode(this.w);
		return result;
	}
	
	@Override
	public String toString() {
		return String.format("[%d  %d  %d  %d]", this.x, this.y, this.z, this.w);
	}
	
}
