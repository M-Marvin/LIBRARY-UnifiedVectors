package de.m_marvin.univec.impl;

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

	public Vec4d(IVector4<? extends Number> vec) {
		this.x = (double) vec.x();
		this.y = (double) vec.y();
		this.z = (double) vec.z();
		this.w = (double) vec.w();
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
		this.x = (double) vec.x();
		this.y = (double) vec.y();
		this.z = (double) vec.z();
		this.w = (double) vec.w();
		return this;
	}

	@Override
	public Vec4d copy() {
		return new Vec4d(this.x, this.y, this.z, this.w);
	}

	@Override
	public Vec4d add(IVector4<? extends Number> vec) {
		return new Vec4d(this.x + (double) vec.x(), this.y + (double) vec.y(), this.z + (double) vec.z(), this.w + (double) vec.w());
	}

	@Override
	public Vec4d add(Double x, Double y, Double z, Double w) {
		return new Vec4d(this.x + x, this.y + y, this.z + z, this.w + w);
	}

	@Override
	public Vec4d sub(IVector4<? extends Number> vec) {
		return new Vec4d(this.x - (double) vec.x(), this.y - (double) vec.y(), this.z - (double) vec.z(), this.w - (double) vec.w());
	}

	@Override
	public Vec4d sub(Double x, Double y, Double z, Double w) {
		return new Vec4d(this.x - x, this.y - y, this.z - z, this.w - w);
	}

	@Override
	public Vec4d mul(IVector4<? extends Number> vec) {
		return new Vec4d(this.x * (double) vec.x(), this.y * (double) vec.y(), this.z * (double) vec.z(), this.w * (double) vec.w());
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
		return new Vec4d(this.x / (double) vec.x(), this.y / (double) vec.y(), this.z / (double) vec.z(), this.w / (double) vec.w());
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
				Math.max((double) min.x(), Math.min(this.x, (double) max.x())),
				Math.max((double) min.y(), Math.min(this.y, (double) max.y())),
				Math.max((double) min.z(), Math.min(this.z, (double) max.z())),
				Math.max((double) min.w(), Math.min(this.w, (double) max.w()))
			);
	}
	
	@Override
	public Vec4d clamp(Double min, Double max) {
		return new Vec4d(
				Math.max((double) min, Math.min(this.x, (double) max)),
				Math.max((double) min, Math.min(this.y, (double) max)),
				Math.max((double) min, Math.min(this.z, (double) max)),
				Math.max((double) min, Math.min(this.w, (double) max))
			);
	}

	@Override
	public Double dot(IVector4<? extends Number> vec) {
		return this.x * (double) vec.x() + this.y * (double) vec.y() + this.z * (double) vec.z() + this.w * (double) vec.w();
	}
	
	@Override
	public Double length() {
		return (double) Math.sqrt(this.lengthSqrt());
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
				this.x * f + (double) vec.x() * delta,
				this.y * f + (double) vec.y() * delta,
				this.z * f + (double) vec.z() * delta,
				this.w * f + (double) vec.w() * delta
			);
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

}
