package de.m_marvin.univec.impl;

import de.m_marvin.unimat.api.IQuaternion;
import de.m_marvin.unimat.impl.Quaternion;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.api.IVector3Math;

/*
 * Implementation of a 3 dimensional float vector
 */
public class Vec3f implements IVector3Math<Float, Vec3f, IVector3<? extends Number>, Quaternion> {

	public float x;
	public float y;
	public float z;
	
	public Vec3f(Float x, Float y, Float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vec3f(IVector3<? extends Number> vec) {
		this.x = (float) vec.x();
		this.y = (float) vec.y();
		this.z = (float) vec.z();
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
	public Vec3f setI(Float x, Float y, Float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	@Override
	public Vec3f setI(IVector3<? extends Number> vec) {
		this.x = (float) vec.x();
		this.y = (float) vec.y();
		this.z = (float) vec.z();
		return this;
	}

	@Override
	public Vec3f copy() {
		return new Vec3f(this.x, this.y, this.z);
	}

	@Override
	public Vec3f add(IVector3<? extends Number> vec) {
		return new Vec3f(this.x + (float) vec.x(), this.y + (float) vec.y(), this.z + (float) vec.z());
	}

	@Override
	public Vec3f add(Float x, Float y, Float z) {
		return new Vec3f(this.x + x, this.y + y, this.z + z);
	}

	@Override
	public Vec3f sub(IVector3<? extends Number> vec) {
		return new Vec3f(this.x - (float) vec.x(), this.y - (float) vec.y(), this.z - (float) vec.z());
	}

	@Override
	public Vec3f sub(Float x, Float y, Float z) {
		return new Vec3f(this.x - x, this.y - y, this.z - z);
	}

	@Override
	public Vec3f mul(IVector3<? extends Number> vec) {
		return new Vec3f(this.x * (float) vec.x(), this.y * (float) vec.y(), this.z * (float) vec.z());
	}

	@Override
	public Vec3f mul(Float x, Float y, Float z) {
		return new Vec3f(this.x * x, this.y * y, this.z * z);
	}

	@Override
	public Vec3f mul(Float n) {
		return new Vec3f(this.x * n, this.y * n, this.z * n);
	}
	
	@Override
	public Vec3f div(IVector3<? extends Number> vec) {
		return new Vec3f(this.x / (float) vec.x(), this.y / (float) vec.y(), this.z / (float) vec.z());
	}

	@Override
	public Vec3f div(Float x, Float y, Float z) {
		return new Vec3f(this.x / x, this.y / y, this.z / z);
	}
	
	@Override
	public Vec3f div(Float n) {
		return new Vec3f(this.x / n, this.y / n, this.z / n);
	}

	@Override
	public Vec3f module(Float m) {
		return new Vec3f(this.x % m, this.y % m, this.z % m);
	}

	@Override
	public Vec3f clamp(IVector3<? extends Number> min, IVector3<? extends Number> max) {
		return new Vec3f(
				Math.max((float) min.x(), Math.min(this.x, (float) max.x())),
				Math.max((float) min.y(), Math.min(this.y, (float) max.y())),
				Math.max((float) min.z(), Math.min(this.z, (float) max.z()))
			);
	}
	
	@Override
	public Vec3f clamp(Float min, Float max) {
		return new Vec3f(
				Math.max((float) min, Math.min(this.x, (float) max)),
				Math.max((float) min, Math.min(this.y, (float) max)),
				Math.max((float) min, Math.min(this.z, (float) max))
			);
	}

	@Override
	public double angle(IVector3<? extends Number> vec) {
		double f1 = this.dot(vec);
		double f2 = this.length() * (float) vec.length();
		return Math.acos(f1 / f2);
	}
	
	@Override
	public Vec3f cross(IVector3<? extends Number> vec) {
		return new Vec3f(
				this.y * (float) vec.z() - this.z * (float) vec.y(),
				this.z * (float) vec.x() - this.x * (float) vec.z(),
				this.x * (float) vec.y() - this.y * (float) vec.x()
			);
	}
	
	@Override
	public Float dot(IVector3<? extends Number> vec) {
		return this.x * (float) vec.x() + this.y * (float) vec.y() + this.z * (float) vec.z();
	}
	
	@Override
	public Float length() {
		return (float) Math.sqrt(this.lengthSqrt());
	}

	@Override
	public Float lengthSqrt() {
		return this.x * this.x + this.y * this.y + this.z * this.z;
	}
	
	@Override
	public Vec3f normalize() {
		float f = this.length();
		return this.div(f);
	}
	
	@Override
	public Vec3f lerp(IVector3<? extends Number> vec, Float delta) {
		float f = 1.0F - delta;
		return new Vec3f(
				this.x * f + (float) vec.x() * delta,
				this.y * f + (float) vec.y() * delta,
				this.z * f + (float) vec.z() * delta
			);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vec3f) {
			return	((Vec3f) obj).x == x && 
					((Vec3f) obj).y == y &&
					((Vec3f) obj).z == z;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		result = result * 31 + Float.hashCode(this.x);
		result = result * 31 + Float.hashCode(this.y);
		result = result * 32 + Float.hashCode(this.z);
		return result;
	}
	
	@Override
	public String toString() {
		return "Vec3f[" + this.x + "," + this.y + "," + this.z + "]";
	}

	@Override
	public IQuaternion<Quaternion> rotationRadians(Float angle) {
		// TODO Verify
		return new Quaternion(new Vec3i(this), angle);
	}

	@Override
	public Vec3f transform(Quaternion quaternion) {
		Quaternion q = quaternion.mul(new Quaternion(x, y, z, 0.0F)).mul(quaternion.conj());
		return new Vec3f(q.i(), q.j(), q.k());
	}
	
}
