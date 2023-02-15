package de.m_marvin.univec.impl;

import de.m_marvin.univec.VectorParser;
import de.m_marvin.univec.api.IVector4;
import de.m_marvin.univec.api.IVector4Math;

public class Vec4i implements IVector4Math<Integer, Vec4i, IVector4<? extends Number>> {
	
	public int x;
	public int y;
	public int z;
	public int w;
	
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

	public static Vec4i fromVec(Object vectorObject) {
		return new Vec4i(0, 0, 0, 0).readFrom(vectorObject);
	}
	
	@Override
	public <T> Vec4i readFrom(T vectorObject) {
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
	public Vec4i copy() {
		return new Vec4i(this.x, this.y, this.z, this.w);
	}

	@Override
	public Vec4i add(IVector4<? extends Number> vec) {
		return new Vec4i(this.x + vec.x().intValue(), this.y + vec.y().intValue(), this.z + vec.z().intValue(), this.w + vec.w().intValue());
	}

	@Override
	public Vec4i add(Integer x, Integer y, Integer z, Integer w) {
		return new Vec4i(this.x + x, this.y + y, this.z + z, this.w + w);
	}

	@Override
	public Vec4i sub(IVector4<? extends Number> vec) {
		return new Vec4i(this.x - vec.x().intValue(), this.y - vec.y().intValue(), this.z - vec.z().intValue(), this.w - vec.w().intValue());
	}

	@Override
	public Vec4i sub(Integer x, Integer y, Integer z, Integer w) {
		return new Vec4i(this.x - x, this.y - y, this.z - z, this.w - w);
	}

	@Override
	public Vec4i mul(IVector4<? extends Number> vec) {
		return new Vec4i(this.x * vec.x().intValue(), this.y * vec.y().intValue(), this.z * vec.z().intValue(), this.w * vec.w().intValue());
	}

	@Override
	public Vec4i mul(Integer x, Integer y, Integer z, Integer w) {
		return new Vec4i(this.x * x, this.y * y, this.z * z, this.w * w);
	}

	@Override
	public Vec4i mul(Integer n) {
		return new Vec4i(this.x * n, this.y * n, this.z * n, this.w * n);
	}
	
	@Override
	public Vec4i div(IVector4<? extends Number> vec) {
		return new Vec4i(this.x / vec.x().intValue(), this.y / vec.y().intValue(), this.z / vec.z().intValue(), this.w / vec.w().intValue());
	}

	@Override
	public Vec4i div(Integer x, Integer y, Integer z, Integer w) {
		return new Vec4i(this.x / x, this.y / y, this.z / z, this.w / w);
	}
	
	@Override
	public Vec4i div(Integer n) {
		return new Vec4i(this.x / n, this.y / n, this.z / n, this.w / n);
	}

	@Override
	public Vec4i module(Integer m) {
		return new Vec4i(this.x % m, this.y % m, this.z % m, this.w % m);
	}

	@Override
	public Vec4i clamp(IVector4<? extends Number> min, IVector4<? extends Number> max) {
		return new Vec4i(
				Math.max(min.x().intValue(), Math.min(this.x, max.x().intValue())),
				Math.max(min.y().intValue(), Math.min(this.y, max.y().intValue())),
				Math.max(min.z().intValue(), Math.min(this.z, max.z().intValue())),
				Math.max(min.w().intValue(), Math.min(this.w, max.w().intValue()))
			);
	}
	
	@Override
	public Vec4i clamp(Integer min, Integer max) {
		return new Vec4i(
				Math.max((Integer) min, Math.min(this.x, (Integer) max)),
				Math.max((Integer) min, Math.min(this.y, (Integer) max)),
				Math.max((Integer) min, Math.min(this.z, (Integer) max)),
				Math.max((Integer) min, Math.min(this.w, (Integer) max))
			);
	}
	
	@Override
	public boolean isFinite() {
		return true;
	}
	
	@Override
	public Integer dot(IVector4<? extends Number> vec) {
		return this.x * vec.x().intValue() + this.y * vec.y().intValue() + this.z * vec.z().intValue() + this.w * vec.w().intValue();
	}
	
	@Override
	public Integer length() {
		return (int) Math.sqrt(this.lengthSqrt());
	}

	@Override
	public Integer lengthSqrt() {
		return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
	}
	
	@Override
	public Vec4i normalize() {
		int f = this.length();
		return this.div(f);
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
		return "Vec4f[" + this.x + "," + this.y + "," + this.z + "," + this.w + "]";
	}

	@Override
	public Class<? extends Number> getTypeClass() {
		return Integer.class;
	}
	
}
