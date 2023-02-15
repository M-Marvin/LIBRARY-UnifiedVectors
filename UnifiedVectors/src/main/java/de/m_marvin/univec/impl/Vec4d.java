package de.m_marvin.univec.impl;

import de.m_marvin.univec.VectorParser;
import de.m_marvin.univec.api.IVector4;
import de.m_marvin.univec.api.IVector4Math;

public class Vec4d implements IVector4Math<Double, Vec4d, IVector4<? extends Number>> {
	
	public double x;
	public double y;
	public double z;
	public double w;
	
	public Vec4d(double x, double y, double z, double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public Vec4d() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.w = 0;
	}

	public Vec4d(IVector4<? extends Number> vec) {
		this.x = vec.x().doubleValue();
		this.y = vec.y().doubleValue();
		this.z = vec.z().doubleValue();
		this.w = vec.w().doubleValue();
	}

	public static Vec4d fromVec(Object vectorObject) {
		return new Vec4d(0, 0, 0, 0).readFrom(vectorObject);
	}
	
	@Override
	public <T> Vec4d readFrom(T vectorObject) {
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
	public Double x() {
		return x;
	}

	@Override
	public Double y() {
		return y;
	}

	@Override
	public Double z() {
		return z;
	}

	@Override
	public Double w() {
		return w;
	}

	@Override
	public void setX(Double x) {
		this.x = x;
	}

	@Override
	public void setY(Double y) {
		this.y = y;
	}

	@Override
	public void setZ(Double z) {
		this.z = z;
	}

	@Override
	public void setW(Double w) {
		this.w = w;
	}

	@Override
	public Vec4d setI(Double x, Double y, Double z, Double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		return this;
	}

	@Override
	public Vec4d setI(IVector4<? extends Number> vec) {
		this.x = vec.x().doubleValue();
		this.y = vec.y().doubleValue();
		this.z = vec.z().doubleValue();
		this.w = vec.w().doubleValue();
		return this;
	}

	@Override
	public Vec4d copy() {
		return new Vec4d(this.x, this.y, this.z, this.w);
	}

	@Override
	public Vec4d add(IVector4<? extends Number> vec) {
		return new Vec4d(this.x + vec.x().doubleValue(), this.y + vec.y().doubleValue(), this.z + vec.z().doubleValue(), this.w + vec.w().doubleValue());
	}

	@Override
	public Vec4d add(Double x, Double y, Double z, Double w) {
		return new Vec4d(this.x + x, this.y + y, this.z + z, this.w + w);
	}

	@Override
	public Vec4d sub(IVector4<? extends Number> vec) {
		return new Vec4d(this.x - vec.x().doubleValue(), this.y - vec.y().doubleValue(), this.z - vec.z().doubleValue(), this.w - vec.w().doubleValue());
	}

	@Override
	public Vec4d sub(Double x, Double y, Double z, Double w) {
		return new Vec4d(this.x - x, this.y - y, this.z - z, this.w - w);
	}

	@Override
	public Vec4d mul(IVector4<? extends Number> vec) {
		return new Vec4d(this.x * vec.x().doubleValue(), this.y * vec.y().doubleValue(), this.z * vec.z().doubleValue(), this.w * vec.w().doubleValue());
	}

	@Override
	public Vec4d mul(Double x, Double y, Double z, Double w) {
		return new Vec4d(this.x * x, this.y * y, this.z * z, this.w * w);
	}

	@Override
	public Vec4d mul(Double n) {
		return new Vec4d(this.x * n, this.y * n, this.z * n, this.w * n);
	}
	
	@Override
	public Vec4d div(IVector4<? extends Number> vec) {
		return new Vec4d(this.x / vec.x().doubleValue(), this.y / vec.y().doubleValue(), this.z / vec.z().doubleValue(), this.w / vec.w().doubleValue());
	}

	@Override
	public Vec4d div(Double x, Double y, Double z, Double w) {
		return new Vec4d(this.x / x, this.y / y, this.z / z, this.w / w);
	}
	
	@Override
	public Vec4d div(Double n) {
		return new Vec4d(this.x / n, this.y / n, this.z / n, this.w / n);
	}

	@Override
	public Vec4d module(Double m) {
		return new Vec4d(this.x % m, this.y % m, this.z % m, this.w % m);
	}

	@Override
	public Vec4d clamp(IVector4<? extends Number> min, IVector4<? extends Number> max) {
		return new Vec4d(
				Math.max(min.x().doubleValue(), Math.min(this.x, max.x().doubleValue())),
				Math.max(min.y().doubleValue(), Math.min(this.y, max.y().doubleValue())),
				Math.max(min.z().doubleValue(), Math.min(this.z, max.z().doubleValue())),
				Math.max(min.w().doubleValue(), Math.min(this.w, max.w().doubleValue()))
			);
	}
	
	@Override
	public Vec4d clamp(Double min, Double max) {
		return new Vec4d(
				Math.max((Double) min, Math.min(this.x, (Double) max)),
				Math.max((Double) min, Math.min(this.y, (Double) max)),
				Math.max((Double) min, Math.min(this.z, (Double) max)),
				Math.max((Double) min, Math.min(this.w, (Double) max))
			);
	}

	@Override
	public boolean isFinite() {
		return Double.isFinite(x) && Double.isFinite(y) && Double.isFinite(z) && Double.isFinite(w);
	}
	
	@Override
	public Double dot(IVector4<? extends Number> vec) {
		return this.x * vec.x().doubleValue() + this.y * vec.y().doubleValue() + this.z * vec.z().doubleValue() + this.w * vec.w().doubleValue();
	}
	
	@Override
	public Double length() {
		return (Double) Math.sqrt(this.lengthSqrt());
	}

	@Override
	public Double lengthSqrt() {
		return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
	}
	
	@Override
	public Vec4d normalize() {
		double f = this.length();
		return this.div(f);
	}
	
	@Override
	public Vec4d lerp(IVector4<? extends Number> vec, Double delta) {
		double f = 1.0F - delta;
		return new Vec4d(
				this.x * f + vec.x().doubleValue() * delta,
				this.y * f + vec.y().doubleValue() * delta,
				this.z * f + vec.z().doubleValue() * delta,
				this.w * f + vec.w().doubleValue() * delta
			);
	}

	@Override
	public Double distSqr(IVector4<? extends Number> vec) {
		Double d1 = this.getX().doubleValue() - vec.getX().doubleValue();
		Double d2 = this.getY().doubleValue() - vec.getY().doubleValue();
		Double d3 = this.getZ().doubleValue() - vec.getZ().doubleValue();
		Double d4 = this.getW().doubleValue() - vec.getW().doubleValue();
		return d1 * d1 + d2 * d2 + d3 * d3 + d4 * d4;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vec4d) {
			return	((Vec4d) obj).x == x && 
					((Vec4d) obj).y == y &&
					((Vec4d) obj).z == z &&
					((Vec4d) obj).w == w;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		result = result * 31 + Double.hashCode(this.x);
		result = result * 31 + Double.hashCode(this.y);
		result = result * 31 + Double.hashCode(this.z);
		result = result * 31 + Double.hashCode(this.w);
		return result;
	}
	
	@Override
	public String toString() {
		return "Vec4f[" + this.x + "," + this.y + "," + this.z + "," + this.w + "]";
	}

	@Override
	public Class<? extends Number> getTypeClass() {
		return Double.class;
	}
	
}
