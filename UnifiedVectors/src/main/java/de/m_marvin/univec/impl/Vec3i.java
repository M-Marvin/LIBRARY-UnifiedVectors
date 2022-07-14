package de.m_marvin.univec.impl;

import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.api.IVector3Math;

/*
 * Implementation of a 3 dimensional integer vector
 */
public class Vec3i implements IVector3Math<Integer, Vec3i, IVector3<? extends Number>> {

	public int x;
	public int y;
	public int z;
	
	public Vec3i(Integer x, Integer y, Integer z) {
		this.x = x;
		this.y = y;
	}

	public Vec3i(IVector3<? extends Number> vec) {
		this.x = (int) vec.x();
		this.y = (int) vec.y();
		this.z = (int) vec.z();
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
	public Vec3i setI(Integer x, Integer y, Integer z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	@Override
	public Vec3i setI(IVector3<? extends Number> vec) {
		this.x = (int) vec.x();
		this.y = (int) vec.y();
		this.z = (int) vec.z();
		return this;
	}

	@Override
	public Vec3i copy() {
		return new Vec3i(this.x, this.y, this.z);
	}

	@Override
	public Vec3i add(IVector3<? extends Number> vec) {
		return new Vec3i(this.x + (int) vec.x(), this.y + (int) vec.y(), this.z + (int) vec.z());
	}

	@Override
	public Vec3i add(Integer x, Integer y, Integer z) {
		return new Vec3i(this.x + x, this.y + y, this.z + z);
	}

	@Override
	public Vec3i sub(IVector3<? extends Number> vec) {
		return new Vec3i(this.x - (int) vec.x(), this.y - (int) vec.y(), this.z - (int) vec.z());
	}

	@Override
	public Vec3i sub(Integer x, Integer y, Integer z) {
		return new Vec3i(this.x - x, this.y - y, this.z - z);
	}

	@Override
	public Vec3i mul(IVector3<? extends Number> vec) {
		return new Vec3i(this.x * (int) vec.x(), this.y * (int) vec.y(), this.z * (int) vec.z());
	}

	@Override
	public Vec3i mul(Integer x, Integer y, Integer z) {
		return new Vec3i(this.x * x, this.y * y, this.z * z);
	}

	@Override
	public Vec3i mul(Integer n) {
		return new Vec3i(this.x * n, this.y * n, this.z * n);
	}
	
	@Override
	public Vec3i div(IVector3<? extends Number> vec) {
		return new Vec3i(this.x / (int) vec.x(), this.y / (int) vec.y(), this.z / (int) vec.z());
	}

	@Override
	public Vec3i div(Integer x, Integer y, Integer z) {
		return new Vec3i(this.x / x, this.y / y, this.z / z);
	}
	
	@Override
	public Vec3i div(Integer n) {
		return new Vec3i(this.x / n, this.y / n, this.z / n);
	}

	@Override
	public Vec3i module(Integer m) {
		return new Vec3i(this.x % m, this.y % m, this.z % m);
	}

	@Override
	public Vec3i clamp(IVector3<? extends Number> min, IVector3<? extends Number> max) {
		return new Vec3i(
				Math.max((int) min.x(), Math.min(this.x, (int) max.x())),
				Math.max((int) min.y(), Math.min(this.y, (int) max.y())),
				Math.max((int) min.z(), Math.min(this.z, (int) max.z()))
			);
	}
	
	@Override
	public Vec3i clamp(Integer min, Integer max) {
		return new Vec3i(
				Math.max((int) min, Math.min(this.x, (int) max)),
				Math.max((int) min, Math.min(this.y, (int) max)),
				Math.max((int) min, Math.min(this.z, (int) max))
			);
	}

	@Override
	public double angle(IVector3<? extends Number> vec) {
		double f1 = this.dot(vec);
		double f2 = this.length() * (int) vec.length();
		return Math.acos(f1 / f2);
	}
	
	@Override
	public Vec3i cross(IVector3<? extends Number> vec) {
		return new Vec3i(
				this.y * (int) vec.z() - this.z * (int) vec.y(),
				this.z * (int) vec.x() - this.x * (int) vec.z(),
				this.x * (int) vec.y() - this.y * (int) vec.x()
			);
	}
	
	@Override
	public Integer dot(IVector3<? extends Number> vec) {
		return this.x * (int) vec.x() + this.y * (int) vec.y() + this.z * (int) vec.z();
	}
	
	@Override
	public Integer length() {
		return (int) Math.sqrt(this.lengthSqrt());
	}

	@Override
	public Integer lengthSqrt() {
		return this.x * this.x + this.y * this.y + this.z * this.z;
	}
	
	@Override
	public Vec3i normalize() {
		int f = this.length();
		return this.div(f);
	}
	
	@Override
	public Vec3i lerp(IVector3<? extends Number> vec, Integer delta) {
		float f = 1.0F - delta;
		return new Vec3i(
				(int) (this.x * f + (int) vec.x() * delta),
				(int) (this.y * f + (int) vec.y() * delta),
				(int) (this.z * f + (int) vec.z() * delta)
			);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vec3i) {
			return	((Vec3i) obj).x == x && 
					((Vec3i) obj).y == y &&
					((Vec3i) obj).z == z;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		result = result * 31 + Integer.hashCode(this.x);
		result = result * 31 + Integer.hashCode(this.y);
		result = result * 32 + Integer.hashCode(this.z);
		return result;
	}
	
	@Override
	public String toString() {
		return "Vec3i[" + this.x + "," + this.y + "," + this.z + "]";
	}
	
}
